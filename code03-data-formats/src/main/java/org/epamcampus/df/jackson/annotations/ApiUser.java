package org.epamcampus.df.jackson.annotations;

import com.fasterxml.jackson.annotation.*;

// Demonstrates: @JsonProperty (snake_case mapping), @JsonIgnore (security)
public class ApiUser {
    @JsonProperty("user_name")
    private String userName;

    @JsonIgnore
    private String password;

    private String email;

    public ApiUser() {}
    public ApiUser(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
