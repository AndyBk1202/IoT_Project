package com.andy.iot.service;

import org.springframework.stereotype.Service;

public interface DataReceivedServiceInterface {
    void saveRecord(String feedName, double value);
    
}
