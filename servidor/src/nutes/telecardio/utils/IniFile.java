package nutes.telecardio.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IniFile {

	private String path;
	private File file;

	public IniFile(String path) {
		this.path = path;
	}

	/**
	 * Cria o arquivo ini e caso ja existe mantem o estado
	 * 
	 * @return boolean
	 */
	public boolean CriarIniFile() {
		try {
			boolean isFileCriado = false;
			file = new File(path);
			isFileCriado = file.createNewFile();

			CriarSessao("BANCO");
			CriarChave("URL", "jdbc:sqlserver://");
			CriarChave("SERVERNAME", "localhost");
			CriarChave("PORTNUMBER", "1433");
			CriarChave("DATABASENAME", "tele_cardio");
			CriarChave("USERNAME", "sa");
			CriarChave("PASSWORD", "sa");

			return isFileCriado;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Ferifica se o arquivo exite e retorna verdadeiro caso exista e falso em
	 * caso contrario
	 * 
	 * @return boolean
	 */
	public boolean exists() {
		file = new File(path);
		return file.exists();
	}

	/**
	 * Criar uma sessao no arquivo ini
	 * 
	 * @param String
	 *            sessao
	 * @return boolean
	 */
	public boolean CriarSessao(String sessao) {
		try {
			StringBuffer bufferSessao = new StringBuffer();
			bufferSessao.append("[");
			bufferSessao.append(sessao);
			bufferSessao.append("]");
			bufferSessao.append("\r\n");

			file = new File(path);
			if (file.exists()) {

				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
				bufferWriter.write(bufferSessao.toString());
				bufferWriter.close();
				fileWriter.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Cria uma chave e seu valor no arquivo ini
	 * 
	 * @param String
	 *            chave
	 * @param String
	 *            valor
	 * @return boolean
	 */
	public boolean CriarChave(String chave, String valor) {
		try {
			StringBuffer bufferSessao = new StringBuffer();
			bufferSessao.append(chave.toUpperCase());
			bufferSessao.append("=");
			bufferSessao.append(valor);
			bufferSessao.append(";");
			bufferSessao.append("\r\n");

			file = new File(path);
			if (file.exists()) {

				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
				bufferWriter.write(bufferSessao.toString());
				bufferWriter.close();
				fileWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Ler uma chave localizada na sessão passada como parâmetro e retorna seu
	 * valor
	 * 
	 * @param nomeSessao
	 * @param nomeChave
	 * @return
	 */
	public String LerValorChavePorSessao(String nomeSessao, String nomeChave) {
		String textoSessao;
		String valorChave = null;

		file = new File(path);

		if (file.exists()) {
			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader leitor = new BufferedReader(fileReader);

				StringBuffer linha = new StringBuffer();
				while (leitor.ready()) {
					linha.append(leitor.readLine());
					linha.append("\n");
				}
				int indiceSessao = linha.indexOf(nomeSessao);
				int indeceAux = linha.indexOf(nomeSessao, indiceSessao
						+ nomeSessao.length());

				if (indeceAux == -1) {
					textoSessao = linha.toString().substring(
							indiceSessao + nomeSessao.length(), linha.length())
							.trim();
				} else {
					textoSessao = linha.toString().substring(
							indiceSessao + nomeSessao.length(),
							linha.indexOf(nomeSessao, indiceSessao + 1)).trim();
				}

				textoSessao = textoSessao.replaceAll(" ", "");
				int indiceChave = textoSessao.indexOf(nomeChave);
				int indiceAux2 = textoSessao.indexOf(";", indiceChave
						+ nomeChave.length());

				valorChave = textoSessao.substring(indiceChave
						+ nomeChave.length() + 1, indiceAux2);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return valorChave.replaceAll("\"", "");

	}

}
