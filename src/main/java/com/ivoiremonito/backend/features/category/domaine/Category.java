package com.ivoiremonito.backend.features.category.domaine;

import com.ivoiremonito.backend.core.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseEntity {
    private String name;
    private String description;
    private String imageUrl;
    private boolean isQuiz;
    private boolean isCourse;
}
