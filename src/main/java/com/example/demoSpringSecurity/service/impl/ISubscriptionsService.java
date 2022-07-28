package com.example.demoSpringSecurity.service.impl;

import com.example.demoSpringSecurity.dto.SubscriptionsIds;
import com.example.demoSpringSecurity.entities.User;

import java.util.List;

public interface ISubscriptionsService {
    boolean isContainSubscription(SubscriptionsIds subscriptionsIds);

    boolean add(SubscriptionsIds subscriptionsIds);
    boolean remove(SubscriptionsIds subscriptionsIds);
}
