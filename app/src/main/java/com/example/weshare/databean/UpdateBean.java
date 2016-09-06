package com.example.weshare.databean;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-06
 * Time: 10:35
 * For:
 */

public class UpdateBean
{

    /**
     * apkVersion : 2.7.11
     * apkDownloadUrl : http://cdn.xxiang365.com/app/StartActivity.apk
     * apkName : StartActivity
     * apkVerCode : 39
     * apkContent : 1.性能优化
     */

    private String apkVersion;
    private String apkDownloadUrl;
    private String apkName;
    private int apkVerCode;
    private String apkContent;

    public String getApkVersion()
    {
        return apkVersion;
    }

    public void setApkVersion(String apkVersion)
    {
        this.apkVersion = apkVersion;
    }

    public String getApkDownloadUrl()
    {
        return apkDownloadUrl;
    }

    public void setApkDownloadUrl(String apkDownloadUrl)
    {
        this.apkDownloadUrl = apkDownloadUrl;
    }

    public String getApkName()
    {
        return apkName;
    }

    public void setApkName(String apkName)
    {
        this.apkName = apkName;
    }

    public int getApkVerCode()
    {
        return apkVerCode;
    }

    public void setApkVerCode(int apkVerCode)
    {
        this.apkVerCode = apkVerCode;
    }

    public String getApkContent()
    {
        return apkContent;
    }

    public void setApkContent(String apkContent)
    {
        this.apkContent = apkContent;
    }
}
