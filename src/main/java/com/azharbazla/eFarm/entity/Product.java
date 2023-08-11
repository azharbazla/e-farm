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
@Table(name = "m_product")
public class Product {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "product_id", updatable = false)
    private String id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @Column(columnDefinition = "int check (price >= 0)")
    private Long price;

    @Column(columnDefinition = "int check (stock >= 0)")
    private Integer stock;

    private Boolean isActive;
}
