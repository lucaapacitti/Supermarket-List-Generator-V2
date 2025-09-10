package listgenerator;

import java.util.ArrayList;

public class User
{
    private String forename;
    private String surname;
    private String username;
    private String passwordHash;
    private String email;
    private ArrayList<String> messages;

    public User(String forename, String surname, String username, String passwordHash, String email)
    {
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        messages = new ArrayList<>();
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

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public ArrayList<String> getMessages()
    {
        return messages;
    }

    public void setMessages(ArrayList<String> messages)
    {
        this.messages = messages;
    }
}
