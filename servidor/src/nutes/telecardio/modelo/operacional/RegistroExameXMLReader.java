package nutes.telecardio.modelo.operacional;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import nutes.telecardio.modelo.configuracao.CamposXml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Classe de leitura do XML apenas para os registros referentes ao exame.
 * 
 * @author WaldsonMoura
 * 
 */
public class RegistroExameXMLReader extends DefaultHandler {

	private RegistroExame registroExame;

	private String tempVal;

	private boolean flagDI, ganhoDI, amostraDI = false;
	private boolean flagDII, ganhoDII, amostraDII = false;
	private boolean flagDIII, ganhoDIII, amostraDIII = false;
	private boolean flagaVR, ganhoaVR, amostraaVR = false;
	private boolean flagaVL, ganhoaVL, amostraaVL = false;
	private boolean flagaVF, ganhoaVF, amostraaVF = false;
	private boolean flagV1, ganhoV1, amostraV1 = false;
	private boolean flagV2, ganhoV2, amostraV2 = false;
	private boolean flagV3, ganhoV3, amostraV3 = false;
	private boolean flagV4, ganhoV4, amostraV4 = false;
	private boolean flagV5, ganhoV5, amostraV5 = false;
	private boolean flagV6, ganhoV6, amostraV6 = false;

	public RegistroExameXMLReader() {
		this.registroExame = new RegistroExame();
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
		tempVal = "";
		if (qName.equalsIgnoreCase(CamposXml.registros)) {
			this.registroExame.setTaxaAmostragem(attributes
					.getValue(CamposXml.taxaAmostragem));
			this.registroExame.setSensibilidade(attributes
					.getValue(CamposXml.sensibilidade));
			this.registroExame.setAmostrasPorBloco(Integer.parseInt(attributes
					.getValue(CamposXml.amostrasPorBloco)));
		}

		// DI
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.dI)) {
				this.flagDI = true;
				this.ganhoDI = true;
			}
		if (this.flagDI)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraDI = true;

		// DII
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.dII)) {
				this.flagDII = true;
				this.ganhoDII = true;
			}
		if (this.flagDII)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraDII = true;

		// DIII
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.dIII)) {
				this.flagDIII = true;
				this.ganhoDIII = true;
			}
		if (this.flagDIII)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraDIII = true;

		// aVR
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.aVR)) {
				this.flagaVR = true;
				this.ganhoaVR = true;
			}
		if (this.flagaVR)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraaVR = true;

		// aVL
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.aVL)) {
				this.flagaVL = true;
				this.ganhoaVL = true;
			}
		if (this.flagaVL)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraaVL = true;

		// avF
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.aVF)) {
				this.flagaVF = true;
				this.ganhoaVF = true;
			}
		if (this.flagaVF)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraaVF = true;

		// V1
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.v1)) {
				this.flagV1 = true;
				this.ganhoV1 = true;
			}
		if (this.flagV1)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraV1 = true;

		// V2
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.v2)) {
				this.flagV2 = true;
				this.ganhoV2 = true;
			}
		if (this.flagV2)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraV2 = true;

		// V3
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.v3)) {
				this.flagV3 = true;
				this.ganhoV3 = true;
			}
		if (this.flagV3)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraV3 = true;

		// V4
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.v4)) {
				this.flagV4 = true;
				this.ganhoV4 = true;
			}
		if (this.flagV4)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraV4 = true;

		// V5
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.v5)) {
				this.flagV5 = true;
				this.ganhoV5 = true;
			}
		if (this.flagV5)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraV5 = true;

		// V6
		if (qName.equalsIgnoreCase(CamposXml.canal))
			if (attributes.getValue(CamposXml.nome).equalsIgnoreCase(
					CamposXml.v6)) {
				this.flagV6 = true;
				this.ganhoV6 = true;
			}
		if (this.flagV6)
			if (qName.equalsIgnoreCase(CamposXml.amostras))
				this.amostraV6 = true;
	}

	/**
	 * Pega os valores de uma determinada tag.
	 * 
	 * @param ch
	 *            [] , start, length
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		tempVal = new String(ch, start, length);
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
		if (qName.equalsIgnoreCase(CamposXml.data))
			this.registroExame.setData(tempVal);
		else if (qName.equalsIgnoreCase(CamposXml.hora))
			this.registroExame.setHora(tempVal);
		else if (qName.equalsIgnoreCase(CamposXml.velocidade))
			this.registroExame.setVelocidade(Integer.parseInt(tempVal));
		else if (qName.equalsIgnoreCase(CamposXml.frequenciaCardiaca))
			this.registroExame.setFrequenciaCardiaca(Integer.parseInt(tempVal));
		else if (qName.equalsIgnoreCase(CamposXml.canalRitmo))
			this.registroExame.setCanalRitmo(Integer.parseInt(tempVal));

		// DI
		if (this.ganhoDI) {
			this.registroExame.setDIGanho(Integer.parseInt(tempVal));
			this.ganhoDI = false;
		}

		if (this.amostraDI) {
			this.registroExame.setDIAmostra(tempVal);
			this.amostraDI = false;
			this.flagDI = false;
		}

		// DII
		if (this.ganhoDII) {
			this.registroExame.setDIIGanho(Integer.parseInt(tempVal));
			this.ganhoDII = false;
		}

		if (this.amostraDII) {
			this.registroExame.setDIIAmostra(tempVal);
			this.amostraDII = false;
			this.flagDII = false;
		}

		// DIII
		if (this.ganhoDIII) {
			this.registroExame.setDIIIGanho(Integer.parseInt(tempVal));
			this.ganhoDIII = false;
		}

		if (this.amostraDIII) {
			this.registroExame.setDIIIAmostra(tempVal);
			this.amostraDIII = false;
			this.flagDIII = false;
		}

		// aVR
		if (this.ganhoaVR) {
			this.registroExame.setAVRGanho(Integer.parseInt(tempVal));
			this.ganhoaVR = false;
		}

		if (this.amostraaVR) {
			this.registroExame.setAVRAmostra(tempVal);
			this.amostraaVR = false;
			this.flagaVR = false;
		}

		// aVL
		if (this.ganhoaVL) {
			this.registroExame.setAVLGanho(Integer.parseInt(tempVal));
			this.ganhoaVL = false;
		}

		if (this.amostraaVL) {
			this.registroExame.setAVLAmostra(tempVal);
			this.amostraaVL = false;
			this.flagaVL = false;
		}

		// aVF
		if (this.ganhoaVF) {
			this.registroExame.setAVFGanho(Integer.parseInt(tempVal));
			this.ganhoaVF = false;
		}

		if (this.amostraaVF) {
			this.registroExame.setAVFAmostra(tempVal);
			this.amostraaVF = false;
			this.flagaVF = false;
		}

		// V1
		if (this.ganhoV1) {
			this.registroExame.setV1Ganho(Integer.parseInt(tempVal));
			this.ganhoV1 = false;
		}

		if (this.amostraV1) {
			this.registroExame.setV1Amostra(tempVal);
			this.amostraV1 = false;
			this.flagV1 = false;
		}

		// V2
		if (this.ganhoV2) {
			this.registroExame.setV2Ganho(Integer.parseInt(tempVal));
			this.ganhoV2 = false;
		}

		if (this.amostraV2) {
			this.registroExame.setV2Amostra(tempVal);
			this.amostraV2 = false;
			this.flagV2 = false;
		}

		// V3
		if (this.ganhoV3) {
			this.registroExame.setV3Ganho(Integer.parseInt(tempVal));
			this.ganhoV3 = false;
		}

		if (this.amostraV3) {
			this.registroExame.setV3Amostra(tempVal);
			this.amostraV3 = false;
			this.flagV3 = false;
		}

		// V4
		if (this.ganhoV4) {
			this.registroExame.setV4Ganho(Integer.parseInt(tempVal));
			this.ganhoV4 = false;
		}

		if (this.amostraV4) {
			this.registroExame.setV4Amostra(tempVal);
			this.amostraV4 = false;
			this.flagV4 = false;
		}

		// V5
		if (this.ganhoV5) {
			this.registroExame.setV5Ganho(Integer.parseInt(tempVal));
			this.ganhoV5 = false;
		}

		if (this.amostraV5) {
			this.registroExame.setV5Amostra(tempVal);
			this.amostraV5 = false;
			this.flagV5 = false;
		}

		// V6
		if (this.ganhoV6) {
			this.registroExame.setV6Ganho(Integer.parseInt(tempVal));
			this.ganhoV6 = false;
		}

		if (this.amostraV6) {
			this.registroExame.setV6Amostra(tempVal);
			this.amostraV6 = false;
			this.flagV6 = false;
		}
	}

	/**
	 * Retorna uma instância preenchida com os dados dos registros do exame do
	 * xml.
	 * 
	 * @return {@link RegistroExame}
	 */
	public RegistroExame retornarRegistroExame() {
		return this.registroExame;
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