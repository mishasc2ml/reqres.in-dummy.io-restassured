package org.example.api.reqres.pojo_request.register_request;

public class SuccessfulRegisterRequest {

    private String email;
    private String password;

    public SuccessfulRegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private SuccessfulRegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
