package com.anilakdemir.ecommerce.repository;

import com.anilakdemir.ecommerce.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    @Override
    List<Campaign> findAll();

    @Override
    Optional<Campaign> findById(Long id);

    @Override
    List<Campaign> findAllById(Iterable<Long> ids);

    @Override
    Campaign save(Campaign campaign);

    @Override
    void deleteById(Long id);
}
