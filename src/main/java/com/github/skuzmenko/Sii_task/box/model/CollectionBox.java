package com.github.skuzmenko.Sii_task.box.model;

import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import jakarta.persistence.*;


@Entity
@Table(name="COLLECTION_BOXES")
public class CollectionBox {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Double euroAmount;

    @Column(nullable = false)
    private Double usdAmount;

    @Column(nullable = false)
    private Double plnAmount;

    @ManyToOne
    @JoinColumn(name="EVENT_ID")
    private FundraisingEvent event;

    public CollectionBox() {
        euroAmount = 0.0;
        usdAmount = 0.0;
        plnAmount = 0.0;
        event = null;
    }

    public Long getId() {
        return id;
    }

    public Double getEuroAmount() {
        return euroAmount;
    }

    public Double getUsdAmount() {
        return usdAmount;
    }

    public Double getPlnAmount() {
        return plnAmount;
    }

    public FundraisingEvent getEvent() {
        return event;
    }

    public void setEuroAmount(Double euroAmount) {
        this.euroAmount = euroAmount;
    }

    public void setUsdAmount(Double usdAmount) {
        this.usdAmount = usdAmount;
    }

    public void setPlnAmount(Double plnAmount) {
        this.plnAmount = plnAmount;
    }

    public void setEvent(FundraisingEvent event) {
        this.event = event;
    }
}
