package lib.configuracao
{

	public class MensagensNegocio
	{
		//Login
		public static const loginSenhaInvalido:String="Login ou senha inválidos!";

		//Padroes
		public static const campoObrigatorio:String="Os seguintes campos são obrigatórios :";
		public static const erroConexaoServidor:String="Servidor indisponível no momento!";
		public static const bemVindo:String="Seja bem vindo";
		public static const erroSessaoFechada:String="Houve algum erro com a sessão, realize o login novamente";

		//Tipos de mensagens
		public static const msgTipoConfirmacao:String="Confirmação";
		public static const msgTipoAlerta:String="Alerta";
		public static const msgTipoErro:String="Erro";
		public static const msgTipoBemVindo:String="Bem vindo";

		//Mensagens de Negocio para usuário
		public static const usuarioCadastradoSucesso:String="Usuário cadastrado com sucesso!";
		public static const usuarioCadastradoInsucesso:String="Usuário não foi cadastrado!";
		public static const usuarioExcluidoSucesso:String="Usuário excluido com sucesso!";
		public static const usuarioExcluidoInsucesso:String="Usuário não foi excluido!";
		public static const usuarioAlteradoSucesso:String="Usuário alterado com sucesso!";
		public static const usuarioAlteradoInsucesso:String="Usuário não foi alterado!";
		public static const senhaNaoConfere:String="A senha não confere!";
		public static const UsuarioConfirmarSalvar:String="Deseja salvar esse usuário?";
		public static const UsuarioConfirmarAlteracao:String="Deseja salvar as alterações desse usuário?";
		public static const UsuarioConfirmarExcluisao:String="Deseja excluir esse usuário?";
		public static const usuarioExcluindoSe:String="Você não pode se excluir!";

		// Paciente
		public static const paciente:String="Paciente";
		public static const pacienteIncluido:String=paciente + " incluído!";
		public static const pacienteExcluidoSucesso:String=paciente + " excluído!";
		public static const pacienteExcluidoInsucesso:String=msgTipoErro + " ao excluir " + paciente + "!";
		public static const pacienteAtualizado:String=paciente + " atualizado!";
		public static const pacienteConfirmarIncluir:String="Deseja incluir esse " + paciente + "?";
		public static const pacienteConfirmarAtualizar:String="Deseja atualizar esse " + paciente + "?";
		public static const pacienteConfirmarExcluir:String="Deseja excluir esse " + paciente + "?";
		public static const pacienteErroConsultar:String=msgTipoErro + " ao consultar o " + paciente;

		// Exame
		public static const exame:String="Exame";
		public static const exameConfirmarExclusao:String="Deseja realmente excluir essa solicitação?";
		public static const exameConfirmarLaudar:String="Deseja realmente laudar essa solicitação?";
		public static const exameConfirmarCancelar:String="Deseja realmente não mais laudar essa solicitação?";
		public static const exameLaudarSucesso:String=exame + " laudado com sucesso!";
		public static const exameCancelarSucesso:String="Solicitação cancelada!";
		public static const exameCancelarInsucesso:String=msgTipoErro + " ao cancelar a solicitação!";
		public static const exameExcluidoSucesso:String=exame + " excluído!";
		public static const exameExcluidoInsucesso:String=msgTipoErro + " ao excluir " + exame + "!";
		public static const exameConfirmarEncaminhamento:String="Deseja encaminhar para outro médico?";
		public static const exameMedicosIndisponiveis:String="Não existem médicos disponíveis para encaminhamento!";
		public static const exameApenasMedicosLaudam:String="Apenas médicos podem laudar!";

		// Configuração
		public static const config:String="Configuração";
		public static const configAtivada:String=config + " ativada!";
		public static const configIncluida:String=config + " incluída!";
		public static const configAtualizada:String=config + " atualizada!";
		public static const configConfirmarSalvar:String="Deseja salvar esse " + config + "?";

		// Tipo Sanguíneo
		public static const tipoSanguineoSts:String="Não definido";
	}
}