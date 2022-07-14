package others.spring_practice.controllers;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User addUser(User user) {
        return new User();
    }
}
