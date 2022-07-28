package com.example.demoSpringSecurity.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private Long fileId;

    @Column(nullable = false)
    private String name;

    private Date createdDate;

    private Date updatedDate;

    @Column(nullable = false)
    private Integer capacity;

    @Column(nullable = false)
    private String pach;

    @OneToOne
    private File parantFile;

    private Integer version;

    @Column(length = 500)
    private String comment;

    @Column(nullable = false, length = 10)
    private String availability;

    @ManyToOne
    @JoinColumn(name = "userId",  nullable = false)
    private User user;

    public File(String name, Integer capacity, Integer version, String pach, String availability, User user) {
        this.name = name;
        this.capacity = capacity;
        this.version = version;
        this.pach = pach;
        this.availability = availability;
        this.user = user;
    }

    public File(Long fileId, String name, Date createdDate, Date updatedDate, Integer capacity, String pach, File parantFile, Integer version, String comment, String availability, User user) {
        this.fileId = fileId;
        this.name = name;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.capacity = capacity;
        this.pach = pach;
        this.parantFile = parantFile;
        this.version = version;
        this.comment = comment;
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
