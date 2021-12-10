/*
 * Copyright 2021 American Well Systems.
 * All rights reserved.
 *
 * It is illegal to use, reproduce or distribute
 * any part of this Intellectual Property without
 * prior written authorization from American Well.
 */

package com.moan.pet.health_service.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class HealthServiceTestConfig {
	public static void setupDataBase(DataSource dataSource, PlatformTransactionManager transactionManager) {
		// database connections are not configured with autoCommit = true,
		// then need to wrap all in a transaction
		new TransactionTemplate(transactionManager).execute((ts) -> {
			try (Connection conn = dataSource.getConnection()) {
				ScriptUtils.executeSqlScript(conn, new ClassPathResource("test_schema.sql"));
				ScriptUtils.executeSqlScript(conn, new ClassPathResource("import_test_data.sql"));
				// should work without manually commit but didn't for me (because of using AUTOCOMMIT=OFF)
				// I use url=jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1;MODE=MySQL;AUTOCOMMIT=OFF
				// same will happen with DataSourceInitializer & DatabasePopulator (at least with this setup)
				conn.commit();
			}
			catch (SQLException e) {
				HealthServiceTestConfig.log.error(e.getMessage(), e);
			}
			return null;
		});
	}
}
