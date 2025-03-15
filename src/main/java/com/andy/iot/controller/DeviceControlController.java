package com.andy.iot.controller;

import com.andy.iot.response.ApiResponse;
import com.andy.iot.service.DeviceControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
public class DeviceControlController {
    private final DeviceControlService deviceControlService;


    @PostMapping("/toggle/{device}")
    public ResponseEntity<ApiResponse<Integer>> toggleDevice(@PathVariable String device) {
        try {
            Integer newState = deviceControlService.toggleDevice(device);
            return ResponseEntity.ok(ApiResponse.<Integer>builder()
                    .status(HttpStatus.OK.value())
                    .message("Device " + device + " toggled successfully.")
                    .data(newState)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponse.<Integer>builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message(e.getMessage())
                            .data(null)
                            .build()
            );
        }
    }
}
