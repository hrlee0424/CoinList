package dr.com.coinscreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetList {
    @SerializedName("market")
    @Expose
    private String market;
    @SerializedName("korean_name")
    @Expose
    private String korean_name;
    @SerializedName("english_name")
    @Expose
    private String english_name;

    public String getMarket() {
        return market;
    }

    public String getKorean_name() {
        return korean_name;
    }

    public String getEnglish_name() {
        return english_name;
    }

}

/*public class GetList {
    @SerializedName("")
    @Expose
    private List<GetListItem> items = null;

    public List<GetListItem> getItems() {
        return items;
    }

    public static class GetListItem implements Serializable {
        private String market, korean_name, english_name;

        public void setMarket(String market) {
            this.market = market;
        }

        public void setKorean_name(String korean_name) {
            this.korean_name = korean_name;
        }

        public void setEnglish_name(String english_name) {
            this.english_name = english_name;
        }

        public String getMarket() {
            return market;
        }

        public String getKorean_name() {
            return korean_name;
        }

        public String getEnglish_name() {
            return english_name;
        }
    }
}*/

