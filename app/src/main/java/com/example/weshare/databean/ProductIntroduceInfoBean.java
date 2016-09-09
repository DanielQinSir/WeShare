package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ProductIntroduceInfoBean {

    /**
     * id : 519
     * member_id : 1
     * member_name : 安徽
     * name : 鸭胗 约300g
     * market_price : 10.98
     * price : 8.98
     * sales : 884
     * pic : http://cdn.xxiang365.com/uploadfile/product/1/2015/11/17/1447738256.jpg
     * pic_more_length : 2
     * pic_more : ["http://cdn.xxiang365.com/uploadfile/product/1/2015/11/17/1447738256.jpg","http://cdn.xxiang365.com/uploadfile/product/1/2015/11/17/1447738261.jpg"]
     * weight : 约300g
     * detail : null
     * pic_detail :
     * pic_detail_length : 0
     * desc : 肉质紧密，紧韧耐嚼，老少皆喜爱的佳肴珍品
     * pic_desc : ["http://cdn.xxiang365.com/uploadfile/product/detail/2015/11/17/1447738384.jpg","http://cdn.xxiang365.com/uploadfile/product/detail/2015/11/17/1447738388.jpg","http://cdn.xxiang365.com/uploadfile/product/detail/2015/11/17/1447738392.jpg","http://cdn.xxiang365.com/uploadfile/product/detail/2015/11/17/1447738396.jpg","http://cdn.xxiang365.com/uploadfile/product/detail/2015/11/17/1447738401.jpg","http://cdn.xxiang365.com/uploadfile/product/detail/2015/11/17/1447738406.jpg","http://cdn.xxiang365.com/uploadfile/product/detail/2015/11/17/1447738410.jpg"]
     * pic_desc_length : 7
     * delivery : 每日上午10:00至晚21:00间订单，次日上午配送；每日晚21:00至次日上午10:00间订单，次日下午或晚间配送。
     * succeed : 1
     */

    private String id;
    private String member_id;
    private String member_name;
    private String name;
    private String market_price;
    private String price;
    private String sales;
    private String pic;
    private int pic_more_length;
    private String weight;
    private Object detail;
    private String pic_detail;
    private int pic_detail_length;
    private String desc;
    private int pic_desc_length;
    private String delivery;
    private int succeed;
    private List<String> pic_more;
    private List<String> pic_desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getPic_more_length() {
        return pic_more_length;
    }

    public void setPic_more_length(int pic_more_length) {
        this.pic_more_length = pic_more_length;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public String getPic_detail() {
        return pic_detail;
    }

    public void setPic_detail(String pic_detail) {
        this.pic_detail = pic_detail;
    }

    public int getPic_detail_length() {
        return pic_detail_length;
    }

    public void setPic_detail_length(int pic_detail_length) {
        this.pic_detail_length = pic_detail_length;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPic_desc_length() {
        return pic_desc_length;
    }

    public void setPic_desc_length(int pic_desc_length) {
        this.pic_desc_length = pic_desc_length;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }

    public List<String> getPic_more() {
        return pic_more;
    }

    public void setPic_more(List<String> pic_more) {
        this.pic_more = pic_more;
    }

    public List<String> getPic_desc() {
        return pic_desc;
    }

    public void setPic_desc(List<String> pic_desc) {
        this.pic_desc = pic_desc;
    }
}
