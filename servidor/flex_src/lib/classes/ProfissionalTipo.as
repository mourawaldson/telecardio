package lib.classes
{

	/**
	 * Classe que representa a entidade profissionais_tipos
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.ProfissionalTipo")]
	[Bindable]
	public class ProfissionalTipo extends EntidadeBasica
	{
		public var orgaoClasse:OrgaoClasse;
		public var nome:String;
	}
}