package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearestShops {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("updated_at")
    @Expose
    private String updated_at;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cell_phone")
    @Expose
    private String cell_phone;

    @SerializedName("home_phone")
    @Expose
    private String home_phone;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("work_time")
    @Expose
    private String work_time;

    @SerializedName("work_date")
    @Expose
    private String work_date;

    @SerializedName("shaba")
    @Expose
    private String shaba;

    @SerializedName("type_id")
    @Expose
    private int type_id;

    @SerializedName("pic_stat_id")
    @Expose
    private int pic_stat_id;

    @SerializedName("category_id")
    @Expose
    private int category_id;

    @SerializedName("c_date")
    @Expose
    private String c_date;

    @SerializedName("city_id")
    @Expose
    private int city_id;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("deleted_at")
    @Expose
    private String deleted_at;


    public NearestShops(Integer id, String created_at, String updated_at, String name, String cell_phone, String home_phone, String address, String latitude, String longitude, String email, String description, String work_time, String work_date, String shaba, int type_id, int pic_stat_id, int category_id, String c_date, int city_id, String password, String token, String deleted_at) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.cell_phone = cell_phone;
        this.home_phone = home_phone;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.email = email;
        this.description = description;
        this.work_time = work_time;
        this.work_date = work_date;
        this.shaba = shaba;
        this.type_id = type_id;
        this.pic_stat_id = pic_stat_id;
        this.category_id = category_id;
        this.c_date = c_date;
        this.city_id = city_id;
        this.password = password;
        this.token = token;
        this.deleted_at = deleted_at;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    public String getShaba() {
        return shaba;
    }

    public void setShaba(String shaba) {
        this.shaba = shaba;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getPic_stat_id() {
        return pic_stat_id;
    }

    public void setPic_stat_id(int pic_stat_id) {
        this.pic_stat_id = pic_stat_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
