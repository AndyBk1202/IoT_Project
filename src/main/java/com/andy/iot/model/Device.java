package com.andy.iot.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private Integer id;
    private String name;
    private String feedName;

    @ManyToOne
    @JoinColumn(name = "server_account_id")
    private ServerAccount serverAccount;
}
