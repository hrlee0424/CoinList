package dr.com.coinscreen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dr.com.coinscreen.Plain;
import dr.com.coinscreen.R;
import dr.com.coinscreen.dto.TradesModel;

public class TradesAdapter extends RecyclerView.Adapter<TradesAdapter.ViewHolder> {
    public List<TradesModel> tradesModels;
    public Context context;
    private static final String TAG = "TradesAdapter";
    public TradesAdapter(Context context, List<TradesModel> models){
        this.context = context;
        this.tradesModels = models;
    }

    @NonNull
    @Override
    public TradesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.trades_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TradesAdapter.ViewHolder holder, int position) {
        if (tradesModels.get(position).getAsk_bid().equals("ASK")){
            holder.trade_volume.setTextColor(context.getResources().getColor(R.color.rateDownColor));
        }else{
            holder.trade_volume.setTextColor(context.getResources().getColor(R.color.rateUpColor));
        }
        String pre = new Plain().toPlainString(String.valueOf(tradesModels.get(position).getTrade_price()));
        if (pre.startsWith(".", 1)){
            double d = Double.parseDouble(pre);
//        DecimalFormat df = new DecimalFormat("#######.000");
//            Log.i(TAG, "onBindViewHolder: df : " + String.format(Locale.KOREA, "%.8f", d));
            holder.trade_price.setText(String.format(Locale.KOREA, "%.8f", d));
        }else{
            holder.trade_price.setText(pre);
        }
//        holder.trade_price.setText(new Plain().toPlainString(String.valueOf(tradesModels.get(position).getTrade_price())));
        holder.trade_volume.setText(new Plain().roundDouble(tradesModels.get(position).getTrade_volume()));
    }

    @Override
    public int getItemCount() {
        return tradesModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView trade_price, trade_volume;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            trade_price = itemView.findViewById(R.id.trade_price);
            trade_volume = itemView.findViewById(R.id.trade_volume);
        }
    }
}
