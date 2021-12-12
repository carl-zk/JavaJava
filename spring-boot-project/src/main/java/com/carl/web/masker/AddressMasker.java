package com.carl.web.masker;

import com.example.mask.core.Masker;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author carl
 */
@Component
public class AddressMasker implements Masker {

    @Override
    public String mask(String v) {
        if (StringUtils.hasText(v)) {
            return "**" + v.charAt(v.length() - 1);
        }
        return v;
    }
}
