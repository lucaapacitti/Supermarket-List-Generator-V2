package listgenerator;

public class List
{
    private int ID;
    private String name;
    private User user;
    private ArrayList<Product> items;

    public List(int ID, String name, User user)
    {
        this.ID = ID;
        this.name = name;
        this.user = user;
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

    public ArrayList<Product> getItems()
    {
        return items;
    }

    public void setItems(ArrayList<Product> items)
    {
        this.items = items;
    }
}
