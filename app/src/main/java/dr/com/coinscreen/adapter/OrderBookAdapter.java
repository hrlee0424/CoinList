package dr.com.coinscreen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dr.com.coinscreen.R;
import dr.com.coinscreen.dto.OrderBookModel;

public class OrderBookAdapter extends RecyclerView.Adapter<OrderBookAdapter.ViewHolder> {
    private static final String TAG = "OrderBookAdapter";
    public List<OrderBookModel> orderBookModelList;
    public Context context;

    public OrderBookAdapter(Context context, List<OrderBookModel> models){
        this.context = context;
        this.orderBookModelList = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.orderbook_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.i(TAG, "onBindViewHolder: " + orderBookModelList.get(0).getItems().get(position).getAsk_price());
        holder.ask_price.setText(String.valueOf(orderBookModelList.get(0).getItems().get(position).getAsk_price()));
        holder.bid_price.setText(String.valueOf(orderBookModelList.get(0).getItems().get(position).getBid_price()));
        holder.ask_size.setText(String.valueOf(orderBookModelList.get(0).getItems().get(position).getAsk_size()));
        holder.bid_size.setText(String.valueOf(orderBookModelList.get(0).getItems().get(position).getBid_size()));
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
            bid_price = itemView.findViewById(R.id.bid_price);
            ask_size = itemView.findViewById(R.id.ask_size);
            bid_size = itemView.findViewById(R.id.bid_size);
        }
    }
}
