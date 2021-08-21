package event.recommendation.system.initialization;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Component
@RequiredArgsConstructor
public class InitializeData {
    private final String initScriptPath = "sql/init-script.sql";
    private final DataSource dataSource;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", getResource());
        resourceDatabasePopulator.execute(dataSource);
    }

    private Resource getResource() {
        try {
            return new ClassPathResource(initScriptPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Cannot load database init script!");
        }
    }

}
