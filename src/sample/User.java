package sample;

public class User {
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String id;
    private String phon;
    private String dostup;

    public String getDostup() {
        return dostup;
    }

    public void setDostup(String dostup) {
        this.dostup = dostup;
    }

    public String getPhon() {
        return phon;
    }

    public void setPhon(String phon) {
        this.phon = phon;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String login, String firstName, String lastName, String password, String id, String phon, String dostup) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.id = id;
        this.phon = phon;
        this.dostup = dostup;
    }

    public User() {
    }
}
