package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("api_token")
    @Expose
    private String api_token;

    @SerializedName("post_id")
    @Expose
    private String post_id;

    @SerializedName("count")
    @Expose
    private String count;

    public Order(String api_token, String post_id, String count) {
        this.api_token = api_token;
        this.post_id = post_id;
        this.count = count;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
