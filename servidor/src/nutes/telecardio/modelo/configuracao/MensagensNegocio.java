package nutes.telecardio.modelo.configuracao;

/**
 * Entidade que ira definir todas as mensagens de negocio dentro do sistema
 * 
 * @author hvb
 */
public class MensagensNegocio {

	// Mensgens padrões
	public static final String campoObrigatorioString = "O campo %s é obrigatório!";
	public static final String invalido = " inválido!";
	public static final String invalida = "inválida!";
	public static final String atualizado = "atualizado!";
	public static final String atualizada = "atualizada!";
	public static final String reativado = "reativado!";
	public static final String excluidoDesativado = "excluído (desativado)!";
	public static final String excluido = "excluído!";
	public static final String ativada = "ativada!";
	public static final String incluido = "incluído!";
	public static final String incluida = "incluída!";
	public static final String erro = "Erro";
	public static final String incluir = "incluir";
	public static final String erroIncluir = erro + " ao incluir ";
	public static final String erroAtualizar = erro + " ao atualizar ";
	public static final String erroExcluir = erro + " ao excluir ";
	public static final String erroAtivar = erro + " ao ativar ";

	public static final String erroChecagemEmail = erro
			+ " na execução da Checagem do E-mail!";
	public static final String erroCortarImagens = erro
			+ " ao cortar a imagem do exame: ";

	// Endereço
	public static final String endereco = "Endereço";
	public static final String enderecoSemEstado = endereco + " sem estado";
	public static final String enderecoErroAtualizar = erroAtualizar + endereco;
	public static final String enderecoErroIncluir = erroIncluir + endereco;

	// Usuário
	public static final String usuario = "Usuário";
	public static final String usuarioInvalido = usuario + invalido;
	public static final String cpfInvalido = "CPF " + invalido;
	public static final String cpfCadastrado = "CPF já cadastrado!";
	public static final String habilitacaoJaCadastrada = "Habilitação já cadastrada para esse estado!";

	// Mensagens padrões
	public final static String campoObrigatorio = "O campo %s é obrigatório!";

	// Transação
	public final static String transacaoSemSucesso = "Não foi possivel efetuar a transação com o banco de dados!";

	// Exame
	public static final String exame = "Exame";
	public static final String exameErroAtualizar = erroAtualizar + exame;
	public static final String exameErroExcluir = erroExcluir + exame;
	public static final String exameErroIncluir = erroIncluir + exame;
	public static final String exameLaudando = "O " + exame
			+ " já está sendo laudado!";
	public static final String exameLaudado = "O " + exame + " já foi laudado!";
	public static final String exameNaoLaudado = "O " + exame
			+ " já foi dado como inconclusivo!";

	// Configuração
	public static final String configuracao = "Configuração";
	public static final String configuracaoErroAtualizar = erroAtualizar
			+ configuracao;
	public static final String configuracaoErroAtivar = erroAtivar
			+ configuracao;
	public static final String configuracaoErroIncluir = erroIncluir
			+ configuracao;
	public static final String configAtivaInexistente = "É necessário que exista uma "
			+ configuracao + " ativa, favor reiniciar o servidor!";

	// Paciente
	public static final String paciente = "Paciente";
	public static final String pacienteErroAtualizar = erroAtualizar + paciente;
	public static final String pacienteErroExcluir = erroExcluir + paciente;
	public static final String pacienteErroIncluir = erroIncluir + paciente;
	public static final String pacienteExistente = paciente + " já existe!";

	// IMC
	public static final String imcMuitoAbaixo = "Muito abaixo do peso";
	public static final String imcAbaixo = "Abaixo do peso";
	public static final String imcNormal = "Peso normal";
	public static final String imcAcima = "Acima do peso";
	public static final String imcObesidadeI = "Obesidade I";
	public static final String imcObesidadeII = "Obesidade II (severa)";
	public static final String imcObesidadeIII = "Obesidade III (mórbida)";

	// Tipo sanguíneo
	public static final String tipoSanguineoNaoInformado = "Tipo sanguíneo não informado!";
}