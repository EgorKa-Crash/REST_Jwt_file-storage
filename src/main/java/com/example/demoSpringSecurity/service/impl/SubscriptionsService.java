package com.example.demoSpringSecurity.service.impl;

import com.example.demoSpringSecurity.dao.SubscriptionsDAO;
import com.example.demoSpringSecurity.dto.SubscriptionsIds;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionsService implements ISubscriptionsService {
    @Override
    public boolean isContainSubscription(SubscriptionsIds subscriptionsIds) {
        return SubscriptionsDAO.isContainSubscription(subscriptionsIds);
    }

    @Override
    public boolean add(SubscriptionsIds subscriptionsIds) {
        return SubscriptionsDAO.add(subscriptionsIds);
    }

    @Override
    public boolean remove(SubscriptionsIds subscriptionsIds) {
        return SubscriptionsDAO.remove(subscriptionsIds);
    }
}
