package com.ag04smarts.sha.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("CRO")
@Service("i18nService")
public class I18NCroatianGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Ljetna zdravstvena aplikacija je pokrenuta ...";
    }
}
