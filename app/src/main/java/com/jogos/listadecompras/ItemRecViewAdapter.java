package com.jogos.listadecompras;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemRecViewAdapter extends RecyclerView.Adapter<ItemRecViewAdapter.ViewHolder>
{

    private ArrayList<Item> items = new ArrayList<>();
    private Context mContext;

    public ItemRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtName.setText(items.get(position).getName());
        holder.txtQntd.setText(items.get(position).getAmount());
        holder.checkBox.setChecked(items.get(position).isChecked());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.get(position).setChecked(items.get(position).isChecked());
                holder.txtName.setText(""+items.get(position).isChecked()+items.get(position).getName());
        //        notifyDataSetChanged();

            }
        });
        Glide.with(mContext)
                .asBitmap()
                .load(items.get(position).getImageUrl())
                .into(holder.imageItem);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, items.get(position).getName() + "Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }
    public ArrayList<Item> getItems() {
        return this.items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private CardView parent;
        private CheckBox checkBox;
        private EditText txtName, txtQntd;
        private ImageView imageItem;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtName = itemView.findViewById(R.id.edTxtName);
            txtQntd = itemView.findViewById(R.id.edTxtQntd);
            imageItem = itemView.findViewById(R.id.imageItem);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
