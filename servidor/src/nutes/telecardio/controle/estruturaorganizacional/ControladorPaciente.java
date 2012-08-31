package nutes.telecardio.controle.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.controle.ControladorFuncionalidade;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.estruturaorganizacional.Pessoa;
import nutes.telecardio.modelo.estruturaorganizacional.Uf;
import nutes.telecardio.modelo.operacional.Paciente;
import nutes.telecardio.modelo.operacional.ServicoPaciente;
import nutes.telecardio.modelo.operacional.TipoSanguineo;

/**
 * Controlador {@link Paciente}.
 * 
 * @author WaldsonMoura
 * 
 */
public class ControladorPaciente extends
		ControladorFuncionalidade<ServicoPaciente> {

	/**
	 * Controlador do {@link Paciente}.
	 */
	public ControladorPaciente() {
		servico = new ServicoPaciente();
	}

	/**
	 * Inclui um novo {@link Paciente}.
	 * 
	 * @param {@link Paciente}
	 * @return {@link Validador}
	 */
	public Validador incluir(Paciente paciente) {
		return servico.incluir(paciente);
	}

	/**
	 * Atualiza as informações do {@link Paciente}.
	 * 
	 * @param {@link Paciente}
	 * @return {@link Validador}
	 */
	public Validador atualizar(Paciente paciente) {
		return servico.atualizar(paciente);
	}

	/**
	 * Torna a {@link Pessoa} Inativa no sistema.
	 * 
	 * @param {@link Paciente}
	 * @return boolean
	 */
	public boolean excluir(Paciente paciente) {
		return servico.excluir(paciente);
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
		return servico.consultarTodos(campo, valor);
	}

	/**
	 * Retorna uma List<{@link Uf}> com todas as ufs.
	 * 
	 * @return List<{@link Uf}>
	 */
	public List<Uf> consultarTodasUfs() {
		return servico.consultarTodasUfs();
	}

	/**
	 * Retorna uma List<{@link TipoSanguineo}> com todos os tipos sanguíneos.
	 * 
	 * @return List<{@link TipoSanguineo}>
	 */
	public List<TipoSanguineo> consultarTodosTiposSanguineos() {
		return servico.consultarTodosTiposSanguineos();
	}

	/**
	 * Retorna o {@link Paciente} de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return {@link Paciente}
	 */
	public Paciente consultarPorId(int id) {
		return servico.consultarPorId(id);
	}
}
