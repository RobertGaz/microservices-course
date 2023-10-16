package my.service;

import my.exception.NotFoundException;
import my.model.SongMetadata;
import my.model.db.SongMetadataTableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import my.repository.SongMetadataRepository;

import java.util.Optional;

@Service
public class SongMetadataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SongMetadataService.class);

    @Autowired
    private SongMetadataRepository songMetadataRepository;

    public int saveSongMetadata(SongMetadata songMetadata) {

        SongMetadataTableObject songMetadataTableObject = new SongMetadataTableObject(
                songMetadata.getTitle(),
                songMetadata.getArtist(),
                songMetadata.getAlbum(),
                songMetadata.getDuration(),
                songMetadata.getResourceId()
        );

        SongMetadataTableObject savedSongMetadataTableObject = songMetadataRepository.save(songMetadataTableObject);

        int id = savedSongMetadataTableObject.getId();
        LOGGER.info("Successfully stored {}, id assigned: {}", songMetadata, id);

        return id;
    }

    public SongMetadata getSongMetadata(int id) {
        Optional<SongMetadataTableObject> foundObject = songMetadataRepository.findById(id);
        if (foundObject.isEmpty()) {
            LOGGER.error("Didn't find song metadata with id {} in DB", id);
            throw new NotFoundException();
        }

        LOGGER.info("Successfully retrieved song metadata with resource id {} from DB", id);

        SongMetadataTableObject tableObject = foundObject.get();
        return new SongMetadata(
                tableObject.getTitle(),
                tableObject.getArtist(),
                tableObject.getAlbum(),
                tableObject.getDuration(),
                tableObject.getResourceId()
        );

    }

}
