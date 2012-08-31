package lib.funcoes
{
	import flash.events.MouseEvent;
	import flash.net.FileReference;
	import flash.utils.ByteArray;

	import lib.classes.Exame;
	import lib.classes.ProfissionalOrgao;
	import lib.configuracao.NomesCampos;

	import org.alivepdf.display.Display;
	import org.alivepdf.fonts.CoreFont;
	import org.alivepdf.fonts.FontFamily;
	import org.alivepdf.fonts.IFont;
	import org.alivepdf.images.*;
	import org.alivepdf.layout.Orientation;
	import org.alivepdf.layout.Size;
	import org.alivepdf.layout.Unit;
	import org.alivepdf.pdf.PDF;
	import org.alivepdf.saving.Method;

	public class gerarPDF
	{

		public static function geraPDF(pEvt:MouseEvent, exame:Exame, codValidador:String):void
		{

			var canalRitmo:int=exame.registroExame.canalRitmo;
			var ritmo:String;

			switch (canalRitmo)
			{
				case 1:
					ritmo=NomesCampos.ritmoSinusal;
					break;
				case 2:
					ritmo=NomesCampos.ritmoTarquicardia;
					break;
				case 3:
					ritmo=NomesCampos.ritmoMarcapasso;
					break;
				case 4:
					ritmo=NomesCampos.ritmoESV;
					break;
				case 5:
					ritmo=NomesCampos.ritmoFA;
					break;
				case 6:
					ritmo=NomesCampos.ritmoBradicardia;
					break;
				default:
					ritmo="Não informado.";
					break;
			}

			var pdf:PDF;
			var minhaFont:IFont=new CoreFont(FontFamily.ARIAL);
			[Embed(source="../images/logo_TeleCardio.jpg", mimeType="application/octet-stream")]
			var logo:Class;
			//Textos comumente utilizados.
			var titulo:String="Eletrocardiograma";
			var linha:String="_________________________________________________________________________________";
			var msgObservacao:String="Este laudo foi gerado através de um sistema de Telessaúde para laudo a distância.";
			var versao:String="Powered by TeleCardio - V.1.0";
			var agility:String="Agility Sistemas";

			pdf=new PDF(Orientation.PORTRAIT, Unit.MM, Size.A4);
			pdf.setDisplayMode(Display.FULL_WIDTH);
			pdf.addPage();
			pdf.setFont(minhaFont, 12);
			pdf.addImageStream(new logo() as ByteArray, ColorSpace.DEVICE_RGB, null, 0, 0, 28, 8, 0);
			pdf.setFontSize(18);
			pdf.addText(titulo, 73, 30);
			pdf.setFontSize(12);
			pdf.addText(linha, 10, 35);
			//Nome do Paciente -  OK.
			pdf.addText("PACIENTE: " + exame.nomePaciente, 10, 52);
			//Idade do Paciente - OK.
			pdf.addText("IDADE: " + Funcoes.calcularIdade(exame.paciente.dataNascimento), 10, 59);
			//Data de conclusão do Laudo - OK.
			pdf.addText("DATA: " + exame.dataConclusao.toString(), 10, 66);
			//Ritmo: 1 - Sinusal, 2 - Tarquicardia, 3 - Marcapasso, 4 - ESV, 5 - FA, 6 - Bradicardia.
			pdf.addText("RITMO: " + ritmo, 10, 82);
			//Taxa de amostragem do exame.
			pdf.addText("TAXA DE AMOSTRAGEM: " + exame.registroExame.taxaAmostragem, 10, 89);
			//Frequencia cardiaca do Paciente.
			pdf.addText("FC: " + exame.registroExame.frequenciaCardiaca + " bpm", 150, 82);
			//Apenas um rótulo.
			pdf.addText("LAUDO:", 10, 110);
			//Informações do laudo
			pdf.setXY(10, 120);
			//pdf.addMultiCell(190, 20, exame.parecerJustificativa, 1, Align.TOP );
			pdf.writeText(5, exame.parecerJustificativa, null);
			//Nome do Cardiologista que está laudando.
			pdf.addText("Dr(a)." + exame.profissional.usuario.nome, 10, 225);
			//Especialidade - FIXO.
			pdf.addText("Cardiologia", 10, 230);
			//Número do CRM e UF do órgão de classe.
			pdf.addText("CRM: " + (exame.profissional.profissionaisOrgaos[0] as ProfissionalOrgao).habilitacao, 10, 235);
			pdf.setFontSize(10);
			pdf.addText("Chave de segurança: " + codValidador, 50, 262);
			pdf.setFontSize(8.5);
			pdf.addText("Para validar esse laudo, acesse www.telecardio.com.br e utilize a opção Validar Laudo.", 49, 267);
			pdf.setFontSize(8.5);
			pdf.addText(msgObservacao, 52, 272);
			pdf.setFontSize(12);
			pdf.addText(linha, 10, 275);
			pdf.setFontSize(8.5);
			pdf.addText(versao, 87, 285);
			pdf.addText(agility, 96, 290);
			//Página 2 do PDF.
			/*pdf.addPage();
			   pdf.setFont(minhaFont, 12);
			   pdf.setFontSize(18);
			   pdf.addText(titulo, 45, 20);
			   pdf.setFontSize(12);
			   pdf.addText(linha, 10, 28);
			 */

			var bytes:ByteArray=pdf.save(Method.LOCAL);
			var f:FileReference=new FileReference();
			f.save(bytes, gerarNomePdf(exame));

			//pdf.save(Method.REMOTE, "http://alivepdf.bytearray.org/wp-content/demos/create.php", "pdf.pdf");	
		}

		public static function gerarNomePdf(exam:Exame):String
		{
			var nomePdf:String;
			var data:String=exam.dataConclusao.substr(0, 2) + exam.dataConclusao.substr(3, 2) + exam.dataConclusao.substr(6, 4);
			var nomePaciente:String=exam.nomePaciente;

			for (var i:int=0; i < exam.nomePaciente.length; i++)
				nomePaciente=nomePaciente.replace(" ", "+");

			nomePdf=exam.chave.toString() + "+";
			nomePdf+=nomePaciente + "+";
			nomePdf+=data;
			nomePdf+=".pdf";

			return nomePdf;
		}
	}
}