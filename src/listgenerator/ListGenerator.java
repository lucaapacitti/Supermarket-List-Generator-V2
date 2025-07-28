package listgenerator;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;

public class ListGenerator
{
    private HashMap<Integer, Product> products;
    private HashMap<String, User> users;
    private HashMap<Integer, List> lists;
    private HashMap<Integer, Store> stores;
    private HashMap<Integer, Location> locations;
    private HashMap<Integer, PreList> preLists; // Not to be serialised.

    public ListGenerator()
    {
        this.products = new HashMap<>();
        this.users = new HashMap<>();
        this.lists = new HashMap<>();
        this.stores = new HashMap<>();
        this.locations = new HashMap<>();
        this.preLists = new HashMap<>();
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

    public int GetAvailableProductID()
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
        int productID = GetAvailableProductID();
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

    public void EditProductCategory(int productID, String category)
    {
        Product product = products.get(productID);
        product.setCategory(category);
    }

    public void EditProductPrice(int productID, double price)
    {
        CheckValidPrice(price);
        Product product = products.get(productID);
        product.setPrice(price);
    }

    public void EditProductLocation(int productID, int locationID)
    {
        CheckValidLocation(locationID);
        Location location = locations.get(locationID);
        Product product = products.get(productID);
        product.setLocation(location);
    }

    public void EditProductInStock(int productID)
    {
        Product product = products.get(productID);
        if (product.getInStock())
        {
            product.setInStock(false);
            for (List list : lists.values())
            {
                ArrayList<Product> contents = list.getItems();
                if (contents.contains(product))
                {
                    int quantity = GetProductQuantityInList(productID, list.getID());
                    RemoveProductFromList(productID, list.getID());
                    User listOwner = list.getUser();
                    ArrayList<String> messages = listOwner.getMessages();
                    String message = "The product " + product.getName() + " is out of stock and has been removed from your list.";
                    messages.add(message);
                    listOwner.setMessages(messages);
                    AutoProductReplacement(productID, list.getID(), quantity);
                }
            }
        }
        else
        {
            product.setInStock(true);
        }
    }

    public void DeleteProduct(int productID)
    {
        Product product = products.get(productID);
        for (List list : lists.values())
        {
            ArrayList<Product> contents = list.getItems();
            if (contents.contains(product))
            {
                int listID = list.getID();
                int quantity = GetProductQuantityInList(productID, listID);
                RemoveProductFromList(productID, listID);
                User listOwner = list.getUser();
                ArrayList<String> messages = listOwner.getMessages();
                String message = "The product " + product.getName() + " no longer exists and has been removed from your list " + list.getName() + ".";
                messages.add(message);
                listOwner.setMessages(messages);
                AutoProductReplacement(productID, listID, quantity);
            }
        }
        products.remove(productID);
    }

    public int GetProductQuantityInList(int productID, int listID)
    {
        Product product = products.get(productID);
        List list = lists.get(listID);
        ArrayList<Product> contents = list.getItems();
        int quantity = 0;
        while (contents.contains(product))
        {
            quantity++;
        }
        return quantity;
    }

    public void RemoveProductFromList(int productID, int listID)
    {
        Product product = products.get(productID);
        List list = lists.get(listID);
        ArrayList<Product> contents = list.getItems();
        while (contents.contains(product))
        {
            contents.remove(product);
        }
        list.setItems(contents);
    }

    public void AutoProductReplacement(int oldProductID, int listID, int quantity)
    {
        Product oldProduct = products.get(oldProductID);
        int arrSize = 0;
        for (Product product : products.values())
        {
            if (product.getCategory().equals(oldProduct.getCategory()) && product.getID() != oldProductID)
            {
                arrSize++;
            }
        }
        if (arrSize != 0)
        {
            Product[] potentialReplacements = new Product[arrSize];
            double oldPrice = oldProduct.getPrice();
            Product replacement = potentialReplacements[0];
            for (int i = 1; i < potentialReplacements.length; i++)
            {
                if (potentialReplacements[i].getPrice() <= oldPrice)
                {
                    if (potentialReplacements[i].getPrice() > replacement.getPrice())
                    {
                        replacement = potentialReplacements[i];
                    }
                }
            }
            List list = lists.get(listID);
            ArrayList<Product> contents = list.getItems();
            for (int i = 0; i < quantity; i++)
            {
                contents.add(replacement);
            }
            list.setItems(contents);
            User listOwner = list.getUser();
            ArrayList<String> messages = listOwner.getMessages();
            messages.add("Product " + oldProduct.getName() + " has been replaced with " + replacement.getName() + " in your list " + list.getName() + ".");
            listOwner.setMessages(messages);
        }
    }

    // Location

    public void CheckValidLocation(int locationID)
    {
        boolean found = false;
        Location location = locations.get(locationID);
        for (Location locationCheck : locations.values())
        {
            if (locationCheck == location)
            {
                found = true;
                break;
            }
        }
        if (!found)
        {
            throw new RuntimeException("Location does not exist.");
        }
    }

    public String GetLocationName(int locationID)
    {
        Location location = locations.get(locationID);
        return location.getName();
    }

    // Stores (come back to this)

    // Lists

    // Method that adds product categories to pre-list, and in what quantity
    // Method that removes product categories from pre-list

    public void CheckProductCategoryExists(String productCategory)
    throws RuntimeException
    {
        boolean found = false;
        String[] productCategories = GetAllProductCategories();
        for (String category : productCategories)
        {
            if (category.equals(productCategory))
            {
                found = true;
                break;
            }
        }
        if (!found)
        {
            throw new RuntimeException("No products assigned to that product category.");
        }
    }

    public int GetAvailablePreListID()
    {
        int availableID = 0;
        while (preLists.get(availableID) != null)
        {
            availableID++;
        }
        return availableID;
    }

    public void CreatePreList()
    {
        int newID = GetAvailablePreListID();
        PreList preList = new PreList(newID);
        preLists.put(newID, preList);
    }

    public void AddToPreList(int preListID, String productCategory, int quantity)
    {
        CheckProductCategoryExists(productCategory);
        PreList preList = preLists.get(preListID);
        ArrayList<String> contents = preList.getContents();
        ArrayList<Integer> priorities = preList.getPriorities();
        for (int i = 0; i < quantity; i++)
        {
            contents.add(productCategory);
            priorities.add(0);
        }
        preList.setContents(contents);
        preList.setPriorities(priorities);
    }

    // Method to give priority
    // Method to remove priority and rearrange existing ones if needed
    // Method to remove items and rearrange existing priorities if needed
    // Method to insert ArrayList of priorities into a Queue
    
    public int GetAvailableListID()
    {
        int availableID = 0;
        while (lists.get(availableID) != null)
        {
            availableID++;
        }
        return availableID;
    }

    // Method to initialise list
    // Method to generate list from pre-list

}
