package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Post {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("shop_id")
    @Expose
    private int shop_id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("discount")
    @Expose
    private int discount;

    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("e_date_use")
    @Expose
    private String e_date_use;

    @SerializedName("e_date_show")
    @Expose
    private String e_date_show;

    @SerializedName("city_id")
    @Expose
    private int city_id;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("shop_name")
    @Expose
    private String shop_name;

    @SerializedName("pics")
    @Expose
    private List<Pics> pics;

    @SerializedName("img")
    @Expose
    private String img;




    public Post(String e_date_show, String s_date_use, int id, int shop_id, String title, int status, String price, int discount, int quantity, int city_id, String address, String shop_name, List<Pics> pics,String img) {
        this.id = id;
        this.shop_id = shop_id;
        this.title = title;
        this.status = status;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.city_id = city_id;
        this.address = address;
        this.shop_name = shop_name;
        this.pics = pics;
        this.img = img;
        this.e_date_show = e_date_show;
        this.e_date_use = s_date_use;

    }

    public String getE_date_use() {
        return e_date_use;
    }

    public void setE_date_use(String s_date_use) {
        this.e_date_use = s_date_use;
    }

    public String getE_date_show() {
        return e_date_show;
    }

    public void setE_date_show(String e_date_use) {
        this.e_date_show = e_date_use;
    }

    public String getShop_name(){
        return shop_name;
    }

    public void setShop_name(String shop_name){
        this.shop_name = shop_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Pics> getPics() {
        return pics;
    }

    public void setPics(List<Pics> pics) {
        this.pics = pics;
    }


    public String getImg() {
        return "https://www.mocatag.ir/" + img;
    }

    public void setImg(String img) {
        this.img =  "https://www.mocatag.ir/" + img;
    }
}
