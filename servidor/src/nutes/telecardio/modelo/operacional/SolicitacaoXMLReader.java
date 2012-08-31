package nutes.telecardio.modelo.operacional;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import nutes.telecardio.modelo.configuracao.CamposXml;
import nutes.telecardio.modelo.estruturaorganizacional.Exame;
import nutes.telecardio.utils.Datas;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SolicitacaoXMLReader extends DefaultHandler {

	private Exame exame;

	private String tempVal, Data, Hora, DataSolicitacao;

	private boolean flagNomePaciente, flagDataNascimento, flagSexoPaciente,
			flagTipoSanguineo = false;
	private boolean flagDataExame, flagHoraExame, flagAlturaExame,
			flagPesoExame, flagObservacoes = false;
	private boolean flagEmailRequisitanteExame = false;

	public SolicitacaoXMLReader() {
		this.exame = new Exame();
	}

	/**
	 * Habilita a API de leitura para utilização de seus métodos.
	 * 
	 * @param xml
	 *            - Caminho completo
	 */
	private void parseDocument(String xml) {

		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser sp = spf.newSAXParser();

			// parse the file and also register this class for call backs
			sp.parse(xml, this);
		} catch (SAXException se) {
			JOptionPane.showMessageDialog(null, se.getMessage());
		} catch (ParserConfigurationException pce) {
			JOptionPane.showMessageDialog(null, pce.getMessage());
		} catch (IOException ie) {
			JOptionPane.showMessageDialog(null, ie.getMessage());
		}
	}

	/**
	 * A interface startElement fornece acesso a informações sobre elementos de
	 * início. A startElement é relatado para cada tag de início do documento.
	 * 
	 * @param uri
	 *            , localName, qName, attributes
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		this.tempVal = "";
		if (qName.equalsIgnoreCase(CamposXml.paciente)) {
			this.flagNomePaciente = true;
			this.flagDataNascimento = true;
			this.flagSexoPaciente = true;
			this.flagTipoSanguineo = true;
		}

		if (qName.equalsIgnoreCase(CamposXml.exame)) {
			this.flagDataExame = true;
			this.flagHoraExame = true;
			this.flagAlturaExame = true;
			this.flagPesoExame = true;
			this.flagObservacoes = true;
		}

		if (qName.equalsIgnoreCase(CamposXml.clinica))
			this.flagEmailRequisitanteExame = true;
	}

	/**
	 * Pega os valores de uma determinada tag.
	 * 
	 * @param ch
	 *            [] , start, length
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		this.tempVal = new String(ch, start, length);
	}

	/**
	 * Uma interface para o evento elemento final. Um endElement é relatado para
	 * cada fim de Tag no documento.
	 * 
	 * @param uri
	 *            , localName, qName
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (this.flagNomePaciente)
			if (qName.equalsIgnoreCase(CamposXml.nome)) {
				this.exame.getPaciente().setNome(tempVal);
				this.flagNomePaciente = false;
			}

		if (this.flagDataNascimento)
			if (qName.equalsIgnoreCase(CamposXml.dataNascimento)) {
				this.exame.getPaciente().setDataNascimento(tempVal);
				this.flagDataNascimento = false;
			}

		if (this.flagSexoPaciente)
			if (qName.equalsIgnoreCase(CamposXml.sexo)) {
				this.exame.getPaciente().setSexo(tempVal);
				this.flagSexoPaciente = false;
			}

		if (this.flagTipoSanguineo)
			if (qName.equalsIgnoreCase(CamposXml.tipoSanguineo)) {
				this.exame.getPaciente().getTipoSanguineo().setChave(
						(tempVal != "") ? Integer.parseInt(tempVal)
								: TipoSanguineo.sTS);
				this.flagTipoSanguineo = false;
			}

		if (this.flagDataExame)
			if (qName.equalsIgnoreCase(CamposXml.data)) {
				this.Data = this.tempVal;
				this.flagDataExame = false;
			}

		if (this.flagHoraExame)
			if (qName.equalsIgnoreCase(CamposXml.hora)) {
				this.Hora = this.tempVal;
				this.flagHoraExame = false;
			}

		if (this.Data != null && this.Hora != null) {
			String dataHora = this.Data + " " + this.Hora;
			this.DataSolicitacao = Datas.formatarData(Datas.criarData(dataHora,
					Datas.dataHoraSemSegundosBrasil), Datas.dataHoraEua);
			this.exame.setDataSolicitacao(this.DataSolicitacao);

			// A data e a hora precisam ficar null uma vez que foram pegas, caso
			// contrário sempre vai entrar e pegar valores errados!
			this.Data = null;
			this.Hora = null;
		}

		if (this.flagAlturaExame)
			if (qName.equalsIgnoreCase(CamposXml.altura)) {
				this.exame.setAlturaPaciente(Float.parseFloat(tempVal));
				this.flagAlturaExame = false;
			}

		if (this.flagPesoExame)
			if (qName.equalsIgnoreCase(CamposXml.peso)) {
				this.exame.setPesoPaciente(Float.parseFloat(tempVal));
				this.flagPesoExame = false;
			}

		if (this.flagObservacoes)
			if (qName.equalsIgnoreCase(CamposXml.observacoes)) {
				this.exame.setObservacoes((tempVal != "") ? tempVal : null);
				this.flagObservacoes = false;
			}

		if (this.flagEmailRequisitanteExame)
			if (qName.equalsIgnoreCase(CamposXml.email)) {
				this.exame.setEmailRequisitante((tempVal != "") ? tempVal
						: null);
				this.flagEmailRequisitanteExame = false;
			}
	}

	/**
	 * Retorna uma instância preenchida com os dados do exame do xml.
	 * 
	 * @return {@link Exame}
	 */
	public Exame getExame() {
		return this.exame;
	}

	/**
	 * Executa a leitura do xml.
	 * 
	 * @param xml
	 */
	public void run(String xml) {
		this.parseDocument(xml);
	}

}