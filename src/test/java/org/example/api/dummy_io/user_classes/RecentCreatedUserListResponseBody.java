package org.example.api.dummy_io.user_classes;

public class RecentCreatedUserListResponseBody {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String registerDate;

    public RecentCreatedUserListResponseBody(String id, String firstName, String lastName, String email, String registerDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registerDate = registerDate;
    }

    private RecentCreatedUserListResponseBody() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
