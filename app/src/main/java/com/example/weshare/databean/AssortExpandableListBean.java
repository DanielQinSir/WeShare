package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class AssortExpandableListBean {

    private ListBean list;
    /**
     * list : {"first":[{"catid":"1000","name":"时鲜蔬菜","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/11/15/1447586710.jpg","picNew":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/29/1461897008.png"},{"catid":"1001","name":"时令水果","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/11/15/1447586724.jpg","picNew":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/29/1461896980.png"},{"catid":"1002","name":"粮油副食","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/11/15/1447586737.jpg","picNew":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/29/1461897041.png"},{"catid":"1003","name":"禽肉蛋豆","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/11/15/1447586758.jpg","picNew":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/29/1461897074.png"},{"catid":"1004","name":"坚果炒货","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/01/21/1453376366.jpg","picNew":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/29/1461897090.png"}],"second":[{"catid":"100001","name":"豆类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435830045.jpg","index":"1000"},{"catid":"100002","name":"根茎类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435830023.jpg","index":"1000"},{"catid":"100003","name":"瓜果类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435828909.jpg","index":"1000"},{"catid":"100004","name":"结球类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435828942.jpg","index":"1000"},{"catid":"100006","name":"叶菜类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435829779.jpg","index":"1000"},{"catid":"100007","name":"葱姜蒜","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435829808.jpg","index":"1000"},{"catid":"100008","name":"菌菇类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435829833.jpg","index":"1000"},{"catid":"100108","name":"桔柚类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/28/1461812197.jpg","index":"1001"},{"catid":"100109","name":"进口水果","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/28/1461811743.jpg","index":"1001"},{"catid":"100110","name":"苹果类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/12/30/1451465818.jpg","index":"1001"},{"catid":"100111","name":"梨类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/19/1461048534.jpg","index":"1001"},{"catid":"100112","name":"浆果类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/12/30/1451464853.jpg","index":"1001"},{"catid":"100113","name":"热带水果","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/12/30/1451464892.jpg","index":"1001"},{"catid":"100115","name":"瓜类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/11/1460362044.png","index":"1001"},{"catid":"100116","name":"桃李类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/04/13/1460536801.jpg","index":"1001"},{"catid":"100201","name":"南北干货","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/01/21/1421820594.jpg","index":"1002"},{"catid":"100202","name":"五谷杂粮","pic":"http://cdn.xxiang365.com/uploadfile/cat/2014/12/24/1419403835.jpg","index":"1002"},{"catid":"100203","name":"厨房调味","pic":"http://cdn.xxiang365.com/uploadfile/cat/2014/12/24/1419403931.jpg","index":"1002"},{"catid":"100204","name":"米面","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/01/04/1420364283.jpg","index":"1002"},{"catid":"100205","name":"食用油","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/04/03/1428029467.jpg","index":"1002"},{"catid":"100207","name":"茶干","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/03/16/1458104411.jpg","index":"1002"},{"catid":"100208","name":"酱","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/03/16/1458104452.jpg","index":"1002"},{"catid":"100209","name":"小菜","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/03/16/1458104498.jpg","index":"1002"},{"catid":"100210","name":"休闲食品","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/06/13/1465799374.jpg","index":"1002"},{"catid":"100211","name":"臻品礼盒","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/06/23/1466671002.jpg","index":"1002"},{"catid":"100212","name":"罐头","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/08/12/1470966591.png","index":"1002"},{"catid":"100213","name":"冲调","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/08/12/1470967001.png","index":"1002"},{"catid":"100301","name":"猪肉类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435829934.jpg","index":"1003"},{"catid":"100302","name":"家禽类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435829954.jpg","index":"1003"},{"catid":"100303","name":"蛋类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/01/21/1421821238.jpg","index":"1003"},{"catid":"100306","name":"豆制品","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/03/23/1427088104.jpg","index":"1003"},{"catid":"100307","name":"牛肉类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/12/07/1449457700.jpg","index":"1003"},{"catid":"100308","name":"羊肉类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2015/12/07/1449471802.jpg","index":"1003"},{"catid":"100309","name":"水产类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/01/08/1452243082.jpg","index":"1003"},{"catid":"100401","name":"坚果类","pic":"http://cdn.xxiang365.com/uploadfile/cat/2016/01/21/1453376399.jpg","index":"1004"}]}
     * length : 5
     * succeed : 1
     */

    private int length;
    private int succeed;

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }

    public static class ListBean {
        /**
         * catid : 1000
         * name : 时鲜蔬菜
         * pic : http://cdn.xxiang365.com/uploadfile/cat/2015/11/15/1447586710.jpg
         * picNew : http://cdn.xxiang365.com/uploadfile/cat/2016/04/29/1461897008.png
         */

        private List<FirstBean> first;
        /**
         * catid : 100001
         * name : 豆类
         * pic : http://cdn.xxiang365.com/uploadfile/cat/2015/07/02/1435830045.jpg
         * index : 1000
         */

        private List<SecondBean> second;

        public List<FirstBean> getFirst() {
            return first;
        }

        public void setFirst(List<FirstBean> first) {
            this.first = first;
        }

        public List<SecondBean> getSecond() {
            return second;
        }

        public void setSecond(List<SecondBean> second) {
            this.second = second;
        }

        public static class FirstBean {
            private String catid;
            private String name;
            private String pic;
            private String picNew;

            public String getCatid() {
                return catid;
            }

            public void setCatid(String catid) {
                this.catid = catid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPicNew() {
                return picNew;
            }

            public void setPicNew(String picNew) {
                this.picNew = picNew;
            }
        }

        public static class SecondBean {
            private String catid;
            private String name;
            private String pic;
            private String index;

            public String getCatid() {
                return catid;
            }

            public void setCatid(String catid) {
                this.catid = catid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getIndex() {
                return index;
            }

            public void setIndex(String index) {
                this.index = index;
            }
        }
    }
}
