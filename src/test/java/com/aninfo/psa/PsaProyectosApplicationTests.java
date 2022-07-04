package com.aninfo.psa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PsaProyectosApplicationTests {

	@Test
	void contextLoads() {
	}

}
