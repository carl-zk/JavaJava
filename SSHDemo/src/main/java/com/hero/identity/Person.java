package com.hero.identity;

import com.hero.test.Axe;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hero on 16-5-21.
 */
public class Person {

    private Axe axe;

    public void useAxe() {
        System.out.println("我准备做饭");
        System.out.println(axe.chop());
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }
}
