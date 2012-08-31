package nutes.telecardio.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nutes.telecardio.modelo.configuracao.NomesCampos;

import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

/**
 * Funções úteis a todo sistema.
 * 
 * @author WaldsonMoura
 * 
 */
public class Funcoes {

	/**
	 * Converter minutos para milisegundos.
	 * 
	 * @param minutes
	 * @return long
	 */
	public static long ConvertMinutesToMilliseconds(int minutes) {
		return TimeUnit.MINUTES.toMillis(minutes);
	}

	/**
	 * Converter horas em milisegundos.
	 * 
	 * @param hours
	 * @return long
	 */
	public static long ConvertHoursToMilliseconds(int hours) {
		return TimeUnit.HOURS.toMillis(hours);
	}

	/**
	 * Converter dias em milisegundos.
	 * 
	 * @param days
	 * @return long
	 */
	public static long ConvetDaysToMilliseconds(int days) {
		return TimeUnit.DAYS.toMillis(days);
	}

	/**
	 * Salvar arquivo.
	 * 
	 * @param filename
	 * @param input
	 * @throws IOException
	 */
	public static void saveFile(String filename, InputStream input)
			throws IOException {
		try {
			if (filename == null)
				filename = File.createTempFile("xx", ".out").getName();

			// Do no overwrite existing file
			File file = new File(filename);
			for (int i = 0; file.exists(); i++)
				file = new File(filename + i);

			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			BufferedInputStream bis = new BufferedInputStream(input);
			int aByte;
			while ((aByte = bis.read()) != -1)
				bos.write(aByte);

			bos.flush();
			bos.close();
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Criar diretórios.
	 * 
	 * @param nameDir
	 * @return boolean
	 * @throws SecurityException
	 */
	public static boolean mkDirs(String dirs) throws SecurityException {
		try {
			File path = new File(dirs);
			return path.mkdirs();
		} catch (SecurityException se) {
			return false;
		}
	}

	/**
	 * Verifica se a String passada por parâmetro é nula ou vazia.
	 * 
	 * @param str
	 *            String
	 * @return boolean true se a String for "null" ou vazia; false caso exista
	 *         conteúdo.
	 */
	public static boolean isNullOrEmpty(String str) {
		return ((str == null || str.isEmpty()) || str.trim().length() == 0) ? true
				: false;
	}

	/**
	 * Valida se a String está realmente preenchida e dentro dos limites de
	 * caracteres. Caso o parâmetro: quantidadeMinima, seja passado "null" ou
	 * "0", a função toma como padrão o valor 5, já para quantidadeMaxima o
	 * valor padrão é 255.
	 * 
	 * @param str
	 * @return boolean true se a String for válida; false caso a mesma não
	 *         esteja dentro dos padrões.
	 */
	public static boolean isValidStr(String str, Integer quantidadeMinima,
			Integer quantidadeMaxima) {
		if (quantidadeMinima == null || quantidadeMinima == 0)
			quantidadeMinima = 5;
		if (quantidadeMaxima == null || quantidadeMaxima == 0)
			quantidadeMaxima = 255;

		if (!isNullOrEmpty(str)) {
			int tamanhoStr = str.trim().length();
			return (tamanhoStr >= quantidadeMinima && tamanhoStr <= quantidadeMaxima) ? true
					: false;
		} else
			return false;
	}

	/**
	 * Valida se o int está realmente preenchido dentro dos limites definidos.
	 * Caso o parâmetro: numeroMinimo, seja passado "null" ou "0", a função toma
	 * como padrão o valor 1, já para numeroMaximo o valor padrão é 99.
	 * 
	 * @param value
	 * @return boolean true se o int for válido; false caso o mesmo não esteja
	 *         dentro dos padrões.
	 */
	public static boolean isValidInt(int value, Integer numeroMinimo,
			Integer numeroMaximo) {
		if (numeroMinimo == null || numeroMinimo == 0)
			numeroMinimo = 1;
		if (numeroMaximo == null || numeroMaximo == 0)
			numeroMaximo = 99;

		return (value < numeroMinimo.intValue() || value > numeroMaximo
				.intValue()) ? false : true;
	}

	/**
	 * Valida se o float está realmente preenchido dentro dos limites definidos.
	 * Caso o parâmetro: numeroMinimo, seja passado "null" ou "0", a função toma
	 * como padrão o valor 0.0f, já para numeroMaximo o valor padrão é 999.9f.
	 * 
	 * @param value
	 * @return boolean true se o float for válido; false caso o mesmo não esteja
	 *         dentro dos padrões.
	 */
	public static boolean isValidFloat(float value, Float numeroMinimo,
			Float numeroMaximo) {
		if (numeroMinimo == null || numeroMinimo == 0.0)
			numeroMinimo = 0.0f;
		if (numeroMaximo == null || numeroMaximo == 0.0)
			numeroMaximo = 999.9f;

		return (value < numeroMinimo.intValue() || value > numeroMaximo
				.intValue()) ? false : true;
	}

	/**
	 * Remove qualquer acentuação de uma String.
	 * 
	 * @param str
	 * @return String sem acentos.
	 */
	public static String removeAccents(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		return str.replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Remove qualquer caracter que não seja um tipo de letra em qualquer língua
	 * nem espaço.
	 * 
	 * @param str
	 * @return String sem caracteres especiais.
	 */
	// REVISAR - deixar os acentos! ()<>,;:\\\"[] `~!#$%^&*={}|/?'
	public static String removeSpecialCharacters(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		return str.replaceAll("[^\\p{L}-\\s]", "");
	}

	/**
	 * Valida o e-mail informado.
	 * 
	 * @param str
	 * @return boolean true se o e-mail for válido; false se o e-mail for
	 *         inválido.
	 */
	public static boolean isValidEmail(String str) {
		if (isNullOrEmpty(str))
			return false;
		else {
			Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
			Matcher m = p.matcher(str);
			boolean matchFound = m.matches();
			return (matchFound) ? true : false;
		}
	}

	/**
	 * Valida o cpf passado como parâmetro e retorna true se for valido e falso
	 * em caso contrario.
	 * 
	 * @param String
	 *            vrCPF
	 * @return boolean
	 */
	public static boolean isValidCPF(String vrCPF) {
		try {
			String[] inputsErros = new String[] { "00000000000", "11111111111",
					"22222222222", "33333333333", "44444444444", "55555555555",
					"66666666666", "77777777777", "88888888888", "99999999999",
					"12345678909" };

			String valor = vrCPF.replace(".", "");

			valor = valor.replace("-", "");

			if (valor.length() != 11)
				return false;

			for (String cpf : inputsErros)
				if (valor.equals(cpf))
					return false;

			// Passando todos os numeros do cpf para um array
			int[] numeros = new int[11];

			for (int i = 0; i < 11; i++)
				numeros[i] = Integer.parseInt(valor.substring(i, i + 1));

			// Verificando a soma para o primeiro digito verificador
			int soma = 0;

			// Multiplicando valores de 10 a 2 com os respectivos números do
			// array do cpf
			for (int i = 0; i < 9; i++)
				soma += (10 - i) * numeros[i];

			int resultado = soma % 11;

			if (resultado == 1 || resultado == 0) {
				if (numeros[9] != 0)
					return false;
			} else if (numeros[9] != 11 - resultado)
				return false;

			soma = 0;

			for (int i = 0; i < 10; i++)
				soma += (11 - i) * numeros[i];

			resultado = soma % 11;

			if (resultado == 1 || resultado == 0) {
				if (numeros[10] != 0)
					return false;

			} else if (numeros[10] != 11 - resultado)
				return false;

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public static String getExtension(String file) {
		return file.substring(file.lastIndexOf(".") + 1, file.length());
	}

	public static String identifyXmlName(String path) {
		File[] files = (new File(path)).listFiles();
		String xml = null;
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String ext = getExtension(fileName);
			if (ext.equalsIgnoreCase(NomesCampos.xmlExtension)) {
				xml = path + File.separator + fileName;
				break;
			}
		}
		return xml;
	}

	 public static String CapturarConteudoTag(String tagInicio, String tagFim, String bufferTexto, int indice)
     {
         String conteudoTag = null;
         int posInicial = -1;
         int posFinal = -1;

         // Encontra a posicao inicial da tag inicial
         if (tagInicio != null)
         {
             posInicial = bufferTexto.indexOf(tagInicio, indice);
         }

         // Encontra a posicao inicial da tag final
         if (tagFim != null)
         {
             posFinal = bufferTexto.indexOf(tagFim, posInicial + 1);
         }

         // Pega o conteudo das tag para retonar como parametro
         if (posInicial != -1 && posFinal != -1)
         {
             conteudoTag = bufferTexto.substring(posInicial + tagInicio.length(), posFinal);
         }
         return conteudoTag;
     }



}