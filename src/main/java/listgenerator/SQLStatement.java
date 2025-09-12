package listgenerator;

import java.util.ArrayList;

public class SQLStatement
{
    private String sql;
    private ArrayList<Object> parameters;

    public SQLStatement(String sql, ArrayList<Object> parameters)
    {
        this.sql = sql;
        this.parameters = parameters;
    }

    public String getSQL()
    {
        return sql;
    }

    public ArrayList<Object> getParameters()
    {
        return parameters;
    }
}
