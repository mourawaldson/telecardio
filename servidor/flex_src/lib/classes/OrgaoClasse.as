package lib.classes
{

	/**
	 * Classe que representa a entidade orgaos_classes
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.OrgaoClasse")]
	[Bindable]
	public class OrgaoClasse extends EntidadeBasica
	{
		public var sigla:String;
		public var nome:String;
	}
}