# 🎵 Spotify Web Search – Spring Boot

Acest proiect este o aplicație **Spring Boot** care permite căutarea albumelor unui artist folosind **Spotify Web API**.  
Aplicația folosește autentificarea prin **Client Credentials Flow** și returnează informații despre albume: nume, artist, număr de piese, imagine, ID și link Spotify.

## 🚀 Funcționalități

- Obține automat **access token** de la Spotify  
- Caută albume după numele artistului  
- Preia informații despre album:  
  - Nume album  
  - Artist  
  - Număr total de melodii  
  - URL imagine  
  - ID album  
  - Link Spotify  
- Returnează datele în format `AlbumDTO`

## 🛠️ Tehnologii

- Java 24+
- Spring Boot  
- RestTemplate  
- Maven  
- Spotify Web API  

## 📦 Structura proiectului

src/main/java/com/example/spotifyweb/
- services/
 - SpotifyService.java
- AlbumDTO.java
- controllers/
 - SpotifyService
- configs/
 - RestTemplateConfig

## 🔑 Configurare – Cheile Spotify

Adaugă în `application.properties`:

```properties
spotify.client-id=YOUR_SPOTIFY_CLIENT_ID
spotify.client-secret=YOUR_SPOTIFY_CLIENT_SECRET


- Pentru a obține aceste chei:

* Intră pe https://developer.spotify.com/dashboard
* Creează o aplicație nouă
* Copiază Client ID și Client Secret
