package my.service;

import my.exception.NotFoundException;
import my.model.db.SongTableObject;
import my.repository.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Mp3GetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Mp3GetService.class);

    @Autowired
    private SongRepository songRepository;

    public byte[] getSongById(int resourceId) {
        Optional<SongTableObject> foundObject = songRepository.findById(resourceId);
        if (foundObject.isEmpty()) {
            LOGGER.error("Didn't find song binary with resource id {} in DB", resourceId);
            throw new NotFoundException();
        }

        LOGGER.info("Successfully retrieved song binary with resource id {} from DB", resourceId);

        return foundObject.get().getSongBinary();
    }
}
