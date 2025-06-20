package listgenerator;

import java.util.HashMap;

public class Test
{
    public static void main(String[] args)
    {
        HashMap<String, User> users = new HashMap<>();
        User TestUser = users.get("Test Username");
        System.out.println(TestUser);
    }
}