package lib.classes
{
	import mx.collections.ArrayCollection;

	/**
	 * Classe básica do Registro Exame.
	 * @autor WaldsonMoura
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.operacional.RegistroExame")]
	[Bindable]
	public class RegistroExame
	{
		public var data:String;
		public var hora:String;
		public var velocidade:int;
		public var sensibilidade:String;
		public var amostrasPorBloco:int;
		public var frequenciaCardiaca:int;
		public var canalRitmo:int;
		public var taxaAmostragem:String;
		public var DIGanho:int;
		public var DIAmostra:ArrayCollection;
		public var DIIGanho:int;
		public var DIIAmostra:ArrayCollection;
		public var DIIIGanho:int;
		public var DIIIAmostra:ArrayCollection;
		public var aVRGanho:int;
		public var aVRAmostra:ArrayCollection;
		public var aVLGanho:int;
		public var aVLAmostra:ArrayCollection;
		public var aVFGanho:int;
		public var aVFAmostra:ArrayCollection;
		public var V1Ganho:int;
		public var V1Amostra:ArrayCollection;
		public var V2Ganho:int;
		public var V2Amostra:ArrayCollection;
		public var V3Ganho:int;
		public var V3Amostra:ArrayCollection;
		public var V4Ganho:int;
		public var V4Amostra:ArrayCollection;
		public var V5Ganho:int;
		public var V5Amostra:ArrayCollection;
		public var V6Ganho:int;
		public var V6Amostra:ArrayCollection;
	}
}