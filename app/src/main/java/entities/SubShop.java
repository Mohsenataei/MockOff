package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubShop {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("pic")
    @Expose
    private String pic;

    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    public SubShop(String name, Integer id, String pic, Pivot pivot) {
        this.name = name;
        this.id = id;
        this.pic = pic;
        this.pivot = pivot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPic() {
        return "https://www.mocatag.ir/"+ this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }
}
