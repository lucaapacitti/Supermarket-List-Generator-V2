package listgenerator;

public class Product
{
    private int ID;
    private String name;
    private String category;
    private double price;
    private Location location;
    private boolean inStock;

    public Product(int ID, String name, String category, double price, Location location)
    {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.price = price;
        this.location = location;
        inStock = true;
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

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public boolean getInStock()
    {
        return inStock;
    }

    public void setInStock(boolean inStock)
    {
        this.inStock = inStock;
    }
}
