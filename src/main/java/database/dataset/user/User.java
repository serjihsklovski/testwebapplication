package database.dataset.user;

public class User {

    private long id;
    private String login;
    private String email;
    private String password;

    public User(long id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("<user id=\"%d\" login=\"%s\" email=\"%s\" password=\"%s\" />",
                id, login, email, password);
    }
}
