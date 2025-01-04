package org.falaleev.repository;

import org.falaleev.entity.DiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DiceRepository extends JpaRepository<DiceEntity, UUID> {
}
