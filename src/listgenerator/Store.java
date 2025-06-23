package listgenerator;

public class Store
{
    private int ID;
    private String name;
    private int[][] layout;

    public Store(int ID, String name, int totalLocations, int[][] layout)
    {
        this.ID = ID;
        this.name = name;
        this.layout = layout;
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

    public int[][] getLayout()
    {
        return layout;
    }

    public void setLayout(int[][] layout)
    {
        this.layout = layout;
    }
}
