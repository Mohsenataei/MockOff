package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShopShits {
    @SerializedName("detail")
    @Expose
    private Detail detail;

    public ShopShits(Detail detail) {
        this.detail = detail;
    }


    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
