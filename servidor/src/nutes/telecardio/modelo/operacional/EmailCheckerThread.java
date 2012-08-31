package nutes.telecardio.modelo.operacional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.swing.JOptionPane;

import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.NomesCampos;
import nutes.telecardio.modelo.estruturaorganizacional.Exame;
import nutes.telecardio.modelo.estruturaorganizacional.ServicoExame;
import nutes.telecardio.utils.Funcoes;

/**
 * Thread para checagem dos e-mails não lidos, baixando os anexos dos mesmos.
 * 
 * @author WaldsonMoura
 * 
 */
public class EmailCheckerThread {
	private String host = null, username = null, password = null,
			pastaExames = null, pastaProjeto = null, path = null,
			nomeExame = null;
	private int port;

	/**
	 * Construtor da thread passando dados referentes ao e-mail.
	 * 
	 * @param host
	 * @param username
	 * @param password
	 * @param port
	 * @param pastaExames
	 * @param pastaProjeto
	 */
	public EmailCheckerThread(String host, String username, String password,
			int port, String pastaExames, String pastaProjeto) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
		this.pastaExames = pastaExames;
		this.pastaProjeto = pastaProjeto;
	}

	/**
	 * Executa a thread.
	 */
	public void execute() {
		try {
			// Get session
			Session session = Session.getInstance(new Properties(), null);

			// Get the store
			Store store = session.getStore("imaps");
			store.connect(this.host, this.port, this.username, this.password);

			// Get folder
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE);

			// Get directory
			Message[] unreadMessages = folder.search(new FlagTerm(new Flags(
					Flags.Flag.SEEN), false));
			if (unreadMessages != null) {
				for (int i = 0, n = unreadMessages.length; i < n; i++) {
					this.nomeExame = unreadMessages[i].getSubject();
					if (this.nomeExame != null) {
						this.path = this.pastaProjeto + File.separator
								+ this.pastaExames + File.separator
								+ this.nomeExame;
						Funcoes.mkDirs(this.path);
						Object content = unreadMessages[i].getContent();
						if (content instanceof Multipart)
							this.handleMultipart((Multipart) content);
						else
							this.handlePart(unreadMessages[i]);
					}
				}
			}

			// Close connection
			folder.close(false);
			store.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					MensagensNegocio.erroChecagemEmail + "\n" + e.getMessage());
		}
	}

	/**
	 * Invoca a função handlePart de acordo com a quantidade de partes.
	 * 
	 * @param multipart
	 * @throws MessagingException
	 * @throws IOException
	 */
	private void handleMultipart(Multipart multipart)
			throws MessagingException, IOException {
		for (int i = 0, n = multipart.getCount(); i < n; i++)
			this.handlePart(multipart.getBodyPart(i));
	}

	/**
	 * Salva os anexos, invoca a função para leitura do xml e cadastra os dados
	 * que foram lidos na base de dados.
	 * 
	 * @param part
	 * @throws MessagingException
	 * @throws IOException
	 */
	private void handlePart(Part part) throws MessagingException, IOException {
		String disposition = part.getDisposition();
		String filename = part.getFileName();
		String fullFilename = this.path + File.separator + filename;
		InputStream inputStream = part.getInputStream();

		if (disposition != null) {
			if (disposition.equalsIgnoreCase(Part.ATTACHMENT)
					|| disposition.equalsIgnoreCase(Part.INLINE))
				Funcoes.saveFile(fullFilename, inputStream);

			String ext = Funcoes.getExtension(filename);

			if (ext.equalsIgnoreCase(NomesCampos.xmlExtension)) {
				Exame exame = new Exame();
				ServicoExame servicoExame = new ServicoExame();
				ServicoPaciente servicoPaciente = new ServicoPaciente();

				SolicitacaoXMLReader solicitacaoXMLReader = new SolicitacaoXMLReader();
				solicitacaoXMLReader.run(fullFilename);
				exame = solicitacaoXMLReader.getExame();

				exame.setCaminhoArquivos(this.pastaExames + File.separator
						+ this.nomeExame);

				exame.getPaciente().setChave(
						servicoPaciente
								.consultarExistencia(exame.getPaciente()));

				Validador validador = new Validador();
				validador = servicoExame.incluir(exame);
				if (validador.getMsgRetorno().size() > 0)
					JOptionPane.showMessageDialog(null, validador
							.getMsgRetorno());
			} else if (ext.equalsIgnoreCase(NomesCampos.jpgExtension)) {
				try {
					CropExam.Crop(this.path, filename);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							MensagensNegocio.erroCortarImagens
									+ ((Message) part).getSubject() + "!\n"
									+ e.getMessage());
				}
			}
		}
	}
}
