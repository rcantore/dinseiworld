package com.michead.dinseiworld.service;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class DataPopulatorService {

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("character-data.json")});
        return factory;
    }
}
