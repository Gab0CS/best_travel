package com.gabo.best_travel.domain.entities.documents;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "app_users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDocument implements Serializable {

    @Id
    private String id;
    private String dni;
    private String username;
    private boolean enabled;
    private String password;
    private Role role;

}
