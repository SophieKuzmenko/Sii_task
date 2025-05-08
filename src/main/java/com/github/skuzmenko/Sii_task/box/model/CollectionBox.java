package com.github.skuzmenko.Sii_task.box.model;

import com.github.skuzmenko.Sii_task.box.dto.BoxInfoDTO;
import com.github.skuzmenko.Sii_task.box.dto.CollectionBoxDTO;
import com.github.skuzmenko.Sii_task.currency.Currency;
import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


@Entity
@Table(name="COLLECTION_BOXES")
public class CollectionBox {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, scale = 2, precision = 12)
    private BigDecimal plnAmount;

    @Column(nullable = false,scale = 2, precision = 12)
    private BigDecimal euroAmount;

    @Column(nullable = false, scale = 2, precision = 12)
    private BigDecimal usdAmount;

    @ManyToOne
    @JoinColumn(name="EVENT_ID")
    private FundraisingEvent event;

    public CollectionBox() {
        setEmptyAmounts();
        event = null;
    }

    public CollectionBoxDTO toDTO(){
        return new CollectionBoxDTO(id,
                plnAmount.setScale(2,RoundingMode.HALF_EVEN),
                euroAmount.setScale(2,RoundingMode.HALF_EVEN),
                usdAmount.setScale(2,RoundingMode.HALF_EVEN),
                (event==null)?null:event.getId());
    }

    public BoxInfoDTO toInfoDTO(){
        Boolean isAssigned = (event!=null);
        return new BoxInfoDTO(id, isAssigned, isEmpty());
    }

    public Boolean isEmpty(){
        return (euroAmount.compareTo(BigDecimal.ZERO)==0) && (plnAmount.compareTo(BigDecimal.ZERO)==0) && (usdAmount.compareTo(BigDecimal.ZERO)==0);
    }

    public void setEmptyAmounts(){
        plnAmount = new BigDecimal("0.0", Currency.context);
        euroAmount = new BigDecimal("0.0", Currency.context);
        usdAmount = new BigDecimal("0.0", Currency.context);
    }

    public Long getId() {
        return id;
    }



    public FundraisingEvent getEvent() {
        return event;
    }

    public BigDecimal getPlnAmount() {
        return plnAmount;
    }

    public BigDecimal getEuroAmount() {
        return euroAmount;
    }

    public BigDecimal getUsdAmount() {
        return usdAmount;
    }

    public void setEvent(FundraisingEvent event) {
        this.event = event;
    }

    public void setPlnAmount(BigDecimal plnAmount) {
        this.plnAmount = plnAmount;
    }

    public void setEuroAmount(BigDecimal euroAmount) {
        this.euroAmount = euroAmount;
    }

    public void setUsdAmount(BigDecimal usdAmount) {
        this.usdAmount = usdAmount;
    }
}
