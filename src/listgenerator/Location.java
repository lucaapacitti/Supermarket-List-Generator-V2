package listgenerator;

public class Location
{
    private int ID;
    private String name;

    // No constructor as no new locations should be made.

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

