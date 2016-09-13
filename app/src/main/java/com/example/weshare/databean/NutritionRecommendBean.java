package com.example.weshare.databean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class NutritionRecommendBean {

    /**
     * desc : http://cdn.xxiang365.com/uploadfile/special_area/2016/09/08/1473321754.jpg
     * name : 营养推荐
     * product : [{"product_id":"977","title":"蜂蜜","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/09/08/1473321793.jpg","reason":"      秋天最大的气候特点就是干燥，而蜂蜜能润燥，所以非常适合秋天服用。喝蜂蜜的时间推荐选择早晨，因为早晨喝蜂蜜，可以快速补充体能，让一天有充足的精神。"},{"product_id":"1019","title":"南瓜","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/09/08/1473321867.jpg","reason":"       秋天气候干燥，增加食用含有丰富维A、维生素E的食品，可增强机体免疫力，对改善秋燥症状大有裨益。南瓜含有丰富的维生素E。其所含的β胡萝卜素，可由人体吸收后转化为维生素A，维生素A和蛋白质结合可形成视蛋白，在视觉上扮演重要的角色。"},{"product_id":"33","title":"山药","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/09/08/1473321917.jpg","reason":"     山药都是秋季最佳滋补食品。对女性来说山药可益气养血暖手脚。对男性来说山药可防止\u201c老胃病\u201d复发。对老人来说山药可补益中气防感冒。对儿童来说山药是防治腹泻好帮手。山药具有补益脾胃的作用，特别适合脾胃虚弱者进补前食用。"},{"product_id":"615","title":"红枣","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/09/08/1473321958.jpg","reason":"    俗话说：\u201c一日仨枣，健康到老。\u201d红枣，能够健脾益胃、补气养血、养血安神以及缓和药性，是很好的营养品。秋季乍寒乍暖，在红枣中加几片桑叶煎汤代茶饮，可预防伤风感冒。\r\n"},{"product_id":"414","title":"糯米","pic":"http://cdn.xxiang365.com/uploadfile/new_special_area/2016/09/08/1473322001.jpg","reason":"      糯米，其味甘、性温，糯米食品宜加热后食用；宜煮稀粥服食，不仅营养滋补，且易消化吸收，养胃气。吃了后会周身发热，起到祛寒、滋补的作用。秋季适当吃点糯米类食物，对身体会有很好的补益作用。 \r\n"}]
     * length : 5
     * succeed : 1
     */

    private String desc;
    private String name;
    private int length;
    private int succeed;
    /**
     * product_id : 977
     * title : 蜂蜜
     * pic : http://cdn.xxiang365.com/uploadfile/new_special_area/2016/09/08/1473321793.jpg
     * reason :       秋天最大的气候特点就是干燥，而蜂蜜能润燥，所以非常适合秋天服用。喝蜂蜜的时间推荐选择早晨，因为早晨喝蜂蜜，可以快速补充体能，让一天有充足的精神。
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
