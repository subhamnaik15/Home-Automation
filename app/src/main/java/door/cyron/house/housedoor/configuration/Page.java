package door.cyron.house.housedoor.configuration;

import com.google.gson.annotations.SerializedName;



public class Page {

    @SerializedName("pagination")
    private Pagination pagination;

    public Pagination getPagination() {
        return pagination;
    }



}
