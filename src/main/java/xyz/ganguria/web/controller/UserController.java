package xyz.ganguria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import xyz.ganguria.web.entity.User;
import xyz.ganguria.web.repository.UserRepository;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUsers(@PathVariable Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found - " + id));
    }

    @PostMapping("/users")
    User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User userDto) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found - " + id));

        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return this.userRepository.save(userDto);
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found - " + id));
        this.userRepository.delete(user);
    }
}
