package listgenerator;

import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class PreList
{
    private int ID;
    private HashMap<String, Integer> contents;
    private Queue<String> priorities; 

    public PreList(int ID)
    {
        this.ID = ID;
        contents = new HashMap<>();
        priorities = new LinkedList<>();
    }

    public int getID()
    {
        return ID;
    }

    public HashMap<String, Integer> getContents()
    {
        return contents;
    }

    public void setContents(HashMap<String, Integer> contents)
    {
        this.contents = contents;
    }

    public Queue<String> getPriorities()
    {
        return priorities;
    }

    public void setPriorities(Queue<String> priorities)
    {
        this.priorities = priorities;
    }
}
