package nutes.telecardio.modelo.estruturaorganizacional;

public class MedicoMobile {
	
	public String Nome;
	public String  Login;
	public String Senha;
	public int Chave;
	public int ChaveProfissional;
	public int Crm;
	
	private String MedicoMobileEmString;	
	
	
	public String converterEntidadeString(){
		
		StringBuilder MedicoTexto = new StringBuilder();
		
		MedicoTexto.append("<NOME>");
		MedicoTexto.append(Nome);
		MedicoTexto.append("</NOME>");
		
		MedicoTexto.append("<LOGIN>");
		MedicoTexto.append(Login);
		MedicoTexto.append("</LOGIN>");
		
		MedicoTexto.append("<SENHA>");
		MedicoTexto.append(Senha);
		MedicoTexto.append("</SENHA>");
		
		MedicoTexto.append("<CHAVE>");
		MedicoTexto.append(Chave);
		MedicoTexto.append("</CHAVE>");
		
		MedicoTexto.append("<CHAVEPROFISSIONAL>");
		MedicoTexto.append(ChaveProfissional);
		MedicoTexto.append("</CHAVEPROFISSIONAL>");
		
		MedicoTexto.append("<CRM>");
		MedicoTexto.append(Crm);
		MedicoTexto.append("</CRM>");
		
		MedicoMobileEmString = MedicoTexto.toString();
		
		return MedicoMobileEmString;
	}
}
