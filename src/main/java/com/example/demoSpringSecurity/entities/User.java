package com.example.demoSpringSecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Userr")
public class User {

    @Id
    @GeneratedValue
    private Long userId;

    @Column(nullable = false)
    private String login;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date created;

    private Date updated;

    @Column(unique = true) //nullable = false,
    private String email;

    @Column(unique = true, nullable = false)
    private String nickName;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true) //EAGER
    private List<File> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Subscriptions> subscriptions;

    @JsonIgnore
    @OneToMany(mappedBy = "subscriber", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) //cascade = CascadeType.ALL, fetch = FetchType.EAGER,
    private List<Subscriptions> subscribers;
}
