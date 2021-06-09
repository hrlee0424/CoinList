package dr.com.coinscreen;

import java.util.List;

import dr.com.coinscreen.dto.GetMainList;
import dr.com.coinscreen.dto.OrderBookModel;
import dr.com.coinscreen.dto.TickerModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitApi {
    @Headers({"content-type:application/json"})
    @GET("market/all")
    Observable<List<GetMainList>> getList();
    @GET("orderbook")
    Observable<List<OrderBookModel>> getOrderBookItem(@Query("markets")String repo);
    @GET("ticker")
    Observable<List<TickerModel>> getTickerList(@Query("markets")String repo);

}
