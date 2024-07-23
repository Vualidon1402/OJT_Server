package com.ojt_server.modules.user.email;

import com.ojt_server.modules.user.dto.OtpCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    private final Map<Long, OtpCode> otps = new HashMap<>();

    public void storeOtp(Long userId,int otp) {
        OtpCode otpCode = new OtpCode(otp);
        otps.put(userId, otpCode);
    }

    public boolean verifyOtp(Long userId, int otp) {
        OtpCode otpCode = otps.get(userId);
        if (otpCode != null && otpCode.getOtp() == otp) {
            return LocalDateTime.now().isBefore(otpCode.getCreationTime().plusMinutes(1));
        }
        return false;  }
}
