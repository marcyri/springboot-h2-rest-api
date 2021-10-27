package com.moan.pet.moanHealthPrj.bootstrap;

import com.moan.pet.moanHealthPrj.domain.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.StreamSupport;

/**
 * Used only to support any checking
 */
@Component
public class MoanLoader implements CommandLineRunner {
    public final IPatientService patientService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MoanLoader.class);

    public MoanLoader(IPatientService patientService) {
        this.patientService = patientService;
    }

//    @Autowired
//    private ApplicationContext context;
//    @Autowired
//    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        dataSourceInfo();
//        handleContextRefresh
//        System.out.println("start loader run");
//
//        patientService.getPatientById(99L);
//
////        patientService.getPatients();
//        patientService.getPatientsWithAttendances();
//        System.out.println("end loader run");
    }

    private void dataSourceInfo() throws SQLException {
//        DataSource ds = context.getBean(DataSource.class);
//        DataSource ds = dataSource;
//        String schema = (!ObjectUtils.isEmpty(ds.getConnection()))?ds.getConnection().getSchema():null;
//        System.out.println("schema = " + schema);
//        HikariDataSource dsh = (HikariDataSource) context.getBean(DataSource.class);
//        HikariPool hp = (HikariPool) dsh.getHikariPoolMXBean();


//        if (dataSource instanceof HikariDataSource) {
//            HikariDataSource ds = (HikariDataSource) dataSource;
//            HikariPool hp = (HikariPool) ds.getHikariPoolMXBean();
//            System.out.println("getJdbcUrl() = " + hp.config.getJdbcUrl());
//        }
//        System.out.println("getJdbcUrl() = " + hp.config.getJdbcUrl());
//        ds.createConnectionBuilder().build()
//        PoolConfiguration poolProperties = ds.getPoolProperties();
//        String url = poolProperties.getUrl();
//        String driverClassName = poolProperties.getDriverClassName();
//        String username = poolProperties.getUsername();
//        String password = poolProperties.getPassword();
//        System.out.println("DataSource info -> URL: " + url + ", driver: " + driverClassName + ", username: " + username + ", password: " + password);
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        final Environment env = event.getApplicationContext().getEnvironment();
        LOGGER.info("====== Environment and configuration ======");
        LOGGER.info("Active profiles: {}", Arrays.toString(env.getActiveProfiles()));
        final MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();
        StreamSupport.stream(sources.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .filter(prop -> !(prop.contains("credentials") || prop.contains("password")))
                .forEach(prop -> LOGGER.info("{}: {}", prop, env.getProperty(prop)));
        LOGGER.info("===========================================");
    }
}
