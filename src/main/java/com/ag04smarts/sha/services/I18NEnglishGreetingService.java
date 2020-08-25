package com.ag04smarts.sha.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("EN")
@Service("i18nService")
public class I18NEnglishGreetingService implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Summer Health Application has started ...";
    }
}
