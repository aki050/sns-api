package com.example.sns_api.service;

import com.example.sns_api.model.User;
import com.example.sns_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 全ユーザーを取得する
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 新しいユーザーを登録する
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
