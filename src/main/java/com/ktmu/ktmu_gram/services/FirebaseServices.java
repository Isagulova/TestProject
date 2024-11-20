package com.ktmu.ktmu_gram.services;

import com.google.firebase.database.*;
import com.ktmu.ktmu_gram.config.FirebaseConfig;
import com.ktmu.ktmu_gram.model.TestEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FirebaseServices extends FirebaseConfig {
    private DatabaseReference database;

    public FirebaseServices(String path) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase.getReference(path);
    }

    // Метод для сохранения AnnouncementsEntity
    public void saveTest(TestEntity testEntity) {
        testEntity.setKey(database.push().getKey());
        database.child(testEntity.getKey()).setValueAsync(testEntity);
    }

    public CompletableFuture<List<String>> getOptionsFromFirebase() {

        CompletableFuture<List<String>> future = new CompletableFuture<>();
        List<String> options = new ArrayList<>();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String surname = snapshot.child("surname").getValue(String.class);
                    String password = snapshot.child("password").getValue(String.class);
                    String key = snapshot.child("key").getValue(String.class);
                    options.add(name);
                    options.add(surname);
                    options.add(password);
                    options.add(key);
                }
                future.complete(options);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(databaseError.toException());
            }
        });
        return future;

    }

    public void deleteByKey(String key) {
        database.child(key).removeValue((databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error deleting data: " + databaseError.getMessage());
            } else {
                System.out.println("Data deleted successfully.");
            }
        });
    }
    public void updateByKey(String key, TestEntity updatedEntity) {
        database.child(key).setValue(updatedEntity, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                System.err.println("Error updating data: " + databaseError.getMessage());
            } else {
                System.out.println("Data updated successfully.");
            }
        });
    }
}