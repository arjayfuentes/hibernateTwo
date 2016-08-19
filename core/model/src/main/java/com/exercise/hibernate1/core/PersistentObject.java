package com.exercise.hibernate1.core;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class PersistentObject implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;


    public Long getId(){
        return this.id;
    }

    @SuppressWarnings("unused")
    public void setId(Long id){
        this.id = id;
    }
}
