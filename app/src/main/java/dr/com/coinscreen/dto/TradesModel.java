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

    public String getTrade_date_utc() {
        return trade_date_utc;
    }

    public String getTrade_time_utc() {
        return trade_time_utc;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getTrade_price() {
        return trade_price;
    }

    public double getTrade_volume() {
        return trade_volume;
    }

    public double getPrev_closing_price() {
        return prev_closing_price;
    }

    public double getChange_price() {
        return change_price;
    }

    public String getAsk_bid() {
        return ask_bid;
    }

    public long getSequential_id() {
        return sequential_id;
    }
}
