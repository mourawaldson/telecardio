package nutes.telecardio.controle.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.controle.ControladorFuncionalidade;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.Configuracao;
import nutes.telecardio.modelo.configuracao.ServicoConfiguracao;

/**
 * Controlador {@link Configuracao}.
 * 
 * @author WaldsonMoura
 * 
 */
public class ControladorConfiguracao extends
		ControladorFuncionalidade<ServicoConfiguracao> {

	/**
	 * Controlador da {@link Configuracao}.
	 */
	public ControladorConfiguracao() {
		servico = new ServicoConfiguracao();
	}

	/**
	 * Inclui uma nova {@link Configuracao}.
	 * 
	 * @param {@link Configuracao}
	 * @return boolean
	 */
	public Validador incluir(Configuracao configuracao) {
		return servico.incluir(configuracao);
	}

	/**
	 * Atualiza as informações da {@link Configuracao}.
	 * 
	 * @param {@link Configuracao}
	 * @return boolean
	 */
	public Validador atualizar(Configuracao configuracao) {
		return servico.atualizar(configuracao);
	}

	/**
	 * Torna a {@link Configuracao} Inativa no sistema.
	 * 
	 * @param {@link Configuracao}
	 * @return boolean
	 */
	public Validador ativar(Configuracao configuracao) {
		return servico.ativar(configuracao);
	}

	/**
	 * Retorna uma List<{@link Configuracao}> com todas as Configurações.
	 * 
	 * @return List<{@link Configuracao}>
	 */
	public List<Configuracao> consultarTodas() {
		return servico.consultarTodas();
	}

	/**
	 * Retorna a Configuração Ativa.
	 * 
	 * @return Configuracao
	 */
	public Configuracao consultarAtiva() {
		return servico.consultarAtiva();
	}

	/**
	 * Retorna a {@link Configuracao} de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return {@link Configuracao}
	 */
	public Configuracao consultarPorId(int id) {
		return servico.consultarPorId(id);
	}
}
