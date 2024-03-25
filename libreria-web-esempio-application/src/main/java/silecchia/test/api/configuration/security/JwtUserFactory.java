package silecchia.test.api.configuration.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import silecchia.test.model.UserModel;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(UserModel user) {
        return new JwtUser(
        		user.getUsername(),
                user.getPassword()
                
             
        );
    }

  
}