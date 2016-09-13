package com.example.weshare.databean;

import java.util.List;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-06
 * Time: 16:56
 * For:
 */

public class ClubBean
{

    /**
     * ad : [{"pic":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/08/24/1472002662.jpg","tid":"2228"},{"pic":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/07/12/1468293512.jpg","tid":"2148"},{"pic":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/07/04/1467593693.jpg","tid":"2113"},{"pic":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/06/08/1465386935.jpg","tid":"2062"},{"pic":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/06/08/1465385776.jpg","tid":"2061"}]
     * ad_num : 5
     * module : [{"id":"1","module_name":"官方通告","ic_launcher":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/06/08/1465384725.jpg","desc":"号外号外！享享新闻、动态、活动通知，都在这里公布哦~"},{"id":"2","module_name":"show菜","ic_launcher":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/06/08/1465384835.jpg","desc":"用放心食材，做安心饭菜，秀出你的厨艺，一起聊聊厨房与美食。"},{"id":"8","module_name":"线下活动","ic_launcher":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/07/01/1467340736.png","desc":"与享享一起畅游寻鲜，活动通知、报名、行程和游记都在这里汇总~"},{"id":"7","module_name":"吐槽吧","ic_launcher":"http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/06/08/1465384858.jpg","desc":"意见建议想说就说，开心不开心都来吐槽，享享有你才会更好~"}]
     * module_num : 4
     * succeed : 1
     */

    private int ad_num;
    private int module_num;
    private int succeed;
    /**
     * pic : http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/08/24/1472002662.jpg
     * tid : 2228
     */

    private List<AdBean> ad;
    /**
     * id : 1
     * module_name : 官方通告
     * ic_launcher : http://cdn.xxiang365.com/uploadfile/product/forumnew/2016/06/08/1465384725.jpg
     * desc : 号外号外！享享新闻、动态、活动通知，都在这里公布哦~
     */

    private List<ModuleBean> module;

    public int getAd_num()
    {
        return ad_num;
    }

    public void setAd_num(int ad_num)
    {
        this.ad_num = ad_num;
    }

    public int getModule_num()
    {
        return module_num;
    }

    public void setModule_num(int module_num)
    {
        this.module_num = module_num;
    }

    public int getSucceed()
    {
        return succeed;
    }

    public void setSucceed(int succeed)
    {
        this.succeed = succeed;
    }

    public List<AdBean> getAd()
    {
        return ad;
    }

    public void setAd(List<AdBean> ad)
    {
        this.ad = ad;
    }

    public List<ModuleBean> getModule()
    {
        return module;
    }

    public void setModule(List<ModuleBean> module)
    {
        this.module = module;
    }

    public static class AdBean
    {

        private String pic;
        private String tid;

        public String getPic()
        {
            return pic;
        }

        public void setPic(String pic)
        {
            this.pic = pic;
        }

        public String getTid()
        {
            return tid;
        }

        public void setTid(String tid)
        {
            this.tid = tid;
        }
    }

    public static class ModuleBean
    {

        private String id;
        private String module_name;
        private String logo;
        private String desc;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getModule_name()
        {
            return module_name;
        }

        public void setModule_name(String module_name)
        {
            this.module_name = module_name;
        }

        public String getLogo()
        {
            return logo;
        }

        public void setLogo(String logo)
        {
            this.logo = logo;
        }

        public String getDesc()
        {
            return desc;
        }

        public void setDesc(String desc)
        {
            this.desc = desc;
        }
    }
}
