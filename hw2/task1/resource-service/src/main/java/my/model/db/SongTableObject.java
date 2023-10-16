package my.model.db;

import jakarta.persistence.*;

@Entity
@Table(name = "song_binaries")
public class SongTableObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name="song_binary")
    private byte[] songBinary;

    public SongTableObject(byte[] songBinary) {
        this.songBinary = songBinary;
    }

    public SongTableObject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getSongBinary() {
        return songBinary;
    }

    public void setSongBinary(byte[] songBinary) {
        this.songBinary = songBinary;
    }
}
