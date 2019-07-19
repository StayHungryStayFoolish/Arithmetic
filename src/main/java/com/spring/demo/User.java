package com.spring.demo;

import java.io.Serializable;

/**
 * @author Created by bonismo@hotmail.com on 2019/6/14 5:07 PM
 * @Description:
 * @Version: 1.0
 */
public class User implements Serializable {

    private String name;

    private Boolean marry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMarry() {
        return marry;
    }

    public void setMarry(Boolean marry) {
        this.marry = marry;
    }
}
