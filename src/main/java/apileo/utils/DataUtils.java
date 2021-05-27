package apileo.utils;

import java.util.Calendar;
import java.util.Date;

public class DataUtils {

	public static int calcularIdade(Date dt_nascimento) {
		Calendar hoje = Calendar.getInstance();

		long diferencaEmAnos = ((hoje.getTime().getTime() - dt_nascimento.getTime()) / (1000 * 60 * 60 * 24) / 30) / 12;

		return (int) diferencaEmAnos;
	}

}
