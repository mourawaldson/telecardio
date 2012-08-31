package nutes.telecardio.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que irá receber o retorno das validações de negocio e as mensagens de
 * negocio para veicula ate a GUI
 * 
 * @author hvb
 * 
 */
public class Validador {

	private List<String> msgsRetorno = new ArrayList<String>();

	public void setMsgRetorno(List<String> msgRetorno) {
		this.msgsRetorno = msgRetorno;
	}

	public List<String> getMsgRetorno() {
		return msgsRetorno;
	}

}
