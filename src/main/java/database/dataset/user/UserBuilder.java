package database.dataset.user;

public class UserBuilder {

    private long id = -1;
    private String login = "";
    private String email = "";
    private String password = "";

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

    public User buildUser() {
        return new User(id, login, email, password);
    }
}
