package lib.classes
{

	/**
	 * Classe que representa a entidade endereço
	 * @autor : hvb
	 * */
	[RemoteClass(alias="nutes.telecardio.modelo.estruturaorganizacional.Endereco")]
	[Bindable]
	public class Endereco extends EntidadeBasica
	{
		public var uf:Uf;
		public var logradouro:String;
		public var bairro:String;
		public var numero:String;
		public var complemento:String;
		public var cep:String;
		public var municipio:String;

		public function formatarCep():void
		{
			for (var i:int=0; i < cep.length; i++)
			{
				cep=cep.replace("-", "");
				cep=cep.replace(".", "");
			}
		}
	}
}