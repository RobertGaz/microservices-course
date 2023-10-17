package my.model.db;

import jakarta.persistence.*;

@Entity
@Table(name = "song_metadata")
public class SongMetadataTableObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String title;

    @Column
    private String artist;

    @Column
    private String album;

    @Column
    private String duration;

    @Column
    private int resourceId;

    public SongMetadataTableObject(String title, String artist, String album, String duration, int resourceId) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.resourceId = resourceId;
    }

    public SongMetadataTableObject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "SongMetadataTableObject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration='" + duration + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }
}
