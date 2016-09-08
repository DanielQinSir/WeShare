package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class HomeListBean {

    /**
     * type : 2
     * adv_pic : http://cdn.xxiang365.com/uploadfile/special_area/2016/09/06/1473124913.jpg
     * id : 64
     * name : 秋季蒸菜
     */

    private List<ThemeAdBean> theme_ad;

    public List<ThemeAdBean> getTheme_ad() {
        return theme_ad;
    }

    public void setTheme_ad(List<ThemeAdBean> theme_ad) {
        this.theme_ad = theme_ad;
    }

    public static class ThemeAdBean {
        private String type;
        private String adv_pic;
        private String id;
        private String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAdv_pic() {
            return adv_pic;
        }

        public void setAdv_pic(String adv_pic) {
            this.adv_pic = adv_pic;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
