package lib.classes
{

	/**
	 * Classe básica do Exame.
	 * @autor WaldsonMoura
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.Exame")]
	[Bindable]
	public class Exame extends EntidadeBasica
	{
		public var paciente:Paciente;
		public var profissional:Profissional;
		public var emailRequisitante:String;
		public var medicoDesignadoCrm:int;
		public var parecerJustificativa:String;
		public var estado:int;
		public var caminhoArquivos:String;
		public var dataSolicitacao:String;
		public var dataConclusao:String;
		public var pesoPaciente:Number;
		public var alturaPaciente:Number;
		public var cid:int;
		public var observacoes:String;
		public var horasAberto:int;
		public var passouLimiteAberto:Boolean;
		public var registroExame:RegistroExame;

		// Propriedade criada para exibir na grid, pois não foi identificado
		// como acessar a propriedade de um objeto para tal.
		public var nomePaciente:String;
		public var nomeMunicipio:String;
		public var situacao:String;

		public static var ABERTO:int=0;
		public static var LAUDANDO:int=1;
		public static var LAUDADO:int=2;
		public static var NAO_LAUDADO:int=3;
		public static var ABERTO_LIMITE:int=4;

		/**
		 * Função que retorna o IMC (Índice de Massa Corporal).
		 *
		 * @return float imc
		 */
		public function calcularImc():Number
		{
			var imc:Number;
			imc=(this.pesoPaciente) / (this.alturaPaciente * this.alturaPaciente);
			return imc;
		}

		/**
		 * Função que retorna a situação de acordo com o IMC.
		 *
		 * @param imc
		 * @return string situacao
		 */
		public function determinarSituacaoImc(imc:Number):String
		{
			var situacao:String=null;
			// Abaixo de 17
			if (imc < 17)
				situacao="Muito abaixo do peso";
			// Entre 17 e 18,49
			else if (imc >= 17 && imc <= 18.49)
				situacao="Abaixo do peso";
			// Entre 18,5 e 24,99
			else if (imc >= 18.5 && imc <= 24.99)
				situacao="Peso normal";
			// Entre 25 e 29,99
			else if (imc >= 25 && imc <= 29.99)
				situacao="Acima do peso";
			// Entre 30 e 34,99
			else if (imc >= 30 && imc <= 34.99)
				situacao="Obesidade I";
			// Entre 35 e 39,99
			else if (imc >= 35 && imc <= 39.99)
				situacao="Obesidade II (severa)";
			// Acima de 40
			else if (imc > 40)
				situacao="Obesidade III (mórbida)";

			return situacao;
		}
	}

}