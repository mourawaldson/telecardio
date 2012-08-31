package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.ArrayList;
import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.ServicoFuncionalidade;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.NomesCampos;
import nutes.telecardio.modelo.configuracao.Transacao;

/**
 * Serviço de Usuario
 * 
 * @author hvb
 * 
 */
public class ServicoUsuario extends ServicoFuncionalidade {

	// Daos
	private IUsuarioDao usuarioDao = new UsuarioDao();
	private IOrgaoClasseDao orgaoClasseDao = new OrgaoClasseDao();
	private IUfDao ufDao = new UfDao();
	private IProfissionaisTipoDao profissionaisTipoDao = new ProfissionaisTipoDao();
	private IProfissionalDao profissionalDao = new ProfissionalDao();
	private IPerfilDao perfilDao = new PerfilDao();
	private IEnderecoDao enderecoDao = new EnderecoDao();
	private IPessoaDao pessoaDao = new PessoaDao();
	private IFuncionalidadeDao funcionalidadeDao = new FuncionalidadeDao();

	/**
	 * Faz a inclusão de um usuário retorna um Validador com alguma mensagem de
	 * validação da operação e da entidade caso exista
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Validador validador
	 */
	public Validador inserirUsuarioProfissional(Usuario usuario) {

		// Objeto que ira receber todas as mensagens de erro para ser
		// retornado ate a GUI
		Validador validador = new Validador();

		// Faz a validação do usuário
		usuario.validarEntidade(validador);
		usuario.getEndereco().validarEntidade(validador);

		// Verifiar se habilitação e cpf do usuário ja existe
		if (usuario.getProfissional() != null) {
			if (profissionalDao.verificarExistenciaHabilitacao(usuario
					.getProfissional(),false)) {
				validador.getMsgRetorno().add(
						MensagensNegocio.habilitacaoJaCadastrada);
			}
			if (pessoaDao.consultarExistenciaCpf(usuario.getCpf(), 0)) {
				validador.getMsgRetorno().add(MensagensNegocio.cpfCadastrado);
			}
		}

		// Se não tiver nenhuma mensagem de erro entao o usuário é valido neste
		// ponto
		if (validador.getMsgRetorno().size() <= 0) {

			// Como vai ter mais de um Dao se realizar a operação atraves de
			// uma
			// transação e adiciona os Daos na mesma
			Transacao transacao = new Transacao();
			transacao.addDaoTransacao((ObjetoNegocioDao) enderecoDao);
			transacao.addDaoTransacao((ObjetoNegocioDao) pessoaDao);
			transacao.addDaoTransacao((ObjetoNegocioDao) usuarioDao);

			// Inclui um endereço e seta a nova chave que o mesmo tem no
			// banco
			usuario.getEndereco().setChave(
					enderecoDao.incluir(usuario.getEndereco()));

			// Inclui uma pessoa e seta a nova chave que o mesmo tem no
			// banco
			usuario.setChavePessoa(pessoaDao.incluir(usuario));

			// Inclui um usuário e seta a nova chave que o mesmo tem no
			// banco
			usuario.setChave(usuarioDao.incluir(usuario));

			// Se o usuario que esta sendo incluido for um profissional de
			// saúde
			// Entao faz a inclusão na tabela de profissinal de saúde
			// atraves do
			// seu Dao
			// E depois seta a chave do mesmo
			if (usuario.getProfissional() != null) {
				transacao.addDaoTransacao((ObjetoNegocioDao) profissionalDao);

				// Profissional de saúde possui um usuário que é o mesmo que
				// esta
				// sendo incluido
				usuario.getProfissional().setUsuario(usuario);

				usuario.getProfissional().setChave(
						profissionalDao.incluir(usuario.getProfissional()));
			}

			// Verifica se todas as entidades tem suas chaves desta
			// inclusão,
			// significa
			// que a operação teve sucesso em todas as entidades, então se
			// realiza o
			// comit atraves da trnasação, caso contrario se realiza um
			// roolback
			// para cancela
			// as inclusões e retorna uma mensagem de erro.
			if (usuario.getChave() > 0 & usuario.getEndereco().getChave() > 0
					& usuario.getChavePessoa() > 0
					& usuario.getProfissional() != null) {

				if (usuario.getProfissional().getChave() > 0) {
					transacao.commitTransacao();

				} else {

					transacao.rollbackTransacao();
					validador.getMsgRetorno().add(
							MensagensNegocio.transacaoSemSucesso);
					return validador;
				}

			} else if (usuario.getChave() > 0
					& usuario.getEndereco().getChave() > 0
					& usuario.getChavePessoa() > 0) {

				transacao.commitTransacao();

			} else {

				transacao.rollbackTransacao();
				validador.getMsgRetorno().add(
						MensagensNegocio.transacaoSemSucesso);

			}

		}

		return validador;

	}

	/**
	 * Faz a alteração de um usuário retorna um Validador com alguma mensagem de
	 * validação da operação e da entidade caso exista
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Validador validador
	 */
	public Validador alterarUsuarioProfissional(Usuario usuario) {

		// Variáveis que ira verificar se definem se as alterções ocorreram
		boolean alterarEndereco = false;
		boolean alterarPessoa = false;
		boolean alterarUsuario = false;
		boolean alterarProfissional = false;
		boolean excluirProfissional = false;

		// Validador que ira veícular as mensagem do negocio à GUI se tiver
		// algum erro
		Validador validador = new Validador();

		// Faz validação do usuário
		usuario.validarEntidade(validador);
		usuario.getEndereco().validarEntidade(validador);

		// Verifiar se habilitação e cpf do usuário ja existe
		if (usuario.getProfissional() != null) {
			if (profissionalDao.verificarExistenciaHabilitacao(usuario
					.getProfissional(),true)) {
				validador.getMsgRetorno().add(
						MensagensNegocio.habilitacaoJaCadastrada);
			}
			if (pessoaDao.consultarExistenciaCpf(usuario.getCpf(), usuario
					.getChavePessoa())) {
				validador.getMsgRetorno().add(MensagensNegocio.cpfCadastrado);
			}
		}

		// Se usuário valido realiza as alterações
		if (validador.getMsgRetorno().size() <= 0) {

			Transacao transacao = new Transacao();

			transacao.addDaoTransacao((ObjetoNegocioDao) enderecoDao);
			transacao.addDaoTransacao((ObjetoNegocioDao) pessoaDao);
			transacao.addDaoTransacao((ObjetoNegocioDao) usuarioDao);

			alterarEndereco = enderecoDao.atualizar(usuario.getEndereco());
			alterarPessoa = pessoaDao.atualizar(usuario);
			alterarUsuario = usuarioDao.atualizar(usuario);

			transacao.addDaoTransacao((ObjetoNegocioDao) profissionalDao);
			if (usuario.getProfissional() != null
					&& usuario.getProfissional().getChave() > 0) {

				alterarProfissional = profissionalDao.atualizar(usuario
						.getProfissional());
			} else if (usuario.getProfissional() != null) {

				alterarProfissional = profissionalDao.incluir(usuario
						.getProfissional()) > 0 ? true : false;
			} else {
				excluirProfissional = profissionalDao
						.excluirProfissionalPermanentePorUsuario(usuario);

			}

			// Verifica se todas as alterações foram efetuadas com sucesso e
			// comita em caso verdadeiro e faz o rollback em caso falso
			if (alterarEndereco & alterarPessoa & alterarUsuario
					& usuario.getProfissional() != null && alterarProfissional) {

				transacao.commitTransacao();

			} else if (alterarEndereco & alterarPessoa & alterarUsuario
					& excluirProfissional) {

				transacao.commitTransacao();

			} else {
				transacao.rollbackTransacao();
				validador.getMsgRetorno().add(
						MensagensNegocio.transacaoSemSucesso);
			}

		}

		return validador;

	}

	/**
	 * Exclui um usuario no banco e retorna verdadeiro se tiver sucesso e falso
	 * em caso contrario
	 * 
	 * @param Usuario
	 *            usuario
	 * @return boolean
	 */
	public boolean excluirUsuario(Usuario usuario) {
		return usuarioDao.excluir(usuario);
	}

	/**
	 * Consulta um Usuario pelo login e senha
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Usuario usuario
	 */
	public Usuario consultarUsuarioPorLoginSenha(Usuario usuario) {

		Usuario user = usuarioDao.consultarUsuarioPorLoginSenha(usuario);
		if (user != null) {
			user.setEndereco(enderecoDao.ConsultarEnderecoPorUsuario(user));
			user.setPerfil(perfilDao.ConsultarPerfilPorUsuario(user));
			user.getPerfil().setFuncionalidades(
					funcionalidadeDao
							.ConsultarFuncionalidadesPorPerfilOuUsuario(null,
									usuario.getPerfil()));
			user.setFuncionalidades(funcionalidadeDao
					.ConsultarFuncionalidadesPorPerfilOuUsuario(user, null));
			user.getEndereco().setUf(
					ufDao.ConsultarUfPorEndereco(user.getEndereco()));
			user.setProfissional(profissionalDao
					.ConsultarProfissionalPorUsuario(user));
		}

		return user;
	}

	/**
	 * Faz uma consulta por campo de pesquisa e o valor e retorna uma lista de
	 * usuarios
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @return List<Usuario> usuario
	 */
	public List<Usuario> consultarUsuarios(String campo, String valor) {

		List<Usuario> usuarios = usuarioDao.consultarUsuarios(campo, valor);
		List<Usuario> listaUsuario = new ArrayList<Usuario>();

		for (Usuario usuario : usuarios) {

			usuario.setEndereco(enderecoDao
					.ConsultarEnderecoPorUsuario(usuario));
			usuario.setPerfil(perfilDao.ConsultarPerfilPorUsuario(usuario));
			usuario.getPerfil().setFuncionalidades(
					funcionalidadeDao
							.ConsultarFuncionalidadesPorPerfilOuUsuario(null,
									usuario.getPerfil()));
			usuario.setFuncionalidades(funcionalidadeDao
					.ConsultarFuncionalidadesPorPerfilOuUsuario(usuario, null));
			usuario.getEndereco().setUf(
					ufDao.ConsultarUfPorEndereco(usuario.getEndereco()));
			usuario.setProfissional(profissionalDao
					.ConsultarProfissionalPorUsuario(usuario));

			listaUsuario.add(usuario);
		}

		return listaUsuario;

	}

	/**
	 * Retorna uma lista de orgão de classes que existe cadastrado no sistema
	 * 
	 * @return List<OrgaoClasse> nomesOrgaoClasses
	 */
	public List<OrgaoClasse> consultarListaOrgaoClasse() {
		return orgaoClasseDao.consultarListaOrgaosClasse();
	}

	/**
	 * Retorna uma lista das siglas dos estados que existe cadastrado no sistema
	 * 
	 * @return List<String> nomesSiglasUf
	 */
	public List<Uf> consultarListaUfPorOrgaoClasse(OrgaoClasse orgaoClasse) {
		return ufDao.consultarListaUfPorOrgaoClasse(orgaoClasse);
	}

	/**
	 * Retorna uma lista de tipos de profissionais cadastrados no sistema
	 * 
	 * @return List<String> nomesProfissionaisTipo
	 * @param OrgaoClasse
	 *            orgaoClasse
	 */
	public List<ProfissionalTipo> consultarListaProfissionaisTipoPorOrgao(
			OrgaoClasse orgaoClasse) {
		return profissionaisTipoDao
				.consultarListaProfissionaisTipoPorOrgao(orgaoClasse);
	}

	/**
	 * Retorna uma lista das siglas dos estados que existe cadastrado no sistema
	 * 
	 * @return List<Uf> nomesSiglasUf
	 */
	public List<Uf> consultarListaUf() {
		return ufDao.consultarListaUf();
	}

	/**
	 * Retorna uma lista de perfil do bando de dados
	 * 
	 * @return List<Perfil> perfis;
	 */
	public List<Perfil> consultarListaPerfil() {
		return perfilDao.consultarListaPerfil();
	}

	/**
	 * Consulta um usuario pela chave e retorna o usuario consultado
	 * 
	 * @param int chaveUsuario
	 * @return Usuario usuario
	 */
	public Usuario consultarUsuarioPorChave(int chaveUsuario) {

		Usuario usuario = usuarioDao.consultarUsuarioPorChave(chaveUsuario);

		usuario.setEndereco(enderecoDao.ConsultarEnderecoPorUsuario(usuario));
		usuario.setPerfil(perfilDao.ConsultarPerfilPorUsuario(usuario));
		usuario.getPerfil().setFuncionalidades(
				funcionalidadeDao.ConsultarFuncionalidadesPorPerfilOuUsuario(
						null, usuario.getPerfil()));
		usuario.setFuncionalidades(funcionalidadeDao
				.ConsultarFuncionalidadesPorPerfilOuUsuario(usuario, null));
		usuario.getEndereco().setUf(
				ufDao.ConsultarUfPorEndereco(usuario.getEndereco()));
		usuario.setProfissional(profissionalDao
				.ConsultarProfissionalPorUsuario(usuario));

		return usuario;
	}

	public MedicoMobile consultarMedicoMobilePorLoginSenha(String login,
			String senha) {
		MedicoMobile medicoMobile = null;
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);

		usuario = consultarUsuarioPorLoginSenha(usuario);
		if (usuario != null && usuario.getProfissional()!=null
				&& usuario.getProfissional().getProfissionalTipo().getNome()
						.equalsIgnoreCase(NomesCampos.perfilMedico)) {
			medicoMobile = new MedicoMobile();
			medicoMobile.Senha = (usuario.getSenha());
			medicoMobile.Login = (usuario.getLogin());
			medicoMobile.Chave = (usuario.getChave());
			medicoMobile.Nome = (usuario.getNome());
			medicoMobile.Crm = (usuario.getProfissional()
					.getProfissionaisOrgaos().get(0).getHabilitacao());
			medicoMobile.ChaveProfissional = usuario.getProfissional()
					.getChave();

		}

		return medicoMobile;

	}

}
