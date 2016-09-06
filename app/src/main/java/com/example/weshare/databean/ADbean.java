package com.example.weshare.databean;

import java.io.Serializable;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-06
 * Time: 17:13
 * For:
 */

public class ADbean implements Serializable
{

    private String picurl;
    private String id;

    public ADbean(String picurl, String id)
    {
        this.picurl = picurl;
        this.id = id;
    }

    public String getPicurl()
    {
        return picurl;
    }

    public void setPicurl(String picurl)
    {
        this.picurl = picurl;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
