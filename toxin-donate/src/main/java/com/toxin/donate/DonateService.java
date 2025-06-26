package com.toxin.donate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonateService {

    private final DonateProducer donateProducer;

    public void checkDonations() {
        donateProducer.sendDonate(new DonateDTO(
                (long) (Math.random() * 100),
                "Test donate",
                BigDecimal.valueOf((Math.random() * 1000))
        ));
    }
}
