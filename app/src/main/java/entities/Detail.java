package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("home_phone")
    @Expose
    private String home_phone;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("work_time")
    @Expose
    private String work_time;

    @SerializedName("work_date")
    @Expose
    private String work_date;

    public Detail(String name, String home_phone, String address, String description, String work_time, String work_date) {
        this.name = name;
        this.home_phone = home_phone;
        this.address = address;
        this.description = description;
        this.work_time = work_time;
        this.work_date = work_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
