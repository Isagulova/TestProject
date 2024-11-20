package com.ktmu.ktmu_gram.services.modul;

import com.ktmu.ktmu_gram.services.FirebaseServices;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThymleafUIModul {
    private Model model;

    public ThymleafUIModul(Model model) {
        this.model = model;
    }

    public void showData(FirebaseServices testFirebase) {
        CompletableFuture<List<String>> table = testFirebase.getOptionsFromFirebase();
        try {
            List<String> all = table.get();

            List<String> nameList = new ArrayList<>();
            List<String> surnameList = new ArrayList<>();
            List<String> passwordList = new ArrayList<>();
            List<String> keyList = new ArrayList<>();

            for (int i = 0; i < all.size(); i++) {
                if (i % 4 == 0) {
                    nameList.add(all.get(i));
                } else if (i % 4 == 1) {
                    surnameList.add(all.get(i));
                } else if (i % 4 == 2) {
                    passwordList.add(all.get(i));
                } else if (i % 4 == 3) {
                    keyList.add(all.get(i));
                }
            }

            model.addAttribute("nameList", nameList);
            model.addAttribute("surnameList", surnameList);
            model.addAttribute("passwordList", passwordList);
            model.addAttribute("keyList", keyList);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateShow(FirebaseServices testFirebase, String updateKey) {
        CompletableFuture<List<String>> table = testFirebase.getOptionsFromFirebase();
        try {
            List<String> all = table.get();

            List<String> nameList = new ArrayList<>();
            List<String> surnameList = new ArrayList<>();
            List<String> passwordList = new ArrayList<>();
            List<String> keyList = new ArrayList<>();

            for (int i = 0; i < all.size(); i++) {
                if (i % 4 == 0) {
                    nameList.add(all.get(i));
                } else if (i % 4 == 1) {
                    surnameList.add(all.get(i));
                } else if (i % 4 == 2) {
                    passwordList.add(all.get(i));
                } else if (i % 4 == 3) {
                    keyList.add(all.get(i));
                }
            }


            for (int i = 0; i < keyList.size(); i++) {
                if (updateKey.equals(keyList.get(i))) {
                    model.addAttribute("key", keyList.get(i));
                    model.addAttribute("name", nameList.get(i));
                    model.addAttribute("surname", surnameList.get(i));
                    model.addAttribute("password", passwordList.get(i));
                    break;
                }
            }

            model.addAttribute("nameList", nameList);
            model.addAttribute("surnameList", surnameList);
            model.addAttribute("passwordList", passwordList);
            model.addAttribute("keyList", keyList);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
   }
}