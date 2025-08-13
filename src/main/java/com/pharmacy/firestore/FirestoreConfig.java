package com.pharmacy.firestore;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirestoreConfig {
  @Bean
  public Firestore firestore(
      @Value("${firestore.project-id:}") String projectId,
      @Value("${firestore.database-id:(default)}") String databaseId) {

    var builder = FirestoreOptions.getDefaultInstance().toBuilder();

    if (projectId != null && !projectId.isBlank()) {
      builder.setProjectId(projectId);
    }
    if (databaseId != null && !databaseId.isBlank()) {
      builder.setDatabaseId(databaseId);   // <<< important
    }

    return builder.build().getService();
  }
}