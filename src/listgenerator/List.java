package listgenerator;

import java.util.ArrayList;

public class List
{
    private int ID;
    private String name;
    private User user;
    private ArrayList<Product> items;
    private Store store;

    public List(int ID, String name, User user, Store store)
    {
        this.ID = ID;
        this.name = name;
        this.user = user;
        items = new ArrayList<Product>();
        this.store = store;
    }

    public int getID()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public User getUser()
    {
        return user;
    }

    public ArrayList<Product> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Product> items)
    {
        this.items = items;
    }

    public Store getStore()
    {
        return store;
    }

    public void setStore(Store store)
    {
        this.store = store;
    }
}
