package org.falaleev.service;

import lombok.RequiredArgsConstructor;
import org.falaleev.entity.CharacterEntity;
import org.falaleev.entity.DiceEntity;
import org.falaleev.entity.RollEntity;
import org.falaleev.model.RollAddRequest;
import org.falaleev.model.RollHistoryItem;
import org.falaleev.repository.CharacterRepository;
import org.falaleev.repository.DiceRepository;
import org.falaleev.repository.RollRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RollService {
    private final RollRepository rollRepository;
    private final DiceRepository diceRepository;
    private final CharacterRepository characterRepository;

    @Transactional
    public UUID add(RollAddRequest rollAddRequest) {
        RollEntity entity = new RollEntity();
        entity.setResult(rollAddRequest.result());

        DiceEntity diceReference = diceRepository.getReferenceById(rollAddRequest.dice());
        CharacterEntity characterReference = characterRepository.getReferenceById(rollAddRequest.character());
        entity.setDice(diceReference);
        entity.setCharacter(characterReference);

        return rollRepository.save(entity).getId();
    }

    public List<RollHistoryItem> getHistory(
            Integer limit,
            LocalDateTime from,
            LocalDateTime to,
            UUID diceId,
            UUID characterId
    ) {
        Specification<RollEntity> diceSpecification = (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("dice").get("id"), diceId);
        Specification<RollEntity> characterSpecification = (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("character").get("id"), characterId);
        Specification<RollEntity> fromSpecification = (root, query, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("date"), from);
        Specification<RollEntity> toSpecification = (root, query, criteriaBuilder)
                -> criteriaBuilder.lessThanOrEqualTo(root.get("date"), to);
        Specification<RollEntity> specification = Specification.where(diceSpecification)
                .and(characterSpecification)
                .and(fromSpecification)
                .and(toSpecification)
                .and(toSpecification);

        if (limit != null) {
            Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "date"));
            List<RollHistoryItem> history = rollRepository.findAll(specification, pageable).stream()
                    .map(entity -> new RollHistoryItem(entity.getResult(), entity.getDate()))
                    .toList();
            history = new ArrayList<>(history);
            Collections.reverse(history);
            return history;
        } else {
            return rollRepository.findAll(specification, Sort.by(Sort.Direction.ASC, "date")).stream()
                    .map(entity -> new RollHistoryItem(entity.getResult(), entity.getDate()))
                    .toList();
        }
    }
}
