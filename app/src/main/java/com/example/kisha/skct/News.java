package com.example.kisha.skct;

/**
 * Created by kisha on 08-12-2016.
 */

public class News
{
    String heading, content;

    News(String heading, String content)
    {
        this.heading = heading;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public String getHeading() {
        return heading;
    }
}
