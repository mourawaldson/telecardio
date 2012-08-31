package lib.classes
{

	/**
	 * Classe que será herdada por paciente e usuário que representa a entidade pessoas
	 * @autor: hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.Pessoa")]
	[Bindable]
	public class Pessoa extends EntidadeBasica
	{
		public var endereco:Endereco;
		public var nome:String;
		public var cpf:String;
		public var dataNascimento:String;
		public var sexo:String;
		public var chavePessoa:int;

		public function formatarCpf():void
		{

			for (var i:int=0; i < cpf.length; i++)
			{
				cpf=cpf.replace("-", "");
				cpf=cpf.replace(".", "");
			}
		}
	}
}