package listgenerator;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class ListGenerator
{
    private HashMap<Integer, Product> products;
    private HashMap<String, User> users;
    private HashMap<Integer, List> lists;
    private HashMap<Integer, PreList> preLists; // Not to be serialised.

    public ListGenerator()
    {
        this.products = new HashMap<>();
        this.users = new HashMap<>();
        this.lists = new HashMap<>();
        this.preLists = new HashMap<>();
    }

    // Getter methods for testing

    public HashMap<Integer, Product> getProducts()
    {
        return products;
    }

    public HashMap<String, User> getUsers()
    {
        return users;
    }

    public HashMap<Integer, List> getLists()
    {
        return lists;
    }

    public HashMap<Integer, PreList> getPreLists()
    {
        return preLists;
    }

    // Data structure exception handling

    public void CheckProductExists(int productID)
    throws RuntimeException
    {
        if (!products.containsKey(productID))
        {
            throw new RuntimeException("Product not found in database.");
        }
    }

    public void CheckUserExists(String username)
    throws RuntimeException
    {
        if (!users.containsKey(username))
        {
            throw new RuntimeException("User not found in database.");
        }
    }

    public void CheckListExists(int listID)
    throws RuntimeException
    {
        if (!lists.containsKey(listID))
        {
            throw new RuntimeException("List not found in database.");
        }
    }

    public void CheckPreListExists(int preListID)
    throws RuntimeException
    {
        if (!preLists.containsKey(preListID))
        {
            throw new RuntimeException("Pre-list not found in database.");
        }
    }

    // Data type exception handling

    public double CheckDoubleType(String string)
    throws NumberFormatException
    {
       try
       {
           return Double.parseDouble(string);
       }
       catch (NumberFormatException e)
       {
           throw new NumberFormatException("A numerical value was expected.");
       }
    }

    public int CheckIntType(String string)
    throws NumberFormatException
    {
       try
       {
           return Integer.parseInt(string);
       }
       catch (NumberFormatException e)
       {
           throw new NumberFormatException("An integer was expected.");
       }
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
        if (upperPresent == false || lowerPresent == false || numberPresent == false || specialPresent == false)
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

    public void CreateUser(String forename, String surname, String username, String password, String email)
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
        CheckUserExists(username);
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
        CheckUserExists(username);
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
    {
        if (price < 0.01)
        {
            throw new IllegalArgumentException("Price must be minimum £0.01");
        }
    }

    public void CreateProduct(String name, String category, String priceString)
    throws NumberFormatException, IllegalArgumentException
    {
        double price = CheckDoubleType(priceString);
        price = Math.floor(price * 100) / 100.0;
        CheckValidPrice(price);
        int productID = GetAvailableProductID();
        Product product = new Product(productID, name, category, price);
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

    public void EditProductPrice(int productID, String priceString)
    {
        double price = CheckDoubleType(priceString);
        CheckValidPrice(price);
        Product product = products.get(productID);
        product.setPrice(price);
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

    // Lists

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

    public void AddToPreList(int preListID, String productCategory, String quantityString)
    {
        int quantity = CheckIntType(quantityString);
        CheckProductCategoryExists(productCategory);
        PreList preList = preLists.get(preListID);
        HashMap<String, Integer> contents = preList.getContents();
        if (contents.get(productCategory) != null)
        {
            int newQuantity = contents.get(productCategory) + quantity;
            contents.put(productCategory, newQuantity);
        }
        else
        {
            contents.put(productCategory, quantity);
        }
    }

    public void AddPriorityInPreList(int preListID, String productCategory)
    {
        PreList preList = preLists.get(preListID);
        Queue<String> priorities = preList.getPriorities();
        if (!priorities.contains(productCategory))
        {
            priorities.add(productCategory);
        }
    }
    
    public void RemovePriorityFromPreList(int preListID, String productCategory)
    {  
        PreList preList = preLists.get(preListID);
        Queue<String> oldPriorities = preList.getPriorities();
        if (oldPriorities.contains(productCategory))
        {
            Queue<String> newPriorities = new LinkedList<>();
            for (String categoryCheck : oldPriorities)
            {
                if (!categoryCheck.equals(productCategory))
                {
                    newPriorities.add(categoryCheck);
                }
            }
            preList.setPriorities(newPriorities);
        }
    }

    public int GetPriorityQueuePosition(int preListID, String productCategory)
    {
        PreList preList = preLists.get(preListID);
        Queue<String> priorities = preList.getPriorities();
        int position = 1;
        for (String categoryCheck : priorities)
        {
            if (categoryCheck.equals(productCategory))
            {
                return position;
            }
            else
            {
                position++;
            }
        }
        return -1;
    }

    public void RemoveOneFromPreList(int preListID, String productCategory)
    {  
        PreList preList = preLists.get(preListID);
        HashMap<String, Integer> contents = preList.getContents();
        Queue<String> priorities = preList.getPriorities();
        int currentQuantity = contents.get(productCategory);
        if (currentQuantity == 1)
        {
            contents.remove(productCategory);
            if (priorities.contains(productCategory))
            {
                RemovePriorityFromPreList(preListID, productCategory);
            }
        }
        else
        {
            int newQuantity = currentQuantity - 1;
            contents.put(productCategory, newQuantity);
        }
    }

    public void RemoveAllFromPreList(int preListID, String productCategory)
    {  
        PreList preList = preLists.get(preListID);
        HashMap<String, Integer> contents = preList.getContents();
        Queue<String> priorities = preList.getPriorities();
        contents.remove(productCategory);
        if (priorities.contains(productCategory))
        {
            RemovePriorityFromPreList(preListID, productCategory);
        }
    }
    
    public int GetAvailableListID()
    {
        int availableID = 0;
        while (lists.get(availableID) != null)
        {
            availableID++;
        }
        return availableID;
    }

    public void CheckValidBudget(double budget)
    throws IllegalArgumentException
    {
        if (budget < 0.01)
        {
            throw new IllegalArgumentException("Budget must be at least £0.01");
        }
    }

    public void CreateList(String listName, String budgetString, String username)
    {
        double budget = CheckDoubleType(budgetString);
        budget = Math.floor(budget * 100) / 100.0;
        CheckValidBudget(budget);
        int listID = GetAvailableListID();
        User user = users.get(username);
        List list = new List(listID, listName, user, budget);
        lists.put(listID, list);
    }
    
    public double GetListCost(int listID)
    {
        List list = lists.get(listID);
        ArrayList<Product> products = list.getItems();
        double cost = 0.00;
        for (Product product : products)
        {
            cost = cost + product.getPrice();
        }
        return cost;
    }

    public double GetListSavings(int listID)
    {
        List list = lists.get(listID);
        double listCost = GetListCost(listID);
        double budget = list.getBudget();
        return budget - listCost;
    }

    public void GenerateList(int listID, int preListID)
    {
        PreList preList = preLists.get(preListID);
        List list = lists.get(listID);
        ArrayList<Product> listProducts = list.getItems();
        HashMap<String, Integer> preListCategories = preList.getContents();
        for (String category : preListCategories.keySet())
        {
            Product toAdd = new Product(-1, "Placeholder", "", 999999999.99);
            for (Product product : products.values())
            {
                if (product.getCategory().equals(category) && product.getPrice() < toAdd.getPrice() && product.getInStock())
                {
                    toAdd = product;
                }
            }
            if (toAdd.getID() != -1)
            {
                int quantity = preListCategories.get(category);
                for (int i = 0; i < quantity; i++)
                {
                    listProducts.add(toAdd);
                }
            }
        }
        if (GetListSavings(listID) > 0.00)
        {
            Queue<String> priorities = preList.getPriorities();
            for (String category : priorities)
            {
                Product productInList = null;
                int quantityInList = 0;
                for (Product product : listProducts)
                {
                    if (product.getCategory().equals(category))
                    {
                        productInList = product;
                        quantityInList++;
                    }
                }
                if (productInList != null && quantityInList > 0)
                {
                    Product toAdd = new Product(-1, "Placeholder", "", 999999999.99);
                    for (Product product : products.values())
                    {
                        if (product.getID() != productInList.getID() && product.getCategory().equals(category) && product.getPrice() > productInList.getPrice() && product.getInStock())
                        {
                            if (product.getPrice() < toAdd.getPrice())
                            {
                                toAdd = product;
                            }
                        }
                    }
                    if (toAdd.getID() != -1)
                    {
                        double productPriceDifference = toAdd.getPrice() - productInList.getPrice();
                        int quantityToAdd = quantityInList;
                        for (int i = 1; i <= quantityInList; i++)
                        {
                            if (GetListCost(listID) + (productPriceDifference * i) > list.getBudget())
                            {
                                quantityToAdd = i - 1;
                                break;
                            }
                        }
                        for (int i = 0; i < quantityToAdd; i++)
                        {
                            listProducts.remove(productInList);
                            listProducts.add(toAdd);
                        }
                    }
                }
            }
        }
        if (GetListSavings(listID) > 0.00)
        {
            boolean anyChangesMade = true;
            while (anyChangesMade)
            {
                anyChangesMade = false;
                for (Product productInList : listProducts)
                {
                    String category = productInList.getCategory();
                    Product toAdd = new Product(-1, "Placeholder", "", 999999999.99);
                    for (Product product : products.values())
                    {
                        if (product.getID() != productInList.getID() && product.getCategory().equals(category) && product.getPrice() > productInList.getPrice() && product.getInStock())
                        {
                            if (product.getPrice() < toAdd.getPrice())
                            {
                                toAdd = product;
                            }
                        }
                    }
                    if (toAdd.getID() != -1)
                    {
                        double productPriceDifference = toAdd.getPrice() - productInList.getPrice();
                        if (GetListSavings(listID) + productPriceDifference <= list.getBudget())
                        {
                            anyChangesMade = true;
                            listProducts.remove(productInList);
                            listProducts.add(toAdd);
                        }
                    }
                }
            }
        }
    }

    public void AddProductToList(int listID, int productID, String quantityString)
    {
        int quantity = CheckIntType(quantityString);
        List list = lists.get(listID);
        ArrayList<Product> listProducts = list.getItems();
        Product product = products.get(productID);
        for (int i = 0; i < quantity; i++)
        {
            listProducts.add(product);
        }
    }

    public void RemoveOneProductInstanceFromList(int listID, int productID)
    {
        List list = lists.get(listID);
        ArrayList<Product> listProducts = list.getItems();
        Product product = products.get(productID);
        listProducts.remove(product);
    }

    public void RemoveAllProductInstancesFromList(int listID, int productID)
    {
        List list = lists.get(listID);
        ArrayList<Product> listProducts = list.getItems();
        Product product = products.get(productID);
        while (listProducts.remove(product)) {};
    }

    public void DeleteList(int listID)
    {
        lists.remove(listID);
    }

    public void EditListName(int listID, String name)
    {
        List list = lists.get(listID);
        list.setName(name);
    }

    public void SortProductsByAscPrice(int listID)
    {
        List list = lists.get(listID);
        ArrayList<Product> listProducts = list.getItems();
        for (int i = 1; i < listProducts.size(); i++)
        {
            Product productToCompare = listProducts.get(i);
            double priceToCompare = productToCompare.getPrice();
            int j = i - 1;
            while (j >= 0 && listProducts.get(j).getPrice() > priceToCompare)
            {
                listProducts.set(j + 1, listProducts.get(j));
                j--;
            }
            listProducts.set(j + 1, productToCompare);
        }
    }

    public void SortProductsByDescPrice(int listID)
    {
        List list = lists.get(listID);
        ArrayList<Product> listProducts = list.getItems();
        for (int i = 1; i < listProducts.size(); i++)
        {
            Product productToCompare = listProducts.get(i);
            double priceToCompare = productToCompare.getPrice();
            int j = i - 1;
            while (j >= 0 && listProducts.get(j).getPrice() < priceToCompare)
            {
                listProducts.set(j + 1, listProducts.get(j));
                j--;
            }
            listProducts.set(j + 1, productToCompare);
        }
    }

    public void SortProductsAlphabetically(int listID)
    {
        List list = lists.get(listID);
        ArrayList<Product> listProducts = list.getItems();
        for (int i = 1; i < listProducts.size(); i++)
        {
            Product productToCompare = listProducts.get(i);
            String nameToCompare = productToCompare.getName();
            int j = i - 1;
            while (j >= 0 && nameToCompare.compareToIgnoreCase(listProducts.get(j).getName()) < 0)
            {
                listProducts.set(j + 1, listProducts.get(j));
                j--;
            }
            listProducts.set(j + 1, productToCompare);
        }
    }

    // Method to sort list by shortest path (Default)
    
    // Method to serialise data structures
    // Method to deserialise data structures

}
