package nutes.telecardio.modelo.operacional;

/**
 * Classe básica com os dados do registro do exame.
 * 
 * @author WaldsonMoura
 * 
 */
public class RegistroExame {
	private String data;
	private String hora;
	private int velocidade;
	private String sensibilidade;
	private int amostrasPorBloco;
	private int frequenciaCardiaca;
	private int canalRitmo;
	private String taxaAmostragem;
	private int DIGanho;
	private String[] DIAmostra;
	private int DIIGanho;
	private String[] DIIAmostra;
	private int DIIIGanho;
	private String[] DIIIAmostra;
	private int aVRGanho;
	private String[] aVRAmostra;
	private int aVLGanho;
	private String[] aVLAmostra;
	private int aVFGanho;
	private String[] aVFAmostra;
	private int V1Ganho;
	private String[] V1Amostra;
	private int V2Ganho;
	private String[] V2Amostra;
	private int V3Ganho;
	private String[] V3Amostra;
	private int V4Ganho;
	private String[] V4Amostra;
	private int V5Ganho;
	private String[] V5Amostra;
	private int V6Ganho;
	private String[] V6Amostra;

	private static final String separador = ";";

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public String getSensibilidade() {
		return sensibilidade;
	}

	public void setSensibilidade(String sensibilidade) {
		this.sensibilidade = sensibilidade;
	}

	public int getAmostrasPorBloco() {
		return amostrasPorBloco;
	}

	public void setAmostrasPorBloco(int amostrasPorBloco) {
		this.amostrasPorBloco = amostrasPorBloco;
	}

	public int getFrequenciaCardiaca() {
		return frequenciaCardiaca;
	}

	public void setFrequenciaCardiaca(int frequenciaCardiaca) {
		this.frequenciaCardiaca = frequenciaCardiaca;
	}

	public int getCanalRitmo() {
		return canalRitmo;
	}

	public void setCanalRitmo(int canalRitmo) {
		this.canalRitmo = canalRitmo;
	}

	public String getTaxaAmostragem() {
		return taxaAmostragem;
	}

	public void setTaxaAmostragem(String taxaAmostragem) {
		this.taxaAmostragem = taxaAmostragem;
	}

	public int getDIGanho() {
		return DIGanho;
	}

	public void setDIGanho(int ganho) {
		DIGanho = ganho;
	}

	public String[] getDIAmostra() {
		return DIAmostra;
	}

	public void setDIAmostra(String amostra) {
		DIAmostra = amostra.split(separador);
	}

	public int getDIIGanho() {
		return DIIGanho;
	}

	public void setDIIGanho(int ganho) {
		DIIGanho = ganho;
	}

	public String[] getDIIAmostra() {
		return DIIAmostra;
	}

	public void setDIIAmostra(String amostra) {
		DIIAmostra = amostra.split(separador);
	}

	public int getDIIIGanho() {
		return DIIIGanho;
	}

	public void setDIIIGanho(int ganho) {
		DIIIGanho = ganho;
	}

	public String[] getDIIIAmostra() {
		return DIIIAmostra;
	}

	public void setDIIIAmostra(String amostra) {
		DIIIAmostra = amostra.split(separador);
	}

	public int getAVRGanho() {
		return aVRGanho;
	}

	public void setAVRGanho(int ganho) {
		aVRGanho = ganho;
	}

	public String[] getAVRAmostra() {
		return aVRAmostra;
	}

	public void setAVRAmostra(String amostra) {
		aVRAmostra = amostra.split(separador);
	}

	public int getAVLGanho() {
		return aVLGanho;
	}

	public void setAVLGanho(int ganho) {
		aVLGanho = ganho;
	}

	public String[] getAVLAmostra() {
		return aVLAmostra;
	}

	public void setAVLAmostra(String amostra) {
		aVLAmostra = amostra.split(separador);
	}

	public int getAVFGanho() {
		return aVFGanho;
	}

	public void setAVFGanho(int ganho) {
		aVFGanho = ganho;
	}

	public String[] getAVFAmostra() {
		return aVFAmostra;
	}

	public void setAVFAmostra(String amostra) {
		aVFAmostra = amostra.split(separador);
	}

	public int getV1Ganho() {
		return V1Ganho;
	}

	public void setV1Ganho(int ganho) {
		V1Ganho = ganho;
	}

	public String[] getV1Amostra() {
		return V1Amostra;
	}

	public void setV1Amostra(String amostra) {
		V1Amostra = amostra.split(separador);
	}

	public int getV2Ganho() {
		return V2Ganho;
	}

	public void setV2Ganho(int ganho) {
		V2Ganho = ganho;
	}

	public String[] getV2Amostra() {
		return V2Amostra;
	}

	public void setV2Amostra(String amostra) {
		V2Amostra = amostra.split(separador);
	}

	public int getV3Ganho() {
		return V3Ganho;
	}

	public void setV3Ganho(int ganho) {
		V3Ganho = ganho;
	}

	public String[] getV3Amostra() {
		return V3Amostra;
	}

	public void setV3Amostra(String amostra) {
		V3Amostra = amostra.split(separador);
	}

	public int getV4Ganho() {
		return V4Ganho;
	}

	public void setV4Ganho(int ganho) {
		V4Ganho = ganho;
	}

	public String[] getV4Amostra() {
		return V4Amostra;
	}

	public void setV4Amostra(String amostra) {
		V4Amostra = amostra.split(separador);
	}

	public int getV5Ganho() {
		return V5Ganho;
	}

	public void setV5Ganho(int ganho) {
		V5Ganho = ganho;
	}

	public String[] getV5Amostra() {
		return V5Amostra;
	}

	public void setV5Amostra(String amostra) {
		V5Amostra = amostra.split(separador);
	}

	public int getV6Ganho() {
		return V6Ganho;
	}

	public void setV6Ganho(int ganho) {
		V6Ganho = ganho;
	}

	public String[] getV6Amostra() {
		return V6Amostra;
	}

	public void setV6Amostra(String amostra) {
		V6Amostra = amostra.split(separador);
	}
}
