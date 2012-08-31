package lib.classes
{
	import mx.collections.ArrayCollection;

	/**
	 * Classe que representa a entidade usuarios
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.Usuario")]
	[Bindable]
	public class Usuario extends Pessoa
	{
		public var login:String;
		public var senha:String;
		public var email:String;
		public var perfil:Perfil;
		public var funcionalidades:ArrayCollection;
		public var profissional:Profissional;
	}
}