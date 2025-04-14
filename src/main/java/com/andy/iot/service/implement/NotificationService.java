package com.andy.iot.service.implement;

import com.andy.iot.model.Notification;
import com.andy.iot.model.ServerAccount;
import com.andy.iot.repository.NotificationRepository;
import com.andy.iot.repository.ServerAccountRepository;
import com.andy.iot.service.interf.NotificationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements NotificationServiceInterface {
    private final NotificationRepository notificationRepository;
    private final ServerAccountRepository serverAccountRepository;
    @Override
    public void addNotification(String description) {
        ServerAccount defaultServerAccount = serverAccountRepository.findById(2).orElseThrow();
        Notification notification = new Notification();
        notification.setTime(LocalDateTime.now());
        notification.setDescription(description);
        notification.setServerAccount(defaultServerAccount);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
