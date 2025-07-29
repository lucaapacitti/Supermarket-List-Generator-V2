package listgenerator;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class PreList
{
    private int ID;
    private ArrayList<String> contents;
    private Queue<Integer> priorities; 

    public PreList(int ID)
    {
        this.ID = ID;
        contents = new ArrayList<>();
        priorities = new LinkedList<>();
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

    public Queue<Integer> getPriorities()
    {
        return priorities;
    }

    public void setPriorities(Queue<Integer> priorities)
    {
        this.priorities = priorities;
    }
}
