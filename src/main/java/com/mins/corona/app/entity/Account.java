package com.mins.corona.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        })
})
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    private int age;

    private String address;
}
