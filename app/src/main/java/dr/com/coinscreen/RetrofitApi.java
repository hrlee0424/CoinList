package dr.com.coinscreen;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RetrofitApi {
    @Headers({"content-type:application/json"})
    @GET("market/all")
    Observable<List<GetList>> getList();

}
