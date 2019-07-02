package com.anilakdemir.ecommerce.service;

import com.anilakdemir.ecommerce.model.Campaign;

import java.util.List;
import java.util.Optional;

public interface CampaignService {
    Optional<Campaign> findById(Long id);

    List<Campaign> findAll();

    List<Campaign> findAllById(Iterable<Long> ids);

    List<Campaign> findByIdList(Iterable<Campaign> campaigns);

    Campaign save(Campaign campaign);

    void deleteById(Long id);
}
