package br.com.mj.dataminer.util;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
/**
 * JPanelImage permite alterar de maneira facil a imagem de um JPanel
 * @author Marcelo Lopes
 * @since 19/06/2013
 */
public class JPanelImage extends JPanel {

	private static final long serialVersionUID = -8687907176608557245L;

	private BufferedImage img = null;

	/**
	 * @author Marcelo Lopes
	 * @param BufferedImage image
	 * permite altear a imagem do JPanel
	 * 
	 * dica se passar null o JPanel ficar� sem imagem
	 */
	public void setImage(BufferedImage image) {
		img = image;
	}

	/**
	 * @author Marcelo Lopes
	 * Realiza a alteracao da imagem passada pelo metodo setImage
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image drawImage;
		if (img != null) {
			drawImage = img.getScaledInstance(-1, this.getHeight() - 4, BufferedImage.SCALE_DEFAULT);

			int xPos = (getWidth() / 2) - (drawImage.getWidth(null) / 2);
			g.drawImage(drawImage, xPos, 2, null);
		}
	}

	/**
	 * @author Marcelo Lopes
	 * @return BufferedImage img
	 * Recupera a imagem encapsulada no JPanel
	 */
	public BufferedImage getImg() {
		return img;
	}

}
