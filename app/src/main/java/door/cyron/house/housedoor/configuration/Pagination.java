package door.cyron.house.housedoor.configuration;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;


public class Pagination {
    @SerializedName("total")
    private int total;

    @SerializedName("perPage")
    private int perPage;

    @SerializedName("currentPage")
    private int currentPage;

    @SerializedName("links")
    private Links links;

    public int getTotal() {
        return total;
    }

    public int getPerPage() {
        return perPage;
    }

    public String getNext() {
        return links.getNext();
    }

    public boolean hasNext() {
        return !TextUtils.isEmpty(links.getNext());
    }

    private class Links {
        @SerializedName("next")
        private String next;

        public String getNext() {
            return next;
        }


    }

}
