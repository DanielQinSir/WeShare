package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class AssortExpandableListOneGoodBean {

    /**
     * goodslist : [{"id":"469","pname":"会理石榴 约700g-800g","market_price":"9.98","price":"7.98","price_desc":"","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/11/09/1447056334.jpg","member_name":"四川","stock":"99999"},{"id":"1038","pname":"会理石榴礼盒  约5kg","market_price":"73.90","price":"59.80","price_desc":"","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/05/1473061472.jpg","member_name":"四川会理","stock":"99998"},{"id":"669","pname":"国产柠檬 2个","market_price":"5.80","price":"3.50","price_desc":"","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/03/04/1457082407.jpg","member_name":"湖北","stock":"99998"},{"id":"217","pname":"柚子 1个约1000g-1200g","market_price":"9.88","price":"7.98","price_desc":"","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/07/1449473961.jpg","member_name":"广东","stock":"99999"},{"id":"1016","pname":"青桔 约400g-500g","market_price":"5.90","price":"2.99","price_desc":"","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/26/1472203612.jpg","member_name":"","stock":"99987"}]
     * index : 0
     * total : 5
     * is_catlist : 0
     * succeed : 1
     */

    private int index;
    private int total;
    private int is_catlist;
    private int succeed;
    /**
     * id : 469
     * pname : 会理石榴 约700g-800g
     * market_price : 9.98
     * price : 7.98
     * price_desc :
     * pic : http://cdn.xxiang365.com/uploadfile/product/1/2015/11/09/1447056334.jpg
     * member_name : 四川
     * stock : 99999
     */

    private List<GoodslistBean> goodslist;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIs_catlist() {
        return is_catlist;
    }

    public void setIs_catlist(int is_catlist) {
        this.is_catlist = is_catlist;
    }

    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }

    public List<GoodslistBean> getGoodslist() {
        return goodslist;
    }

    public void setGoodslist(List<GoodslistBean> goodslist) {
        this.goodslist = goodslist;
    }

    public static class GoodslistBean {
        private String id;
        private String pname;
        private String market_price;
        private String price;
        private String price_desc;
        private String pic;
        private String member_name;
        private String stock;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice_desc() {
            return price_desc;
        }

        public void setPrice_desc(String price_desc) {
            this.price_desc = price_desc;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getMember_name() {
            return member_name;
        }

        public void setMember_name(String member_name) {
            this.member_name = member_name;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }
    }
}
