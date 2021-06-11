package dr.com.coinscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import dr.com.coinscreen.adapter.MainRecyclerViewAdapter;
import dr.com.coinscreen.databinding.ActivityMainBinding;
import dr.com.coinscreen.dto.GetMainList;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getApplicationContext(),new LinearLayoutManager(this).getOrientation());
        binding.mainList.addItemDecoration(dividerItemDecoration);

        getTicker();
    }

    List<GetMainList> getMainList;
    private void getTicker(){
        RetrofitApi service = RestfulAdapter.getInstance().getServiceApi();
        Observable<List<GetMainList>> observable = service.getList();
        //retrofit + okHttp + rxJava
        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<GetMainList>>(){

                    @Override
                    public void onNext(List<GetMainList> value) {
                        getMainList = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(getMainList, getApplicationContext());
                        binding.mainList.setAdapter(adapter);
//                        Log.i(TAG, "onComplete: " + );
//                        binding.market.setText(getList.get(1).getMarket());
//                        binding.koreanName.setText(getList.get(1).getKorean_name());
//                        binding.englishName.setText(getList.get(1).getEnglish_name());

                    }
                })
        );

    }
}