package lib.classes
{
	import mx.collections.ArrayCollection;

	/**
	 * Classe que representa a entidade perfis
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.Perfil")]
	[Bindable]
	public class Perfil extends EntidadeBasica
	{
		public var nome:String;
		public var funcionalidades:ArrayCollection;
	}
}