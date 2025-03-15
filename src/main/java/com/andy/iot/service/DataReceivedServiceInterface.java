package com.andy.iot.service;

public interface DataReceivedServiceInterface {
    void saveTemperature(double value);
    void saveHumidity(double value);
    void saveLight(int value);
    void saveAirQuality(double value);
}
