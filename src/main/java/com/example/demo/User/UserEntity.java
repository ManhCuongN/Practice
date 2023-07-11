package com.example.demo.User;


import com.example.demo.Base.BaseEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // Getter và setter cho thuộc tính "username"
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter và setter cho thuộc tính "password"
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Các getter và setter khác

}