package silecchia.test.service;


import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import silecchia.test.model.UserModel;
import silecchia.test.model.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class UserService {

    private static List<UserModel> users = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;
  
    @PostConstruct
    public void postConstruct() {
        UserModel user = new UserModel();
        user.setRole(UserRole.ADMIN);
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("abc"));
        users.add(user);
    }

    public void register(UserModel user) {
   
        user.setRole(UserRole.USER);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.add(user);
    }

    public UserModel findByLogin(String login) {
        return users.stream().filter(user -> user.getUsername().equals(login))
                .findFirst()
                .orElse(null);
    }
}
