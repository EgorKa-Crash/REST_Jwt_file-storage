package com.example.demoSpringSecurity.dto;

import com.example.demoSpringSecurity.entities.User;
import lombok.Data;

@Data
public class SubscriptionsIds {
    private long userId;
    private long subId;

    public SubscriptionsIds(long userId, long subId) {
        this.userId = userId;
        this.subId = subId;
    }
}