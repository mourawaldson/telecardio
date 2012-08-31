package nutes.telecardio.modelo.configuracao;

/**
 * Entidade que irá definir os nomes de todos os campos do bando de dados do
 * sistema para poder ser utilizado nas consultas
 * 
 * @author hvb
 * 
 */
public class CamposBanco {
	public static final String id = "id";
	public static final String status = "status";
	private static final char separador = ".".charAt(0);
	public static final char statusAtivo = "A".charAt(0);
	public static final char statusInativo = "I".charAt(0);

	/**
	 * Função do SQL Server que retorna o id do último registro incluído,
	 * Precisa ser associado a uma tabela.
	 */
	public static final String selectIdentity = "SELECT DISTINCT @@IDENTITY as "
			+ id + " FROM ";

	// Exame
	public static final String exameTabela = "exames";
	public static final String exameTabelaComSeparador = exameTabela
			+ separador;
	public static final String exameIdPaciente = "id_paciente";
	public static final String exameIdProfissional = "id_profissional";
	public static final String exameEmailRequisitante = "email_requisitante";
	public static final String exameMedicoDesignadoCrm = "medico_designado_crm";
	public static final String exameParecerJustificativa = "parecer_justificativa";
	public static final String exameEstado = "estado";
	public static final String exameCaminhoArquivos = "caminho_arquivos";
	public static final String exameDataSolicitacao = "data_solicitacao";
	public static final String exameDataConclusao = "data_conclusao";
	public static final String examePesoPaciente = "peso_paciente";
	public static final String exameAlturaPaciente = "altura_paciente";
	public static final String exameCid = "cid";
	public static final String exameObservacoes = "observacoes";
	public static final String exameHorasAlias = "horas";

	// Paciente
	public static final String pacienteTabela = "pacientes";
	public static final String pacienteTabelaComSeparador = pacienteTabela
			+ separador;
	public static final String pacienteIdPessoa = "id_pessoa";
	public static final String pacienteIdConvenio = "id_convenio";
	public static final String pacienteIdTipoSanguineo = "id_tipo_sanguineo";
	public static final String pacienteDataUltimoContato = "data_ultimo_contato";
	public static final String pacienteNomeMae = "nome_mae";

	// Tipo Sanguíneo
	public static final String tipoSanguineoAlias = "tipo_sanguineo";
	public static final String tipoSanguineoTabela = "tipo_sanguineo";
	public static final String tipoSanguineoTabelaComSeparador = "tipo_sanguineo"
			+ separador;
	public static final String tipoSanguineoNome = "nome";

	// Pessoa
	public static final String pessoaTabela = "pessoas";
	public static final String pessoaTabelaComSeparador = pessoaTabela
			+ separador;
	public static final String pessoaIdEndereco = "id_endereco";
	public static final String pessoaNome = "nome";
	public static final String pessoaCpf = "cpf";
	public static final String pessoaDataNascimento = "data_nascimento";
	public static final String pessoaSexo = "sexo";

	// Configuração
	public static final String configTabela = "configuracoes";
	public static final String configTabelaComSeparador = configTabela
			+ separador;
	public static final String configNome = "nome";
	public static final String configPastaExames = "pasta_exames";
	public static final String configLimiteHabilitacoesMedico = "limite_habilitacoes_medico";
	public static final String configLimiteHabilitacoesEnfermeiro = "limite_habilitacoes_enfermeiro";
	public static final String configLimiteExameLaudando = "limite_exame_laudando";
	public static final String configLimiteExameAberto = "limite_exame_aberto";
	public static final String configEmailChecagemEmail = "email_checagem_email";
	public static final String configSenhaChecagemEmail = "senha_checagem_email";
	public static final String configPortaChecagemEmail = "porta_checagem_email";
	public static final String configHostChecagemEmail = "host_checagem_email";
	public static final String configIntervaloChecagemEmail = "intervalo_checagem_email";

	// Usuário
	public static final String usuarioTabela = "usuarios";
	public static final String usuarioTabelaComSeparador = usuarioTabela
			+ separador;
	public static final String usuarioLogin = "login";
	public static final String usuarioIdPerfil = "id_perfil";
	public static final String usuarioSenha = "senha";
	public static final String usuarioEmail = "email";
	public static final String usuarioIdPessoa = "id_pessoa";

	// Endereço
	public static final String enderecoTabela = "enderecos";
	public static final String enderecoTabelaComSeparador = enderecoTabela
			+ separador;
	public static final String enderecoIdEstado = "id_estado";
	public static final String enderecoLogradouro = "logradouro";
	public static final String enderecoNumero = "numero";
	public static final String enderecoComplemento = "complemento";
	public static final String enderecoBairro = "bairro";
	public static final String enderecoMunicipio = "municipio";
	public static final String enderecoCep = "cep";

	// Estados(Uf)
	public static final String ufTabela = "estados";
	public static final String ufTabelaComSeparador = ufTabela + separador;
	public static final String ufNome = "nome";
	public static final String ufSigla = "uf";

	// Orgãos Classes Estados
	public static final String orgaosClassesEstadosTabela = "orgaos_classes_estados";
	public static final String orgaosClassesEstadosTabelaComSeparador = orgaosClassesEstadosTabela
			+ separador;
	public static final String orgaosClassesEstadosIdEstado = "id_estado";
	public static final String orgaosClassesEstadosIdOrgaoClasseIdEstado = "id_orgao_classe";

	// Orgãos Classe
	public static final String orgaoClasseTabela = "orgaos_classes";
	public static final String orgaoClasseTabelaComSeparador = orgaoClasseTabela
			+ separador;
	public static final String orgaoClasseSigla = "sigla";
	public static final String orgaoClasseNome = "nome";

	// Profissionao Tipo
	public static final String profissionalTipoTabela = "profissionais_tipos";
	public static final String profissionalTipoTabelaComSeparador = profissionalTipoTabela
			+ separador;
	public static final String profissionalTipoIdOrgaoClasse = "id_orgao_classe";
	public static final String profissionalTipoNome = "nome";

	// Profissionais
	public static final String profissionalTabela = "profissionais";
	public static final String profissionalTabelaComSeparador = profissionalTabela
			+ separador;
	public static final String profissionalIdUsuario = "id_usuario";
	public static final String profissionalIdProfissionaoTipo = "id_profissional_tipo";

	// Profissional Orgão
	public static final String profissionalOrgaoTabela = "profissionais_orgaos";
	public static final String profissionalOrgaoTabelaComSeparador = profissionalOrgaoTabela
			+ separador;
	public static final String profissionalOrgaoIdEstado = "id_estado";
	public static final String profissionalOrgaoIdProfissional = "id_profissional";
	public static final String profissionalOrgaoIdOrgaoClasse = "id_orgao_classe";
	public static final String profissionalOrgaoHabilitacao = "habilitacao";

	// Funcionalidade
	public static final String funcionalidadeTabela = "funcionalidades";
	public static final String funcionalidadeTabelaComSeparador = funcionalidadeTabela
			+ separador;
	public static final String funcionalidadeNome = "nome";
	public static final String funcionalidadeDescricao = "descricao";

	// Funcionalidade Perfil
	public static final String funcionalidadePerfilTabela = "funcionalidades_perfis";
	public static final String funcionalidadePerfilTabelaComSeparador = funcionalidadePerfilTabela
			+ separador;
	public static final String funcionalidadePerfilIdFuncionalidade = "id_funcionalidade";
	public static final String funcionalidadePerfilIdPerfil = "id_perfil";

	// Funcionalidade Usuário
	public static final String funcionalidadeUsuarioTabela = "funcionalidades_usuarios";
	public static final String funcionalidadeUsuarioTabelaComSeparador = funcionalidadeUsuarioTabela
			+ separador;
	public static final String funcionalidadeUsuarioIdFuncionalidade = "id_funcionalidade";
	public static final String funcionalidadeUsuarioIdUsuario = "id_usuario";

	// Perfil
	public static final String perfilTabela = "perfis";
	public static final String perfilTabelaComSeparador = perfilTabela
			+ separador;
	public static final String perfilNome = "nome";

}
