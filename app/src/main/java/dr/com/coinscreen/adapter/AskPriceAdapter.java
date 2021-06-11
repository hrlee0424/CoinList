package dr.com.coinscreen.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dr.com.coinscreen.Plain;
import dr.com.coinscreen.R;
import dr.com.coinscreen.dto.OrderBookModel;

public class AskPriceAdapter extends RecyclerView.Adapter<AskPriceAdapter.ViewHolder> {
    private static final String TAG = "OrderBookAdapter";
    public List<OrderBookModel> orderBookModelList;
    public Context context;
    public double preClosingPrice;

    public AskPriceAdapter(Context context, List<OrderBookModel> models, double preClosingPrice){
        this.context = context;
        this.orderBookModelList = models;
        this.preClosingPrice = preClosingPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.orderbook_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.ask_price.setText(String.valueOf(orderBookModelList.get(0).getItems().get(position).getAsk_price()));
        double now_orderBook = orderBookModelList.get(0).getItems().get(position).getAsk_price();
        String rate = new Plain().toFluctuationRate(now_orderBook, preClosingPrice);
        Log.i(TAG, "onBindViewHolder: ssssssss " + rate);
        if (now_orderBook < preClosingPrice){
            holder.ask_price.setTextColor(Color.BLUE);
        }else if(now_orderBook == preClosingPrice){
            holder.ask_price.setTextColor(Color.BLACK);
        }else {
            holder.ask_price.setTextColor(Color.RED);
        }

        if (rate.contains("-100")){
            holder.ask_price.setText("");
        }else{
            holder.ask_price.setText(String.format("%s%s", new Plain().toPlainString(String.valueOf(now_orderBook)), " " + rate + "%"));
        }
    }

    @Override
    public int getItemCount() {
        return orderBookModelList.get(0).getItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView ask_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ask_price = itemView.findViewById(R.id.ask_price);
        }
    }
}
