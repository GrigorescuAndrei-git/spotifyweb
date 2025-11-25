package com.example.spotifyweb.controllers;

import com.example.spotifyweb.services.SpotifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;

    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/albums")
    public ResponseEntity<?> getAlbums(@RequestParam String artist) {
        return ResponseEntity.ok(spotifyService.cautaAlbume(artist));
    }
}
