package lib.classes
{

	/**
	 * Classe básica do Tipo Sanguíneo.
	 * @autor WaldsonMoura
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.operacional.TipoSanguineo")]
	[Bindable]
	public class TipoSanguineo extends EntidadeBasica
	{
		public var nome:String;
	}
}