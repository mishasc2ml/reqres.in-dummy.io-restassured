package org.example.api.dummy_io.user_classes;

import org.example.api.util.ReadProperties;

public class CreateUserRequestBody {

    private String title;
    private String firstName;
    private String lastName;
    private String email;

    public CreateUserRequestBody() {
        this.firstName = ReadProperties.readTestDataProperties("firstname");
        this.lastName = ReadProperties.readTestDataProperties("lastname");
        this.email = ReadProperties.readTestDataProperties("email");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
