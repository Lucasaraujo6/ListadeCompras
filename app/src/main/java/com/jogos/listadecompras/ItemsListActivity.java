package com.jogos.listadecompras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class ItemsListActivity extends AppCompatActivity {

    private RecyclerView itemsRecView;
    private ItemRecViewAdapter adapter;
    private ImageView imageNewItem;
    private EditText edTxtPants, edTxtName;
    private CheckBox checkBox;

    private ArrayList<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_list);

        adapter = new ItemRecViewAdapter(this);
        itemsRecView = findViewById(R.id.itensRecView);
        edTxtName = findViewById(R.id.edTxtName);
        edTxtPants = findViewById(R.id.edTxtPants);
        checkBox = findViewById(R.id.checkbox);
        itemsRecView.setAdapter(adapter);
        itemsRecView.setLayoutManager(new GridLayoutManager(this, 1));

        items = new ArrayList<>();
        items.add(new Item(0, "Banana", 6,"Natural", 6, "https://www.infoescola.com/wp-content/uploads/2010/04/banana_600797891.jpg",null, false));
        items.add(new Item(1, "Apple", 4,"Natural", 4, "https://www.collinsdictionary.com/images/thumb/apple_158989157_250.jpg",null, false));
        items.add(new Item(2, "Orange", 2,"Natural", 6, "https://riviste.newbusinessmedia.it/wp-content/uploads/sites/27/2013/12/Fotolia_11313277_M-300x264.jpg",null, false));
        items.add(new Item(3, "Papaya", 2,"Natural", 6, "https://comper.vteximg.com.br/arquivos/ids/182176-1000-1000/631906_mamao.jpg",null, false));
        items.add(new Item(4, "Melon", 2,"Natural", 6, "https://deluxeproduce.com/wp-content/uploads/2017/06/canary-melon.jpg",null,true));
        items.add(new Item(5, "Strawberry", 2,"Natural", 6, "https://www.collinsdictionary.com/images/full/strawberry_227472010.jpg",null, false));
        items.add(new Item(6, "Lemon", 2,"Natural", 6, "https://t4.ftcdn.net/jpg/02/55/39/77/360_F_255397744_rwNCund3WjKsrsv6yKKpK8tzmJ8sYRnF.jpg",null, false));

        adapter.setItems(items);

//        Parte do Drag and Drop
        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(itemsRecView);


    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,0 ) {
//      ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END,0 ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

//            FUNCIONA MAS SÓ DEIXA ROLAR 5 LINHAS
//                adapter.notifyItemMoved(fromPosition,toPosition);
//                adapter.notifyItemChanged(fromPosition);
//                adapter.notifyItemChanged(toPosition);

//          muda o que está sendo exibido
            adapter.notifyItemMoved(fromPosition,toPosition);
            Collections.swap(items, fromPosition, toPosition);
            


//                adapter.setPositions(fromPosition, toPosition);

                //muda o banco de dados
//            Collections.swap(items, fromPosition, toPosition);

                //atualiza o que está sendo exibido para o novo banco de dados.
                //isso pooderia ser efetivado somente no ONDROP, porém ainda não controlo os eventos individualmente.


//               adapter.setItems(items);


//            adapter.setItems(Collections.swap(items, fromPosition, toPosition));
                //               Collections.swap(items, fromPosition, toPosition);
                //               recyclerView.getAdapter().notifyItemMoved(fromPosition,toPosition);

      //      Item temp = items.get(fromPosition);


//            items.remove(fromPosition);
//            items.add(toPosition, temp);

//            PADRÃO
//            adapter.notifyItemMoved(fromPosition, toPosition);

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

//            EU CRIEI UM MÉTODO QUE FAZ A MESMA COISA QUE O ITEMRANGECHANGED
//            adapter.refresh(fromPos,toPos);

        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

    public void onImgClicked(View view) {
        if (edTxtPants.getVisibility()==View.VISIBLE){
            edTxtPants.setVisibility(View.GONE);
        }else{
            edTxtPants.setVisibility(View.VISIBLE);
        };
        int lastPosition = items.size()-1;
        items.add(0, new Item(lastPosition+1, edTxtName.getText().toString(), 84,"Natural", lastPosition, "https://www.collinsdictionary.com/images/thumb/apple_158989157_250.jpg",null, false));
//        adapter.notifyItemInserted(0);
        adapter.notifyDataSetChanged();

    }
}