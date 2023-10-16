package my.service;

import my.external.SongMetadataSender;
import my.model.SongMetadata;
import my.model.db.SongTableObject;
import my.repository.SongRepository;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@Service
public class Mp3SaveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Mp3SaveService.class);

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongMetadataSender songMetadataSender;

    public int processMp3(InputStream inputStream) throws IOException, SQLException {
        byte[] songBinary = inputStream.readAllBytes();

        int resourceId = persistSong(songBinary);
        LOGGER.info("Saved song binary (size: {}) with resource id: {}", songBinary.length, resourceId);

        sendSongMetadata(songBinary, resourceId);

        return resourceId;
    }

    private int persistSong(byte[] songBinary) throws SQLException {

        SongTableObject song = new SongTableObject(songBinary);

        SongTableObject persistedSong = songRepository.save(song);

        return persistedSong.getId();
    }

    private void sendSongMetadata(byte[] songBinary, int resourceId) {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();

        //Mp3 parser
        Mp3Parser mp3Parser = new Mp3Parser();
        try {
            mp3Parser.parse(new ByteArrayInputStream(songBinary), handler, metadata, new ParseContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String songTitle = metadata.get("title");
        String songAlbum = metadata.get("xmpDM:album");
        String songArtist = metadata.get("xmpDM:artist");
        String songDuration = metadata.get("xmpDM:duration");

        SongMetadata songMetadata = new SongMetadata(songTitle, songAlbum, songArtist, songDuration, resourceId);

        System.out.println(songMetadata);

        songMetadataSender.sendMetadata(songMetadata);
    }
}
