package com.kogus.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FundDataScheduler {

    JsonPlaceHolderService jsonPlaceHolderService;

    public FundDataScheduler(JsonPlaceHolderService jsonPlaceHolderService) {
        this.jsonPlaceHolderService = jsonPlaceHolderService;
    }

    @Scheduled(cron = "0 02 18 * * ?")
    public void scheduledTask() {
        jsonPlaceHolderService.fetchAndSaveFundData();
    }
}