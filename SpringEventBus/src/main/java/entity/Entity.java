package entity;

import java.util.UUID;

/**
 * Created by hero on 17-4-3.
 */
public class Entity implements java.io.Serializable{
    private static final long serialVersionUID = 2077513369403144312L;
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Entity() {
    }
}
