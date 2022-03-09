package apileo.controller;

import org.junit.Assert;
import org.junit.Test;

public class AbstractControllerTest {

	@Test
	public void testaHealth() {
		HealthController controller = new HealthController();
		Assert.assertNotNull(controller.health());
	}

}
