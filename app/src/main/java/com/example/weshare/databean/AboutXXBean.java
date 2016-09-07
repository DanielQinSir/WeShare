package com.example.weshare.databean;

import java.util.List;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-07
 * Time: 16:30
 * For:
 */

public class AboutXXBean
{

    /**
     * pic : ["http://cdn.xxiang365.com/uploadfile/apk/2016/03/03/1456990215.jpg","http://cdn.xxiang365.com/uploadfile/apk/2016/03/03/1456990219.jpg","http://cdn.xxiang365.com/uploadfile/apk/2016/03/03/1456990223.jpg","http://cdn.xxiang365.com/uploadfile/apk/2016/03/03/1456990228.jpg","http://cdn.xxiang365.com/uploadfile/apk/2016/03/03/1456990232.jpg","http://cdn.xxiang365.com/uploadfile/apk/2016/03/03/1456990237.jpg"]
     * length : 6
     * succeed : 1
     */

    private int length;
    private int succeed;
    private List<String> pic;

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public int getSucceed()
    {
        return succeed;
    }

    public void setSucceed(int succeed)
    {
        this.succeed = succeed;
    }

    public List<String> getPic()
    {
        return pic;
    }

    public void setPic(List<String> pic)
    {
        this.pic = pic;
    }
}
