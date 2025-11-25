package com.example.spotifyweb;

public class AlbumDTO {
    private String albumName;
    private String artistName;
    private int totalTracks;
    private String imageUrl;
    private String albumID;
    private String albumUrl;

    public AlbumDTO(String albumName, String artistName, int totalTracks, String imageUrl, String albumID, String albumUrl) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.totalTracks = totalTracks;
        this.imageUrl = imageUrl;
        this.albumID = albumID;
        this.albumUrl = albumUrl;
    }

    public String getAlbumID() {
        return albumID;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
