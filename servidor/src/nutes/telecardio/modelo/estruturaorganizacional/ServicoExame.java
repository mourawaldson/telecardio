package nutes.telecardio.modelo.estruturaorganizacional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.ServicoFuncionalidade;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.ValidadorMobile;
import nutes.telecardio.modelo.configuracao.Configuracao;
import nutes.telecardio.modelo.configuracao.ConfiguracaoDao;
import nutes.telecardio.modelo.configuracao.IConfiguracaoDao;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.Transacao;
import nutes.telecardio.modelo.operacional.IPacienteDao;
import nutes.telecardio.modelo.operacional.ITipoSanguineoDao;
import nutes.telecardio.modelo.operacional.PacienteDao;
import nutes.telecardio.modelo.operacional.PacienteMobile;
import nutes.telecardio.modelo.operacional.RegistroExame;
import nutes.telecardio.modelo.operacional.RegistroExameXMLReader;
import nutes.telecardio.modelo.operacional.TipoSanguineoDao;
import nutes.telecardio.utils.Funcoes;

/**
 * Serviço Exame.
 * 
 * @author WaldsonMoura
 * 
 */
public class ServicoExame extends ServicoFuncionalidade {

	IExameDao exameDao = new ExameDao();
	IEnderecoDao enderecoDao = new EnderecoDao();
	IPessoaDao pessoaDao = new PessoaDao();
	IPacienteDao pacienteDao = new PacienteDao();
	ITipoSanguineoDao tipoSanguineoDao = new TipoSanguineoDao();
	IConfiguracaoDao configuracaoDao = new ConfiguracaoDao();
	IProfissionalDao profissionalDao = new ProfissionalDao();
	IUsuarioDao usuarioDao = new UsuarioDao();

	/**
	 * Inclui um novo {@link Exame} (solicitação), deixando seu estado 0 -
	 * Aberto.
	 * 
	 * @param {@link Exame}
	 * @return boolean
	 */
	public Validador incluir(Exame exame) {
		Validador validador = new Validador();
		exame.validarEntidade(validador);
		exame.getPaciente().validarEntidade(validador);

		if (validador.getMsgRetorno().size() <= 0) {

			int idPaciente = exame.getPaciente().getChave();

			int idExame = 0;

			boolean existeEndereco = false, existePessoa = false;

			Transacao transacao = new Transacao();

			if (idPaciente <= 0) {

				int idEndereco = 0, idPessoa = 0;

				// Verifica se existe Endereço associado.
				if (exame.getPaciente().getEndereco() != null) {

					// Se existir Endereço, valida-o.
					exame.getPaciente().getEndereco()
							.validarEntidade(validador);

					if (validador.getMsgRetorno().size() <= 0) {
						transacao
								.addDaoTransacao((ObjetoNegocioDao) enderecoDao);

						// Se Endereço for válido, tenta cadastrar.
						idEndereco = enderecoDao.incluir(exame.getPaciente()
								.getEndereco());
						exame.getPaciente().getEndereco().setChave(idEndereco);
						if (idEndereco > 0)
							existeEndereco = true;
					} else
						// Caso o Endereço esteja inválido, já retorno o
						// validador.
						return validador;
				}

				transacao.addDaoTransacao((ObjetoNegocioDao) pessoaDao);
				transacao.addDaoTransacao((ObjetoNegocioDao) pacienteDao);

				idPessoa = pessoaDao.incluir(exame.getPaciente());
				exame.getPaciente().setChavePessoa(idPessoa);

				if (idPessoa > 0)
					existePessoa = true;

				idPaciente = pacienteDao.incluir(exame.getPaciente());
				exame.getPaciente().setChave(idPaciente);
			} else {
				existeEndereco = true;
				existePessoa = true;
			}

			transacao.addDaoTransacao((ObjetoNegocioDao) exameDao);

			idExame = exameDao.incluir(exame);

			if ((exame.getPaciente().getEndereco() != null && existeEndereco)
					|| existePessoa && idPaciente > 0 && idExame > 0)
				transacao.commitTransacao();
			else {
				transacao.rollbackTransacao();
				validador.getMsgRetorno().add(
						MensagensNegocio.transacaoSemSucesso);
			}
		}

		return validador;
	}

	/**
	 * Atualiza as informações do {@link Exame}.
	 * 
	 * @param {@link Exame}
	 * @return boolean
	 */
	public boolean atualizar(Exame exame) {
		return exameDao.atualizar(exame);
	}

	/**
	 * Torna o Exame Inativo no sistema.
	 * 
	 * @param Exame
	 * @return boolean
	 */
	public boolean excluir(Exame exame) {
		return exameDao.excluir(exame);
	}

	/**
	 * Consulta o estado de um {@link Exame} de acordo com o Id que foi passado.
	 * 
	 * @return int
	 */
	public int consultarEstado(int id) {
		return exameDao.consultarEstado(id);
	}

	/**
	 * Laudar a soliciticação.
	 * 
	 * @param {@link Exame}
	 * @return {@link Validador}
	 */
	public Validador laudar(Exame exame) {
		Validador validador = new Validador();
		exame.validarEntidadeLaudar(validador);
		if (validador.getMsgRetorno().size() <= 0)
			exameDao.laudar(exame);

		return validador;
	} 

	/**
	 * Definir a solicitação como Laudando.
	 * 
	 * @param idExame
	 * @return Validador.
	 */
	public Validador definirLaudando(int id) {
		Exame exame = new Exame();
		exame = exameDao.consultarPorId(id);

		Validador validador = new Validador();
		int estado = consultarEstado(id);

		if (estado == Exame.Estados.ABERTO.getId()
				|| (estado == Exame.Estados.NAO_LAUDADO.getId() && exame
						.getMedicoDesignadoCrm() != 0))
			exameDao.definirLaudando(id);
		else if (estado == Exame.Estados.LAUDANDO.getId())
			validador.getMsgRetorno().add(MensagensNegocio.exameLaudando);
		else if (estado == Exame.Estados.LAUDADO.getId())
			validador.getMsgRetorno().add(MensagensNegocio.exameLaudado);
		else if (estado == Exame.Estados.NAO_LAUDADO.getId()
				&& exame.getMedicoDesignadoCrm() == 0)
			validador.getMsgRetorno().add(MensagensNegocio.exameNaoLaudado);

		return validador;
	}

	/**
	 * Definir a solicitação como Laudando pelo mobile.
	 * 
	 * @param idExame
	 * @return Validador.
	 */
	public ValidadorMobile definirLaudandoMobile(int chaveExame) {
		Exame exame = new Exame();
		exame = exameDao.consultarPorId(chaveExame);

		Validador validador = new Validador();
		int estado = consultarEstado(chaveExame);

		if (estado == Exame.Estados.ABERTO.getId()
				|| (estado == Exame.Estados.NAO_LAUDADO.getId() && exame
						.getMedicoDesignadoCrm() != 0))
			exameDao.definirLaudando(chaveExame);
		else if (estado == Exame.Estados.LAUDANDO.getId())
			validador.getMsgRetorno().add(MensagensNegocio.exameLaudando);
		else if (estado == Exame.Estados.LAUDADO.getId())
			validador.getMsgRetorno().add(MensagensNegocio.exameLaudado);
		else if (estado == Exame.Estados.NAO_LAUDADO.getId()
				&& exame.getMedicoDesignadoCrm() == 0)
			validador.getMsgRetorno().add(MensagensNegocio.exameNaoLaudado);

		ValidadorMobile validadormobile = new ValidadorMobile();
		validadormobile.msgsRetorno = new String[validador.getMsgRetorno()
				.size()];
		for (int i = 0; i < validadormobile.msgsRetorno.length; i++) {
			validadormobile.msgsRetorno[i] = validador.getMsgRetorno().get(i);
		}

		return validadormobile;
	}

	/**
	 * Cancelar laudo (deixar solicitação no estado anterior).
	 * 
	 * @param {@link Exame}
	 * @return boolean true caso tenha cancelado, false caso não tenha
	 *         cancelado.
	 */
	public boolean cancelar(Exame exame) {
		return exameDao.cancelar(exame);
	}

	/**
	 * Retorna um {@link Exame} de acordo com o Id que foi passado..
	 * 
	 * @return {@link Exame}
	 */
	public Exame consultarPorId(int id) {
		Exame exame = new Exame();
		exame = exameDao.consultarPorId(id);

		int estado = consultarEstado(id);

		if (estado == Exame.Estados.ABERTO.getId()
				|| (estado == Exame.Estados.NAO_LAUDADO.getId() && exame
						.getMedicoDesignadoCrm() != 0)) {
			int idTipoSanguineo = exame.getPaciente().getTipoSanguineo()
					.getChave();
			exame.getPaciente().setTipoSanguineo(
					tipoSanguineoDao.consultarPorId(idTipoSanguineo));
		}

		return exame;
	}

	/**
	 * Retorna uma List<Exame> com todos os exames abertos.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @param int crm
	 * 
	 * @return List<Exame>
	 */
	public List<Exame> consultarTodosAbertos(String campo, String valor, int crm) {
		List<Exame> exames = exameDao.consultarTodosAbertos(campo, valor, crm);
		List<Exame> listaExames = new ArrayList<Exame>();
		Configuracao configuracao = configuracaoDao.consultarAtiva();
		int limiteExameAberto = configuracao.getLimiteExameAberto();

		for (Exame exame : exames) {
			exame.getPaciente().setEndereco(
					enderecoDao.ConsultarEnderecoPorId(exame.getPaciente()
							.getEndereco().getChave()));
			exame
					.setPassouLimiteAberto((exame.getHorasAberto() >= limiteExameAberto));

			listaExames.add(exame);
		}

		return listaExames;
	}

	/**
	 * Retorna uma List<{@link Exame}> com todos os exames laudados ou não
	 * laudados, contanto que os não laudados não tenham encaminhamento, ou
	 * seja, o crm do médico designado seja null.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * 
	 * @return List<{@link Exame}>
	 */
	public List<Exame> consultarTodosConcluidos(String campo, String valor) {
		List<Exame> exames = exameDao.consultarTodosConcluidos(campo, valor);
		List<Exame> listaExames = new ArrayList<Exame>();

		int chaveUsuario = 0;

		for (Exame exame : exames) {
			exame.getPaciente().setEndereco(
					enderecoDao.ConsultarEnderecoPorId(exame.getPaciente()
							.getEndereco().getChave()));
			String xml = Funcoes.identifyXmlName(Configuracao.pathLocal
					+ File.separator + exame.getCaminhoArquivos());
			RegistroExameXMLReader registroExameXMLReader = new RegistroExameXMLReader();
			registroExameXMLReader.run(xml);
			RegistroExame registroExame = registroExameXMLReader
					.retornarRegistroExame();
			exame.setRegistroExame(new RegistroExame());
			exame.setRegistroExame(registroExame);
			exame.setProfissional(profissionalDao
					.ConsultarProfissionalPorChave(exame.getProfissional()
							.getChave()));
			chaveUsuario = exame.getProfissional().getUsuario().getChave();
			exame.getProfissional().setUsuario(
					usuarioDao.consultarUsuarioPorChave(chaveUsuario));

			listaExames.add(exame);
		}

		return listaExames;
	}

	/**
	 * Consulta todos os profissionais do tipo médico, menos o passado por
	 * parâmetro!
	 * 
	 * @param id
	 * @return List<{@link Profissional}>
	 */
	public List<Profissional> consultarMedicosMenosParametro(int id) {
		List<Profissional> profissionais = profissionalDao
				.consultarMedicosMenosParametro(id);

		if (profissionais != null) {
			for (Profissional profissional : profissionais) {
				profissional.setUsuario(usuarioDao
						.consultarUsuarioPorChave(profissional.getUsuario()
								.getChave()));
			}
		}

		return profissionais;
	}

	/**
	 * Consulta todos os profissionais do tipo médico, menos o passado por
	 * parâmetro!
	 * 
	 * @param id
	 * @return List<{@link Profissional}>
	 */
	public MedicoMobile[] consultarMedicosMobileMenosParametro(int id) {
		List<Profissional> profissionais = profissionalDao
				.consultarMedicosMenosParametro(id);

		if (profissionais != null) {
			for (Profissional profissional : profissionais) {
				profissional.setUsuario(usuarioDao
						.consultarUsuarioPorChave(profissional.getUsuario()
								.getChave()));
			}
		}
		MedicoMobile[] medicosMobile = new MedicoMobile[profissionais.size()];
		for (int i = 0; i < medicosMobile.length; i++) {
			MedicoMobile medicoMobile = null;
			medicoMobile = new MedicoMobile();
			
			medicoMobile.Crm = profissionais.get(i)
					.getProfissionaisOrgaos().get(0).getHabilitacao();
			medicoMobile.Senha = profissionais.get(i).getUsuario()
					.getSenha();
			medicoMobile.Chave = profissionais.get(i).getUsuario()
					.getChave();
			medicoMobile.Nome = profissionais.get(i).getUsuario().getNome();
			medicoMobile.Login = profissionais.get(i).getUsuario()
					.getLogin();
			medicosMobile[i] = medicoMobile;
		}

		return medicosMobile;
	}

	/**
	 * Retorna uma coleção com todos os exames abertos.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @param int crm
	 * 
	 * @return Exame[]
	 */
	public ExameMobile[] consultarSolicitacoesAbertosMobile(String campo,
			String valor, int crm) {
		List<Exame> exames = exameDao.consultarTodosAbertos(campo, valor, crm);
		List<Exame> listaExames = new ArrayList<Exame>();
		Configuracao configuracao = configuracaoDao.consultarAtiva();
		int limiteExameAberto = configuracao.getLimiteExameAberto();

		for (Exame exame : exames) {
			exame.getPaciente().setEndereco(
					enderecoDao.ConsultarEnderecoPorId(exame.getPaciente()
							.getEndereco().getChave()));
			exame
					.setPassouLimiteAberto((exame.getHorasAberto() >= limiteExameAberto));

			listaExames.add(exame);
		}

		ExameMobile[] examesMobile = new ExameMobile[listaExames.size()];

		for (int i = 0; i < examesMobile.length; i++) {

			EnderecoMobile enderecoMobile = null;
			enderecoMobile = new EnderecoMobile();
			enderecoMobile.bairro = listaExames.get(i).getPaciente()
					.getEndereco().getBairro();
			enderecoMobile.cep = listaExames.get(i).getPaciente().getEndereco()
					.getCep();
			enderecoMobile.complemento = listaExames.get(i).getPaciente()
					.getEndereco().getComplemento();
			enderecoMobile.idUf = listaExames.get(i).getPaciente()
					.getEndereco().getUf().getChave() == null ? 0 : listaExames
					.get(i).getPaciente().getEndereco().getUf().getChave();
			enderecoMobile.logradouro = listaExames.get(i).getPaciente()
					.getEndereco().getLogradouro();
			enderecoMobile.municipio = listaExames.get(i).getPaciente()
					.getEndereco().getMunicipio();
			enderecoMobile.numero = listaExames.get(i).getPaciente()
					.getEndereco().getNumero();
			enderecoMobile.ufNome = listaExames.get(i).getPaciente()
					.getEndereco().getUf().getNome();
			enderecoMobile.ufSigla = listaExames.get(i).getPaciente()
					.getEndereco().getUf().getSigla();

			PacienteMobile pacienteMobile = null;
			pacienteMobile = new PacienteMobile();
			pacienteMobile.enderecoMobileEmString = enderecoMobile
					.converterEntidadeString();
			pacienteMobile.chave = listaExames.get(i).getPaciente().getChave() == null ? 0
					: listaExames.get(i).getPaciente().getChave();
			pacienteMobile.dataUltimoContato = listaExames.get(i).getPaciente()
					.getDataUltimoContato();
			pacienteMobile.idConvenio = listaExames.get(i).getPaciente()
					.getIdConvenio();
			pacienteMobile.idTipoSanguineo = listaExames.get(i).getPaciente()
					.getTipoSanguineo().getChave() == null ? 0 : listaExames
					.get(i).getPaciente().getTipoSanguineo().getChave();
			pacienteMobile.nomeMae = listaExames.get(i).getPaciente()
					.getNomeMae();
			pacienteMobile.tipoSanguineoNome = listaExames.get(i).getPaciente()
					.getTipoSanguineo().getNome();
			pacienteMobile.nomePaciente = listaExames.get(i).getPaciente()
					.getNome();
			pacienteMobile.cpfPaciente = listaExames.get(i).getPaciente()
					.getCpf();
			pacienteMobile.dataNascimentoPaciente = listaExames.get(i)
					.getPaciente().getDataNascimento();
			pacienteMobile.sexo = listaExames.get(i).getPaciente().getSexo();

			ExameMobile exameMobile = null;
			exameMobile = new ExameMobile();
			exameMobile.pacienteMobileEmString = pacienteMobile
					.converterPacienteString();
			exameMobile.chave = listaExames.get(i).getChave() == null ? 0
					: listaExames.get(i).getChave();
			exameMobile.dataSolicitacao = listaExames.get(i)
					.getDataSolicitacao();
			exameMobile.horasAberto = listaExames.get(i).getHorasAberto();
			exameMobile.observacoes = listaExames.get(i).getObservacoes();
			exameMobile.parecerJustificativa = listaExames.get(i)
					.getParecerJustificativa();
			exameMobile.passouLimiteAberto = listaExames.get(i)
					.isPassouLimiteAberto();
			exameMobile.pesoPaciente = listaExames.get(i).getPesoPaciente();

			examesMobile[i] = exameMobile;

		}

		return examesMobile;
	}

	/**
	 * Retorna uma coleção com todos os exames abertos.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @param int crm
	 * 
	 * @return Exame[]
	 */
	public ExameMobile consultarExameMobilePorChave(int chaveExame) {

		Exame exame = new Exame();
		exame = exameDao.consultarPorId(chaveExame);

		int estado = consultarEstado(chaveExame);

		if (estado == Exame.Estados.ABERTO.getId()
				|| (estado == Exame.Estados.NAO_LAUDADO.getId() && exame
						.getMedicoDesignadoCrm() != 0)) {
			int idTipoSanguineo = exame.getPaciente().getTipoSanguineo()
					.getChave();
			exame.getPaciente().setTipoSanguineo(
					tipoSanguineoDao.consultarPorId(idTipoSanguineo));
		}

		EnderecoMobile enderecoMobile = null;
		enderecoMobile = new EnderecoMobile();

		if (exame.getPaciente().getEndereco() != null
				&& exame.getPaciente().getEndereco().getUf() != null) {

			enderecoMobile.idUf = exame.getPaciente().getEndereco().getUf()
					.getChave() == null ? 0 : exame.getPaciente().getEndereco()
					.getUf().getChave();
			enderecoMobile.ufNome = exame.getPaciente().getEndereco().getUf()
					.getNome();
			enderecoMobile.ufSigla = exame.getPaciente().getEndereco().getUf()
					.getSigla();

		}

		if (exame.getPaciente().getEndereco() != null) {
			enderecoMobile.bairro = exame.getPaciente().getEndereco()
					.getBairro();
			enderecoMobile.cep = exame.getPaciente().getEndereco().getCep();
			enderecoMobile.complemento = exame.getPaciente().getEndereco()
					.getComplemento();
			enderecoMobile.logradouro = exame.getPaciente().getEndereco()
					.getLogradouro();

			enderecoMobile.municipio = exame.getPaciente().getEndereco()
					.getMunicipio();
			enderecoMobile.numero = exame.getPaciente().getEndereco()
					.getNumero();
		}

		PacienteMobile pacienteMobile = null;
		pacienteMobile = new PacienteMobile();
		pacienteMobile.enderecoMobileEmString = enderecoMobile
				.converterEntidadeString();
		pacienteMobile.chave = exame.getPaciente().getChave() == null ? 0
				: exame.getPaciente().getChave();

		pacienteMobile.dataUltimoContato = exame.getPaciente()
				.getDataUltimoContato();
		pacienteMobile.idConvenio = exame.getPaciente().getIdConvenio();

		pacienteMobile.nomeMae = exame.getPaciente().getNomeMae();

		if (exame.getPaciente().getTipoSanguineo() == null) {
			pacienteMobile.tipoSanguineoNome = exame.getPaciente()
					.getTipoSanguineo().getNome();
			pacienteMobile.idTipoSanguineo = exame.getPaciente()
					.getTipoSanguineo().getChave() == null ? 0 : exame
					.getPaciente().getTipoSanguineo().getChave();
		}
		pacienteMobile.nomePaciente = exame.getPaciente().getNome();
		pacienteMobile.cpfPaciente = exame.getPaciente().getCpf();
		pacienteMobile.dataNascimentoPaciente = exame.getPaciente()
				.getDataNascimento();
		pacienteMobile.sexo = exame.getPaciente().getSexo();

		ExameMobile exameMobile = null;
		exameMobile = new ExameMobile();
		exameMobile.pacienteMobileEmString = pacienteMobile
				.converterPacienteString();
		exameMobile.chave = exame.getChave() == null ? 0 : exame.getChave();
		exameMobile.dataSolicitacao = exame.getDataSolicitacao();
		exameMobile.horasAberto = exame.getHorasAberto();
		exameMobile.observacoes = exame.getObservacoes();
		exameMobile.parecerJustificativa = exame.getParecerJustificativa();
		exameMobile.passouLimiteAberto = exame.isPassouLimiteAberto();
		exameMobile.pesoPaciente = exame.getPesoPaciente();
		exameMobile.alturaPaciente = exame.getAlturaPaciente();
		exameMobile.estado = exame.getEstado();
		exameMobile.caminhoArquivos = exame.getCaminhoArquivos();

		return exameMobile;
	}

}