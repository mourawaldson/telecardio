package nutes.telecardio.controle.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.controle.ControladorFuncionalidade;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.estruturaorganizacional.OrgaoClasse;
import nutes.telecardio.modelo.estruturaorganizacional.Perfil;
import nutes.telecardio.modelo.estruturaorganizacional.ProfissionalTipo;
import nutes.telecardio.modelo.estruturaorganizacional.ServicoUsuario;
import nutes.telecardio.modelo.estruturaorganizacional.Uf;
import nutes.telecardio.modelo.estruturaorganizacional.Usuario;

/**
 * Classe que representa o controlador de usuário
 * 
 * @author hvb
 * 
 */
public class ControladorUsuario extends
		ControladorFuncionalidade<ServicoUsuario> {

	public ControladorUsuario() {
		servico = new ServicoUsuario();
	}

	/**
	 * Faz a inclusão de um usuário retorna um Validador com alguma mensagem de
	 * validação da operção e da entidade
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Validador validador
	 */
	public Validador InserirUsuarioProfissional(Usuario usuario) {
		return servico.inserirUsuarioProfissional(usuario);

	}

	/**
	 * Faz a alteração de um usuário retorna um Validador com alguma mensagem de
	 * validação da operação e da entidade caso exista
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Validador validador
	 */
	public Validador alterarUsuarioProfissional(Usuario usuario) {
		return servico.alterarUsuarioProfissional(usuario);
	}

	/**
	 * Retorna uma lista de perfil do bando de dados
	 * 
	 * @return List<Perfil> perfis;
	 */
	public List<Perfil> consultarListaPerfil() {
		return servico.consultarListaPerfil();
	}

	/**
	 * Exclui um usuario no banco chamando uma solicitação do serviço e retorna
	 * verdadeiro se tiver sucesso e falso no caso contrario
	 * 
	 * @param Usuario
	 *            usuario
	 * @return boolean
	 */
	public boolean excluirUsuario(Usuario usuario) {
		return servico.excluirUsuario(usuario);
	}

	/**
	 * Consulta um Usuario pelo login e senha
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Usuario usuario
	 */
	public Usuario consultarUsuarioPorLoginSenha(Usuario usuario) {
		return servico.consultarUsuarioPorLoginSenha(usuario);
	}

	/**
	 * Faz uma consulta por campo de pesquisa e o valor e retorna uma lista de
	 * usuarios
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @return List<Usuario> usuario
	 */
	public List<Usuario> consultarUsuarios(String campo, String valor) {

		return servico.consultarUsuarios(campo, valor);

	}

	/**
	 * Retorna uma lista de orgão de classes que existe cadastrado no sistema
	 * 
	 * @return List<OrgaoClasse> nomesOrgaoClasses
	 */
	public List<OrgaoClasse> consultarListaOrgaoClasse() {
		return servico.consultarListaOrgaoClasse();
	}

	/**
	 * Retorna uma lista das siglas dos estados que existe cadastrado no sistema
	 * pelo orgao de classe
	 * 
	 * @return List<Uf> nomesSiglasUf
	 */
	public List<Uf> consultarListaUfPorOrgaoClasse(OrgaoClasse orgaoClasse) {
		return servico.consultarListaUfPorOrgaoClasse(orgaoClasse);
	}

	/**
	 * Retorna uma lista das siglas dos estados que existe cadastrado no sistema
	 * 
	 * @return List<Uf> nomesSiglasUf
	 */
	public List<Uf> consultarListaUf() {
		return servico.consultarListaUf();
	}

	/**
	 * Retorna uma lista de tipos de profissionais cadastrados no sistema
	 * 
	 * @return List<String> nomesProfissionaisTipo
	 * @param OrgaoClasse
	 *            orgaoClasse
	 */
	public List<ProfissionalTipo> consultarListaProfissionaisTipoPorOrgao(
			OrgaoClasse orgaoClasse) {
		return servico.consultarListaProfissionaisTipoPorOrgao(orgaoClasse);
	}

	/**
	 * Consulta um usuario pela chave e retorna o usuario consultado
	 * 
	 * @param int chaveUsuario
	 * @return Usuario usuario
	 */
	public Usuario consultarUsuarioPorChave(int chaveUsuario) {
		return servico.consultarUsuarioPorChave(chaveUsuario);
	}

}
