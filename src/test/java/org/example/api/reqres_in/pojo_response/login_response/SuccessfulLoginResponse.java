package org.example.api.reqres_in.pojo_response.login_response;

public class SuccessfulLoginResponse {

    private String token;

    public SuccessfulLoginResponse(String token) {
        this.token = token;
    }

    private SuccessfulLoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
