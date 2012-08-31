package nutes.telecardio.modelo.operacional;

import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.ServicoFuncionalidade;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.Transacao;
import nutes.telecardio.modelo.estruturaorganizacional.EnderecoDao;
import nutes.telecardio.modelo.estruturaorganizacional.IEnderecoDao;
import nutes.telecardio.modelo.estruturaorganizacional.IPessoaDao;
import nutes.telecardio.modelo.estruturaorganizacional.IUfDao;
import nutes.telecardio.modelo.estruturaorganizacional.Pessoa;
import nutes.telecardio.modelo.estruturaorganizacional.PessoaDao;
import nutes.telecardio.modelo.estruturaorganizacional.Uf;
import nutes.telecardio.modelo.estruturaorganizacional.UfDao;

/**
 * Serviço {@link Paciente}.
 * 
 * @author WaldsonMoura
 * 
 */
public class ServicoPaciente extends ServicoFuncionalidade {
	private IPessoaDao pessoaDao = new PessoaDao();
	private IPacienteDao pacienteDao = new PacienteDao();
	private IEnderecoDao enderecoDao = new EnderecoDao();
	private IUfDao ufDao = new UfDao();
	private ITipoSanguineoDao tipoSanguineoDao = new TipoSanguineoDao();

	/**
	 * Inclui um novo {@link Paciente}.
	 * 
	 * @param {@link Paciente}
	 * @return {@link Validador}
	 */
	public Validador incluir(Paciente paciente) {
		Validador validador = new Validador();
		paciente.validarEntidade(validador);

		if (validador.getMsgRetorno().size() <= 0) {

			if ((paciente.getCpf() != null)
					&& !pessoaDao.consultarExistenciaCpf(paciente.getCpf(), 0)) {

				if (consultarExistencia(paciente) <= 0) {

					int idEndereco = 0, idPessoa = 0, idPaciente = 0;

					Transacao transacao = new Transacao();

					if (paciente.getEndereco() != null) {
						paciente.getEndereco().validarEntidade(validador);

						if (validador.getMsgRetorno().size() <= 0) {
							transacao
									.addDaoTransacao((ObjetoNegocioDao) enderecoDao);

							idEndereco = enderecoDao.incluir(paciente
									.getEndereco());
							paciente.getEndereco().setChave(idEndereco);
						} else
							return validador;
					}

					transacao.addDaoTransacao((ObjetoNegocioDao) pessoaDao);
					transacao.addDaoTransacao((ObjetoNegocioDao) pacienteDao);

					idPessoa = pessoaDao.incluir(paciente);
					paciente.setChavePessoa(idPessoa);

					idPaciente = pacienteDao.incluir(paciente);

					if ((paciente.getEndereco() != null && idEndereco > 0)
							|| idPessoa > 0 && idPaciente > 0)
						transacao.commitTransacao();
					else {
						transacao.rollbackTransacao();
						validador.getMsgRetorno().add(
								MensagensNegocio.transacaoSemSucesso);
					}
				} else
					validador.getMsgRetorno().add(
							MensagensNegocio.pacienteExistente);
			} else
				validador.getMsgRetorno().add(MensagensNegocio.cpfCadastrado);
		}

		return validador;
	}

	/**
	 * Atualiza as informações do {@link Paciente}.
	 * 
	 * @param {@link Paciente}
	 * @return {@link Validador}
	 */
	public Validador atualizar(Paciente paciente) {
		Validador validador = new Validador();
		paciente.validarEntidade(validador);

		if (validador.getMsgRetorno().size() <= 0) {

			if ((paciente.getCpf() != null)
					&& !pessoaDao.consultarExistenciaCpf(paciente.getCpf(),
							paciente.getChavePessoa())) {

				int idPacienteExistente = consultarExistencia(paciente);

				// Caso o paciente não exista ou seja o paciente que está
				// sendo atualizado, continua a atualização
				if (idPacienteExistente == 0
						|| idPacienteExistente == paciente.getChave()) {

					Transacao transacao = new Transacao();

					int idEndereco = 0;

					if (paciente.getEndereco() != null) {
						paciente.getEndereco().validarEntidade(validador);

						if (validador.getMsgRetorno().size() <= 0) {
							Pessoa pessoa = new Pessoa();
							pessoa = pessoaDao.consultarPorId(paciente
									.getChavePessoa());

							idEndereco = pessoa.getEndereco().getChave();

							transacao
									.addDaoTransacao((ObjetoNegocioDao) enderecoDao);

							// Se já existir endereço incluído, atualiza!
							if (idEndereco > 0) {
								paciente.getEndereco().setChave(idEndereco);
								boolean atualizarEndereco = enderecoDao
										.atualizar(paciente.getEndereco());
								if (!atualizarEndereco) {
									validador
											.getMsgRetorno()
											.add(
													MensagensNegocio.enderecoErroAtualizar);
									return validador;
								}
								// Caso contrário, inclui!
							} else {
								idEndereco = enderecoDao.incluir(paciente
										.getEndereco());
								paciente.getEndereco().setChave(idEndereco);
								if (idEndereco <= 0) {
									validador
											.getMsgRetorno()
											.add(
													MensagensNegocio.enderecoErroIncluir);
									return validador;
								}
							}
						} else
							return validador;
					}

					transacao.addDaoTransacao((ObjetoNegocioDao) pessoaDao);
					transacao.addDaoTransacao((ObjetoNegocioDao) pacienteDao);

					boolean atualizarPessoa = pessoaDao.atualizar(paciente);
					boolean atualizarPaciente = pacienteDao.atualizar(paciente);

					if (atualizarPaciente && atualizarPessoa) {
						transacao.commitTransacao();
					} else {
						transacao.rollbackTransacao();
						validador.getMsgRetorno().add(
								MensagensNegocio.transacaoSemSucesso);
					}
				} else
					validador.getMsgRetorno().add(
							MensagensNegocio.pacienteExistente);
			} else
				validador.getMsgRetorno().add(MensagensNegocio.cpfCadastrado);
		}

		return validador;
	}

	/**
	 * Torna a {@link Pessoa} Inativa no sistema.
	 * 
	 * @param {@link Paciente}
	 * @return boolean
	 */
	public boolean excluir(Paciente paciente) {
		return pacienteDao.excluir(paciente);
	}

	/**
	 * Retorna uma List<{@link Paciente}> com todos os pacientes.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @return List<{@link Paciente}>
	 */
	public List<Paciente> consultarTodos(String campo, String valor) {
		return pacienteDao.consultarTodos(campo, valor);
	}

	/**
	 * Retorna uma List<{@link Uf}> com todas as ufs.
	 * 
	 * @return List<{@link Uf}>
	 */
	public List<Uf> consultarTodasUfs() {
		return ufDao.consultarListaUf();
	}

	/**
	 * Retorna uma List<{@link TipoSanguineo}> com todos os tipos sanguíneos.
	 * 
	 * @return List<{@link TipoSanguineo}>
	 */
	public List<TipoSanguineo> consultarTodosTiposSanguineos() {
		return tipoSanguineoDao.consultarTodos();
	}

	/**
	 * Consulta pelo nome e pela data de nascimento se o {@link Paciente} já
	 * está cadastrado.
	 * 
	 * @param {@link Paciente}
	 * @return int id do {@link Paciente} caso ele exista e 0 se não existir.
	 */
	public int consultarExistencia(Paciente paciente) {
		return pacienteDao.consultarExistencia(paciente);
	}

	/**
	 * Retorna o {@link Paciente} de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return {@link Paciente}
	 */
	public Paciente consultarPorId(int id) {
		Paciente paciente = new Paciente();

		paciente = pacienteDao.consultarPorId(id);
		int idTipoSanguineo = paciente.getTipoSanguineo().getChave();
		paciente.setTipoSanguineo(tipoSanguineoDao
				.consultarPorId(idTipoSanguineo));
		int idEndereco = paciente.getEndereco().getChave();
		if (idEndereco > 0) {
			paciente
					.setEndereco(enderecoDao.ConsultarEnderecoPorId(idEndereco));
			int idUf = paciente.getEndereco().getUf().getChave();
			paciente.getEndereco().setUf(ufDao.consultarUfPorId(idUf));
		} else
			paciente.setEndereco(null);

		return paciente;
	}
}
