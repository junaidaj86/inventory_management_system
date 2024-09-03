package com.zinu.inventory.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zinu.inventory.configuration.JwtService;
import com.zinu.inventory.exception.MasterException;
import com.zinu.inventory.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;

        private final AuthenticationManager authenticationManager;

        public AuthResponse register(RegisterReq request) throws MasterException {
                Role role = Role.valueOf("ROLE_"+request.role().toUpperCase());
                var user = Users.builder()
                                .firstName(request.firstName())
                                .lastName(request.lastName())
                                .email(request.email())
                                .password(passwordEncoder.encode(request.password()))
                                .tenantId(request.tenantId())
                                .role(role)
                                .build();
                repository.save(user);
                var jwtToken = jwtService.generateToken(user, request.tenantId());
                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthResponse authenticate(AuthRequest request) throws MasterException {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                var user = repository.findByEmail(request.getEmail())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user, user.getTenantId());
                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }
}
