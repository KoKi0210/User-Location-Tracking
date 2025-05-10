package com.example.GeolocationDemo.user.model;

public class UserDTO {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String number;

    public UserDTO(User user) {
        this.id = user.getId();;
        this.username = user.getUsername();;
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.number = user.getNumber();
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
