package com.example.demoSpringSecurity.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SubscriptionsPK.class)
public class Subscriptions {

//    @Id
//    @GeneratedValue
//    private Long subscriptionsId;
//    @EmbeddedId
//    private SubscriptionsPK subscriptionsPK;

    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
//
    @Id
    @ManyToOne
    @JoinColumn(name = "subscriberId")
    private User subscriber;

    private Timestamp subscriptionDate;

    public Subscriptions(User user, User subscriber) {
        this.user = user;
        this.subscriber = subscriber;
    }
}