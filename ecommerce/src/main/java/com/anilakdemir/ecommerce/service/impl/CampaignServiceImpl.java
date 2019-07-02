package com.anilakdemir.ecommerce.service.impl;

import com.anilakdemir.ecommerce.model.Campaign;
import com.anilakdemir.ecommerce.repository.CampaignRepository;
import com.anilakdemir.ecommerce.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public List<Campaign> findAllById(Iterable<Long> ids) {
        return campaignRepository.findAllById(ids);
    }

    @Override
    public List<Campaign> findByIdList(Iterable<Campaign> campaigns) {
        List<Long> idList = new LinkedList<>();
        for(Campaign campaign: campaigns)
            idList.add(campaign.getId());
        return findAllById(idList);
    }

    @Override
    public Campaign save(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        campaignRepository.deleteById(id);
    }
}
