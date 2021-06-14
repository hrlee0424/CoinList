package dr.com.coinscreen.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dr.com.coinscreen.R;

public class BidSizeAdapter extends RecyclerView.Adapter<BidSizeAdapter.ViewHolder>{
    @NonNull
    @Override
    public BidSizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BidSizeAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView bid_size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bid_size = itemView.findViewById(R.id.ask_price);

        }
    }
}
