package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class NewGoodsBean {

    /**
     * new_sale : [{"id":"1047","pname":"砀山金盖酥梨 15斤/箱","price":"69.80","market_price":"85.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/12/1473662196.jpg","member_name":"砀山","stock":"0"},{"id":"1046","pname":"脆柿 约500g","price":"2.98","market_price":"7.99","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/09/1473389964.jpg","member_name":"安徽","stock":"99973"},{"id":"1021","pname":"嫩姜 约500g","price":"3.98","market_price":"6.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/30/1472537326.jpg","member_name":"合肥","stock":"99998"},{"id":"1020","pname":"红杭椒 约300g","price":"4.98","market_price":"5.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/30/1472537034.jpg","member_name":"云南","stock":"99999"},{"id":"1045","pname":"子弹头枣 约500g","price":"6.98","market_price":"9.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/09/1473389543.jpg","member_name":"山西","stock":"99998"},{"id":"1031","pname":"红香酥 约500g-600g","price":"3.99","market_price":"4.59","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472779531.jpg","member_name":"陕西","stock":"99997"},{"id":"1011","pname":"泰国山竹 约500g-600g","price":"19.80","market_price":"25.80","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472102823.jpg","member_name":"泰国","stock":"99999"},{"id":"1012","pname":"新徽农绿壳蛋 6枚/盒","price":"5.98","market_price":"9.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472112646.jpg","member_name":"池州九华山","stock":"43"},{"id":"1044","pname":"【周日预售】52户600天散养老母鸡 约1500g-1600g","price":"139.00","market_price":"168.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/08/1473324433.jpg","member_name":"芜湖","stock":"49"},{"id":"1043","pname":"【周四预售】52户600天散养老母鸡 约1500g-1600g","price":"139.00","market_price":"168.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/08/1473324261.jpg","member_name":"芜湖","stock":"50"},{"id":"1042","pname":"【周四预售】52户400天散养老母鸡 约1500g","price":"128.00","market_price":"149.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/08/1473324004.jpg","member_name":"芜湖","stock":"50"},{"id":"1041","pname":"【周日预售】52户400天散养老母鸡 约1500g","price":"128.00","market_price":"149.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/08/1473323556.jpg","member_name":"芜湖","stock":"48"},{"id":"1017","pname":"马奶葡萄 约500g","price":"9.90","market_price":"12.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/29/1472447407.jpg","member_name":"山西","stock":"99999"},{"id":"1018","pname":"蟠桃 约600g-700g","price":"8.98","market_price":"11.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/29/1472447579.jpg","member_name":"陕西","stock":"99999"},{"id":"1019","pname":"青南瓜 约500g","price":"2.99","market_price":"4.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/30/1472536396.jpg","member_name":"六安","stock":"99999"},{"id":"1040","pname":"火龙果礼盒  约2kg","price":"49.80","market_price":"66.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/05/1473062296.jpg","member_name":"海南","stock":"99999"},{"id":"1039","pname":"冬枣礼盒 约2kg","price":"59.80","market_price":"72.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/05/1473061865.jpg","member_name":"沾化","stock":"99999"},{"id":"1038","pname":"会理石榴礼盒  约5kg","price":"59.80","market_price":"73.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/05/1473061472.jpg","member_name":"四川会理","stock":"99999"},{"id":"1016","pname":"青桔 约400g-500g","price":"2.99","market_price":"5.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/26/1472203612.jpg","member_name":"","stock":"99991"},{"id":"1003","pname":"将军寨 菜籽油5L","price":"79.00","market_price":"109.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472110212.png","member_name":"霍山","stock":"5"},{"id":"1024","pname":"深海鳕鱼片 300g/袋","price":"27.80","market_price":"38.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795725.jpg","member_name":"浙江台州","stock":"14"},{"id":"1030","pname":"去头带鱼段 约750g/袋","price":"33.80","market_price":"45.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795697.jpg","member_name":"浙江台州","stock":"15"},{"id":"1022","pname":"冻鲜贝柱 300g/袋 ","price":"39.80","market_price":"49.80","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795628.jpg","member_name":"广东","stock":"19"},{"id":"1027","pname":"冻裙边扇贝 200g/袋","price":"25.80","market_price":"36.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795604.jpg","member_name":"浙江","stock":"14"},{"id":"1026","pname":"冻花甲肉 300g/袋","price":"27.80","market_price":"38.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795577.jpg","member_name":"广东","stock":"16"},{"id":"1029","pname":"冻秋刀鱼 500g","price":"21.80","market_price":"30.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795556.jpg","member_name":"广东深圳","stock":"20"},{"id":"1028","pname":"冻带鱼（去头尾） 500g","price":"23.80","market_price":"34.80","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795531.jpg","member_name":"广东深圳","stock":"18"},{"id":"1025","pname":"冻巴沙鱼柳 400g/袋","price":"30.80","market_price":"41.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795484.jpg","member_name":"浙江","stock":"19"},{"id":"1023","pname":"冻小银鱼 200g/袋","price":"25.80","market_price":"35.80","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472795368.jpg","member_name":"广东","stock":"11"},{"id":"1037","pname":"青虾仁 200g/袋","price":"31.80","market_price":"40.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472792337.jpg","member_name":"浙江台州","stock":"17"},{"id":"1036","pname":"红虾仁 220g/袋","price":"31.80","market_price":"40.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472792167.jpg","member_name":"舟山","stock":"16"},{"id":"1035","pname":"冻鱿鱼仔 300g/袋","price":"23.80","market_price":"32.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472791837.jpg","member_name":"广东深圳","stock":"20"},{"id":"1034","pname":"冻鱿鱼须 300g/袋","price":"43.80","market_price":"55.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472791632.jpg","member_name":"广东深圳","stock":"19"},{"id":"1033","pname":"冻去头小黄鱼 400g/袋","price":"16.80","market_price":"27.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472791144.jpg","member_name":"广东深圳","stock":"14"},{"id":"1032","pname":"冻墨鱼仔 300g/袋","price":"34.80","market_price":"45.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472786900.jpg","member_name":"广东深圳","stock":"19"},{"id":"994","pname":"冠生园 繁花似锦礼盒","price":"78.00","market_price":"128.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/23/1471920384.jpg","member_name":"上海","stock":"8"},{"id":"999","pname":"十月初五 酥皮月饼","price":"89.00","market_price":"138.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/23/1471939163.jpg","member_name":"广东","stock":"12"},{"id":"995","pname":"冠生园 团团圆圆礼盒","price":"49.00","market_price":"78.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/23/1471918555.jpg","member_name":"上海","stock":"49"},{"id":"1002","pname":"将军寨 菜籽油2.5L","price":"45.00","market_price":"59.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472110272.png","member_name":"霍山","stock":"5"},{"id":"986","pname":"将军寨菜籽油1.8L","price":"35.80","market_price":"55.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472110290.png","member_name":"霍山","stock":"7"},{"id":"1013","pname":"新徽农土鸡蛋 6枚/盒","price":"8.00","market_price":"9.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/26/1472192447.jpg","member_name":"池州九华山","stock":"3"},{"id":"1001","pname":"果棵鲜 茶籽调和油 5L","price":"88.00","market_price":"108.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472110459.png","member_name":"六安","stock":"4"},{"id":"1004","pname":"上悦谷 野生山茶油 500ml*2","price":"119.00","market_price":"149.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472110378.png","member_name":"霍山","stock":"16"},{"id":"987","pname":"上悦谷山茶油750ml","price":"88.00","market_price":"99.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472110317.png","member_name":"霍山","stock":"13"},{"id":"991","pname":"新徽农土鸡蛋礼盒装 ","price":"68.90","market_price":"88.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/19/1471588048.jpg","member_name":"池州九华山","stock":"0"},{"id":"990","pname":"新徽农土鸡蛋 10枚/盒","price":"12.80","market_price":"15.50","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/19/1471571523.jpg","member_name":"池州九华山","stock":"34"},{"id":"1000","pname":"杏花楼 广式月饼","price":"99.00","market_price":"118.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/23/1471939651.jpg","member_name":"上海","stock":"5"},{"id":"998","pname":"十月初五 双簧蛋白莲蓉礼盒","price":"138.00","market_price":"168.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/23/1471938041.jpg","member_name":"广东","stock":"12"},{"id":"997","pname":"美心 金装彩月礼盒","price":"188.00","market_price":"218.00","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/23/1471937719.jpg","member_name":"香港","stock":"10"}]
     * total : 49
     * index : 0
     * length : 49
     * succeed : 1
     */

    private int total;
    private int index;
    private int length;
    private int succeed;
    /**
     * id : 1047
     * pname : 砀山金盖酥梨 15斤/箱
     * price : 69.80
     * market_price : 85.90
     * pic : http://cdn.xxiang365.com/uploadfile/product//2016/09/12/1473662196.jpg
     * member_name : 砀山
     * stock : 0
     */

    private List<NewSaleBean> new_sale;

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

    public List<NewSaleBean> getNew_sale() {
        return new_sale;
    }

    public void setNew_sale(List<NewSaleBean> new_sale) {
        this.new_sale = new_sale;
    }

    public static class NewSaleBean {
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
