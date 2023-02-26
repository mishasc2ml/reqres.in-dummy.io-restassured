package org.example.api.reqres_in.pojo_response.register_response;

public class SuccessfulRegisterResponse {

    private Integer id;
    private String token;

    public SuccessfulRegisterResponse(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    private SuccessfulRegisterResponse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
