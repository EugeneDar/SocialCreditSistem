package com.barbar.npkproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    //DatabaseReference myPef;

    private final Map<String, List<String>> allDataMap;

    private void initializeListsOfMaps() {
        allDataMap.put("rating", new ArrayList<>());
    }

    public Database (DatabaseReference myRef) {
        allDataMap = new HashMap<>();

        initializeListsOfMaps();
        fillMapFromDatabase(myRef);
    }

    private void fillMapFromDatabase (DatabaseReference myRef) {
        myRef.child("rating").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String data = snapshot.getValue(String.class);
                allDataMap.get("rating").add(data);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public Map<String, List<String>> getAllDataMap () {
        return allDataMap;
    }
}
