package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeaderPics {

    @SerializedName("thumblink")
    @Expose
    private String thumblink;

    @SerializedName("shop_id")
    @Expose
    private String shop_id;

    @SerializedName("post_id")
    @Expose
    private String post_id;

    @SerializedName("city_id")
    @Expose
    private String city_id;

    public HeaderPics(String thumblink, String shop_id, String post_id, String city_id) {
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

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
