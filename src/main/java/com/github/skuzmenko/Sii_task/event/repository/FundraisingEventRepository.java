package com.github.skuzmenko.Sii_task.event.repository;

import com.github.skuzmenko.Sii_task.event.model.FundraisingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundraisingEventRepository extends JpaRepository<FundraisingEvent, Long> {
}
