package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.operacional.PacienteMobile;


public class ExameMobile {
	
	public int chave;	
	public float pesoPaciente;
	public float alturaPaciente;
	public int medicoDesignadoCrm;
	public String emailRequisitante;	
	public String parecerJustificativa;	
	public String caminhoArquivos;
	public String dataSolicitacao;
	public String dataConclusao;
	public int cid;
	public int estado;
	public String observacoes;
	public int horasAberto;
	public boolean passouLimiteAberto;
	public String pacienteMobileEmString;
	public String usuarioMobileEmString;
    public int limiteExameLaudando ;
	
	
	public String exameMobileEmString;
	public UsuarioMobile usuarioMedico;
	public PacienteMobile pacienteMobile;
	
	
	
	public String converterEntidadeString(){
		StringBuilder ExameTexto = new StringBuilder();
		
		ExameTexto.append("<CHAVE>");
		ExameTexto.append(chave);
		ExameTexto.append("</CHAVE>");
		
		ExameTexto.append("<PESOPACIENTE>");
		ExameTexto.append(pesoPaciente);
		ExameTexto.append("</PESOPACIENTE>");
		
		ExameTexto.append("<ALTURAPACIENTE>");
		ExameTexto.append(alturaPaciente);
		ExameTexto.append("</ALTURAPACIENTE>");
		
		ExameTexto.append("<MEDICODESIGNADOCRM>");
		ExameTexto.append(medicoDesignadoCrm);
		ExameTexto.append("</MEDICODESIGNADOCRM>");
		
		ExameTexto.append("<EMAILREQUISITANTE>");
		ExameTexto.append(emailRequisitante);
		ExameTexto.append("</EMAILREQUISITANTE>");
		
		ExameTexto.append("<PARECERJUSTIFICATIVA>");
		ExameTexto.append(parecerJustificativa);
		ExameTexto.append("</PARECERJUSTIFICATIVA>");
		
		ExameTexto.append("<CAMINHOARQUIVO>");
		ExameTexto.append(caminhoArquivos);
		ExameTexto.append("</CAMINHOARQUIVO>");
		
		ExameTexto.append("<DATASOLICITACAO>");
		ExameTexto.append(dataSolicitacao);
		ExameTexto.append("</DATASOLICITACAO>");
		
		ExameTexto.append("<DATACONCLUSAO>");
		ExameTexto.append(dataConclusao);
		ExameTexto.append("</DATACONCLUSAO>");
		
		ExameTexto.append("<CID>");
		ExameTexto.append(cid);
		ExameTexto.append("</CID>");
		
		ExameTexto.append("<ESTADO>");
		ExameTexto.append(estado);
		ExameTexto.append("</ESTADO>");
		
		ExameTexto.append("<OBSERVACOES>");
		ExameTexto.append(observacoes);
		ExameTexto.append("</OBSERVACOES>");
		
		ExameTexto.append("<HORASABERTO>");
		ExameTexto.append(horasAberto);
		ExameTexto.append("</HORASABERTO>");
		
		ExameTexto.append("<PASSOULIMITEABERTO>");
		ExameTexto.append(passouLimiteAberto);
		ExameTexto.append("</PASSOULIMITEABERTO>");
		
		
		
		ExameTexto.append("<PACIENTEMOBILEMSTRING>");
		ExameTexto.append(pacienteMobileEmString);
		ExameTexto.append("</PACIENTEMOBILEMSTRING>");
		
		ExameTexto.append("<USUARIOMOBILEMSTRING>");
		ExameTexto.append(usuarioMobileEmString);
		ExameTexto.append("</USUARIOMOBILEMSTRING>");

		ExameTexto.append("<LIMITEEXAMELAUDANDO>");
		ExameTexto.append(limiteExameLaudando);
		ExameTexto.append("</LIMITEEXAMELAUDANDO>");
		
		
		
		exameMobileEmString = ExameTexto.toString();
		
		return exameMobileEmString;
	}
	
	

}
