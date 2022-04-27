package com.example.demoSpringSecurity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
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

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    private Date created;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date updated;

    @Column(unique = true) //nullable = false,
    private String email;

    @Column(unique = true) //nullable = false,
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
    private List<File> posts = new LinkedList<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<GroupOfUsers> groupOfUsers = new LinkedList<>();
//
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Subscriptions> subscriptions = new LinkedList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "subscriber", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) //cascade = CascadeType.ALL, fetch = FetchType.EAGER,
    private List<Subscriptions> subscribers = new LinkedList<>();

//    public User(String login, String password, String email, String nickName) {
//        this.login = login;
//        this.password = password;
//        this.email = email;
//        this.nickName = nickName;
//    }
//
//    public User() {
//    }
//
//    public User(Integer userId, String login, String password, String email, String nickName) {
//        this.userId = userId;
//        this.login = login;
//        this.password = password;
//        this.email = email;
//        this.nickName = nickName;
//    }
}
