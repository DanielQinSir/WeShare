package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class HomeListDetailBean {

    /**
     * desc : http://cdn.xxiang365.com/uploadfile/special_area/2016/08/12/1470971659.jpg
     * name : 金牌水果
     * product : [{"product_id":"949","title":"梨","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/08/12/1470971014.jpg","reason":"梨因鲜嫩多汁被称为\u201c天然矿泉水\u201d，具有清热解毒、生津润燥、清心降火的作用，非常适合伏天食用。对肺、支气管及上呼吸道有相当好的滋润功效，还可帮助消化、促进食欲。正常体质直接吃，脾胃寒凉的可煮熟了吃。"},{"product_id":"962","title":"葡萄","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/08/12/1470971078.jpg","reason":"葡萄全身都是宝，所含的多酚类物质是天然的自由基清除剂，具有很强的抗氧化活性，尤其是葡萄籽中富含的花青素，其抗氧化的功效比维生素C高出18倍之多。食用前应充分清洗干净，连皮带籽榨汁喝，充分吸收营养。\r\n"},{"product_id":"469","title":"石榴","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/08/12/1470971135.jpg","reason":"石榴红了，又到了吃石榴的季节。石榴的果实色彩缤纷，红如玛瑙，白若水晶。石榴入口汁多，酸甜爽口，有\u201c御饥疗渴，解酲止醉\u201d之功。除鲜食外，如加工成清凉饮料或酿酒造醋，则滋味独特，别具风格。"},{"product_id":"209","title":"苹果","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/08/12/1470971219.jpg","reason":"苹果对健康有利，更是女性健康的守护神。吃苹果最好连皮一起吃，因为与苹果肉相比，苹果皮中黄酮类化合物含量较高，抗氧化活性也较强。苹果当做加餐可以提供身体、大脑所需的水分和营养，还可以带来饱腹感，减少正餐的饭量。"},{"product_id":"213","title":"香蕉","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/08/12/1470971282.jpg","reason":"香蕉被称为\u201c快乐水果\u201d，因为它所含的泛酸等成分是人体的\u201c开心激素\u201d，补充香蕉可提供所需营养物质并缓解消极情绪，减轻压力。此外，含钾元素丰富的香蕉是食物中排名第一的\u201c美腿高手。\r\n"}]
     * length : 5
     * succeed : 1
     */

    private String desc;
    private String name;
    private int length;
    /**
     * product_id : 949
     * title : 梨
     * pic : http://cdn.xxiang365.com/uploadfile/new_special_area/2016/08/12/1470971014.jpg
     * reason : 梨因鲜嫩多汁被称为“天然矿泉水”，具有清热解毒、生津润燥、清心降火的作用，非常适合伏天食用。对肺、支气管及上呼吸道有相当好的滋润功效，还可帮助消化、促进食欲。正常体质直接吃，脾胃寒凉的可煮熟了吃。
     */

    private List<ProductBean> product;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }

    public static class ProductBean {
        private String product_id;
        private String title;
        private String pic;
        private String reason;

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
