package entities;

public class Code {
    private String code_name;
    private String price_with_off;
    private String original_price;
    private String count;
    private String total_price;
    private String buy_date;
    private String expiration_date;
    private String shop_name;
    private String image_url;

    public Code(String code_name, String price_with_off, String original_price, String count, String total_price, String buy_date, String expiration_date, String image_url,String shop_name) {
        this.code_name = code_name;
        this.price_with_off = price_with_off;
        this.original_price = original_price;
        this.count = count;
        this.total_price = total_price;
        this.buy_date = buy_date;
        this.expiration_date = expiration_date;
        this.image_url = image_url;
        this.shop_name = shop_name;

    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getPrice_with_off() {
        return price_with_off;
    }

    public void setPrice_with_off(String price_with_off) {
        this.price_with_off = price_with_off;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(String buy_date) {
        this.buy_date = buy_date;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
