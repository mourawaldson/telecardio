package validators
{
	import mx.validators.ValidationResult;
	import mx.validators.Validator;

	public class validacaoCpf extends Validator
	{
		private var resultados:Array;

		public function validacaoCpf():void
		{
			this.requiredFieldError="Digite o CPF!";
		}

		override protected function doValidation(value:Object):Array
		{
			resultados=super.doValidation(value);
			if (resultados.length > 0)
			{
				return resultados;
			}

			if (!CPFValido(value.toString()))
			{
				resultados.push(new ValidationResult(true, null, "1", "O CPF informado não é válido!"));
				return resultados;
			}
			else
			{
				return resultados;
			}
		}

		private function CPFValido(cpf:String):Boolean
		{
			return true;
		}
	}
}