package com.zinu.inventory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zinu.inventory.authentication.Role;
import com.zinu.inventory.authentication.UserRepository;
import com.zinu.inventory.authentication.Users;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.findByEmail("superadmin@example.com").isPresent()) {
				Role role = Role.valueOf("ROLE_SUPERADMIN");
                Users superAdmin = Users.builder()
                    .firstName("Super")
                    .lastName("Admin")
                    .email("junaid@gmail.com")
                    .password(passwordEncoder.encode("!stCutd@ne12")) // Hash the password
                    .role(role)
                    .tenantId(0)
                    .build();
                userRepository.save(superAdmin);
            }
        };
    }

}
