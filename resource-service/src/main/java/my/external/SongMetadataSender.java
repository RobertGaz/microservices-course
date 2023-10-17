package my.external;

import my.model.IdResponse;
import my.model.SongMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SongMetadataSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongMetadataSender.class);

    @Value("${song.service.url}")
    private String songServiceUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public void sendMetadata(SongMetadata songMetadata) {
        ResponseEntity<IdResponse> responseEntity= restTemplate.postForEntity(songServiceUrl + "/songs", songMetadata, IdResponse.class);
        LOGGER.info("Sent metadata to song service, received metadata id: {}", responseEntity.getBody().getId());
    }
}
