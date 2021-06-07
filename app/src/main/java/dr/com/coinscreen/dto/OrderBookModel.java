package dr.com.coinscreen.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderBookModel {
    @SerializedName("market")
    @Expose
    private String market;
    @SerializedName("timestamp")
    @Expose
    private long timestamp;
    @SerializedName("total_ask_size")
    @Expose
    private double total_ask_size;
    @SerializedName("total_bid_size")
    @Expose
    private double total_bid_size;
    @SerializedName("orderbook_units")
    @Expose
    private List<OrderBookModel.OrderBookModelItem> items = null;

    public String getMarket() {
        return market;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getTotal_ask_size() {
        return total_ask_size;
    }

    public double getTotal_bid_size() {
        return total_bid_size;
    }

    public List<OrderBookModelItem> getItems() {
        return items;
    }

    public class OrderBookModelItem implements Serializable {
        private double ask_price, bid_price, ask_size, bid_size;

        public void setAsk_price(double ask_price) {
            this.ask_price = ask_price;
        }

        public void setBid_price(double bid_price) {
            this.bid_price = bid_price;
        }

        public void setAsk_size(double ask_size) {
            this.ask_size = ask_size;
        }

        public void setBid_size(double bid_size) {
            this.bid_size = bid_size;
        }

        public double getAsk_price() {
            return ask_price;
        }

        public double getBid_price() {
            return bid_price;
        }

        public double getAsk_size() {
            return ask_size;
        }

        public double getBid_size() {
            return bid_size;
        }
    }
}
