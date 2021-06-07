package dr.com.coinscreen.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetMainList {
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
