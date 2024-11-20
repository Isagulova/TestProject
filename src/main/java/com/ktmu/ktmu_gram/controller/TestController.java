package com.ktmu.ktmu_gram.controller;

import com.ktmu.ktmu_gram.model.TestEntity;
import com.ktmu.ktmu_gram.services.FirebaseServices;
import com.ktmu.ktmu_gram.services.modul.ThymleafUIModul;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class TestController {
    private FirebaseServices testFirebase = new FirebaseServices("test");

    private ThymleafUIModul thymleafUIModul;
    @GetMapping("/test")
    public String getTest(Model model) {
        thymleafUIModul = new ThymleafUIModul(model);
        thymleafUIModul.showData(testFirebase);
        return "test";
    }

    @PostMapping("/test")
    public String postTest(
            @RequestParam(defaultValue = "none") String type,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("password") String password,
            Model model)
            throws IOException {

        TestEntity testEntity = new TestEntity(name, surname, password);

        if (type.equals("none"))
            testFirebase.saveTest(testEntity);
        else testFirebase.updateByKey(type, testEntity);

        thymleafUIModul = new ThymleafUIModul(model);
        thymleafUIModul.showData(testFirebase);
        return "test";
    }
    @PostMapping("/update")
    public String updateTest(@RequestParam("update_key") String update_key, Model model) {
        thymleafUIModul = new ThymleafUIModul(model);
        thymleafUIModul.updateShow(testFirebase, update_key);
        System.out.println("keyList");
        return "test";
    }

    @PostMapping("/delete")
    public String deleteTest(@RequestParam ("delete_key") String delete_key){
        testFirebase.deleteByKey(delete_key);
        return "redirect:/test";
    }

}