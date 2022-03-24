package com.jogos.listadecompras;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ItemsListActivity extends AppCompatActivity {

    private RecyclerView itemsRecView;
    private ItemRecViewAdapter adapter;
    private ImageView imgShowMore, imgShowMore2;
    private EditText edTxtPants, edTxtName, edTxtBrand;
    private CheckBox checkBox;
    private TextView txtShowMore;
    private Lista lista;

    private ArrayList<Item> items;
    private ArrayList<Integer> positions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_list);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


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
//        Map<String, Object> user = new HashMap<>();
//        user.put("login","testeeee" );
//        user.put("first", "firstName");
//        user.put("last", "Lovelace");
//        user.put("born", 1815);
//        lista = new Lista();
////        PATH É O CAMINHO DENTRO DA COLEÇÃO DO DB.
////        O PRIMEIRO VAI SER O PADRÃO E O SEGUNDO PRA TESTE.
////        String path = db.toString();
//        String path = "Primeiro";
//
//        db.collection("devices")
//                .document(path).set(user);

//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
////                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    private String TAG;
//
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
        //    CRIANDO UM NOVO NÓ NO DATABASE
//    private void setUser(){

//    }
        items = new ArrayList<>();
        positions = new ArrayList<>();
        items.add(new Item(0, "Banana", 6,"Natural", 6, "https://www.infoescola.com/wp-content/uploads/2010/04/banana_600797891.jpg",null, false));
        positions.add(1);

//        lista.setItem(0);
        items.add(new Item(1, "Apple", 4,"Natural", 4, "https://www.collinsdictionary.com/images/thumb/apple_158989157_250.jpg",null, false));
        positions.add(0);
//        lista.setItem(1);
        items.add(new Item(2, "Orange", 2,"Natural", 6, "https://riviste.newbusinessmedia.it/wp-content/uploads/sites/27/2013/12/Fotolia_11313277_M-300x264.jpg",null, false));
        positions.add(2);

//        lista.setItem(2);
        items.add(new Item(3, "Papaya", 2,"Natural", 6, "https://comper.vteximg.com.br/arquivos/ids/182176-1000-1000/631906_mamao.jpg",null, false));
        positions.add(3);

//        lista.setItem(3);
        items.add(new Item(4, "Melon", 2,"Natural", 6, "https://deluxeproduce.com/wp-content/uploads/2017/06/canary-melon.jpg",null,true));
        positions.add(4);

//        lista.setItem(4);
        items.add(new Item(5, "Strawberry", 2,"Natural", 6, "https://www.collinsdictionary.com/images/full/strawberry_227472010.jpg",null, false));
        positions.add(5);

//        lista.setItem(5);
        items.add(new Item(6, "Lemon", 2,"Natural", 6, "https://t4.ftcdn.net/jpg/02/55/39/77/360_F_255397744_rwNCund3WjKsrsv6yKKpK8tzmJ8sYRnF.jpg",null, false));
        positions.add(6);

//        lista.setItem(6);
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
        items.add(lastPosition, new Item(lastPosition, edTxtName.getText().toString(), 84,"", 100, "https://www.collinsdictionary.com/images/thumb/apple_158989157_250.jpg",null, false));
        int id = lastPosition;
//        int test = id-1;
        String name = edTxtName.getText().toString();
        double amount = 25;
        String brand = "Natural";
        int price = 100;
        String imageUrl = "QUALQUER URL";
        String observations = null;
        boolean isChecked = false;
            Map<String, Object>item = new HashMap<>();
            item.put("id", id );
            if (!name.equals("")){item.put("name", name);};
            if (amount>0){item.put("amount", amount);}
            if (!brand.equals("")){item.put("brand", brand);}
            if (price>0){item.put("price",price);}
            if (imageUrl!=""){item.put("imageurl",imageUrl);}
            if (observations!=null){item.put("observations",observations);}
            if (id>0){item.put("checked", isChecked);}
        sendToCloud(lastPosition, item);
//        getFromCloud("nomedalista", ""+lastPosition);


        adapter.setItems(items, positions);
        adapter.notifyDataSetChanged();
    }
    public void getFromCloud(String nomeDaLista,String id){
        Map ds;
        ds = db.collection("devices")
                .document(path2).collection(nomeDaLista).document(id).get().getResult().toObject(Map.class);
        Collection values = ds.values();
        Toast.makeText(this, ds.toString(), Toast.LENGTH_SHORT).show();



//        loginRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    //Toast.makeText(MainActivity.this, document.toString(), Toast.LENGTH_SHORT).show();
//
//                    if (document.exists()) {
//                        Toast.makeText(ItemsListActivity.this,
//                                , Toast.LENGTH_SHORT).show();
//
//                    } else {
//
//                    }
//                } else {
//
//
//                }
//            }
//        });
    }
    public void sendToCloud(int id, Map<String, Object> item){
        db =  FirebaseFirestore.getInstance();
        this.path2 = db.toString();
        path3 = "nomedalista";

        db.collection("devices")
                .document(path2).collection(path3).document(""+id)
                .set(item);
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Map ds;
//                        ds = db.collection("devices")
//                                .document(path2).collection(path3).document(""+id).get().getResult().toObject(Map.class);
//                        Toast.makeText(ItemsListActivity.this, ds.toString(), Toast.LENGTH_SHORT).show();
//
//                    }
////
////                    @Override
////                    public void onComplete(Void aVoid) {
////                        Map ds;
////                        ds = db.collection("devices")
////                                .document(path2).collection(path3).document(""+id).get().getResult().toObject(Map.class);
////                        Toast.makeText(ItemsListActivity.this, ds.toString(), Toast.LENGTH_SHORT).show();
////
//////                        Log.d(TAG, "DocumentSnapshot successfully written!");
////                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error writing document", e);
//                    }
//                });
        final DocumentReference docRef = db.collection("devices").document(path2).collection(path3).document(""+id);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Map ds;
                        ds = snapshot.getData();
                        Toast.makeText(ItemsListActivity.this, ds.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Current data: " + snapshot.getData());
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
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
    private String path2, path3;
    FirebaseFirestore db;


    public void reordenate(View view) {
        adapter.filterAtoZ();
    }

}