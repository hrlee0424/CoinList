package dr.com.coinscreen.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dr.com.coinscreen.dto.GetMainList;
import dr.com.coinscreen.OrderBookActivity;
import dr.com.coinscreen.R;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    public List<GetMainList> getMainList;
    public  Context context;
    public MainRecyclerViewAdapter(List<GetMainList> getList, Context getContext){
        this.getMainList = getList;
        this.context = getContext;
    }

/*public class MainRecyclerViewAdapter extends RecyclerView.Adapter<BindingViewHolder<ListItemBinding>.ViewHolder> {
    public List<GetMainList> getMainList;

    public MainRecyclerViewAdapter(List<GetMainList> getList){
        getMainList = getList;
    }

    @NonNull
    @Override
    public BindingViewHolder<ListItemBinding>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.mainlist_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ListItemBinding>.ViewHolder holder, int position) {
        holder.binding().setViewModel(listItemViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return getMainList.size();
    }*/

    @NonNull
    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.mainlist_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.market.setText(getMainList.get(position).getMarket());
        holder.koreanName.setText(getMainList.get(position).getKorean_name());
        holder.englishName.setText(getMainList.get(position).getEnglish_name());
    }

    @Override
    public int getItemCount() {
        return getMainList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView market, koreanName, englishName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            market = itemView.findViewById(R.id.market);
            koreanName = itemView.findViewById(R.id.koreanName);
            englishName = itemView.findViewById(R.id.englishName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
//                       Toast.makeText(context, getMainList.get(position).getKorean_name(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, OrderBookActivity.class);
                        intent.putExtra("market", getMainList.get(position).getMarket());
                        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                }
            });

        }
    }

}
