package tools;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        StandardPasswordEncoder passwordEncoder = new StandardPasswordEncoder();
        System.out.println(passwordEncoder.encode("12345678"));
    }
}
