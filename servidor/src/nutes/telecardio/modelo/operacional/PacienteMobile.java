package nutes.telecardio.modelo.operacional;

import nutes.telecardio.utils.Funcoes;

public class PacienteMobile {

	public int chave;
	
	
	public String nomePaciente;
	public String cpfPaciente;
	public String dataNascimentoPaciente;
	public String sexo;
	
	public int idConvenio;
	public String dataUltimoContato;
	public String nomeMae;
	public int idTipoSanguineo;
	public String tipoSanguineoNome;
	


	public String pacienteMobileEmString;
	public String enderecoMobileEmString;

	public String converterPacienteString() {

		StringBuilder PacientecoTexto = new StringBuilder();

		PacientecoTexto.append("<CHAVE>");
		PacientecoTexto.append(chave);
		PacientecoTexto.append("</CHAVE>");
		
		PacientecoTexto.append("<NOME>");
		PacientecoTexto.append(nomePaciente);
		PacientecoTexto.append("</NOME>");
		
		PacientecoTexto.append("<CPF>");
		PacientecoTexto.append(cpfPaciente);
		PacientecoTexto.append("</CPF>");
		
		PacientecoTexto.append("<DATANASCIMENTO>");
		PacientecoTexto.append(dataNascimentoPaciente);
		PacientecoTexto.append("</DATANASCIMENTO>");
		
		PacientecoTexto.append("<SEXO>");
		PacientecoTexto.append(sexo);
		PacientecoTexto.append("</SEXO>");

		PacientecoTexto.append("<IDCONVENCIO>");
		PacientecoTexto.append(idConvenio);
		PacientecoTexto.append("</IDCONVENCIO>");

		PacientecoTexto.append("<DATAULTIMOCONTATO>");
		PacientecoTexto.append(dataUltimoContato);
		PacientecoTexto.append("</DATAULTIMOCONTATO>");

		PacientecoTexto.append("<NOMEMAE>");
		PacientecoTexto.append(nomeMae);
		PacientecoTexto.append("</NOMEMAE>");

		PacientecoTexto.append("<IDTIPOSANGUINEO>");
		PacientecoTexto.append(idTipoSanguineo);
		PacientecoTexto.append("</IDTIPOSANGUINEO>");

		PacientecoTexto.append("<TIPOSANGUINEONOME>");
		PacientecoTexto.append(tipoSanguineoNome);
		PacientecoTexto.append("</TIPOSANGUINEONOME>");
		
		PacientecoTexto.append("<ENDERECOMOBILEMSTRING>");
		PacientecoTexto.append(enderecoMobileEmString);
		PacientecoTexto.append("</ENDERECOMOBILEMSTRING>");

		pacienteMobileEmString = PacientecoTexto.toString();

		return pacienteMobileEmString;
	}

	public void CarregarPaciente(String exameString) {
		chave = Integer.parseInt(Funcoes.CapturarConteudoTag("<CHAVE>",
				"</CHAVE>", exameString, 0));
		idConvenio = Integer.parseInt(Funcoes.CapturarConteudoTag(
				"<IDCONVENCIO>", "</IDCONVENCIO>", exameString, 0));
		dataUltimoContato = Funcoes.CapturarConteudoTag("<DATAULTIMOCONTATO>",
				"</DATAULTIMOCONTATO>", exameString, 0);
		nomeMae = Funcoes.CapturarConteudoTag("<NOMEMAE>", "</NOMEMAE>",
				exameString, 0);
		idTipoSanguineo = Integer.parseInt(Funcoes.CapturarConteudoTag(
				"<IDTIPOSANGUINEO>", "</IDTIPOSANGUINEO>", exameString, 0));

		tipoSanguineoNome = Funcoes.CapturarConteudoTag("<TIPOSANGUINEONOME>",
				"</TIPOSANGUINEONOME>", exameString, 0);
		
		enderecoMobileEmString = Funcoes.CapturarConteudoTag("<ENDERECOMOBILEMSTRING>",
				"</ENDERECOMOBILEMSTRING>", exameString, 0);
		
		cpfPaciente = Funcoes.CapturarConteudoTag("<CPF>",
				"</CPF>", exameString, 0);
		
		nomePaciente = Funcoes.CapturarConteudoTag("<NOME>",
				"</NOME>", exameString, 0);
		
		dataNascimentoPaciente = Funcoes.CapturarConteudoTag("<DATANASCIMENTO>",
				"</DATANASCIMENTO>", exameString, 0);
		
		sexo = Funcoes.CapturarConteudoTag("<SEXO>",
				"</SEXO>", exameString, 0);
		
	}

}
