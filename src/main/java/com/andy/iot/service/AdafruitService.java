package com.andy.iot.service;

import com.andy.iot.response.SensorDataResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AdafruitService {
    private final RestTemplate restTemplate;
    //private final DataReceivedServiceInterface dataReceivedService;

    @Value("${adafruit.base-url}")
    private String baseUrl;

    @Value("${adafruit.api-key}")
    private String apiKey;

    // lấy dữ liệu từ API Adafruit
    public SensorDataResponse fetchData() {
        SensorDataResponse response = new SensorDataResponse();

        double temperature = getSensorValue("temp");
        double humidity = getSensorValue("humi");
        int light = (int)getSensorValue("light");
        double conc = getSensorValue("conc");

//        dataReceivedService.saveTemperature(temperature);
//        dataReceivedService.saveHumidity(humidity);
//        dataReceivedService.saveLight(light);
//        dataReceivedService.saveAirQuality(conc);

        response.setTemperature(temperature);
        response.setHumidity(humidity);
        response.setLight(light);
        response.setAirQuality(conc);

        return response;
    }

    // Gọi API và lấy giá trị từ feed
    private double getSensorValue(String feed) {
        try {
            String url = String.format("%s/%s/data/last?X-AIO-Key=%s", baseUrl, feed, apiKey);
            String jsonResponse = restTemplate.getForObject(url, String.class);

            // Parse JSON để lấy giá trị "value"
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            String valueStr = rootNode.path("value").asText();

            return Double.parseDouble(valueStr);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0; //Double.NaN ;
        }
    }
}