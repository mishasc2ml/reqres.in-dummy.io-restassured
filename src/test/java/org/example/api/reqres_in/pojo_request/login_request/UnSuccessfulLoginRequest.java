package org.example.api.reqres_in.pojo_request.login_request;

public class UnSuccessfulLoginRequest {

    private String email;

    public UnSuccessfulLoginRequest(String email) {
        this.email = email;
    }

    private UnSuccessfulLoginRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
