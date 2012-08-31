package validators
{
	import mx.validators.EmailValidator;

	public class validacaoEmail extends EmailValidator
	{
		public function validacaoEmail():void
		{
			this.invalidCharError="Caracteres inválidos no seu e-mail!";
			this.invalidDomainError="O domínio do e-mail está errado!";
			this.invalidIPDomainError="O IP no seu e-mail está errado!";
			this.missingAtSignError="E-mail não possui o caracter @!";
			this.missingUsernameError="O usuário em seu e-mail está inválido!";
			this.tooManyAtSignsError="Existem muitos @ no seu e-mail!";
			this.requiredFieldError="Este campo é obrigatório!";
			this.invalidPeriodsInDomainError="teste";
			this.missingPeriodInDomainError="O domínio do seu endereço de e-mail está incompleto!";
		}
	}
}