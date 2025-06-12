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
        CheckLegalPassword(password);
        CheckValidEmail(email);
        User newUser = new User(forename, surname, username, password, email);
        users.put(username, newUser);
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
        boolean upperPresent = false;
        boolean lowerPresent = false;
        boolean numberPresent = false;
        boolean specialPresent = false;
        for (int i = 0; i < password.length; i++)
        {
            if (password.substring(i, i + 1) == " ")
            {
                throw new RuntimeException("Password must not contain spaces.");
            }
            if (Character.isUpperCase(password.substring(i, i + 1)))
            {
                upperPresent = true;
            }
            if (Character.isLowerCase(password.substring(i, i + 1)))
            {
                lowerPresent = true;
            }
            if (Character.isDigit(password.substring(i, i + 1)))
            {
                numberPresent = true;
            }
            if (!Character.isLetterOrDigit(password.substring(i, i + 1)))
            {
                specialPresent = true;
            }
        }
        if (upperPresent == false || lowerPresent == false || numberPresent == false || specialPresent || false)
        {
            throw new RuntimeException("Password must be a combination of lower case, upper case, numeric and special characters.");
        }
    }

    public void CheckValidEmail(String email)
    {
        String permittedChars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890.@"
        if (!Character.isLetterOrDigit(email.substring(0, 1)) || !Character.isLetterOrDigit(email.substring(email.length - 1, email.length)))
        {
            throw new RuntimeException("Email is of invalid format.")
        }
        int atCount = 0;
        boolean periodPresent = false;
        for (int i = 0; i < email.length; i++)
        {
            if (!permittedChars.contains(email.substring(i, i + 1)))
            {
                throw new RuntimeException("Email is of invalid format.")
            }
            if (email.substring(i, i + 1).equals("@"))
            {
                atCount++;
            }
            if (email.substring(i, i + 1).equals("."))
            {
                periodPresent = true;
            }
        }
        if (atCount != 1 || periodPresent == false)
        {
            throw new RuntimeException("Email is of invalid format.")
        }
        for (int i = 0; i < email.length - 1; i++)
        {
            if (email.substring(i, i + 2).equals("..") || email.substring(i, i + 2).equals(".@") || email.substring(i, i + 2).equals("@."))
            {
                throw new RuntimeException("Email is of invalid format.")
            }
        }
    }
}
