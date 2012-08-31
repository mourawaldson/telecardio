package lib.configuracao
{

	public class CamposBanco
	{
		//status independente da tabela
		public static const status:String="status";
		public static const statusAtivo:String="A";
		public static const statusInativo:String="I";
		public static const statusConfigAtivaFinal:String="Ativa";
		public static const statusConfigInativaFinal:String="Inativa";

		//Campos da tabela pessoa
		public static const pessoaNome:String="nome";
		public static const pessoaCpf:String="cpf";
		public static const pessoaDataNascimento:String="data_nascimento";

		//Campos da tabela usuario
		public static const usuarioTipoProfissional:String="";

		// Paciente
		public static const pacienteNomeMae:String="nome_mae";

		// Tipo Sanguíneo
		public static const tipoSanguineoSts:String="STS";

		// Exame
		public static const exameParecer:String="Parecer";
		public static const exameJustificativa:String="Justificativa";
		public static const exameMedico:String="Selecione o médico";
	}
}