package com.example.packmybag.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.packmybag.Constants.MyConstants;
import com.example.packmybag.Database.RoomDB;
import com.example.packmybag.Models.Items;
import com.example.packmybag.R;

import java.util.List;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListViewHolder>{
    Context context;
    List<Items> itemsList;
    RoomDB database;
    String show;

    public CheckListAdapter() {
    }

    public CheckListAdapter(Context context, List<Items> itemsList, RoomDB database, String show) {
        this.context = context;
        this.itemsList = itemsList;
        this.database = database;
        this.show = show;
        if(itemsList.size()==0){
            Toast.makeText(context.getApplicationContext(), "Nothing To Show", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public CheckListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckListViewHolder(LayoutInflater.from(context).inflate(R.layout.check_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckListViewHolder holder, int position) {
        holder.checkBox.setText(itemsList.get(position).getItemname());
        holder.checkBox.setChecked(itemsList.get(position).getChecked());

        if (MyConstants.FALSE_STRING.equals(show)){
            holder.btnDelete.setVisibility(View.GONE);
            holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.border_one));
        } else {
            if (itemsList.get(position).getChecked()){
                holder.layout.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            }
            else
                holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.border_one));
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = holder.checkBox.isChecked();
                database.mainDao().checkUnchecked(itemsList.get(position).getID(), check);
                if (MyConstants.FALSE_STRING.equals(show)){
                    itemsList = database.mainDao().getAllSelected(true);
                    notifyDataSetChanged();
                }
                else {
                    itemsList.get(position).setChecked(check);
                    notifyDataSetChanged();
                    Toast toastMessage = null;
                    if(toastMessage!=null){
                        toastMessage.cancel();
                    }
                    if(itemsList.get(position).getChecked()){
                        toastMessage = Toast.makeText(context,"("+holder.checkBox.getText()+") packed",Toast.LENGTH_SHORT);
                    }
                    else {
                        toastMessage = Toast.makeText(context,"("+holder.checkBox.getText()+") Un-packed",Toast.LENGTH_SHORT);
                    }
                    toastMessage.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}

class CheckListViewHolder extends RecyclerView.ViewHolder{

    LinearLayout layout;
    CheckBox checkBox;
    Button btnDelete;

    public CheckListViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.linearLayout);
        checkBox = itemView.findViewById(R.id.checkbox);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}
