package qa.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import qa.dao.UserDao;
import qa.domain.QaUser;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDetailsService {
    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QaUser user = userDao.findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException("invalid user name or password.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getName(), user.getPassword(), authorities);
    }

    public QaUser addOneUser(QaUser user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userDao.persist(user);
    }

    public QaUser find(String username) {
        return userDao.findByName(username);
    }
}
