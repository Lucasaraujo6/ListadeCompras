package com.jogos.listadecompras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class ItemsListActivity extends AppCompatActivity {

    private RecyclerView itemsRecView;
    private ItemRecViewAdapter adapter;
    private ImageView imgShowMore, imgShowMore2;
    private EditText edTxtPants, edTxtName, edTxtBrand;
    private CheckBox checkBox;
    private TextView txtShowMore;

    private ArrayList<Item> items;
    private ArrayList<Integer> positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_list);

        adapter = new ItemRecViewAdapter(this);
        itemsRecView = findViewById(R.id.itensRecView);
        edTxtName = findViewById(R.id.edTxtName);
        edTxtBrand = findViewById(R.id.edTxtBrand);
        edTxtPants = findViewById(R.id.edTxtPants);
        checkBox = findViewById(R.id.checkbox);
        txtShowMore = findViewById(R.id.txtShowMore);
        imgShowMore = findViewById(R.id.imgShowMore);
        imgShowMore2 = findViewById(R.id.imgShowMore2);

        itemsRecView.setAdapter(adapter);
        itemsRecView.setLayoutManager(new LinearLayoutManager(this));



        items = new ArrayList<>();
        positions = new ArrayList<>();
        items.add(new Item(0, "Banana", 6,"Natural", 6, "https://www.infoescola.com/wp-content/uploads/2010/04/banana_600797891.jpg",null, false));
        positions.add(1);
        items.add(new Item(1, "Apple", 4,"Natural", 4, "https://www.collinsdictionary.com/images/thumb/apple_158989157_250.jpg",null, false));
        positions.add(0);
        items.add(new Item(2, "Orange", 2,"Natural", 6, "https://riviste.newbusinessmedia.it/wp-content/uploads/sites/27/2013/12/Fotolia_11313277_M-300x264.jpg",null, false));
        positions.add(2);
        items.add(new Item(3, "Papaya", 2,"Natural", 6, "https://comper.vteximg.com.br/arquivos/ids/182176-1000-1000/631906_mamao.jpg",null, false));
        positions.add(3);
        items.add(new Item(4, "Melon", 2,"Natural", 6, "https://deluxeproduce.com/wp-content/uploads/2017/06/canary-melon.jpg",null,true));
        positions.add(4);
        items.add(new Item(5, "Strawberry", 2,"Natural", 6, "https://www.collinsdictionary.com/images/full/strawberry_227472010.jpg",null, false));
        positions.add(5);
        items.add(new Item(6, "Lemon", 2,"Natural", 6, "https://t4.ftcdn.net/jpg/02/55/39/77/360_F_255397744_rwNCund3WjKsrsv6yKKpK8tzmJ8sYRnF.jpg",null, false));
        positions.add(6);

//        Parte do Drag and Drop
        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(itemsRecView);
        adapter.setItems(items, positions);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,0 ) {
//      ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,0 ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();


            adapter.notifyItemMoved(fromPosition,toPosition);
            adapter.setPositions(fromPosition,toPosition);



            return true;
        }

        @Override
        public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {
            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
//            if (fromPos<toPos){
//                adapter.notifyItemRangeChanged(fromPos,toPos);
//            } else {
//                adapter.notifyItemRangeChanged(toPos,fromPos);
//            }

//            adapter.setPositions(fromPos,toPos);
//            items= adapter.getItems();

//            EU CRIEI UM MÉTODO QUE FAZ A MESMA COISA QUE O ITEMRANGECHANGED
//            adapter.refresh(fromPos,toPos);
//            adapter.notifyDataSetChanged();

//            adapter.refresh(fromPos,toPos);
//            Collections.swap(items, fromPos, toPos);
//            adapter.notifyItemChanged(fromPos);
//            adapter.notifyItemChanged(toPos);
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }


    };

    public void onAddClicked(View view) {

        int lastPosition = items.size();
        items.add(lastPosition, new Item(lastPosition, edTxtName.getText().toString(), 84,"Natural", 100, "https://www.collinsdictionary.com/images/thumb/apple_158989157_250.jpg",null, false));

        adapter.setItems(items, positions);
        adapter.notifyDataSetChanged();
    }

    public void moreOptions(View view) {
        if (edTxtPants.getVisibility()==View.VISIBLE){
            edTxtPants.setVisibility(View.GONE);
            edTxtBrand.setVisibility(View.GONE);

            imgShowMore.setRotation(0);
            imgShowMore2.setRotation(0);


            txtShowMore.setText("Show More");
        }else{
            edTxtPants.setVisibility(View.VISIBLE);
            edTxtBrand.setVisibility(View.VISIBLE);
            txtShowMore.setText("Show Less");
            imgShowMore.setRotation(180);
            imgShowMore2.setRotation(180);

        };
    }

    public void reordenate(View view) {
        adapter.filterAtoZ();
    }
}