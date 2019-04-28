package entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("image_url")
    private String image_url;

    @SerializedName("shop_name")
    private String shop_name;

    @SerializedName("post_name")
    private String post_name;

    @SerializedName("original_price")
    private int original_price;

    @SerializedName("off_price")
    private int off_price;

    @SerializedName("off_percentage")
    private int off_percentage;

    @SerializedName("shop_location")
    private String shop_location;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public int getOff_price() {
        return off_price;
    }

    public void setOff_price(int off_price) {
        this.off_price = off_price;
    }

    public int getOff_percentage() {
        return off_percentage;
    }

    public void setOff_percentage(int off_percentage) {
        this.off_percentage = off_percentage;
    }

    public String getShop_location() {
        return shop_location;
    }

    public void setShop_location(String shop_location) {
        this.shop_location = shop_location;
    }
}
