package com.azharbazla.eFarm.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "order_id", updatable = false)
    private String id;

    @Column(name = "trans_date", updatable = false)
    private LocalDateTime transDate;

    @ManyToOne
    @JoinColumn(name = "company_id", updatable = false)
    private Company company;

    @OneToOne
    @JoinColumn(name = "product_id", updatable = false)
    private Product product;

    @Column(columnDefinition = "int check (quantity > 0)", updatable = false)
    private Integer quantity;
}
