package com.example.weshare.databean;

import java.util.List;

/**
 * Created by: Daniel Qin
 * Date: 2016-09-09
 * Time: 09:46
 * For:
 */

public class ProductBean
{

    /**
     * cart : [{"id":"1281386","product_id":"768","num":"1","market_price":"19.80","price":"15.80","pname":"银屏山土鸡蛋 10枚","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2016/04/09/1460209558.jpg","member_name":"享享社区"},{"id":"1281481","product_id":"268","num":"1","market_price":"5.80","price":"2.99","pname":"藕 约450g-600g","pic":"http://cdn.xxiang365.com/uploadfile/product/1/2015/11/12/1447326466.jpg","member_name":"享享社区"}]
     * total : 5
     * index : 0
     * length : 2
     * succeed : 1
     */

    private int total;
    private int index;
    private String length;
    private int succeed;
    /**
     * id : 1281386
     * product_id : 768
     * num : 1
     * market_price : 19.80
     * price : 15.80
     * pname : 银屏山土鸡蛋 10枚
     * pic : http://cdn.xxiang365.com/uploadfile/product/1/2016/04/09/1460209558.jpg
     * member_name : 享享社区
     */

    private List<CartBean> cart;

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getLength()
    {
        return length;
    }

    public void setLength(String length)
    {
        this.length = length;
    }

    public int getSucceed()
    {
        return succeed;
    }

    public void setSucceed(int succeed)
    {
        this.succeed = succeed;
    }

    public List<CartBean> getCart()
    {
        return cart;
    }

    public void setCart(List<CartBean> cart)
    {
        this.cart = cart;
    }

    public static class CartBean
    {

        private String id;
        private String product_id;
        private String num;
        private String market_price;
        private String price;
        private String pname;
        private String pic;
        private String member_name;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getProduct_id()
        {
            return product_id;
        }

        public void setProduct_id(String product_id)
        {
            this.product_id = product_id;
        }

        public String getNum()
        {
            return num;
        }

        public void setNum(String num)
        {
            this.num = num;
        }

        public String getMarket_price()
        {
            return market_price;
        }

        public void setMarket_price(String market_price)
        {
            this.market_price = market_price;
        }

        public String getPrice()
        {
            return price;
        }

        public void setPrice(String price)
        {
            this.price = price;
        }

        public String getPname()
        {
            return pname;
        }

        public void setPname(String pname)
        {
            this.pname = pname;
        }

        public String getPic()
        {
            return pic;
        }

        public void setPic(String pic)
        {
            this.pic = pic;
        }

        public String getMember_name()
        {
            return member_name;
        }

        public void setMember_name(String member_name)
        {
            this.member_name = member_name;
        }
    }
}
