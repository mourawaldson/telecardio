package lib.funcoes
{
	import flash.display.DisplayObject;

	import lib.classes.EntidadeBasica;
	import lib.configuracao.MensagensNegocio;

	import md_configuracao.*;

	import md_lembrete.*;

	import md_paciente.*;

	import md_sobre.*;

	import md_solicitacao.*;

	import md_usuario.*;

	import mx.controls.Alert;
	import mx.controls.ComboBox;
	import mx.controls.DataGrid;
	import mx.controls.TextInput;
	import mx.core.IFlexDisplayObject;
	import mx.core.UIComponent;
	import mx.managers.PopUpManager;
	import mx.rpc.events.FaultEvent;
	import mx.utils.StringUtil;

	public class Funcoes
	{
		public static function telaLembrete(parent:DisplayObject):void
		{
			var popUpLembrete:lembrete=new lembrete();
			instanciarTela(popUpLembrete, parent);
		}

		public static function telaLogin(parent:DisplayObject):void
		{
			var popUpLogin:login=new login();
			popUpLogin.telaPai=index(parent);
			instanciarTela(popUpLogin, parent);
		}

		public static function telaEditarUsuario(parent:DisplayObject):void
		{
			var popUpEditarUsuario:editarUsuario=new editarUsuario();
			popUpEditarUsuario.telaPai=usuario(parent);
			instanciarTela(popUpEditarUsuario, parent);
		}

		public static function telaNovoUsuario(parent:DisplayObject):void
		{
			var popUpNovoUsuario:novoUsuario=new novoUsuario();
			popUpNovoUsuario.telaPai=usuario(parent);
			instanciarTela(popUpNovoUsuario, parent);
		}

		public static function telaConfiguracoes(parent:DisplayObject):void
		{
			var popUpConfiguracao:configuracao=new configuracao();
			instanciarTela(popUpConfiguracao, parent);
		}

		public static function telaNovaConfiguracao(parent:DisplayObject):void
		{
			var popUpNovaConfiguracao:novaConfiguracao=new novaConfiguracao();
			popUpNovaConfiguracao.telaPai=configuracao(parent);
			Funcoes.instanciarTela(popUpNovaConfiguracao, parent);
		}

		public static function telaPacientes(parent:DisplayObject):void
		{
			var popUpPaciente:paciente=new paciente();
			instanciarTela(popUpPaciente, parent);
		}

		public static function telaNovoPaciente(parent:DisplayObject):void
		{
			var popUpNovoPaciente:novoPaciente=new novoPaciente();
			popUpNovoPaciente.telaPai=paciente(parent);
			Funcoes.instanciarTela(popUpNovoPaciente, parent);
		}

		public static function telaSobre(parent:DisplayObject):void
		{
			var popUpSobre:sobre=new sobre();
			instanciarTela(popUpSobre, parent);
		}

		public static function telaSolicitacoesConcluidas(parent:DisplayObject):void
		{
			var popUpSolicitacaoConcluida:solicitacaoConcluida=new solicitacaoConcluida();
			instanciarTela(popUpSolicitacaoConcluida, parent);
		}

		public static function telaSolicitacoesAbertas(parent:DisplayObject):void
		{
			var popUpSolicitacaoAberta:solicitacaoAberta=new solicitacaoAberta();
			instanciarTela(popUpSolicitacaoAberta, parent);
		}

		public static function telaUsuario(parent:DisplayObject):void
		{
			var popUpUsuario:usuario=new usuario();
			instanciarTela(popUpUsuario, parent);
		}

		//window (obrigatório) - instância da nova janela
		//parent (obrigatório) - janela pai (normalmente será "this", pois é a janela atual).
		//modal  (opcional)    - boleano que identifica se a nova janela é modal ou não, por padrão é true.
		public static function instanciarTela(window:IFlexDisplayObject, parent:DisplayObject, modal:Boolean=true):void
		{
			PopUpManager.addPopUp(window, parent, modal);
			centralizarTela(window as UIComponent);
		}

		// Funcionalidade para centralizar o form na tela. Dessa forma, o form não mais será alinhado
		// pelo topo do form PAI, e sim no centro da tela.
		public static function centralizarTela(componente:UIComponent):void
		{
			if (componente != null)
			{
				var diferencaLargura:Number=componente.screen.width - componente.width;
				var diferencaAltura:Number=componente.screen.height - componente.height;
				componente.x=componente.screen.x + (diferencaLargura / 2);
				componente.y=componente.screen.y + (diferencaAltura / 2);
			}
		}

		public static function fecharPopUp(popUp:IFlexDisplayObject):void
		{
			PopUpManager.removePopUp(popUp);
		}

		// Alerta qualquer falha.
		public static function onFault(event:FaultEvent):void
		{
			Alert.show(MensagensNegocio.erroConexaoServidor + " : " + event.fault.rootCause.toString());
		}

		// Retorna o objeto do item selecionado no dataGrid.
		public static function retornarObjetoSelecionado(dataGrid:DataGrid):Object
		{
			if (dataGrid.selectedIndex != -1)
				var objeto:Object=dataGrid.selectedItem;
			return objeto;
		}

		/**
		 * Formata o CPF informado para o padrão ###.###.###-## após a digitação de 11 números.
		 * @return TextInput
		 */
		public static function formatarCpf(cpfNumero:TextInput):TextInput
		{
			var cpf:String=new String();
			cpf=cpfNumero.text;

			if (cpfNumero.length == 11)
				cpfNumero.text=cpf.charAt(0) + cpf.charAt(1) + cpf.charAt(2) + "." + cpf.charAt(3) + cpf.charAt(4) + cpf.charAt(5) + "." + cpf.charAt(6) + cpf.charAt(7) + cpf.charAt(8) + "-" + cpf.charAt(9) + cpf.charAt(10);

			return cpfNumero;
		}

		/**
		 * Formata o CEP informado para o padrão ##.###-### após a digitação de 8 números.
		 * @return TextInput
		 */
		public static function formatarCep(cepNumero:TextInput):TextInput
		{
			var cep:String=new String();
			cep=cepNumero.text;

			if (cepNumero.length == 8)
				cepNumero.text=cep.charAt(0) + cep.charAt(1) + "." + cep.charAt(2) + cep.charAt(3) + cep.charAt(4) + "-" + cep.charAt(5) + cep.charAt(6) + cep.charAt(7);

			return cepNumero;
		}

		public static function verificarIndexElemento(combobox:ComboBox, entidade:EntidadeBasica):int
		{
			var index:int=0;
			for (var i:int=0; i < combobox.dataProvider.length; i++)
			{
				combobox.selectedIndex=i;
				var entidadeCombo:EntidadeBasica=combobox.selectedItem as EntidadeBasica;
				if (entidadeCombo.chave == entidade.chave)
				{
					index=i;
					break;
				}
			}

			return index;
		}

		/**
		 * Verifica se a String passada por parâmetro é nula ou vazia.
		 *
		 * @param str
		 *            String
		 * @return boolean true se a String for "null" ou vazia; false caso exista
		 *         conteúdo.
		 */
		public static function isNullOrEmpty(str:String):Boolean
		{
			return ((str === null || str === "") || StringUtil.trim(str).length === 0) ? true : false;
		}

		/**
		 * Compara o texto entre dois campos.
		 *
		 * @return boolean true se o texto dos campos forem iguais; false se o
		 * 		   texto dos campos forem diferentes.
		 */
		public static function comparateTextInputs(textInputA:TextInput, textInputB:TextInput):Boolean
		{
			return (textInputA.text == textInputB.text) ? true : false;
		}

		/**
		 * Limpa todos os campos do array.
		 */
		public static function clearTextInputs(array:Array):void
		{
			var textInput:TextInput;
			for (var i:int=0; i < array.length; i++)
			{
				textInput=array[i] as TextInput;
				textInput.text="";
			}
		}

		/**
		 * Compara o texto entre dois campos de senha e exibe um alerta caso as
		 * senhas não confiram.
		 */
		public static function compararSenhas(textInputA:TextInput, textInputB:TextInput):void
		{
			if (!Funcoes.comparateTextInputs(textInputA, textInputB))
			{
				var inputs:Array=new Array(textInputA, textInputB);
				Funcoes.clearTextInputs(inputs);
				//focusManager.setFocus(textInputA);
				Alert.show(MensagensNegocio.senhaNaoConfere);
			}
		}

		public static function calcularIdade(dataNasc:String):int
		{
			//1982-10-31 formato de data que espero
			//0123456789
			var dateAtual:Date=new Date();

			var ano:int=Number(dataNasc.substr(0, 4));
			var mes:int=Number(dataNasc.substr(5, 2)) - 1;
			var dia:int=Number(dataNasc.substr(8, 2));

			var idade:int=dateAtual.getFullYear() - ano;
			if ((mes == dateAtual.getMonth()) && (dia > dateAtual.getDate()))
				idade-=1;
			else if (mes > dateAtual.getMonth())
				idade-=1;
			return idade;
		}

		public static function convertMinutesToMilliseconds(min:int):int
		{
			min=min * 60000;
			return min;
		}
	}
}