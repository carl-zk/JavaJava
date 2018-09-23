package com.hero.user.controller;

import com.hero.common.Result;
import org.springframework.stereotype.Component;

/**
 * @author carl
 */
@Component
public class FallbackCoreService implements CoreService {
    @Override
    public Result hello() {
        return Result.error(-1, "not ok");
    }

    @Override
    public Result getInfo(int id) {
        return Result.error(-1, "no info");
    }
}
