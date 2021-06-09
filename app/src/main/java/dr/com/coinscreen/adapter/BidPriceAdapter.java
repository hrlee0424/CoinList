package dr.com.coinscreen.adapter;

import android.content.Context;
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

public class BidPriceAdapter extends RecyclerView.Adapter<BidPriceAdapter.ViewHolder> {
    public List<OrderBookModel> orderBookModelList;
    public Context context;

    public BidPriceAdapter(Context context, List<OrderBookModel> models){
        this.context = context;
        this.orderBookModelList = models;
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
//        holder.bid_price.setText(String.valueOf(orderBookModelList.get(0).getItems().get(position).getBid_price()));
        holder.bid_price.setText(new Plain().toPlainString(String.valueOf(orderBookModelList.get(0).getItems().get(position).getBid_price())));
//        holder.bid_price.setText(String.format("%1$,.0f", orderBookModelList.get(0).getItems().get(position).getBid_price()));
    }

    @Override
    public int getItemCount() {
        return orderBookModelList.get(0).getItems().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView bid_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bid_price = itemView.findViewById(R.id.ask_price);

        }
    }
}
