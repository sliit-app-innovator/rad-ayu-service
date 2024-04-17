package com.sliit.ayu.ayuservice.model;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "refresh_tokens ")
public class RefreshTokenEntity {
    @Id
    private String username;
    private String refreshToken;
}
