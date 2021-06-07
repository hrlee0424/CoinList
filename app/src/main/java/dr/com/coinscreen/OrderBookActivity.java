package dr.com.coinscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dr.com.coinscreen.adapter.AskPriceAdapter;
import dr.com.coinscreen.adapter.BidPriceAdapter;
import dr.com.coinscreen.databinding.DetailBinding;
import dr.com.coinscreen.dto.OrderBookModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

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
        }

    }

    List<OrderBookModel> getList;
    private void getOrderBook(){
        RetrofitApi service = RestfulAdapter.getInstance().getServiceApi();
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
                Log.i(TAG, "onComplete: 1111" + getList.get(0).getItems().get(1).getBid_price());
                if (getList.get(0).getItems().size() > 0){
                    AskPriceAdapter askPriceAdapter = new AskPriceAdapter(getApplicationContext(), getList);
                    BidPriceAdapter bidPriceAdapter = new BidPriceAdapter(getApplicationContext(), getList);
                    binding.askPriceList.setAdapter(askPriceAdapter);
                    binding.askPriceList.setNestedScrollingEnabled(true);
                    binding.bidPriceList.setAdapter(bidPriceAdapter);
                    binding.bidPriceList.setNestedScrollingEnabled(true);
                }
            }
        }));


    }
}