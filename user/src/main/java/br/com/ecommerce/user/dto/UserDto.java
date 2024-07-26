package br.com.ecommerce.user.dto;

public class UserDto {

    private String name;
    private String lastName;
    private String email;
    private String login;

    public UserDto() {
    }

    public UserDto(String name, String lastName, String email, String login) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
