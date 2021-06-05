package dr.com.coinscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dr.com.coinscreen.databinding.ActivityMainBinding;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

        getTicker();
    }

    List<GetList> getList;
    private void getTicker(){
        RetrofitApi service = RestfulAdapter.getInstance().getServiceApi();
//        Observable<GetList> listObservable = service.getList();
        Observable<List<GetList>> observable = service.getList();
        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<GetList>>(){

                    @Override
                    public void onNext(List<GetList> value) {
                        Log.i(TAG, "onNext: " + value.toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.toString());
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: 111111" + getList.get(0).getItems().get(0).getMarket() + " " + getList.get(0).getItems().get(0).getKorean_name());
//                        binding.market.setText(getList.get(1).getMarket());
//                        binding.koreanName.setText(getList.get(1).getKorean_name());
//                        binding.englishName.setText(getList.getItems().get(0).getEnglish_name());
                    }
                })
        );
    }
}