package dots;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

	public static final int SCREEN_WIDTH = 1170;
	public static final int SCREEN_HEIGHT = 720;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private JButton exitButton = new JButton(exitButtonBasicImage);



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();

	}
	public void initializeIntroScreen(JFrame frame){


		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				g.drawImage(new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage(),0,0,SCREEN_WIDTH , SCREEN_HEIGHT, this);
			}
		};
		panel.setLayout(null);

		ImageIcon startB = new ImageIcon(Main.class.getResource("../images/start.png"));
		JButton start = new JButton(startB);
		start.setBounds(990, 550, 100, 100);
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setFocusPainted(false);
		start.setVisible(true);
		start.addMouseListener(new MouseAdapter() {
								   @Override
								   public void mouseEntered(MouseEvent e) {
									   start.setIcon(startB);
									   start.setCursor(new Cursor(Cursor.HAND_CURSOR));
								   }

								   @Override
								   public void mouseExited(MouseEvent e) {
									   start.setIcon(startB);
									   start.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
								   }

								   @Override
								   public void mousePressed(MouseEvent e) {
										start.setVisible(false);
								   }
							   });



		frame.setContentPane(panel);
		frame.add(start);
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);




	}

	public Main(){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		frame.setTitle("Dots-And-Boxes");
		frame.setResizable(false);
		initializeIntroScreen(frame);
	}

}
