package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class HomeRecyclerViewSecondADBean {

    /**
     * local_sale : [{"id":"268","pname":"藕 约450g-600g","price":"2.99","market_price":"5.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/11/12/1447326466.jpg","member_name":"安徽","stock":"99983"},{"id":"49","pname":"蒜头 约200g","price":"2.98","market_price":"4.98","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2014/12/04/1417671949.jpg","member_name":"合肥","stock":"99984"},{"id":"1016","pname":"青桔 约400g-500g","price":"2.99","market_price":"5.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/26/1472203612.jpg","member_name":"","stock":"99991"},{"id":"591","pname":"和喜来土鸡蛋 10个","price":"25.00","market_price":"28.00","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/01/27/1453877002.jpg","member_name":"宣城","stock":"5"},{"id":"1003","pname":"将军寨 菜籽油5L","price":"79.00","market_price":"109.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472110212.png","member_name":"霍山","stock":"1"},{"id":"695","pname":"金龙鱼优质东北大米 5kg ","price":"35.00","market_price":"49.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/03/11/1457677828.jpg","member_name":"东北","stock":"3"}]
     * total : 6
     * index : 0
     * length : 6
     * succeed : 1
     */

    private int total;
    private int index;
    private int length;
    private int succeed;
    /**
     * id : 268
     * pname : 藕 约450g-600g
     * price : 2.99
     * market_price : 5.80
     * pic : http://cdn.xxiang365.com/uploadfile/product/1/2015/11/12/1447326466.jpg
     * member_name : 安徽
     * stock : 99983
     */

    private List<LocalSaleBean> local_sale;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public List<LocalSaleBean> getLocal_sale() {
        return local_sale;
    }

    public void setLocal_sale(List<LocalSaleBean> local_sale) {
        this.local_sale = local_sale;
    }

    public static class LocalSaleBean {
        private String id;
        private String pname;
        private String price;
        private String market_price;
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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
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
