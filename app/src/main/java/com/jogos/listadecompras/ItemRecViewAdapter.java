package com.jogos.listadecompras;

import static android.view.DragEvent.ACTION_DROP;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class ItemRecViewAdapter extends RecyclerView.Adapter<ItemRecViewAdapter.ViewHolder>

{

    public Context getContext;
    private ArrayList<Item> items = new ArrayList<>();
    private Context mContext;
    private int fromPosition;
    private int toPosition;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    private LayoutParams layoutParams;
//    private static final String RECYCLERVIEW_TAG = "icon bitmap";
    private ArrayList<Integer> positions;
    private boolean theresChange;
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



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


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        holder.parent.setTag(RECYCLERVIEW_TAG);


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.get(position).setChecked(items.get(position).isChecked());
                holder.txtName.setText(""+items.get(position).isChecked()+items.get(position).getName());
        //        notifyDataSetCha
                //        ged();

            }
        });
//        Glide.with(mContext)
//                .asBitmap()
//                .load(items.get(position).getImageUrl())
//                .into(holder.imageItem);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, items.get(position).getName() + "Selected", Toast.LENGTH_SHORT).show();
            }
        });

//        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            @Override
//            public boolean onLongClick(View v) {
//                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
//                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
//
//                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
//
//                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(holder.parent);
//
//
//                v.startDrag(dragData,myShadow,null,0);
//
//                return true;
//            }
//        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//        holder.parent.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                switch(event.getAction()) {
//                    case DragEvent.ACTION_DRAG_STARTED:
////                        Toast.makeText(mContext, "Action is DragEvent.ACTION_DRAG_STARTED", Toast.LENGTH_SHORT).show();
//
//                        break;
//
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        Toast.makeText(mContext, "Action is DragEvent.ACTION_DRAG_ENTERED", Toast.LENGTH_SHORT).show();
//
//
//                        break;
//
//                    case DragEvent.ACTION_DRAG_EXITED :
//                        Toast.makeText(mContext, "Action is DragEvent.ACTION_DRAG_EXITED", Toast.LENGTH_SHORT).show();
////                        notifyItemRangeInserted(getItemCount() - 1 - getItemCount(), getItemCount() - 1);
//                        Collections.swap(items, fromPosition, toPosition);
//
////                        v.setLayoutParams(layoutParams);
//                        break;
//
//                    case DragEvent.ACTION_DRAG_LOCATION  :
////                        Toast.makeText(mContext, "Action is DragEvent.ACTION_DRAG_LOCATION", Toast.LENGTH_SHORT).show();
////
////                        x_cord = (int) event.getX();
////                        y_cord = (int) event.getY();
//                        break;
//
//                    case DragEvent.ACTION_DRAG_ENDED   :
////                        Toast.makeText(mContext, "Action is DragEvent.ACTION_DRAG_ENDED", Toast.LENGTH_SHORT).show();
//
//
//                        // Do nothing
//                        break;
//
//                    case DragEvent.ACTION_DROP:
//                        Toast.makeText(mContext, "ACTION_DROP event", Toast.LENGTH_SHORT).show();
//                        Collections.swap(items, fromPosition, toPosition);
//
//
//                        // Do nothing
//                        break;
//                    default: break;
//                }
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<Item> items,ArrayList<Integer> positions) {
        ArrayList<Item> mostrando = new ArrayList<>();
        positions.add(0,items.size()-1);
        for (int i = 0;i<items.size();i++){
            mostrando.add(items.get(positions.get(i)));
        }
        this.items = mostrando;
        this.positions = positions;

        notifyDataSetChanged();
    }



//    public void insertItem(ArrayList<Item> items) {
//         positions.add(0,0);
//         for (int i = 1;i<positions.size();i++){
//             positions.set(i,positions.get(i)+1);
//             items.get(i).setId(items.get(i).getId()+1);
//        }
//         correctItemsPositions(items);
//    }
public void adicionar(ArrayList<Item> items, ArrayList<Integer> positions ) {
    positions.add(0,items.size()-1);
    this.positions = positions;
    this.items=items;
//    correctItemsPositions(items);
//SE A CONDIÇÃO PARA ORGANIZAR EM ORDEM ALFABÉTICA FOR SUPRIDA, FAZER O SEGUINTE
//    for (int i =0;i<items.size();i++){
//        int j=i;
//        while(j>0 && items.get(j).getName().toLowerCase(Locale.ROOT).compareTo(items.get(j-1).getName().toLowerCase(Locale.ROOT))<0 ){
//            Collections.swap(items, j,j-1);
//            j--;
//        }
//    }
    System.out.println("Novo item adicionado com sucesso!!!\n");

}

//    public ArrayList<Item> getItems(int fromPosition, int toPosition ) {
//    public ArrayList<Item> getItems() {
//        Collections.swap(items,fromPosition,toPosition);
//        notifyItemChanged(fromPosition);
//        notifyItemChanged(toPosition);
//
//
//        return this.items;
//    }

    public void refresh() {
//        Collections.swap(items,fromPosition,toPosition);
        notifyDataSetChanged();
//        if(fromPosition<toPosition) {
//            for (int i = fromPosition; i < toPosition + 1; i++) {
//
//                notifyItemChanged(i);
//            }
//        }else if(fromPosition>toPosition) {
//            for (int i = toPosition; i < fromPosition + 1; i++) {
//                notifyItemChanged(i);
//            }
//        }
    }

    public void setPositions(int fromPosition, int toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
        Collections.swap(positions,fromPosition,toPosition);



    }

    public void filterAtoZ() {
        //todo: falta configurar o novo vetor de posições para essa configuração
        //todo: falta o z to A
        for (int i =0;i<items.size();i++){
            int j=i;
            while(j>0 && items.get(j).getName().toLowerCase(Locale.ROOT).compareTo(items.get(j-1).getName().toLowerCase(Locale.ROOT))<0 ){
                Collections.swap(items, j,j-1);
                Collections.swap(positions,j,j-1);
                j--;
            }
        }
        notifyDataSetChanged();
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
