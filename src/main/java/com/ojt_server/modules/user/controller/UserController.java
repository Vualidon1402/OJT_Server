package com.ojt_server.modules.user.controller;

import com.ojt_server.modules.user.dto.LoginDTO;
import com.ojt_server.modules.user.dto.OtpVerificationDTO;
import com.ojt_server.modules.user.dto.RegisterDTO;
import com.ojt_server.modules.user.dto.UploadUserDTO;
import com.ojt_server.modules.user.email.CreateRespone;
import com.ojt_server.modules.user.email.EmailService;
import com.ojt_server.modules.user.email.OtpService;

import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.RoleRepository;

import com.ojt_server.modules.user.service.UserService;


import com.ojt_server.util.jwt.JwtService;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private RoleRepository roleRepository;
    //hiện thị tất cả users
    @GetMapping("/findAll")

    public ResponseEntity<Object> getUsers(){
        try{
            return new ResponseEntity<>(userService.findUsers(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    //thay đổi trạng thái staus
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable Long id){
        try{
            UserModel user = userService.update(id);
            if(user != null){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    //tìm kiếm user theo userName
    @GetMapping("/findByUserName/{userName}")
    public ResponseEntity<Object> findByUserName(@PathVariable String userName){
        try{
            List<UserModel> users = userService.findByUserName(userName);
            if(!users.isEmpty()){
                return new ResponseEntity<>(users, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No users found with userName containing: " + userName, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    //sắp xếp user
    @GetMapping("/sort")
    public ResponseEntity<Object> sortUser(@RequestParam(defaultValue = "asc") String sortType) {
        try {
            return new ResponseEntity<>(userService.sortUser(sortType), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    // phân trang user
    @GetMapping("/find")
    public ResponseEntity<Object> findUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        try {
            Page<UserModel> usersPage = userService.findUsers(page, size);
            return new ResponseEntity<>(usersPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    //thanh đổi trạng thái role của users
    @PutMapping("/updateRole/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody List<Long> roleIds) {
        System.out.println("roleIds: " + roleIds);
        try {
            return new ResponseEntity<>(userService.updateRole(id, roleIds), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    //đăng ký tài khoản
@PostMapping("/register")
public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO registerDTO){

    try{
        String hashedPassword = BCrypt.hashpw(registerDTO.getPassword(), BCrypt.gensalt());
        registerDTO.setPassword(hashedPassword);
        UserModel user = userService.register(registerDTO);
        if(user != null) {
            CreateRespone createRespone = new CreateRespone();
            createRespone.setData(user);
            createRespone.setMessage("Đăng ký tài khoản thành công");
            String userEmail = createRespone.getData().getEmail();
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            try {
                emailService.sendOtpToUser(userEmail, otp);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending OTP");
            }
            otpService.storeOtp(createRespone.getData().getId(), otp);
            return ResponseEntity.ok(user.getId());
        }
        return new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
    }catch (Exception e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }
}

    //nhạp otp
    @PostMapping("/activate")
    public ResponseEntity<Object> activateAccount(@RequestBody OtpVerificationDTO otpVerificationDTO) {
        try {
            boolean isOtpValid = otpService.verifyOtp(otpVerificationDTO.getUserId(), otpVerificationDTO.getOtp());
            if (!isOtpValid) {
                String errorMessage = otpService.getOtpErrorMessage(otpVerificationDTO.getUserId(), otpVerificationDTO.getOtp());
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
            UserModel user = userService.activate(otpVerificationDTO.getUserId());
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found with id: " + otpVerificationDTO.getUserId(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
    //nhận lại mã otp khi khách hàng yêu cầu
    @PostMapping("/resendOtp")
    public ResponseEntity<Object> resendOtp(@RequestBody OtpVerificationDTO otpVerificationDTO) {
        try {
            UserModel user = userService.findUserById(otpVerificationDTO.getUserId());
            if (user == null) {
                return new ResponseEntity<>("User not found with id: " + otpVerificationDTO.getUserId(), HttpStatus.NOT_FOUND);
            }
            String userEmail = user.getEmail();
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            try {
                emailService.sendOtpToUser(userEmail, otp);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending OTP");
            }
            otpService.storeOtp(otpVerificationDTO.getUserId(), otp);
            return ResponseEntity.ok("OTP sent successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    //đăng nhập tài khoản
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO data){

       try{
           UserModel user = userService.findUserByInfor(data.getLoginId());
           if (user == null) {
               throw new Exception("Tài khoản không tồn tại");
           }
           if (!BCrypt.checkpw(data.getPassword(), user.getPassword())) {
               throw new Exception("Sai mật khẩu");
           }
           if (!user.isStatus()) {
               throw new Exception("Tài khoản da bi khoa");
           }

           String token = JwtService.createTokenUser(user);
           JedisPool jedisPool = new JedisPool("localhost", 6379);
           try (Jedis jedis = jedisPool.getResource()) {
               jedis.set(String.valueOf(user.getId()), token);
           }
           jedisPool.close();


           Map<String, Object> response = new HashMap<>();
           response.put("message", "Đăng nhập thành công");
           response.put("token", token);

           return new ResponseEntity<>(response, HttpStatus.OK);

       }catch (Exception e) {
           return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
       }
    }
    @PostMapping("/authen")
    public ResponseEntity<Object> authen(@RequestAttribute("data") UserModel user) {
        try {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/requestPasswordChangeOtp/{id}")
    public ResponseEntity<Object> requestPasswordChangeOtp(@PathVariable Long id,@RequestBody Map<String, String> data) {
        try {
            UserModel user = userService.findUserById(id);
            if (user == null) {
                return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
            }
            // Get the old password from the request
            String oldPassword = data.get("oldPassword");
            // Check if the old password matches the current password
            if (!BCrypt.checkpw(oldPassword, user.getPassword())) {
                return new ResponseEntity<>("Mật khẩu cũ không đúng", HttpStatus.BAD_REQUEST);
            }
            String userEmail = user.getEmail();
            Random random = new Random();
            int otp = 100000 + random.nextInt(900000);
            try {
                emailService.sendOtpToUser(userEmail, otp);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while sending OTP");
            }
            otpService.storeOtp(id, otp);
            return ResponseEntity.ok("OTP sent successfully for password change");
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    //lấy mật khẩu cũ để so sánh rồi thanh đổi mật khẩu mới
    @PutMapping("/changePassword/{id}")
    public ResponseEntity<Object> changePassword(@PathVariable Long id, @RequestBody Map<String, String> data) {

        try {
            int otpValue;
            try {
                otpValue = Integer.parseInt(data.get("otp"));

            } catch (NumberFormatException e) {
                return new ResponseEntity<>("OTP không hợp lệ", HttpStatus.BAD_REQUEST);
            }
            // Xác minh OTP trước
            boolean isOtpValid = otpService.verifyOtp(id, otpValue);

            if (!isOtpValid) {
                String errorMessage = otpService.getOtpErrorMessage(id, otpValue);
                System.out.println("errorMessage: " + errorMessage);
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
            // Nếu OTP hợp lệ, tiếp tục thay đổi mật khẩu
            UserModel user = userService.changePassword(id, data.get("oldPassword"), data.get("newPassword"));
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }


    //chinhr sửa thông tin ueser
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> updateProfile(@PathVariable Long id, @RequestBody UploadUserDTO userDto) {
        try {
            UserModel updatedUser = userService.update(id, userDto);
            if (updatedUser != null) {
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }



}
