package com.github.skuzmenko.Sii_task.box.model;

import com.github.skuzmenko.Sii_task.box.dto.CollectionBoxDTO;
import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import jakarta.persistence.*;


@Entity
@Table(name="COLLECTION_BOXES")
public class CollectionBox {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Double plnAmount;

    @Column(nullable = false)
    private Double euroAmount;

    @Column(nullable = false)
    private Double usdAmount;

    @ManyToOne
    @JoinColumn(name="EVENT_ID")
    private FundraisingEvent event;

    public CollectionBox(FundraisingEvent event) {
        plnAmount = 0.0;
        euroAmount = 0.0;
        usdAmount = 0.0;
        this.event = event;
    }
    public CollectionBoxDTO toDTO(){
        return new CollectionBoxDTO(id, plnAmount, euroAmount, usdAmount, event.getId());
    }

    public Long getId() {
        return id;
    }

    public Double getPlnAmount() {
        return plnAmount;
    }

    public Double getEuroAmount() {
        return euroAmount;
    }

    public Double getUsdAmount() {
        return usdAmount;
    }


    public FundraisingEvent getEvent() {
        return event;
    }

    public void setPlnAmount(Double plnAmount) {
        this.plnAmount = plnAmount;
    }

    public void setEuroAmount(Double euroAmount) {
        this.euroAmount = euroAmount;
    }

    public void setUsdAmount(Double usdAmount) {
        this.usdAmount = usdAmount;
    }

    public void setEvent(FundraisingEvent event) {
        this.event = event;
    }
}
