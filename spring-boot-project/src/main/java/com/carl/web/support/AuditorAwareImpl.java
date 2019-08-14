package com.carl.web.support;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author carl
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("user123");
    }
}
