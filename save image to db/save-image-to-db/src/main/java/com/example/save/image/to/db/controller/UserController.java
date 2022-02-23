package com.example.save.image.to.db.controller;

import com.example.save.image.to.db.model.FileResponse;
import com.example.save.image.to.db.model.User;
import com.example.save.image.to.db.repository.UserRepository;
import com.example.save.image.to.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("files")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        userService.saveUser(file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
    }



    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<User> userOptional = userService.getFile(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.notFound()
                    .build();
        }

        User user = userOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + user.getName() + "\"")
                .contentType(MediaType.valueOf(user.getContentType()))
                .body(user.getImage());
    }
    
}
