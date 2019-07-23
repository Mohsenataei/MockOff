package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopInfo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("work_time")
    @Expose
    private String work_time;

    @SerializedName("pics")
    @Expose
    private List<Pics> pics;

    public ShopInfo(String id, String name, String work_time, List<Pics> pics) {
        this.id = id;
        this.name = name;
        this.work_time = work_time;
        this.pics = pics;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public List<Pics> getPics() {
        return pics;
    }

    public void setPics(List<Pics> pics) {
        this.pics = pics;
    }
}
