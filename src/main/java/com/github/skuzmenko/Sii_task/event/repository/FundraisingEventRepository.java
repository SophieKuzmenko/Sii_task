package com.github.skuzmenko.Sii_task.event.repository;

import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundraisingEventRepository extends JpaRepository<FundraisingEvent, Long> {
    public Optional<FundraisingEvent> findByName(String name);
}
