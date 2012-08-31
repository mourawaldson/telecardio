package lib.classes
{
	import mx.collections.ArrayCollection;

	/**
	 * Classe que representa a entidade profissinais
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.Profissional")]
	[Bindable]
	public class Profissional extends EntidadeBasica
	{
		public var usuario:Usuario;
		public var profissionalTipo:ProfissionalTipo;
		public var profissionaisOrgaos:ArrayCollection;

		public var nomeProfissional:String;
	}
}