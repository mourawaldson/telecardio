package nutes.telecardio.controle.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.controle.ControladorFuncionalidade;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.estruturaorganizacional.Exame;
import nutes.telecardio.modelo.estruturaorganizacional.Profissional;
import nutes.telecardio.modelo.estruturaorganizacional.ServicoExame;

/**
 * Controlador {@link Exame}.
 * 
 * @author WaldsonMoura
 * 
 */
public class ControladorExame extends ControladorFuncionalidade<ServicoExame> {

	/**
	 * Controlador do {@link Exame}.
	 */
	public ControladorExame() {
		servico = new ServicoExame();
	}

	/**
	 * Inclui um novo {@link Exame} (solicitação), deixando seu estado 0 -
	 * Aberto.
	 * 
	 * @param {@link Exame}
	 * @return {@link Validador}
	 */
	public Validador incluir(Exame exame) {
		return servico.incluir(exame);
	}

	/**
	 * Atualiza as informações do {@link Exame}.
	 * 
	 * @param {@link Exame}
	 * @return boolean
	 */
	public boolean atualizar(Exame exame) {
		return servico.atualizar(exame);
	}

	/**
	 * Torna o {@link Exame} Inativo no sistema.
	 * 
	 * @param {@link Exame}
	 * @return boolean
	 */
	public boolean excluir(Exame exame) {
		return servico.excluir(exame);
	}

	/**
	 * Laudar a soliciticação.
	 * 
	 * @param {@link Exame}
	 * @return {@link Validador}
	 */
	public Validador laudar(Exame exame) {
		return servico.laudar(exame);
	}

	/**
	 * Definir a solicitação como Laudado.
	 * 
	 * @param id
	 * @return boolean true caso tenha laudado, false caso não tenha laudado.
	 */
	public Validador definirLaudando(int id) {
		return servico.definirLaudando(id);
	}

	/**
	 * Cancelar laudo (deixar solicitação no estado anterior).
	 * 
	 * @param {@link Exame}
	 * @return boolean true caso tenha cancelado, false caso não tenha
	 *         cancelado.
	 */
	public boolean cancelar(Exame exame) {
		return servico.cancelar(exame);
	}

	/**
	 * Retorna um {@link Exame} de acordo com o Id que foi passado..
	 * 
	 * @return {@link Exame}
	 */
	public Exame consultarPorId(int id) {
		return servico.consultarPorId(id);
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
		return servico.consultarTodosAbertos(campo, valor, crm);
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
		return servico.consultarTodosConcluidos(campo, valor);
	}

	/**
	 * Consulta todos os profissionais do tipo médico, menos o passado por
	 * parâmetro!
	 * 
	 * @param id
	 * @return List<{@link Profissional}>
	 */
	public List<Profissional> consultarMedicosMenosParametro(int id) {
		return servico.consultarMedicosMenosParametro(id);
	}
}