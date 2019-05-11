package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeaderPics {

    @SerializedName("thumblink")
    @Expose
    private String thumblink;

    @SerializedName("shop_id")
    @Expose
    private int shop_id;

    @SerializedName("post_id")
    @Expose
    private int post_id;

    @SerializedName("city_id")
    @Expose
    private int city_id;

    public HeaderPics(String thumblink, int shop_id, int post_id, int city_id) {
        this.thumblink = "https://www.mocatag.ir/" +  thumblink;
        this.shop_id = shop_id;
        this.post_id = post_id;
        this.city_id = city_id;
    }


    public String getThumblink() {
        return "https://www.mocatag.ir/" +  thumblink;
    }

    public void setThumblink(String thumblink) {
        this.thumblink = "https://www.mocatag.ir/" + thumblink;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }
}
