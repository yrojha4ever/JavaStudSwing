package javastud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//IMP: project>new> Source Folder> resources folder for images
//Eclipse does not create resource folder in class path it paste directly
//in main path, so dont use resources folder, give name directly liek "/nepal.png"
public class LogoPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		try {
			super.paintComponent(g);

			BufferedImage bImg = ImageIO.read(LogoPanel.class.getResource("/student_login.png"));
			g.drawImage(bImg, 0, 0, 195, 100, null);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}