package lib.classes
{

	/**
	 * Classe que representa a entidade funcionalidades
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.Funcionalidade")]
	[Bindable]
	public class Funcionalidade extends EntidadeBasica
	{
		public var nome:String;
		public var descricao:String;
	}
}