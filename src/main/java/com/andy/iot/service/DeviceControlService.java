package com.andy.iot.service;

import com.andy.iot.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DeviceControlService {
    @Value("${adafruit.api-key}")
    private String apiKey;

    private static final String BASE_URL = "https://io.adafruit.com/api/v2/Anhdo020204/feeds/";

    private final RestTemplate restTemplate = new RestTemplate();

    public Integer toggleDevice(String device) throws Exception {
        String url = BASE_URL + device + "/data";

        // Lấy trạng thái hiện tại từ Adafruit IO
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-AIO-Key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url + "/last", HttpMethod.GET, entity, Map.class);
        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new Exception("Failed to retrieve current state from Adafruit IO.");
        }

        int currentValue = Integer.parseInt(response.getBody().get("value").toString());

        // Toggle trạng thái (0 ↔ 1)
        int newValue = (currentValue == 1) ? 0 : 1;

        // Gửi lệnh cập nhật đến Adafruit IO
        Map<String, String> requestBody = Map.of("value", String.valueOf(newValue));
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(url, request, String.class);

        if (!postResponse.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Failed to update device state.");
        }

        // Trả về trạng thái mới
        return newValue;
    }
}
