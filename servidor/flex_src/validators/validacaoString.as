package validators
{
	import mx.validators.StringValidator;

	public class validacaoString extends StringValidator
	{
		public function validacaoString():void
		{
			var quantidadeMinima:int=int(this.minLength);
			this.required=true;
			this.requiredFieldError="Este campo é requerido!";
			this.tooShortError="Este campo deve conter no mínimo " + quantidadeMinima + " caracteres!";
		}
	}
}