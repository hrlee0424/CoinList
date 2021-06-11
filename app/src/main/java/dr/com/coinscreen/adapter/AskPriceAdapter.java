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
    private final double change;

    public AskPriceAdapter(Context context, List<OrderBookModel> models, double change){
        this.context = context;
        this.orderBookModelList = models;
        this.change = change;
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
        double num = orderBookModelList.get(0).getItems().get(position).getAsk_price();
        if (num < change){
            holder.ask_price.setTextColor(Color.BLUE);
        }else if(num == change){
            Log.i(TAG, "onBindViewHolder: 222222222222");
            Log.i(TAG, "onBindViewHolder: 222222222222" + "num : " + num + "   " + "change : " + change);
            holder.ask_price.setTextColor(Color.BLACK);
        }else {
            Log.i(TAG, "onBindViewHolder: 33333333333");
            Log.i(TAG, "onBindViewHolder: 33333333333" + "num : " + num + "   " + "change : " + change);
            holder.ask_price.setTextColor(Color.RED);
        }

        holder.ask_price.setText(new Plain().toPlainString(String.valueOf(num)));

        /*switch (change){
            case num < change :
                holder.ask_price.setTextColor(Color.BLACK);
                break;
            case "RISE" :
                holder.ask_price.setTextColor(Color.RED);
                break;
            case "FALL" :
                holder.ask_price.setTextColor(Color.BLUE);
                break;
        }*/

    }

    @Override
    public int getItemCount() {
        return orderBookModelList.get(0).getItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView ask_price, bid_price, ask_size, bid_size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ask_price = itemView.findViewById(R.id.ask_price);
//            bid_price = itemView.findViewById(R.id.bid_price);
//            ask_size = itemView.findViewById(R.id.ask_size);
//            bid_size = itemView.findViewById(R.id.bid_size);
        }
    }
}
