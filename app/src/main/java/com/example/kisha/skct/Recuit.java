package com.example.kisha.skct;

/**
 * Created by kisha on 07-12-2016.
 */

public class Recuit
{
    String name;
    int no;

    Recuit(String name, int no)
    {
        this.name = name;
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public int getNo() {
        return no;
    }
}
