package dmacc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dmacc.controller.VehicleController;

@SpringBootTest
class TonysAutoShopApplicationTests {
	
	@Autowired
	private VehicleController vController;

	@Test
	void contextLoads() throws Exception {
		assertThat(vController).isNotNull();	}
	

}
