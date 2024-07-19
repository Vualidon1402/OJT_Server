package com.ojt_server.modules.user.service;

import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


//    findById

    //hiển thị danh sách user
    public List<UserModel> findUsers(){
        return userRepository.findAll();
    }
    //thay đổi trạng thái status
    public UserModel update(Long id){
            Optional<UserModel> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                UserModel user = optionalUser.get();
                user.setStatus(!user.isStatus()); // Flip the status
                return userRepository.save(user);
            } else {
                System.out.println("User not found with id: " + id);
                return null;
            }


    }





}
