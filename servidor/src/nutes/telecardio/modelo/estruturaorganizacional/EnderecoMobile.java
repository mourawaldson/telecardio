package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.utils.Funcoes;

public class EnderecoMobile {

	public int idUf;
	public String ufNome;
	public String ufSigla;
	public String bairro;
	public String logradouro;
	public String numero;
	public String complemento;
	public String cep;
	public String municipio;

	public String EnderecoMobileEmString;

	public String converterEntidadeString() {

		StringBuilder EnderecoTexto = new StringBuilder();

		EnderecoTexto.append("<IDUF>");
		EnderecoTexto.append(idUf);
		EnderecoTexto.append("</IDUF>");

		EnderecoTexto.append("<UFNOME>");
		EnderecoTexto.append(ufNome);
		EnderecoTexto.append("</UFNOME>");

		EnderecoTexto.append("<UFSIGLA>");
		EnderecoTexto.append(ufSigla);
		EnderecoTexto.append("</UFSIGLA>");

		EnderecoTexto.append("<BAIRRO>");
		EnderecoTexto.append(bairro);
		EnderecoTexto.append("</BAIRRO>");

		EnderecoTexto.append("<LOGRADOUTO>");
		EnderecoTexto.append(logradouro);
		EnderecoTexto.append("</LOGRADOUTO>");

		EnderecoTexto.append("<NUMERO>");
		EnderecoTexto.append(numero);
		EnderecoTexto.append("</NUMERO>");

		EnderecoTexto.append("<COMPLEMENTO>");
		EnderecoTexto.append(complemento);
		EnderecoTexto.append("</COMPLEMENTO>");

		EnderecoTexto.append("<CEP>");
		EnderecoTexto.append(cep);
		EnderecoTexto.append("</CEP>");

		EnderecoTexto.append("<MUNICIPIO>");
		EnderecoTexto.append(municipio);
		EnderecoTexto.append("</MUNICIPIO>");

		EnderecoMobileEmString = EnderecoTexto.toString();

		return EnderecoMobileEmString;
	}

	public void CarregarEndereco(String exameString) {
		idUf = Integer.parseInt(Funcoes.CapturarConteudoTag("<IDUF>",
				"</IDUF>", exameString, 0));
		ufNome = Funcoes.CapturarConteudoTag("<UFNOME>", "</UFNOME>",
				exameString, 0);
		ufSigla = Funcoes.CapturarConteudoTag("<UFSIGLA>", "</UFSIGLA>",
				exameString, 0);
		logradouro = Funcoes.CapturarConteudoTag("<LOGRADOUTO>",
				"</LOGRADOUTO>", exameString, 0);
		numero = Funcoes.CapturarConteudoTag("<NUMERO>", "</NUMERO>",
				exameString, 0);
		complemento = Funcoes.CapturarConteudoTag("<COMPLEMENTO>",
				"</COMPLEMENTO>", exameString, 0);
		cep = Funcoes.CapturarConteudoTag("<CEP>", "</CEP>", exameString, 0);
		bairro = Funcoes.CapturarConteudoTag("<BAIRRO>", "</BAIRRO>",
				exameString, 0);
		municipio = Funcoes.CapturarConteudoTag("<MUNICIPIO>", "</MUNICIPIO>",
				exameString, 0);

	}

}
