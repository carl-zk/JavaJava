package org.hibernate.test;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by hero on 16-5-22.
 */
@Entity
@Table(name = "i_am_user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name="increment", strategy="increment")
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name +" "+ id;
    }

    protected User() {
    }

    public User(String name) {
        this.name = name;
    }
}
