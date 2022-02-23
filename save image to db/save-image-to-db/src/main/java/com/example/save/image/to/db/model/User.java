package com.example.save.image.to.db.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(length = Integer.MAX_VALUE)
    private String name;

    @Lob
    @Column()
    private byte[] data;

    @Column
    private Long size;

    @Column
    private String contentType;

    public User() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return data;
    }

    public void setImage(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public User(Long id, String name, byte[] data, Long size, String contentType) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.size = size;
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}
