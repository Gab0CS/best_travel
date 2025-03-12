package com.gabo.best_travel.domain.entities.documents;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role {

    @Field(name = "granted_authorities")
    private Set<String> grantedAuthorities;
}
