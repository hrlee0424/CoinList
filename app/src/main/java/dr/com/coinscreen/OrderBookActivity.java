package dr.com.coinscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class OrderBookActivity extends AppCompatActivity {
    private static final String TAG = "OrderBookActivity";
    private DetailBinding binding;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private String market, korName;
    final Handler handler = new Handler(Looper.getMainLooper());
    List<OrderBookModel> getList;
    private AskPriceAdapter askPriceAdapter;
    private BidPriceAdapter bidPriceAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        startHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_book);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_book);

        Intent intent = getIntent();
        market = intent.getStringExtra("market");
        korName = intent.getStringExtra("korName");
        Log.i(TAG, "onCreate: " + market);

        setSupportActionBar(binding.toolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        /*if (!market.isEmpty()) {
            getOrderBook();
            getTicker();
        }*/

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getApplicationContext(),new LinearLayoutManager(this).getOrientation());
        dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.recyclerview_divider));
        binding.askPriceList.addItemDecoration(dividerItemDecoration);
        binding.bidPriceList.addItemDecoration(dividerItemDecoration);

    }

    private void startHandler(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!market.isEmpty()) {
                        getTicker();
                    }
                    handler.postDelayed(this, 200);
                }
            }, 0, 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (handler != null){
            handler.removeMessages(0);
        }
    }

    RetrofitApi service = RestfulAdapter.getInstance().getServiceApi();
    private void getOrderBook(double preClosingPrice, double nowClosingPrice){
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
                        if (handler != null){
                            handler.removeMessages(0);
                        }
                    }

                    @Override
                    public void onComplete() {
                        binding.market.setText(getList.get(0).getMarket());
//                        binding.timestamp.setText(new Plain().toTimeStamp(getList.get(0).getTimestamp()));
                        binding.korName.setText(korName);

                        /*binding.totalAskSize.setText(String.valueOf(getList.get(0).getTotal_ask_size()));
                        binding.totalBidSize.setText(String.valueOf(getList.get(0).getTotal_bid_size()));*/
                        binding.totalAskSize.setText(new Plain().roundDouble(getList.get(0).getTotal_ask_size()));
                        binding.totalBidSize.setText(new Plain().roundDouble(getList.get(0).getTotal_bid_size()));
                        if (getList.get(0).getItems().size() > 0){
                            askPriceAdapter = new AskPriceAdapter(getApplicationContext(), getList, preClosingPrice);
                            bidPriceAdapter = new BidPriceAdapter(getApplicationContext(), getList, preClosingPrice);
                            binding.askPriceList.setAdapter(askPriceAdapter);
                            binding.askPriceList.setNestedScrollingEnabled(true);
                            binding.bidPriceList.setAdapter(bidPriceAdapter);
                            binding.askPriceList.smoothScrollToPosition(7);
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
                            if (handler != null){
                                handler.removeMessages(0);
                            }
                        }

                        @Override
                        public void onComplete() {
                            int idx = market.indexOf("-");
                            String kind = market.substring(idx + 1);
                            double preClosingPrice = getTickerList.get(0).getPrev_closing_price();
                            double nowClosingPrice = getTickerList.get(0).getTrade_price();
                            Log.i(TAG, "onComplete: change : " + preClosingPrice);
                            binding.accTradeVolume24h.setText(String.format("거래량\n%s", String.format(Locale.KOREA, "%1$,.0f", getTickerList.get(0).getAcc_trade_volume_24h()) + " " + kind));
                            binding.accTradePrice24h.setText(String.format("거래대금\n%s", new Plain().toPlainString(String.valueOf(getTickerList.get(0).getAcc_trade_price_24h()))));
                            binding.highest52WeekPrice.setText(String.format("52주 최고\n%s", new Plain().toPlainString(String.valueOf(getTickerList.get(0).getHighest_52_week_price())) + "\n" + getTickerList.get(0).getHighest_52_week_date()));
                            binding.lowest52WeekPrice.setText(String.format("52주 최저\n%s", new Plain().toPlainString(String.valueOf(getTickerList.get(0).getLowest_52_week_price())) + "\n" + getTickerList.get(0).getLowest_52_week_date()));
                            binding.prevClosingPrice.setText(String.format("전일 종가\n%s", new Plain().toPlainString(String.valueOf(getTickerList.get(0).getPrev_closing_price()))));
                            binding.highPrice.setText(String.format("당일 고가\n%s", new Plain().toPlainString(String.valueOf(getTickerList.get(0).getHigh_price()))));
                            binding.lowPrice.setText(String.format("당일 저가\n%s", new Plain().toPlainString(String.valueOf(getTickerList.get(0).getLow_price()))));
                            getOrderBook(preClosingPrice, nowClosingPrice);
                        }
                    }));
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}