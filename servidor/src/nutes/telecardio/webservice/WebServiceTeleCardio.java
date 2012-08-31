package nutes.telecardio.webservice;

import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.ValidadorMobile;
import nutes.telecardio.modelo.configuracao.Configuracao;
import nutes.telecardio.modelo.configuracao.ServicoConfiguracao;
import nutes.telecardio.modelo.estruturaorganizacional.Exame;
import nutes.telecardio.modelo.estruturaorganizacional.ExameMobile;
import nutes.telecardio.modelo.estruturaorganizacional.MedicoMobile;
import nutes.telecardio.modelo.estruturaorganizacional.Profissional;
import nutes.telecardio.modelo.estruturaorganizacional.ServicoExame;
import nutes.telecardio.modelo.estruturaorganizacional.ServicoUsuario;
import nutes.telecardio.modelo.operacional.Paciente;

public class WebServiceTeleCardio {

	ServicoUsuario servicoUsuario = new ServicoUsuario();
	ServicoExame servicoExame = new ServicoExame();
	ServicoConfiguracao servicoConfiguracao = new ServicoConfiguracao();

	public String consultarMedicoMobilePorLoginSenha(String login, String senha) {
		MedicoMobile medico =servicoUsuario.consultarMedicoMobilePorLoginSenha(login, senha); 
		if(medico!=null){
			return medico.converterEntidadeString();
		}else{
			return null;
		}
	}

	public String[] consultarSolicitacoesAbertosMobile(String campo,
			String valor, int crm) {
		if (valor.equalsIgnoreCase("null")) {
			valor = null;
		}

		if (campo.equalsIgnoreCase("null")) {
			campo = null;
		}

		ExameMobile[] exames = servicoExame.consultarSolicitacoesAbertosMobile(
				campo, valor, crm);
		String[] examesTexto = new String[exames.length];
		for (int i = 0; i < exames.length; i++) {
			examesTexto[i] = exames[i].converterEntidadeString();
		}

		return examesTexto;
	}

	public String consultarExameMobilePorChave(int chaveExame) {

		ExameMobile exame = servicoExame
				.consultarExameMobilePorChave(chaveExame);
		
		Configuracao configuracao = servicoConfiguracao.consultarAtiva();
		exame.limiteExameLaudando = configuracao.getLimiteExameLaudando();
		
		String exameTexto = new String();
		
		exameTexto = exame.converterEntidadeString();

		return exameTexto;
	}

	public TesteFuncionalidade[] getTeste(String nome, String senha) {
		ClasseTesteWebService teste = new ClasseTesteWebService();
		teste.setNome(nome);
		teste.setSenha(senha);
		teste.setTesteFuncionalidade(new TesteFuncionalidade[10]);

		for (int i = 0; i < teste.getTesteFuncionalidade().length; i++) {
			TesteFuncionalidade testeFun = null;
			testeFun = new TesteFuncionalidade();
			testeFun.setNome(nome + " - " + i);
			teste.getTesteFuncionalidade()[i] = testeFun;
		}

		return teste.getTesteFuncionalidade();

	}

	public String[] definirLaudando(int chaveExame) {
		ValidadorMobile validadorMobile = servicoExame
				.definirLaudandoMobile(chaveExame);

		return validadorMobile.msgsRetorno;
	}

	public String[] consultarMedicosMobileMenosParametro(int chaveUsuario) {
		MedicoMobile[] medicosMobile = servicoExame
				.consultarMedicosMobileMenosParametro(chaveUsuario);

		String[] medicosTexto = new String[medicosMobile.length];
		for (int i = 0; i < medicosTexto.length; i++) {
			medicosTexto[i] = medicosMobile[i].converterEntidadeString();
		}

		return medicosTexto;
	}

	public boolean cancelar(int chaveExame) {
		Exame exame = new Exame();
		exame.setChave(chaveExame);
		return servicoExame.cancelar(exame);
	}

	public String[] laudar(ExameMobile exameMobile) {
		Exame exame = new Exame();
		exame.setChave(exameMobile.chave);
		exame.setCaminhoArquivos(exameMobile.caminhoArquivos);
		exame.setAlturaPaciente(exameMobile.alturaPaciente);
		exame.setCid(exameMobile.cid);
		exame.setDataConclusao(exameMobile.dataConclusao);
		exame.setDataSolicitacao(exameMobile.dataSolicitacao);
		exame.setEmailRequisitante(exameMobile.emailRequisitante);
		exame.setEstado(exameMobile.estado);
		exame.setHorasAberto(exameMobile.horasAberto);
		exame.setMedicoDesignadoCrm(exameMobile.medicoDesignadoCrm);
		exame.setObservacoes(exameMobile.observacoes);
		exame.setParecerJustificativa(exameMobile.parecerJustificativa);
		exame.setPassouLimiteAberto(exameMobile.passouLimiteAberto);
		exame.setPesoPaciente(exameMobile.pesoPaciente);
		exame.setProfissional(new Profissional());
		exame.getProfissional().setChave(
				exameMobile.usuarioMedico.chaveProfissional);
		exame.setPaciente(new Paciente());
		exame.getPaciente().setChave(exameMobile.pacienteMobile.chave);

		Validador validador = servicoExame.laudar(exame);
		String[] msgsRetorno = new String[validador.getMsgRetorno().size()];
		if (validador.getMsgRetorno().size() > 0) {

			for (int i = 0; i < msgsRetorno.length; i++) {
				String msg = validador.getMsgRetorno().get(i);
				msgsRetorno[i] = msg;
			}

		}
		return msgsRetorno;

	}
}
