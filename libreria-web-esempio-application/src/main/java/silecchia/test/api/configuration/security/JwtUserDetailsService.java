package silecchia.test.api.configuration.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import silecchia.test.model.UserModel;
import silecchia.test.service.UserService;


@NoArgsConstructor

public class JwtUserDetailsService implements UserDetailsService {
	@org.springframework.beans.factory.annotation.Autowired(required=true)
    private UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserModel user = userService.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
