package listgenerator;

import java.util.HashMap;

public class TestSystem
{
    public static void main(String[] args)
    {
        ListGenerator test = new ListGenerator();

        System.out.println("--- USER TESTING ---");
        
        String legalUsername = "lucaapacitti";
        String illegalUsername1 = "";
        String illegalUsername2 = "lucaaaaaaaaaaaaaaaaaaaaaaaaaapacitti";
        String forename = "Luca";
        String newForename = "Alex";
        String surname = "Pacitti";
        String newSurname = "Lambert";
        String legalPassword = "jshv3r-7fjeic-2HSwfh";
        String newlegalPassword = "ipwgh3f-ieihHFH-cbcbdw12";
        String illegalPassword1 = "pswrd one";
        String illegalPassword2 = "pswrd";
        String illegalPassword3 = "jiipwpiwnUUOHUOO123";
        String illegalPassword4 = "ihgioirgoir123!?";
        String illegalPassword5 = "HGHGHSHDCN123!?";
        String illegalPassword6 = "igirgpiwjgWIOIB!?";
        String legalEmail = "ljmp1501@icloud.com";
        String newlegalEmail = "tom@davies.com";
        String illegalEmail = "ljmp1501icloud.com";

        System.out.println("Attempting to create user with illegal password '" + illegalPassword1 + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, illegalPassword1, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create user with illegal password '" + illegalPassword2 + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, illegalPassword2, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create user with illegal password '" + illegalPassword3 + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, illegalPassword3, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create user with illegal password '" + illegalPassword4 + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, illegalPassword4, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create user with illegal password '" + illegalPassword5 + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, illegalPassword5, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create user with illegal password '" + illegalPassword6 + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, illegalPassword6, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create user with illegal username '" + illegalUsername1 + "':");
        try
        {
            test.CreateUser(forename, surname, illegalUsername1, legalPassword, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create user with illegal username '" + illegalUsername2 + "':");
        try
        {
            test.CreateUser(forename, surname, illegalUsername2, legalPassword, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create user with illegal email '" + illegalEmail + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, legalPassword, illegalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create legal user with username '" + legalUsername + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, legalPassword, legalEmail);
            System.out.println("PASS: Account created successfully.");
        }
        catch (RuntimeException e)
        {
            System.out.println("FAIL: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to create illegal duplicate user with username '" + legalUsername + "':");
        try
        {
            test.CreateUser(forename, surname, legalUsername, legalPassword, legalEmail);
            System.out.println("FAIL");
        }
        catch (RuntimeException e)
        {
            System.out.println("PASS: " + e.getMessage());
        }
        System.out.println("");

        System.out.println("Attempting to log into account with username '" + legalUsername + "':");
        if (test.AttemptLogin(legalUsername, legalPassword))
        {
            System.out.println("PASS: Logged in successfully.");
        }
        else
        {
            System.out.println("FAIL");
        }
        System.out.println("");

        System.out.println("Attempting to log into account with incorrect password '" + illegalPassword1 + "':");
        if (test.AttemptLogin(legalUsername, illegalPassword1))
        {
            System.out.println("FAIL");
        }
        else
        {
            System.out.println("PASS: Log in failed.");
        }
        System.out.println("");

        HashMap<String, User> users = test.getUsers();
        System.out.println("Attempting to change user forename to '" + newForename + "':");
        test.EditForename(legalUsername, newForename);
        if (users.get(legalUsername).getForename().equals(newForename))
        {
            System.out.println("PASS: Forename changed succesfully.");
        }
        else
        {
            System.out.println("FAIL");
        }
        System.out.println("");

        System.out.println("Attempting to change user surname to '" + newSurname + "':");
        test.EditSurname(legalUsername, newSurname);
        if (users.get(legalUsername).getSurname().equals(newSurname))
        {
            System.out.println("PASS: Surname changed succesfully.");
        }
        else
        {
            System.out.println("FAIL");
        }
        System.out.println("");

        System.out.println("Attempting to change user password to '" + newlegalPassword + "':");
        test.EditPassword(legalUsername, newlegalPassword);
        if (users.get(legalUsername).getPassword().equals(newlegalPassword))
        {
            System.out.println("PASS: Password changed succesfully.");
        }
        else
        {
            System.out.println("FAIL");
        }
        System.out.println("");

        System.out.println("Attempting to change user email to '" + newlegalEmail + "':");
        test.EditEmail(legalUsername, newlegalEmail);
        if (users.get(legalUsername).getEmail().equals(newlegalEmail))
        {
            System.out.println("PASS: Email changed succesfully.");
        }
        else
        {
            System.out.println("FAIL");
        }
        System.out.println("");

        System.out.println("All existing users:");
        for (String username : users.keySet())
        {
            System.out.println(username);
        }
        System.out.println("");

        System.out.println("Deleting user with username '" + legalUsername + "':");
        test.DeleteUser(legalUsername);
        System.out.println("");

        System.out.println("All existing users:");
        for (String username : users.keySet())
        {
            System.out.println(username);
        }
        System.out.println("");

        System.out.println("Attempting to log into recently deleted account with username '" + legalUsername + "':");
        if (test.AttemptLogin(legalUsername, newlegalPassword))
        {
            System.out.println("FAIL");
        }
        else
        {
            System.out.println("PASS: Log in failed.");
        }
        System.out.println("");

        System.out.println("NOTE: Function GetUserLists still needs testing.");
        System.out.println("--- END OF USER TESTING ---");
    }
}

       