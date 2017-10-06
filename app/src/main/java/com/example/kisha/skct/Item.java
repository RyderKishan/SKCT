package com.example.kisha.skct;

/**
 * Created by kisha on 15-10-2016.
 */

public class Item
{
    String name, no;

    public Item(String name, String no)
    {
        this.name = name;
        this.no = no;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNo(String no)
    {
        this.no = no;
    }

    public String getName()
    {
        return name;
    }

    public String getNo()
    {
        return no;
    }
}
