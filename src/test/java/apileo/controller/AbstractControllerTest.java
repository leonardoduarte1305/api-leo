package apileo.controller;

import org.junit.Assert;
import org.junit.Test;

import apileo.controller.AbstractController;

public class AbstractControllerTest {

	@Test
	public void testaHealth() {
		AbstractController controller = new AbstractController();
		Assert.assertNotNull(controller.health());
	}

}
