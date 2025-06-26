package com.toxin.donate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DonateScheduler {

    private final DonateService donateService;

    @Scheduled(cron = "${app.donate.cron}")
    public void checkDonations() {
        log.info("START Checking donations");

        donateService.checkDonations();

        log.info("END Checking donations");
    }
}
