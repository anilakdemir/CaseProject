package com.anilakdemir.ecommerce.controller;

import com.anilakdemir.ecommerce.model.Campaign;
import com.anilakdemir.ecommerce.model.Category;
import com.anilakdemir.ecommerce.service.CampaignService;
import com.anilakdemir.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CampaignController {

    private final CampaignService campaignService;
    private final CategoryService categoryService;

    @Autowired
    public CampaignController(CampaignService campaignService, CategoryService categoryService) {
        this.campaignService = campaignService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/campaign", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Campaign> getAllCampaigns(){
        return campaignService.findAll();
    }

    @GetMapping(value = "/campaign/{id}",produces = "application/json")
    @ResponseBody
    public Optional<Campaign> getCampaignById(@PathVariable Long id) {
        return campaignService.findById(id);
    }

    @PostMapping(value = "/campaign", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = "application/json")
    @ResponseBody
    public Campaign addCampaign(@RequestBody Campaign campaign) {
        return campaignService.save(campaign);
    }
}
