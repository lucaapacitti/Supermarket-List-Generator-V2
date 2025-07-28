package listgenerator;

import java.util.ArrayList;

public class PreList
{
    private int ID;
    private ArrayList<String> contents;
    private ArrayList<Integer> priorities; 

    public PreList(int ID)
    {
        this.ID = ID;
        contents = new ArrayList<>();
        priorities = new ArrayList<>();
    }

    public int getID()
    {
        return ID;
    }

    public ArrayList<String> getContents()
    {
        return contents;
    }

    public void setContents(ArrayList<String> contents)
    {
        this.contents = contents;
    }

    public ArrayList<Integer> getPriorities()
    {
        return priorities;
    }

    public void setPriorities(ArrayList<Integer> priorities)
    {
        this.priorities = priorities;
    }
}
