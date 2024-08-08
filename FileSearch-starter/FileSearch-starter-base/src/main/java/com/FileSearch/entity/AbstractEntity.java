package com.FileSearch.entity;

import com.FileSearch.bean.BaseBean;

import java.io.Serializable;
import java.util.Objects;

public class AbstractEntity<ID extends Serializable> extends BaseBean {
    private ID id;

    public AbstractEntity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) obj;
        return Objects.equals(that.id, id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
