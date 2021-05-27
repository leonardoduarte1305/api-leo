package apileo.utils;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import apileo.utils.DataUtils;

public class DataUtilsTest {

	@Test
	public void testaCalculoDeIdade() {
		Calendar dataNascim = Calendar.getInstance();
		dataNascim.set(1989, 5, 13);

		int idade = DataUtils.calcularIdade(dataNascim.getTime());

		Assert.assertEquals(32, idade);
	}

}
