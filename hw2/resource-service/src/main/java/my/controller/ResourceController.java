package my.controller;

import my.exception.NotFoundException;
import my.model.IdResponse;
import my.service.Mp3GetService;
import my.service.Mp3SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@RestController
public class ResourceController {

    @Autowired
    private Mp3SaveService mp3SaveService;

    @Autowired
    private Mp3GetService mp3GetService;

    @PostMapping("/resources")
    public IdResponse saveMp3(InputStream data) throws SQLException, IOException {
        int resourceId = mp3SaveService.processMp3(data);

        return new IdResponse(resourceId);
    }

    @GetMapping(value = "/resources/{id}", produces = "audio/mpeg")
    public byte[] getMp3(@PathVariable int id) {
        return mp3GetService.getSongById(id);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.getMessage()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
