package com.ojt_server.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OtpCode {
    private int otp;
    private LocalDateTime creationTime;

    public OtpCode(int otp) {
        this.otp = otp;
        this.creationTime = LocalDateTime.now();
    }
    public int getOtp() {
        return otp;
    }
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
