package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Code {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("discount")
    @Expose
    private Integer discount;

    @SerializedName("order_date")
    @Expose
    private String order_date;

    @SerializedName("e_date_use")
    @Expose
    private String e_date_use;

    @SerializedName("qr_code")
    @Expose
    private String qr_code;

    @SerializedName("type_id")
    @Expose
    private Integer type_id;

    @SerializedName("shop_id")
    @Expose
    private String shop_id;

    @SerializedName("post_id")
    @Expose
    private Integer post_id;

    private String total_price;

    @SerializedName("pic")
    @Expose
    private String pic;

    private String discount_price;

    public Code(String title, String price, Integer count, Integer discount, String order_date, String e_date_use, String qr_code, Integer type_id, String shop_id, int post_id, String pic) {

        this.title = title;
        this.price = price;
        this.count = count;
        this.discount = discount;
        this.order_date = order_date;
        this.e_date_use = e_date_use;
        this.qr_code = qr_code;
        this.type_id = type_id;
        this.shop_id = shop_id;
        this.post_id = post_id;
        int temp = 0;
        temp = Integer.parseInt(price) * count;
        this.total_price = String.valueOf(temp);
        this.pic = "https://www.mocatag.ir/"+ pic;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getDiscount() {
        return discount;
    }

    public String getOrder_date() {
        return order_date;
    }

    public String getE_date_use() {
        return e_date_use;
    }

    public String getQr_code() {
        return qr_code;
    }

    public Integer getType_id() {
        return type_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getTotal_price() {

        return String.valueOf(Integer.parseInt(getPrice()) * getCount());

    }

    public String getPic() {
//        String s = pic.replace("\\","");
//        return s;
        return "https://www.mocatag.ir/"+ pic;//.replace("\\","/");

    }

    public String getDiscount_price() {
        return String.valueOf(Integer.parseInt(getPrice()) - (Integer.parseInt(getPrice())/getDiscount()));
    }
}
