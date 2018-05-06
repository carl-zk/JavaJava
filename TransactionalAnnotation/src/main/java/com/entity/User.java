package com.entity;

/**
 * Created by hero on 17-2-27.
 */
public class User {
    private String name;
    private int age;

    protected User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getAge() != user.getAge()) return false;
        return getName().equals(user.getName());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAge();
        return result;
    }
}
