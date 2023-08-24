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
import com.example.ordermunch.model.DoUongModel;

import java.util.ArrayList;

public class DoUongAdapter extends RecyclerView.Adapter<DoUongAdapter.ViewHolder> {

    private ArrayList<DoUongModel> data = new ArrayList<DoUongModel>();
    private ICallBack iCallBack;

    public DoUongAdapter(ArrayList<DoUongModel> data, ICallBack iCallBack) {
        this.data = data;
        this.iCallBack = iCallBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_do_uong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoUongModel dataModel = data.get(position);

        holder.txt_name.setText(dataModel.getName());
        holder.txt_name.setText(Html.fromHtml(dataModel.getName()));
        GlideHelper.loadImage(dataModel.getImage(), holder.img_doUong);

        holder.itemView.setOnClickListener(v -> iCallBack.onClickImage(dataModel.getName(), dataModel.getImage() ,dataModel.getDescription()));

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_doUong;
        TextView  txt_name, txt_short_description, txt_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_doUong = itemView.findViewById(R.id.img_douong);
            txt_name = itemView.findViewById(R.id.txt_name_douong);
        }
    }
}
