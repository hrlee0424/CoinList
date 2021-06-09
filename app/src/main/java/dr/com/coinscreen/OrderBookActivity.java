package dr.com.coinscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import dr.com.coinscreen.adapter.AskPriceAdapter;
import dr.com.coinscreen.adapter.BidPriceAdapter;
import dr.com.coinscreen.databinding.DetailBinding;
import dr.com.coinscreen.dto.OrderBookModel;
import dr.com.coinscreen.dto.TickerModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class OrderBookActivity extends AppCompatActivity {
    private static final String TAG = "OrderBookActivity";
    private DetailBinding binding;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private String market;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_book);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_book);

        Intent intent = getIntent();
        market = intent.getStringExtra("market");
        Log.i(TAG, "onCreate: " + market);

        if (!market.isEmpty()) {
            getOrderBook();
            getTicker();
        }


    }

    List<OrderBookModel> getList;
    RetrofitApi service = RestfulAdapter.getInstance().getServiceApi();
    private void getOrderBook(){
        Observable<List<OrderBookModel>> observable = service.getOrderBookItem(market);

        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<List<OrderBookModel>>(){

            @Override
            public void onNext(List<OrderBookModel> value) {
                Log.i(TAG, "onNext: " + value.toString());
                getList = value;
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                binding.market.setText(getList.get(0).getMarket());
                binding.timestamp.setText(String.valueOf(getList.get(0).getTimestamp()));
                binding.totalAskSize.setText(String.valueOf(getList.get(0).getTotal_ask_size()));
                binding.totalBidSize.setText(String.valueOf(getList.get(0).getTotal_bid_size()));
                if (getList.get(0).getItems().size() > 0){
                    AskPriceAdapter askPriceAdapter = new AskPriceAdapter(getApplicationContext(), getList);
                    BidPriceAdapter bidPriceAdapter = new BidPriceAdapter(getApplicationContext(), getList);
                    binding.askPriceList.setAdapter(askPriceAdapter);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            binding.askPriceList.scrollToPosition(7);
//                            binding.askPriceList.getLayoutManager().scrollToPosition(7);
//                        }
//                    }, 1000);
                    binding.askPriceList.setNestedScrollingEnabled(true);
                    binding.bidPriceList.setAdapter(bidPriceAdapter);
                    binding.bidPriceList.setNestedScrollingEnabled(true);
                }
            }
        }));
    }

    List<TickerModel> getTickerList;
    private void getTicker(){
        Observable<List<TickerModel>> observable = service.getTickerList(market);
        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<TickerModel>>() {

                    @Override
                    public void onNext(List<TickerModel> value) {
                        Log.i(TAG, "onNext: 11111111" + value.toString());
                        getTickerList = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: 1111111" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        /*binding.accTradeVolume24h.setText(String.valueOf(getTickerList.get(0).getAcc_trade_volume_24h()));
                        binding.accTradePrice24h.setText(String.valueOf(getTickerList.get(0).getAcc_trade_price_24h()));
                        binding.highest52WeekPrice.setText(String.valueOf(getTickerList.get(0).getHighest_52_week_price()));
                        binding.lowest52WeekPrice.setText(String.valueOf(getTickerList.get(0).getLowest_52_week_price()));
                        binding.prevClosingPrice.setText(String.valueOf(getTickerList.get(0).getPrev_closing_price()));
                        binding.highPrice.setText(String.valueOf(getTickerList.get(0).getHigh_price()));
                        binding.lowPrice.setText(String.valueOf(getTickerList.get(0).getLow_price()));*/
                        double d = getTickerList.get(0).getLowest_52_week_price();
                        Log.i(TAG, "onComplete: qqqqqq" +String.valueOf(getTickerList.get(0).getAcc_trade_volume_24h()));
                        DecimalFormat df = new DecimalFormat();
                        Log.i(TAG, "onComplete: qqqqqq" + df.format(d));
                        Log.i(TAG, "onComplete: qqqqqq" + new Plain().toPlainString(String.valueOf(getTickerList.get(0).getAcc_trade_volume_24h())));
                        Log.i(TAG, "onComplete: hhhhhhhhhhhhhhh" + String.format("%1$,.0f", getTickerList.get(0).getAcc_trade_volume_24h()));
                        Log.i(TAG, "onComplete: aaaaaaaaaaaaaaa" + String.format("%1$,.0f", getTickerList.get(0).getLowest_52_week_price()));


//                        binding.accTradeVolume24h.setText(new Plain().toPlainString(String.valueOf(getTickerList.get(0).getAcc_trade_volume_24h())));
                        binding.accTradeVolume24h.setText(String.format(Locale.KOREA, "%1$,.0f", getTickerList.get(0).getAcc_trade_volume_24h()));
                        binding.accTradePrice24h.setText(new Plain().toPlainString(String.valueOf(getTickerList.get(0).getAcc_trade_price_24h())));
                        binding.highest52WeekPrice.setText(new Plain().toPlainString(String.valueOf(getTickerList.get(0).getHighest_52_week_price())));
                        binding.lowest52WeekPrice.setText(new Plain().toPlainString(String.valueOf(getTickerList.get(0).getLowest_52_week_price())));
                        binding.prevClosingPrice.setText(new Plain().toPlainString(String.valueOf(getTickerList.get(0).getPrev_closing_price())));
                        binding.highPrice.setText(new Plain().toPlainString(String.valueOf(getTickerList.get(0).getHigh_price())));
                        binding.lowPrice.setText(new Plain().toPlainString(String.valueOf(getTickerList.get(0).getLow_price())));
                    }
                }));
    }
}