package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class HomeRecyclerViewLogoBean {

    /**
     * logo : http://cdn.xxiang365.com/uploadfile/special_area/local_sale/2016/06/09/1465452350.png
     * name : 原产地直供
     * product : [{"id":"760","pname":"万佛湖有机鱼身 约1000g ","price":"26.80","market_price":"49.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/04/26/1461638497.jpg","member_name":"万佛湖","stock":"985"},{"id":"761","pname":"万佛湖有机鱼头 约1000g","price":"36.80","market_price":"69.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/04/26/1461638593.jpg","member_name":"万佛湖","stock":"986"},{"id":"765","pname":"万佛湖有机鱼（整条） 约2000g","price":"59.80","market_price":"98.00","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/04/26/1461638682.jpg","member_name":"万佛湖","stock":"998"},{"id":"768","pname":"银屏山土鸡蛋 10枚","price":"15.80","market_price":"19.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/04/09/1460209558.jpg","member_name":"巢湖","stock":"2"},{"id":"762","pname":"焦岗湖咸鸭蛋 6枚","price":"9.90","market_price":"16.00","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/04/07/1460021026.jpg","member_name":"焦岗湖","stock":"0"},{"id":"767","pname":"徽蛋小盒  5枚装","price":"13.80","market_price":"19.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/04/08/1460101338.jpg","member_name":"淮南","stock":"65"},{"id":"582","pname":"和喜来五花肉 约500g","price":"49.80","market_price":"50.00","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/02/26/1456467762.jpg","member_name":"宣城","stock":"6"},{"id":"580","pname":"和喜来猪瘦肉 约300g","price":"29.80","market_price":"31.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/02/26/1456467822.jpg","member_name":"宣城","stock":"6"},{"id":"581","pname":"和喜来猪腿肉 约300g","price":"23.80","market_price":"24.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/02/26/1456467684.jpg","member_name":"宣城","stock":"1"},{"id":"578","pname":"和喜来里脊肉 约300g","price":"35.80","market_price":"36.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/02/26/1456467501.jpg","member_name":"宣城","stock":"7"},{"id":"579","pname":"和喜来排骨 约500g","price":"59.80","market_price":"60.80","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/02/26/1456467585.jpg","member_name":"宣城","stock":"3"},{"id":"648","pname":"和喜来仔公鸡 约750g","price":"69.00","market_price":"75.00","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/02/29/1456728882.jpg","member_name":"宣城","stock":"8"},{"id":"583","pname":"和喜来土鸡 约1000g","price":"138.00","market_price":"140.00","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/01/13/1452666348.jpg","member_name":"宣城","stock":"8"},{"id":"591","pname":"和喜来土鸡蛋 10个","price":"25.00","market_price":"28.00","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/01/27/1453877002.jpg","member_name":"宣城","stock":"6"}]
     * length : 14
     * bgpic_new : http://cdn.xxiang365.com/uploadfile/special_area/local_sale/2016/04/15/1460705079.jpg
     * bgcolor : #ffffff
     * succeed : 1
     */

    private String logo;
    private String name;
    private int length;
    private String bgpic_new;
    private String bgcolor;
    /**
     * id : 760
     * pname : 万佛湖有机鱼身 约1000g
     * price : 26.80
     * market_price : 49.80
     * pic : http://cdn.xxiang365.com/uploadfile/product/1/2016/04/26/1461638497.jpg
     * member_name : 万佛湖
     * stock : 985
     */

    private List<ProductBean> product;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBgpic_new() {
        return bgpic_new;
    }

    public void setBgpic_new(String bgpic_new) {
        this.bgpic_new = bgpic_new;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ProductBean {
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
