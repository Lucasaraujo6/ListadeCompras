package com.jogos.listadecompras;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Lista {
    private static String path1, pathPositions;
    private String path2, path3;
    FirebaseFirestore db;

    public Lista(int id, String name, String path2) {



    }
    public Lista(){
        db =  FirebaseFirestore.getInstance();
        path1 = "devices";
        this.path2 = db.toString();
        pathPositions = "positions";

        path3 = "nomedalista";
    }

//    public void setItem(int id) {
//        Map<String, Object> item;
////        item = Item.getItem();
//
//        db.collection("devices")
//                .document(path2).collection(path3).document(""+id).set(item);
//    }
}
