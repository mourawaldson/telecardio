package nutes.telecardio.modelo;

/**
 * Entidade que representa a pessoa do banco de dados, ou seja, todas as
 * entidades devem herdar dela
 * 
 * @author hvb
 * 
 * @param <TChave>
 */
public abstract class ObjetoNegocio<TChave> {

	/**
	 * Chave a entidade do tipo que a mesma possui no banco de dados
	 */
	private TChave chave;

	/**
	 * Metodo que faz a validação do obejto de negocio antes de ser incluido no
	 * banco de dados e retorna o validador que foi passado como parametro
	 * 
	 * @return Validador validador
	 * @param Validador
	 *            validador
	 */
	public abstract Validador validarEntidade(Validador validador);

	public TChave getChave() {
		return chave;
	}

	public void setChave(TChave chave) {
		this.chave = chave;
	}

}
