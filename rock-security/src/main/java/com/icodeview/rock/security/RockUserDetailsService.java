package com.icodeview.rock.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface RockUserDetailsService extends UserDetailsService {
    UserDetails getUserDetailsById(Long id);
}
