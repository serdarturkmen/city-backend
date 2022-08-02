package com.example.city.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken implements Serializable {
    @JsonProperty("token")
    private String jwtToken;

    private Long expireTime;
}