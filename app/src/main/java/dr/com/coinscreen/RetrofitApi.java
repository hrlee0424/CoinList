package dr.com.coinscreen;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApi {
    @Headers({"content-type:application/json"})
    @GET("market/all")
    Observable<List<GetMainList>> getList();
    @GET("orderbook")
    Observable<List<OrderBookModel>> getOrderBookItem(@Query("markets")String repo);

}
