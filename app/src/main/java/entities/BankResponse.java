package entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BankResponse {

    @SerializedName("bank_token")
    @Expose
    private String bank_token;

    @SerializedName("price")
    @Expose
    private int price;

    public BankResponse(String bank_token, int price) {
        this.bank_token = bank_token;
        this.price = price;
    }

    public String getBank_token() {
        return bank_token;
    }

    public void setBank_token(String bank_token) {
        this.bank_token = bank_token;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
