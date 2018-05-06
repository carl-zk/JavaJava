package org.auto.scan;

/**
 * Created by hero on 16-5-21.
 */
public class HibernateUserRepository {
    private String say;

    public void init(){
        say="say say say";
    }

    public void print(){
        System.out.println("hibernate user repsitory" + say);
    }

    public HibernateUserRepository() {
        say = "say hello";
        System.out.println("HebernateUserRepository constructor");
    }
}
