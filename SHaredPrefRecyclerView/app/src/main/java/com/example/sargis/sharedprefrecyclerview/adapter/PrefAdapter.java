package com.example.sargis.sharedprefrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sargis.sharedprefrecyclerview.model.PrefData;
import com.example.sargis.sharedprefrecyclerview.R;
import com.example.sargis.sharedprefrecyclerview.utilities.PrefUtil;

import java.util.List;

public class PrefAdapter extends RecyclerView.Adapter<PrefAdapter.PrefViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<PrefData> list;

    public PrefAdapter(Context context, List<PrefData> prefDataList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = prefDataList;
    }

    @NonNull
    @Override
    public PrefViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.item, parent, false);
        return new PrefViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrefViewHolder holder, int position) {
        final PrefData model = list.get(position);
        holder.textViewKey.setText(model.key);
        holder.textViewValue.setText(model.value);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PrefViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewKey;
        private TextView textViewValue;

        PrefViewHolder(View itemView) {
            super(itemView);
            textViewKey = itemView.findViewById(R.id.text_view_key);
            textViewValue = itemView.findViewById(R.id.text_view_value);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final PrefData prefData = PrefAdapter.this.list.get(getAdapterPosition());
                    PrefUtil.removeString(context, prefData.key);
                    PrefAdapter.this.list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}