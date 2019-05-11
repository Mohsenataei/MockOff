package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pics {

    @SerializedName("thumblink")
    @Expose
    private String thumblink;

    public Pics(String thumblink) {
        this.thumblink = "https://www.mocatag.ir/" + thumblink;
    }

    public String getThumblink() {
        return "https://www.mocatag.ir/" + thumblink;
    }

    public void setThumblink(String thumblink) {
        this.thumblink = "https://www.mocatag.ir/" +  thumblink;
    }
}
