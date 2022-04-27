package com.example.demoSpringSecurity.entities;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubscriptionsPK implements Serializable{
    private User user;
    private User subscriber;
}

