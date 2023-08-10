package com.azharbazla.eFarm.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_company")
public class Company {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "company_id")
    private String id;

    private String name;

    private String address;

    @Column(unique = true)
    private String telephone;

    @Column(unique = true)
    private String email;
}
