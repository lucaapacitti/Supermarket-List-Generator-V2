package listgenerator;

public class Location
{
    private int ID;
    private String name;

    // No constructor as no new locations should be made.
    // The layout may vary with different stores, but the total number of locations should remain constant.

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
}

