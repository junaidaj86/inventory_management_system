package com.zinu.inventory.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class TenantedAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final Long tenantId;

    public TenantedAuthenticationToken(Object principal, Long tenantId, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.tenantId = tenantId;
    }

    public Long getTenantId() {
        return tenantId;
    }
}
