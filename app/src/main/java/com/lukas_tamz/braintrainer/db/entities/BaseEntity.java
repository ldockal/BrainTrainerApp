package com.lukas_tamz.braintrainer.db.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ldockal on 12/17/2017.
 */

public abstract class BaseEntity implements Serializable{

    private int id;

    public BaseEntity(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity baseDTO = (BaseEntity) o;
        return getId() == baseDTO.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
