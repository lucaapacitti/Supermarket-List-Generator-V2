package listgenerator;

import java.util.HashMap;

public class ListGenerator
{
    private HashMap<Integer, Product> products;
    private HashMap<String, User> users;
    private HashMap<Integer, List> lists;

    public ListGenerator()
    {
        this.products = new HashMap<>();
        this.users = new HashMap<>();
        this.lists = new HashMap<>();
    }

    // Users

    public void CreateUser(String username, String forename, String surname, String password, String email)
    throws RuntimeException
    {
        CheckLegalUsername(username);
    }

    public void CheckLegalUsername(String username)
    {
        if (username.length() < 1 || username.length() > 20)
        {
            throw new RuntimeException("Username must be 1-20 characters long.");
        }
        String permittedChars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890._";
        for (int i = 0; i < username.length(); i++)
        {
            if (!permittedChars.contains(username.substring(i, i + 1)))
            {
                throw new RuntimeException("Username may only contain letters, numbers, periods and underscores.");
            }
        }
        for (String existingUsername : users.keySet())
        {
            if (existingUsername.toLowerCase() == username.toLowerCase())
            {
                throw new RuntimeException("Username already in use.");
            }
        }
    }

    public void CheckLegalPassword(String password)
    {
        if (password.length() < 8)
        {
            throw new RuntimeException("Password must be minimum 8 characters.");
        }
    }
}
