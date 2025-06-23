package listgenerator;

import java.util.HashMap;
import java.util.ArrayList;

public class ListGenerator
{
    private HashMap<Integer, Product> products;
    private HashMap<String, User> users;
    private HashMap<Integer, List> lists;
    private HashMap<Integer, Store> stores;
    private HashMap<Integer, Location> locations;

    public ListGenerator()
    {
        this.products = new HashMap<>();
        this.users = new HashMap<>();
        this.lists = new HashMap<>();
        this.stores = new HashMap<>();
        this.locations = new HashMap<>();
    }

    // Users

    public void CheckLegalUsername(String username)
    throws RuntimeException
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
        User user = users.get(username.toLowerCase());
        if (user != null)
        {
            throw new RuntimeException("Username already in use.");
        }
    }

    public void CheckLegalPassword(String password)
    throws RuntimeException
    {
        if (password.length() < 8)
        {
            throw new RuntimeException("Password must be minimum 8 characters.");
        }
        boolean upperPresent = false;
        boolean lowerPresent = false;
        boolean numberPresent = false;
        boolean specialPresent = false;
        for (int i = 0; i < password.length(); i++)
        {
            if (password.charAt(i) == ' ')
            {
                throw new RuntimeException("Password must not contain spaces.");
            }
            if (Character.isUpperCase(password.charAt(i)))
            {
                upperPresent = true;
            }
            if (Character.isLowerCase(password.charAt(i)))
            {
                lowerPresent = true;
            }
            if (Character.isDigit(password.charAt(i)))
            {
                numberPresent = true;
            }
            if (!Character.isLetterOrDigit(password.charAt(i)))
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
    throws RuntimeException
    {
        String permittedChars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890.@";
        if (!Character.isLetterOrDigit(email.charAt(0)) || !Character.isLetterOrDigit(email.charAt(email.length() - 1)))
        {
            throw new RuntimeException("Email is of invalid format.");
        }
        int atCount = 0;
        boolean periodPresent = false;
        for (int i = 0; i < email.length(); i++)
        {
            if (!permittedChars.contains(email.substring(i, i + 1)))
            {
                throw new RuntimeException("Email is of invalid format.");
            }
            if (email.charAt(i) == '@')
            {
                atCount++;
            }
            if (email.charAt(i) == '.')
            {
                periodPresent = true;
            }
        }
        if (atCount != 1 || periodPresent == false)
        {
            throw new RuntimeException("Email is of invalid format.");
        }
        for (int i = 0; i < email.length() - 1; i++)
        {
            if (email.substring(i, i + 2).equals("..") || email.substring(i, i + 2).equals(".@") || email.substring(i, i + 2).equals("@."))
            {
                throw new RuntimeException("Email is of invalid format.");
            }
        }
    }

    public void CreateUser(String username, String forename, String surname, String password, String email)
    {
        CheckLegalUsername(username);
        CheckLegalPassword(password);
        CheckValidEmail(email);
        User newUser = new User(forename, surname, username.toLowerCase(), password, email);
        users.put(username, newUser);
    }

    public boolean AttemptLogin(String username, String password)
    {
        User user = users.get(username.toLowerCase());
        if (user == null)
        {
            return false;
        }
        if (password != user.getPassword())
        {
            return false;
        }
        return true;
    }

    public void EditForename(String username, String forename)
    {
        User user = users.get(username);
        user.setForename(forename);
    }

    public void EditSurname(String username, String surname)
    {
        User user = users.get(username);
        user.setSurname(surname);
    }

    public void EditEmail(String username, String email)
    {
        User user = users.get(username);
        user.setEmail(email);
    }

    public void EditPassword(String username, String password)
    {
        CheckLegalPassword(password);
        User user = users.get(username);
        user.setPassword(password);
    }

    public int[] GetUserLists(String username)
    {
        int arrSize = 0;
        for (List list : lists.values())
        {
            if (list.getUser().getUsername() == username)
            {
                arrSize++;
            }
        }
        int[] UserLists = new int[arrSize];
        int index = 0;
        for (List list : lists.values())
        {
            if (list.getUser().getUsername() == username)
            {
                UserLists[index] = list.getID();
                index++;
            }
        }
        return UserLists;
    }

    public void DeleteUser(String username)
    {
        for (List list : lists.values())
        {
            if (list.getUser().getUsername() == username)
            {
                lists.remove(list.getID());
            }
        }
        users.remove(username);
    }

    // Products

    public int GetAvailableID()
    {
        int availableID = 0;
        while (products.get(availableID) != null)
        {
            availableID++;
        }
        return availableID;
    }

    public void CheckValidPrice(double price)
    throws RuntimeException
    {
        if (price < 0.01)
        {
            throw new RuntimeException("Product price must be minimum £0.01");
        }
    }

    public void CreateProduct(String name, String category, double price, Location location)
    {
        int productID = GetAvailableID();
        price = Math.floor(price * 100) / 100.0;
        CheckValidPrice(price);
        Product product = new Product(productID, name, category, price, location);
        products.put(productID, product);
    }

    public String[] GetAllProductCategories()
    {
        ArrayList<String> categoriesList = new ArrayList<>();
        for (Product product : products.values())
        {
            if (!categoriesList.contains(product.getCategory()))
            {
                categoriesList.add(product.getCategory());
            }
        }
        String[] categoriesArr = new String[categoriesList.size()];
        int index = 0;
        for (String category : categoriesList)
        {
            categoriesArr[index] = category;
        }
        return categoriesArr;
    }

    public void EditProductCategory(int ID, String category)
    {
        Product product = products.get(ID);
        product.setCategory(category);
    }

    public void EditProductPrice(int ID, double price)
    {
        CheckValidPrice(price);
        Product product = products.get(ID);
        product.setPrice(price);
    }

    public void EditProductLocation(int ID, Location location)
    {
        Product product = products.get(ID);
        product.setLocation(location);
    }

    public void EditProductInStock(int ID)
    {
        Product product = products.get(ID);
        if (product.getInStock())
        {
            product.setInStock(false);
        }
        else
        {
            product.setInStock(true);
        }
    }

    public void DeleteProduct(int ID)
    {
        Product product = products.get(ID);
        for (List list : lists.values())
        {
            ArrayList<Product> contents = list.getItems();
            if (contents.contains(product))
            {
                // Add operation to remove product from list
                // Users should also be informed that an item has been removed from one of their lists.
            }
        }
        products.remove(ID);
    }

    // Need to make a method that updates all list properties once any of these edit methods has been carried out.

}
