package lib.classes
{

	/**
	 * Classe que representa a entidade estados
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.Uf")]
	[Bindable]
	public class Uf extends EntidadeBasica
	{
		public var sigla:String;
		public var nome:String;
	}
}