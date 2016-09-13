package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class HomeRecyclerViewFirstADBean {


    /**
     * product : [{"id":"22","pname":"黄瓜 约350g-450g","price":"5.80","promote_price":"1.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/17/1450350765.jpg","origin":"岗集","amount":"65","limit_amount":"2","sales":"13","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:55:16"},{"id":"41","pname":"西红柿 约 450g-550g","price":"5.00","promote_price":"2.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/16/1450252762.jpg","origin":"三十岗","amount":"72","limit_amount":"5","sales":"13","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:55:04"},{"id":"36","pname":"土豆 约600g-650g","price":"3.80","promote_price":"1.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/16/1450252635.jpg","origin":"安徽","amount":"52","limit_amount":"3","sales":"25","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:50:28"},{"id":"974","pname":"鲜花生 约500g","price":"7.50","promote_price":"3.58","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/10/1470811277.jpg","origin":"六安","amount":"60","limit_amount":"5","sales":"4","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:49:57"},{"id":"20","pname":"甜玉米 1根 约260g","price":"4.00","promote_price":"1.68","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/14/1450058911.jpg","origin":"山东","amount":"38","limit_amount":"5","sales":"10","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:49:25"},{"id":"43","pname":"薄皮椒 约300g","price":"2.50","promote_price":"1.58","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/22/1450762300.jpg","origin":"三十岗","amount":"24","limit_amount":"2","sales":"15","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:48:16"},{"id":"1021","pname":"嫩姜 约500g","price":"6.90","promote_price":"1.99","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/30/1472537326.jpg","origin":"合肥","amount":"29","limit_amount":"3","sales":"4","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:47:31"},{"id":"40","pname":"长豆角 约500g","price":"5.99","promote_price":"2.99","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/18/1471499577.jpg","origin":"六安","amount":"20","limit_amount":"1","sales":"7","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:46:29"},{"id":"50","pname":"洋葱 约500g-600g","price":"2.50","promote_price":"1.38","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2014/12/04/1417672248.jpg","origin":"山东","amount":"14","limit_amount":"1","sales":"12","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:46:05"},{"id":"268","pname":"藕 约450g-600g","price":"5.80","promote_price":"2.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/11/12/1447326466.jpg","origin":"安徽","amount":"17","limit_amount":"2","sales":"15","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:45:12"},{"id":"201","pname":"毛豆角 约500g","price":"4.00","promote_price":"1.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/07/10/1436489823.jpg","origin":"昆明","amount":"15","limit_amount":"1","sales":"11","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:44:58"},{"id":"10","pname":"花菜 约500g","price":"7.80","promote_price":"4.58","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/11/30/1448862920.jpg","origin":"上海","amount":"15","limit_amount":"1","sales":"3","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:42:50"},{"id":"21","pname":"冬瓜 约550g-650g","price":"2.80","promote_price":"0.68","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/07/09/1436427113.jpg","origin":"广东","amount":"13","limit_amount":"1","sales":"8","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:42:49"},{"id":"204","pname":"蒜苔 约500g","price":"6.80","promote_price":"3.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/07/16/1437035565.jpg","origin":"山东","amount":"11","limit_amount":"2","sales":"2","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:41:35"},{"id":"57","pname":"三十岗棵白菜 约500g","price":"6.58","promote_price":"2.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/07/14/1436860052.jpg","origin":"三十岗","amount":"11","limit_amount":"2","sales":"9","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:41:03"},{"id":"1011","pname":"泰国山竹 约500g-600g","price":"25.80","promote_price":"13.80","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472102823.jpg","origin":"泰国","amount":"10","limit_amount":"2","sales":"1","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:40:21"},{"id":"487","pname":"迷你黄瓜 约500g","price":"9.80","promote_price":"5.98","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/10/24/1445659965.jpg","origin":"广东","amount":"9","limit_amount":"1","sales":0,"limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:39:24"},{"id":"30","pname":"白萝卜 约800g-1000g ","price":"5.50","promote_price":"3.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/07/09/1436427044.jpg","origin":"安徽","amount":"7","limit_amount":"1","sales":"3","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:38:29"},{"id":"26","pname":"金针菇 约250g","price":"4.98","promote_price":"2.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/15/1450162497.jpg","origin":"安徽","amount":"6","limit_amount":"1","sales":"5","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:36:57"},{"id":"33","pname":"山药 约500g","price":"3.99","promote_price":"2.58","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/15/1450163686.jpg","origin":"山东","amount":"7","limit_amount":"1","sales":"6","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:36:35"},{"id":"9","pname":"香芹 约350g","price":"6.99","promote_price":"3.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/10/1449709485.jpg","origin":"山东","amount":"6","limit_amount":"1","sales":"2","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:35:27"},{"id":"150","pname":"三十岗香菜 约150g","price":"4.59","promote_price":"2.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/07/14/1436861151.jpg","origin":"三十岗","amount":"6","limit_amount":"1","sales":"3","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:35:16"},{"id":"565","pname":"秋葵 约200g","price":"3.80","promote_price":"2.58","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/28/1451272208.jpg","origin":"江西","amount":"6","limit_amount":"1","sales":"3","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:34:39"},{"id":"39","pname":"四季豆 约500g","price":"10.80","promote_price":"5.98","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2014/12/04/1417661031.jpg","origin":"山东","amount":"4","limit_amount":"1","sales":"3","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:29:10"},{"id":"540","pname":"西芹 约800g-900g","price":"9.80","promote_price":"5.58","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/11/23/1448257524.jpg","origin":"山东","amount":"3","limit_amount":"1","sales":"2","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:25:15"},{"id":"305","pname":"彩椒 约200g-300g","price":"8.90","promote_price":"5.39","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/03/17/1426583630.jpg","origin":"山东","amount":"3","limit_amount":"1","sales":"1","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:24:48"},{"id":"329","pname":"芦笋 约350g-450g/份","price":"11.28","promote_price":"7.98","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/28/1451266488.jpg","origin":"山东","amount":"3","limit_amount":"1","sales":"1","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:24:37"},{"id":"226","pname":"后腿肉 约500g","price":"17.80","promote_price":"13.58","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/10/1470796293.jpg","origin":"六安","amount":"10","limit_amount":"1","sales":"7","limit_etime":"2016-09-10 16:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:22:43"},{"id":"229","pname":"前腿肉 约500g","price":"17.98","promote_price":"12.98","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/28/1451266373.jpg","origin":"六安","amount":"10","limit_amount":"1","sales":"4","limit_etime":"2016-09-10 16:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:22:00"},{"id":"323","pname":"毛豆仁 约350g","price":"10.98","promote_price":"4.98","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/03/31/1427806603.jpg","origin":"山东","amount":"3","limit_amount":"1","sales":"2","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:19:02"},{"id":"24","pname":"生瓜 约700g-800g","price":"9.00","promote_price":"5.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2014/12/03/1417596319.jpg","origin":"岗集","amount":"2","limit_amount":"1","sales":"1","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:16:41"},{"id":"324","pname":"去皮茭白 约500g","price":"14.28","promote_price":"9.89","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/05/26/1464250820.jpg","origin":"安徽","amount":"2","limit_amount":"1","sales":"1","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-10 08:16:06"},{"id":"23","pname":"南瓜 约500g","price":"2.58","promote_price":"0.38","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/12/15/1450162764.jpg","origin":"海南","amount":"100","limit_amount":"1","sales":"87","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-09 21:00:01"},{"id":"988","pname":"新徽农绿壳鸡蛋 10枚/盒","price":"18.99","promote_price":"9.90","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/08/25/1472085805.jpg","origin":"池州","amount":"50","limit_amount":"1","sales":"13","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-09 21:00:01"},{"id":"781","pname":"新疆哈密瓜  约1500g-2000g","price":"19.80","promote_price":"9.90","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/04/22/1461293241.jpg","origin":"新疆","amount":"30","limit_amount":"2","sales":"23","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-09 21:00:15"},{"id":"516","pname":"鸡脯肉 约400g-500g","price":"7.98","promote_price":"5.98","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/11/13/1447377436.jpg","origin":"安徽","amount":"50","limit_amount":"3","sales":"39","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-09 21:00:01"},{"id":"1031","pname":"红香酥 约500g-600g","price":"4.59","promote_price":"2.99","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/02/1472779531.jpg","origin":"陕西","amount":"50","limit_amount":"3","sales":"46","limit_etime":"2016-09-10 21:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-09 21:00:01"},{"id":"1045","pname":"子弹头枣 约500g","price":"9.90","promote_price":"5.98","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/09/09/1473389543.jpg","origin":"山西","amount":"80","limit_amount":"5","sales":"52","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-09 21:00:01"},{"id":"827","pname":"红提 约500g","price":"9.50","promote_price":"3.99","pic":"http://cdn.xxiang365.com/uploadfile/product//2016/05/13/1463105993.jpg","origin":"云南","amount":"100","limit_amount":"5","sales":"73","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-09 21:00:01"},{"id":"442","pname":"新西兰绿奇异果 1个约80g-100g","price":"5.98","promote_price":"1.99","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/07/03/1435934963.jpg","origin":"新西兰","amount":"150","limit_amount":"10","sales":"37","limit_etime":"2016-09-10 10:00:00","now_time":"2016-09-10 09:29:08","limit_stime":"2016-09-09 21:00:01"}]
     * length : 40
     * succeed : 1
     */

    private int length;
    private int succeed;
    /**
     * id : 22
     * pname : 黄瓜 约350g-450g
     * price : 5.80
     * promote_price : 1.99
     * pic : http://cdn.xxiang365.com/uploadfile/product/1/2015/12/17/1450350765.jpg
     * origin : 岗集
     * amount : 65
     * limit_amount : 2
     * sales : 13
     * limit_etime : 2016-09-10 10:00:00
     * now_time : 2016-09-10 09:29:08
     * limit_stime : 2016-09-10 08:55:16
     */

    private List<ProductBean> product;

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
        private String promote_price;
        private String pic;
        private String origin;
        private String amount;
        private String limit_amount;
        private String sales;
        private String limit_etime;
        private String now_time;
        private String limit_stime;

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

        public String getPromote_price() {
            return promote_price;
        }

        public void setPromote_price(String promote_price) {
            this.promote_price = promote_price;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getLimit_amount() {
            return limit_amount;
        }

        public void setLimit_amount(String limit_amount) {
            this.limit_amount = limit_amount;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        public String getLimit_etime() {
            return limit_etime;
        }

        public void setLimit_etime(String limit_etime) {
            this.limit_etime = limit_etime;
        }

        public String getNow_time() {
            return now_time;
        }

        public void setNow_time(String now_time) {
            this.now_time = now_time;
        }

        public String getLimit_stime() {
            return limit_stime;
        }

        public void setLimit_stime(String limit_stime) {
            this.limit_stime = limit_stime;
        }
    }
}
