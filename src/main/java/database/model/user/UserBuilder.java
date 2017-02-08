package database.model.user;

public class UserBuilder {

    private long id = -1;
    private String login = null;
    private String email = null;
    private String password = null;
    private String role = User.ROLE_USER;

    public UserBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public User buildUser() {
        return new User(id, login, email, password, role);
    }
}
