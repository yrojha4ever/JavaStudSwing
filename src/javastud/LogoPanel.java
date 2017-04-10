package javastud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LogoPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		try {
			super.paintComponent(g);

			BufferedImage bImg = ImageIO.read(LogoPanel.class.getResource("/logo.jpg"));
			g.drawImage(bImg, 0, 0, 195, 100, null);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}