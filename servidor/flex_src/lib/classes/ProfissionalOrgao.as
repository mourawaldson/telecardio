package lib.classes
{

	/**
	 * Classe que representa a entidade profissionais_orgaos
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.ProfissionalOrgao")]
	[Bindable]
	public class ProfissionalOrgao extends EntidadeBasica
	{
		public var profissional:Profissional;
		public var habilitacao:int;
		public var orgaoClasse:OrgaoClasse;
		public var uf:Uf;
	}
}