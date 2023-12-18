package fr.insee.onyxia.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.onyxia.api.configuration.properties.CatalogsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class CatalogsLoader {

    @Autowired private ResourceLoader resourceLoader;

    @Autowired private ObjectMapper mapper;

    @Autowired private CatalogFilter catalogFilter;

    @Autowired private CatalogsConfiguration catalogsConfiguration;

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogsLoader.class);

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Catalogs catalogs() {
        Catalogs catalogs = new Catalogs();
        catalogs.setCatalogs(catalogsConfiguration.getResolvedCatalogs());
        catalogs.setCatalogs(catalogFilter.filterCatalogs(catalogs.getCatalogs()));
        LOGGER.info("Serving {} catalogs", catalogs.getCatalogs().size());
        return catalogs;
    }
}
