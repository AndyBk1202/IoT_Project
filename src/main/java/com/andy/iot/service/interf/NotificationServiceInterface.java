package com.andy.iot.service.interf;

import com.andy.iot.model.Notification;

import java.util.List;

public interface NotificationServiceInterface {
    void addNotification(String description);
    List<Notification> getAllNotifications();
}
