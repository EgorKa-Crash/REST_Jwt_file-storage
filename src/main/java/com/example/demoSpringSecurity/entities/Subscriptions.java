package com.example.demoSpringSecurity.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SubscriptionsPK.class)
public class Subscriptions {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "subscriberId")
    private User subscriber;

    private Date subscriptionDate;

    @PrePersist
    protected void onCreate() {
        subscriptionDate = new Date();
    }

    public Subscriptions(User user, User subscriber) {
        this.user = user;
        this.subscriber = subscriber;
    }
}