package org.falaleev.repository;

import org.falaleev.entity.RollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface RollRepository extends JpaRepository<RollEntity, UUID>, JpaSpecificationExecutor<RollEntity> {
}
