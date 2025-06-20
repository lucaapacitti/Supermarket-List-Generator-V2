package listgenerator;

public class User
{
    private String forename;
    private String surname;
    private String username;
    private String password;
    private String email;

    public User(String forename, String surname, String username, String password, String email)
    {
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getForename()
    {
        return forename;
    }

    public void setForename(String forename)
    {
        this.forename = forename;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
