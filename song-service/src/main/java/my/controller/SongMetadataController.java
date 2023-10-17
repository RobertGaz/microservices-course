package my.controller;

import my.model.IdResponse;
import my.model.SongMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import my.service.SongMetadataService;

@RestController
@RequestMapping("/songs")
public class SongMetadataController {

    @Autowired
    private SongMetadataService songMetadataService;

    @PostMapping
    public IdResponse saveSongMetadata(@RequestBody SongMetadata songMetadata) {
        int id = songMetadataService.saveSongMetadata(songMetadata);
        return new IdResponse(id);
    }

    @GetMapping("/{id}")
    public SongMetadata getSongMetadata(@PathVariable int id) {
        return songMetadataService.getSongMetadata(id);
    }
}
