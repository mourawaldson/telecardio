package nutes.telecardio.controle;

/**
 * Entidade comum a todos os controladores do sistema que recebe a classe
 * ServicoFuncionalidade que é classe pai de todos os serviços
 * 
 * @author hvb
 * 
 * @param <ServicoFuncionalidade>
 */
public class ControladorFuncionalidade<ServicoFuncionalidade> {

	/**
	 * Propriedade que irá receber o serviço do sistema.
	 */
	public ServicoFuncionalidade servico;

}