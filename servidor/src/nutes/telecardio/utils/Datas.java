package nutes.telecardio.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WaldsonMoura
 */
public class Datas {

	public static final String dataHoraBrasil = "dd/MM/yyyy HH:mm:ss";
	public static final String dataHoraSemSegundosBrasil = "dd/MM/yyyy HH:mm";
	public static final String dataBrasil = "dd/MM/yyyy";
	public static final String dataHoraEua = "yyyy-MM-dd HH:mm:ss";
	public static final String dataEua = "yyyy-MM-dd";

	/**
	 * Transforma uma string em uma data.
	 * 
	 * @param data
	 *            , formato
	 * @return Date
	 */
	public static Date criarData(String data, String formatacao) {
		Date date = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatacao);
			date = format.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Retorna uma string no formato que foi passado no parâmetro.
	 * 
	 * @param data
	 * @param formato
	 * @return String
	 */
	public static String formatarData(Date data, String formato) {
		SimpleDateFormat format;
		String dataFormatada;

		if (data != null) {
			format = new SimpleDateFormat(formato);
			dataFormatada = format.format(data);
		} else
			dataFormatada = "";

		return dataFormatada;
	}

}