package com.zinu.inventory.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zinu.inventory.configuration.JwtService;
import com.zinu.inventory.exception.MasterException;
import com.zinu.inventory.store.Store;
import com.zinu.inventory.store.StoreService;

@Service
@RequiredArgsConstructor
public class AuthService {
        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;

        private final AuthenticationManager authenticationManager;
        private final StoreService storeService;

        public AuthResponse register(RegisterReq request) throws MasterException {
                Store store = storeService.getStoreById(Long.valueOf(request.tenantId()))
                .orElseThrow(() -> new RuntimeException("Store not found with ID: " + request.tenantId()));
                Role role = Role.valueOf("ROLE_"+request.role().toUpperCase());
                var user = Users.builder()
                                .firstName(request.firstName())
                                .lastName(request.lastName())
                                .email(request.email())
                                .password(passwordEncoder.encode(request.password()))
                                .tenantId(request.tenantId())
                                .role(role)
                                .store(store)
                                .build();
                repository.save(user);
                var jwtToken = jwtService.generateToken(user, Long.valueOf(request.tenantId()));
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
                var jwtToken = jwtService.generateToken(user, Long.valueOf(user.getTenantId()));
                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }
}
