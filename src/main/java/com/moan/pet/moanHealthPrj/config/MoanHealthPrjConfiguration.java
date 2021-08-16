package com.moan.pet.moanHealthPrj.config;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class MoanHealthPrjConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setPropertyCondition(context -> !(context.getSource() instanceof PersistentCollection))
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR)
        ;
        return modelMapper;
    }
}
