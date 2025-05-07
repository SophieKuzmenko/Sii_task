package com.github.skuzmenko.Sii_task.event.model;

import com.github.skuzmenko.Sii_task.box.dto.CollectionBoxDTO;
import com.github.skuzmenko.Sii_task.box.model.CollectionBox;
import com.github.skuzmenko.Sii_task.event.dto.FundraisingEventDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="EVENTS")
public class FundraisingEvent {

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double account;

    @Column(nullable = false, columnDefinition = "NVARCHAR(3)")
    private String currency;

    @OneToMany(mappedBy = "event")
    private Set<CollectionBox> boxes;

    public FundraisingEvent(String name, String currency) {
        this.name = name;
        this.currency = currency;
        this.account = 0.0;
        boxes = new HashSet<>();
    }

    public FundraisingEvent() {
    }

    public FundraisingEventDTO toDTO(){
        List<CollectionBoxDTO> boxList = new ArrayList<>();
        boxes.forEach(b -> boxList.add(b.toDTO()));
        return new FundraisingEventDTO(id,name,account,currency,boxList);
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getId() {
        return id;
    }

    public Set<CollectionBox> getBoxes() {
        return boxes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
