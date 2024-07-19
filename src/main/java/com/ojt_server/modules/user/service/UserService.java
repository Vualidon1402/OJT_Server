package com.ojt_server.modules.user.service;

import com.ojt_server.modules.user.model.UserModel;
import com.ojt_server.modules.user.reqository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //hiển thị danh sách user
    public List<UserModel> findUsers(){
        try{
            return userRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
