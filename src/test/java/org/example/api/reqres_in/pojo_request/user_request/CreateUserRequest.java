package org.example.api.reqres_in.pojo_request.user_request;

public class CreateUserRequest {

    private String name;
    private String job;

    public CreateUserRequest(String name, String job) {
        this.name = name;
        this.job = job;
    }

    private CreateUserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
