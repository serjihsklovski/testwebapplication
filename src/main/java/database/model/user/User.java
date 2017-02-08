package database.model.user;

public class User {

    private long id;
    private String login;
    private String email;
    private String password;
    private String role;

    public User(long id, String login, String email, String password, String role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Represents a user as a json object.
     *
     * @return String json
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("{\"id\": %d, ")
                .append("\"login\": \"%s\", ")
                .append("\"email\": \"%s\", ")
                .append("\"password\": \"%s\", ")
                .append("\"role\": \"%s\"}");

        return String.format(stringBuilder.toString(),
                id, login, email, password, role);
    }
}
