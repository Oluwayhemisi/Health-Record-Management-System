package com.example.Health.Record.Management.System.config;

import com.example.Health.Record.Management.System.entity.Role;
import com.example.Health.Record.Management.System.entity.User;
import com.example.Health.Record.Management.System.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@AllArgsConstructor
@Configuration
@Transactional
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {


    private final PasswordEncoder passwordEncoder;
    private  final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.findByEmail("ismailoluwayemisi@gmail.com").isEmpty()){
            User user = new User("OluwayemisiIsmail","ismailoluwayemisi@gmail.com", passwordEncoder.encode("password1234#"), Role.ROLE_ADMIN);
            user.setDateJoined(LocalDate.now());


        }
    }
}
