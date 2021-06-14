package dr.com.coinscreen.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TradesModel {
    @SerializedName("trade_date_utc")
    @Expose
    private String trade_date_utc;
    @SerializedName("trade_time_utc")
    @Expose
    private String trade_time_utc;
    @SerializedName("timestamp")
    @Expose
    private long timestamp;
    @SerializedName("trade_price")
    @Expose
    private double trade_price;
    @SerializedName("trade_volume")
    @Expose
    private double trade_volume;
    @SerializedName("prev_closing_price")
    @Expose
    private double prev_closing_price;
    @SerializedName("change_price")
    @Expose
    private double change_price;
    @SerializedName("ask_bid")
    @Expose
    private String ask_bid;
    @SerializedName("sequential_id")
    @Expose
    private long sequential_id;
}
