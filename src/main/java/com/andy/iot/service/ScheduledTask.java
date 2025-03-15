package com.andy.iot.service;

import com.andy.iot.response.SensorDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTask {
    private final AdafruitService adafruitService;
    private final WebSocketService webSocketService;

    //Cứ 5 giây lấy dữ liệu và gửi lên WebSocket
    @Scheduled(fixedRateString = "${adafruit.receive-interval}")
    public void fetchAndBroadcast() {
        SensorDataResponse data = adafruitService.fetchData();
        webSocketService.sendJson(data);
        System.out.println("Broadcasted: " + data);
    }
}