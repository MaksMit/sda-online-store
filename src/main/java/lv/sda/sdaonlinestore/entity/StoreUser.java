package lv.sda.sdaonlinestore.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="store_users")
public class StoreUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeUserId;

    @Column(name = "login")
    @NotNull
    @Size(min=3, max=40)
    @Pattern(regexp = "[a-zA-Z0-9]{3,40}")
    private String login;

    @Column(name = "password")
    @NotNull
    @Size(min=6, max=64)
    @Pattern(regexp = "[a-zA-Z0-9].{6,64}")
    //@Pattern(regexp = "^[a-zA-Z0-9].{6,64}$")
    //@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z]).{6,64}$")
    private String password;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    @Email
    @NotNull
    private String email;

    @Column(name = "phone")
    @Digits(integer = 10, fraction = 0)
    @NotNull
    private String phone;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getStoreUserId() {
        return storeUserId;
    }

    public void setStoreUserId(Long storeUserId) {
        this.storeUserId = storeUserId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "StoreUser{" +
                "storeUserId=" + storeUserId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", city=" + city +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
