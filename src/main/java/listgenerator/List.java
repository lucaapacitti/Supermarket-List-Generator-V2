package listgenerator;

import java.util.ArrayList;

public class List
{
    private int ID;
    private String name;
    private User user;
    private double budget;
    private ArrayList<Product> items;

    public List(int ID, String name, User user, double budget)
    {
        this.ID = ID;
        this.name = name;
        this.user = user;
        this.budget = budget;
        items = new ArrayList<Product>();
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

    public double getBudget()
    {
        return budget;
    }

    public void setBudget(int budget)
    {
        this.budget = budget;
    }

    public ArrayList<Product> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Product> items)
    {
        this.items = items;
    }
}
