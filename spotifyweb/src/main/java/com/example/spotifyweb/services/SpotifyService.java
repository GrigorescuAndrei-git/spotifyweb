package com.example.spotifyweb.services;

import com.example.spotifyweb.AlbumDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Service
public class SpotifyService {

    @Value("${spotify.client-id}")
    private String clientId;

    @Value("${spotify.client-secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    public SpotifyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getAccessToken() {
        String url = "https://accounts.spotify.com/api/token";

        HttpHeaders headers = new HttpHeaders();
        String credentials = clientId + ":" + clientSecret;
        String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());

        headers.set("Authorization", "Basic " + encoded);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return (String) response.getBody().get("access_token");
    }

    public List<AlbumDTO> cautaAlbume(String artist) {
        String token = getAccessToken();
        String encodedArtist = UriUtils.encode(artist, StandardCharsets.UTF_8);
        String url = "https://api.spotify.com/v1/search?q=" + encodedArtist + "&type=album&limit=5";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        Map<String, Object> albums = (Map<String, Object>) response.getBody().get("albums");

        List<Map<String, Object>> items = (List<Map<String, Object>>) albums.get("items");

        List<AlbumDTO> result = new ArrayList<>();
        for (Map<String, Object> album : items) {
            String albumName = (String) album.get("name");
            int totalTracks = (Integer) album.get("total_tracks");

            String albumID = (String) album.get("id");

            List<Map<String, Object>> images = (List<Map<String, Object>>) album.get("images");
            String imageUrl = images.isEmpty() ? null : (String) images.get(0).get("url");

            List<Map<String, Object>> artists = (List<Map<String, Object>>) album.get("artists");
            String artistName = artists.isEmpty() ? null : (String) artists.get(0).get("name");

            Map<String, Object> externalUrls = (Map<String, Object>) album.get("external_urls");
            String albumUrl = externalUrls != null ? (String) externalUrls.get("spotify") : null;

            AlbumDTO dto = new AlbumDTO(albumName, artistName, totalTracks, imageUrl, albumID, albumUrl);
            result.add(dto);
        }

        return result;
    }
}
