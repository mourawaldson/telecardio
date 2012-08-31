package lib.classes
{

	/**
	 * Classe básica da Configuracao.
	 * @autor WaldsonMoura
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.configuracao.Configuracao")]
	[Bindable]
	public class Configuracao extends EntidadeBasica
	{
		public var nome:String;
		public var pastaExames:String;
		public var limiteHabilitacoesMedico:int;
		public var limiteHabilitacoesEnfermeiro:int;
		public var limiteExameLaudando:int;
		public var limiteExameAberto:int;
		public var emailChecagemEmail:String;
		public var senhaChecagemEmail:String;
		public var portaChecagemEmail:int;
		public var hostChecagemEmail:String;
		public var intervaloChecagemEmail:int;		public var status:String;
	}
}
