package lib.classes
{

	/**
	 * Classe básica de Paciente.
	 * @autor WaldsonMoura
	 **/
	[RemoteClass(alias="nutes.telecardio.modelo.operacional.Paciente")]
	[Bindable]
	public class Paciente extends Pessoa
	{
		public var idConvenio:int;
		public var dataUltimoContato:String;
		public var nomeMae:String;
		public var tipoSanguineo:TipoSanguineo;
		// Propriedade criada para exibir na grid, pois não foi identificado
		// como acessar a propriedade de um objeto para tal.
		public var nomeTipoSanguineo:String;
	}
}