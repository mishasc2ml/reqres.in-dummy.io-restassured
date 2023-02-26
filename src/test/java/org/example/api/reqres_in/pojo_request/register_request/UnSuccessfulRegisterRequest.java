package org.example.api.reqres_in.pojo_request.register_request;

public class UnSuccessfulRegisterRequest {

    private String email;

    public UnSuccessfulRegisterRequest(String email) {
        this.email = email;
    }

    private UnSuccessfulRegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
