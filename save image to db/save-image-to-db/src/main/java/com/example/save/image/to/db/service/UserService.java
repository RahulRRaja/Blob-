package com.example.save.image.to.db.service;

import com.example.save.image.to.db.model.User;
import com.example.save.image.to.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void saveUser(MultipartFile file) throws IOException{
        User user = new User();
        user.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        user.setImage(file.getBytes());
        user.setSize(file.getSize());
        user.setContentType(file.getContentType());
        userRepository.save(user);
    }

    public Optional<User> getFile(Long id)
    {
        return userRepository.findById(id);
    }

    public List<User> getAllFiles()
    {
        return userRepository.findAll();
    }
}
