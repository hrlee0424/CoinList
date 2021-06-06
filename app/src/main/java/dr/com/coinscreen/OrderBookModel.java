package dr.com.coinscreen;

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
    private float timestamp;
    @SerializedName("total_ask_size")
    @Expose
    private float total_ask_size;
    @SerializedName("total_bid_size")
    @Expose
    private float total_bid_size;
    @SerializedName("orderbook_units")
    @Expose
    private List<OrderBookModelItem> items = null;

    public String getMarket() {
        return market;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public float getTotal_ask_size() {
        return total_ask_size;
    }

    public float getTotal_bid_size() {
        return total_bid_size;
    }

    public List<OrderBookModelItem> getItems() {
        return items;
    }

    public class OrderBookModelItem implements Serializable {
        private float ask_price, bid_price;
        private float ask_size, bid_size;

        public void setAsk_price(float ask_price) {
            this.ask_price = ask_price;
        }

        public void setBid_price(float bid_price) {
            this.bid_price = bid_price;
        }

        public void setAsk_size(float ask_size) {
            this.ask_size = ask_size;
        }

        public void setBid_size(float bid_size) {
            this.bid_size = bid_size;
        }

        public float getAsk_price() {
            return ask_price;
        }

        public float getBid_price() {
            return bid_price;
        }

        public float getAsk_size() {
            return ask_size;
        }

        public float getBid_size() {
            return bid_size;
        }
    }
}
