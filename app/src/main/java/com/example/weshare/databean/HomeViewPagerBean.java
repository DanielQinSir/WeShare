package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class HomeViewPagerBean {

    /**
     * pic : http://cdn.xxiang365.com/uploadfile/adv/2016/09/07/1473217000.jpg
     * catid : 0
     * catname : null
     * product_id : 0
     * product_name : null
     * activityid : 0
     * special_areaid : 75
     * forumtype : 0
     * forum_name : null
     */

    private List<AdvsBean> advs;

    public List<AdvsBean> getAdvs() {
        return advs;
    }

    public void setAdvs(List<AdvsBean> advs) {
        this.advs = advs;
    }

    public static class AdvsBean {
        private String pic;
        private String catid;
        private Object catname;
        private String product_id;
        private Object product_name;
        private String activityid;
        private String special_areaid;
        private String forumtype;
        private Object forum_name;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public Object getCatname() {
            return catname;
        }

        public void setCatname(Object catname) {
            this.catname = catname;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public Object getProduct_name() {
            return product_name;
        }

        public void setProduct_name(Object product_name) {
            this.product_name = product_name;
        }

        public String getActivityid() {
            return activityid;
        }

        public void setActivityid(String activityid) {
            this.activityid = activityid;
        }

        public String getSpecial_areaid() {
            return special_areaid;
        }

        public void setSpecial_areaid(String special_areaid) {
            this.special_areaid = special_areaid;
        }

        public String getForumtype() {
            return forumtype;
        }

        public void setForumtype(String forumtype) {
            this.forumtype = forumtype;
        }

        public Object getForum_name() {
            return forum_name;
        }

        public void setForum_name(Object forum_name) {
            this.forum_name = forum_name;
        }
    }
}
