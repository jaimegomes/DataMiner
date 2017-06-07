package br.com.mj.dataminer.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.mj.dataminer.controller.PrincipalFormCnt;

public class PrincipalView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JDesktopPane desktopPane;
	private JLabel lblNomeArquivoUpload;
	private JLabel lblNomeDiretorioDestino;
	private JLabel lblStatus;
	private JButton btnSelecionar;
	private JButton btnDiretorioDestino;
	private JButton btnIniciar;
	private String bancoLogado;

	public PrincipalView(final PrincipalFormCnt principalFormCnt) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logotipo.png"));

		desktopPane = new JDesktopPane();
		desktopPane.setLayout(null);
		
		setTitle("CreditMiner Plus v1.0.0");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 180);
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int componentWidth = getWidth();
		int componentHeight = getHeight();
		setBounds((screenSize.width - componentWidth) / 2, (screenSize.height - componentHeight) / 2, componentWidth, componentHeight);

		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, Color.DARK_GRAY, Color.GREEN, Color.CYAN));
		setContentPane(desktopPane);

		btnDiretorioDestino = new JButton("Diretório Destino");
		btnIniciar = new JButton("Iniciar Captura");

		btnSelecionar = new JButton("Upload Arquivo");
		btnSelecionar.setForeground(SystemColor.DARK_GRAY);
		btnSelecionar.setFont(new Font("Agency FB", Font.BOLD, 12));
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					File file = chooser.getSelectedFile();
					principalFormCnt.setFileUpload(file);
					lblNomeArquivoUpload.setText(file.getName());
					if (principalFormCnt.getFileDestino() != null) {
						lblNomeDiretorioDestino.setText(principalFormCnt.getFileDestino().getAbsolutePath());
					}
					btnDiretorioDestino.setEnabled(true);
				}
			}
		});
		btnSelecionar.setBounds(10, 40, 156, 23);
		desktopPane.add(btnSelecionar);

		lblNomeArquivoUpload = new JLabel();
		lblNomeArquivoUpload.setBounds(185, 40, 597, 23);
		desktopPane.add(lblNomeArquivoUpload);

		btnDiretorioDestino.setEnabled(false);
		btnDiretorioDestino.setForeground(SystemColor.DARK_GRAY);
		btnDiretorioDestino.setFont(new Font("Agency FB", Font.BOLD, 12));
		btnDiretorioDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int res = fc.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					File diretorio = fc.getSelectedFile();
					principalFormCnt.setFileDestino(diretorio);
					lblNomeDiretorioDestino.setText(principalFormCnt.getFileDestino().getAbsolutePath());
					btnIniciar.setEnabled(true);
				}

			}
		});
		btnDiretorioDestino.setBounds(10, 70, 156, 23);
		desktopPane.add(btnDiretorioDestino);

		lblNomeDiretorioDestino = new JLabel();
		lblNomeDiretorioDestino.setBounds(185, 70, 597, 23);
		desktopPane.add(lblNomeDiretorioDestino);

		btnIniciar.setEnabled(false);
		btnIniciar.setForeground(SystemColor.DARK_GRAY);
		btnIniciar.setFont(new Font("Agency FB", Font.BOLD, 12));
		btnIniciar.setIcon(new ImageIcon("img/mdc_logo.PNG"));
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnIniciar.setEnabled(false);
					principalFormCnt.iniciarProcesso();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnIniciar.setBounds(10, 100, 156, 23);
		desktopPane.add(btnIniciar);

		lblStatus = new JLabel();
		lblStatus.setBounds(185, 100, 597, 23);
		desktopPane.add(lblStatus);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(-125, -111, 1366, 692);
		lblLogo.setIcon(new ImageIcon("img/7484_32x32.png"));
		desktopPane.add(lblLogo);


	}

	public static void controlaJanela(JInternalFrame janela) {
		try {
			janela.setVisible(true);
			desktopPane.add(janela);
			desktopPane.getDesktopManager().activateFrame(janela);

			// centraliza no desktopPane
			int lDesk = desktopPane.getWidth();
			int aDesk = desktopPane.getHeight();
			int lIFrame = janela.getWidth();
			int aIFrame = janela.getHeight();

			janela.setLocation(lDesk / 2 - lIFrame / 2, aDesk / 2 - aIFrame / 2);

		} catch (Exception e) {
			System.out.println("Erro ao criar janela " + e.getMessage());
		}

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	/**
	 * @return the lblStatus
	 */
	public JLabel getLblStatus() {
		return lblStatus;
	}

	/**
	 * @param lblStatus
	 *            the lblStatus to set
	 */
	public void setLblStatus(JLabel lblStatus) {
		this.lblStatus = lblStatus;
	}

	/**
	 * @return the contentPane
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	/**
	 * @param contentPane
	 *            the contentPane to set
	 */
	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	/**
	 * @return the desktopPane
	 */
	public static JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	/**
	 * @param desktopPane
	 *            the desktopPane to set
	 */
	public static void setDesktopPane(JDesktopPane desktopPane) {
		PrincipalView.desktopPane = desktopPane;
	}


	/**
	 * @return the lblNomeArquivoUpload
	 */
	public JLabel getLblNomeArquivoUpload() {
		return lblNomeArquivoUpload;
	}

	/**
	 * @param lblNomeArquivoUpload
	 *            the lblNomeArquivoUpload to set
	 */
	public void setLblNomeArquivoUpload(JLabel lblNomeArquivoUpload) {
		this.lblNomeArquivoUpload = lblNomeArquivoUpload;
	}

	/**
	 * @return the lblNomeDiretorioDestino
	 */
	public JLabel getLblNomeDiretorioDestino() {
		return lblNomeDiretorioDestino;
	}

	/**
	 * @param lblNomeDiretorioDestino
	 *            the lblNomeDiretorioDestino to set
	 */
	public void setLblNomeDiretorioDestino(JLabel lblNomeDiretorioDestino) {
		this.lblNomeDiretorioDestino = lblNomeDiretorioDestino;
	}

	/**
	 * @return the btnSelecionar
	 */
	public JButton getBtnSelecionar() {
		return btnSelecionar;
	}

	/**
	 * @param btnSelecionar
	 *            the btnSelecionar to set
	 */
	public void setBtnSelecionar(JButton btnSelecionar) {
		this.btnSelecionar = btnSelecionar;
	}

	/**
	 * @return the btnDiretorioDestino
	 */
	public JButton getBtnDiretorioDestino() {
		return btnDiretorioDestino;
	}

	/**
	 * @param btnDiretorioDestino
	 *            the btnDiretorioDestino to set
	 */
	public void setBtnDiretorioDestino(JButton btnDiretorioDestino) {
		this.btnDiretorioDestino = btnDiretorioDestino;
	}

	/**
	 * @return the btnIniciar
	 */
	public JButton getBtnIniciar() {
		return btnIniciar;
	}

	/**
	 * @param btnIniciar
	 *            the btnIniciar to set
	 */
	public void setBtnIniciar(JButton btnIniciar) {
		this.btnIniciar = btnIniciar;
	}

	/**
	 * @return the bancoLogado
	 */
	public String getBancoLogado() {
		return bancoLogado;
	}

	/**
	 * @param bancoLogado the bancoLogado to set
	 */
	public void setBancoLogado(String bancoLogado) {
		this.bancoLogado = bancoLogado;
	}
	
	

}
