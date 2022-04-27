package com.example.demoSpringSecurity.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private Integer fileId;

    @Column(nullable = false, length = 50)
    private String name;

    private Date createdDate;

    private Date updatedDate;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private String pach;

    private Integer parantFileId;

    private Integer version;

    @Column(length = 500)
    private String comment;

    @Column(nullable = false, length = 10)
    private String availability;

    @ManyToOne
    @JoinColumn(name = "userId",  nullable = false)
    private User user;

    public File(String name, Integer capacity, String pach, String availability, User user) {
        this.name = name;
        this.capacity = capacity;
        this.pach = pach;
        this.availability = availability;
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }
}
