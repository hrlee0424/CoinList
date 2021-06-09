package dr.com.coinscreen.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TickerModel {
    @SerializedName("market")
    @Expose
    private String market;
    @SerializedName("trade_date")
    @Expose
    private String trade_date;
    @SerializedName("trade_time")
    @Expose
    private String trade_time;
    @SerializedName("trade_date_kst")
    @Expose
    private String trade_date_kst;
    @SerializedName("trade_time_kst")
    @Expose
    private String trade_time_kst;
    @SerializedName("opening_price")
    @Expose
    private double opening_price;
    @SerializedName("high_price")
    @Expose
    private double high_price;
    @SerializedName("low_price")
    @Expose
    private double low_price;
    @SerializedName("trade_price")
    @Expose
    private double trade_price;
    @SerializedName("prev_closing_price")
    @Expose
    private double prev_closing_price;
    @SerializedName("change")
    @Expose
    private String change;
    @SerializedName("change_price")
    @Expose
    private double change_price;
    @SerializedName("change_rate")
    @Expose
    private double change_rate;
    @SerializedName("signed_change_price")
    @Expose
    private double signed_change_price;
    @SerializedName("signed_change_rate")
    @Expose
    private double signed_change_rate;
    @SerializedName("trade_volume")
    @Expose
    private double trade_volume;
    @SerializedName("acc_trade_price")
    @Expose
    private double acc_trade_price;
    @SerializedName("acc_trade_price_24h")
    @Expose
    private double acc_trade_price_24h;
    @SerializedName("acc_trade_volume")
    @Expose
    private double acc_trade_volume;
    @SerializedName("acc_trade_volume_24h")
    @Expose
    private double acc_trade_volume_24h;
    @SerializedName("highest_52_week_price")
    @Expose
    private double highest_52_week_price;
    @SerializedName("highest_52_week_date")
    @Expose
    private String highest_52_week_date;
    @SerializedName("lowest_52_week_price")
    @Expose
    private double lowest_52_week_price;
    @SerializedName("lowest_52_week_date")
    @Expose
    private String lowest_52_week_date;
    @SerializedName("timestamp")
    @Expose
    private long timestamp;

    public String getMarket() {
        return market;
    }

    public String getTrade_date() {
        return trade_date;
    }

    public String getTrade_time() {
        return trade_time;
    }

    public String getTrade_date_kst() {
        return trade_date_kst;
    }

    public String getTrade_time_kst() {
        return trade_time_kst;
    }

    public double getOpening_price() {
        return opening_price;
    }

    public double getHigh_price() {
        return high_price;
    }

    public double getLow_price() {
        return low_price;
    }

    public double getTrade_price() {
        return trade_price;
    }

    public double getPrev_closing_price() {
        return prev_closing_price;
    }

    public String getChange() {
        return change;
    }

    public double getChange_price() {
        return change_price;
    }

    public double getChange_rate() {
        return change_rate;
    }

    public double getSigned_change_price() {
        return signed_change_price;
    }

    public double getSigned_change_rate() {
        return signed_change_rate;
    }

    public double getTrade_volume() {
        return trade_volume;
    }

    public double getAcc_trade_price() {
        return acc_trade_price;
    }

    public double getAcc_trade_price_24h() {
        return acc_trade_price_24h;
    }

    public double getAcc_trade_volume() {
        return acc_trade_volume;
    }

    public double getAcc_trade_volume_24h() {
        return acc_trade_volume_24h;
    }

    public double getHighest_52_week_price() {
        return highest_52_week_price;
    }

    public String getHighest_52_week_date() {
        return highest_52_week_date;
    }

    public double getLowest_52_week_price() {
        return lowest_52_week_price;
    }

    public String getLowest_52_week_date() {
        return lowest_52_week_date;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
