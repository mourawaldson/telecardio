package lib.configuracao
{
	import mx.collections.ArrayCollection;

	/**
	 * Classe que representa O validador do negocio
	 * @autor : hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.Validador")]
	[Bindable]
	public class Validador
	{
		public var msgRetorno:ArrayCollection;
	}
}