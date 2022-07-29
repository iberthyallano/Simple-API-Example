package com.iberthy.backend.domain.entity;

import com.iberthy.backend.domain.abstracts.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Role extends AbstractEntity {

    private String nome;

}
