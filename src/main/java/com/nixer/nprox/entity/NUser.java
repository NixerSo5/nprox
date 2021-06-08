package com.nixer.nprox.entity;

import java.io.Serializable;

/**
 * (NUser)实体类
 *
 * @author makejava
 * @since 2021-06-07 16:45:29
 */
public class NUser implements Serializable {
    private static final long serialVersionUID = 341486280537553982L;

    private Integer id;

    private String username;

    private String password;

    private String role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
