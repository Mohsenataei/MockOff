package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Followed {

    private List<Integer> shop_ids ;

    public Followed (){
        shop_ids = new ArrayList<>();
    }

    public Followed(List<Integer> shop_ids){
        this.shop_ids = shop_ids;

    }

    public void addId(int id){
        if(!shop_ids.contains(id))
            shop_ids.add(id);
    }

    public void removeId(int id){
        if(shop_ids.contains(id)) shop_ids.remove(id);
    }

    public List<Integer> getShop_ids() {
        return shop_ids;
    }

    public void setShop_ids(List<Integer> shop_ids) {
        this.shop_ids = shop_ids;
    }

}
