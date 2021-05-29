package apileo.controller;

import org.junit.Assert;
import org.junit.Test;

import apileo.controller.RootController;

public class AbstractControllerTest {

	@Test
	public void testaHealth() {
		RootController controller = new RootController();
		Assert.assertNotNull(controller.health());
	}

}
