package com.ktmu.ktmu_gram.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public class FirebaseConfig {
    public FirebaseConfig() {
        try {
            ClassPathResource resource = new ClassPathResource("config/testproject-68f46-firebase-adminsdk-p1sg0-fa336802b4.json");
            InputStream inputStream = resource.getInputStream();
            GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setDatabaseUrl("https://testproject-68f46-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
