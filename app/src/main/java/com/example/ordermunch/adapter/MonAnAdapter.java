package com.example.ordermunch.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordermunch.GlideHelper;
import com.example.ordermunch.R;
import com.example.ordermunch.itf.ICallBack;
import com.example.ordermunch.model.DataModel;
import com.example.ordermunch.model.MonAnModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.ViewHolder> {
    private ArrayList<MonAnModel> data = new ArrayList<MonAnModel>();

    private ICallBack iCallBack;

    public MonAnAdapter(ArrayList<MonAnModel> data, ICallBack iCallBack) {
        this.data = data;
        this.iCallBack = iCallBack;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_mon_an, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAnModel dataModel = data.get(position);

        holder.txt_name.setText(dataModel.getName());
        holder.txt_name.setText(Html.fromHtml(dataModel.getName()));
        GlideHelper.loadImage(dataModel.getImage(), holder.img_ma);

        holder.itemView.setOnClickListener(v -> iCallBack.onClickImage(dataModel.getName() , dataModel.getImage(), dataModel.getDescription()));

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_ma;
        TextView txt_id, txt_name, txt_short_description, txt_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_ma = itemView.findViewById(R.id.img_ma);
            txt_name = itemView.findViewById(R.id.txt_name_ma);
        }
    }
}
