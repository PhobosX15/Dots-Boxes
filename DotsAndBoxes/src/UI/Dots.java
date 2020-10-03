package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dots extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(UIMainIntro.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(UIMainIntro.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startB = new ImageIcon(UIMainIntro.class.getResource("../images/start.png"));
	private ImageIcon exitB = new ImageIcon(UIMainIntro.class.getResource("../images/exit.png"));
	private ImageIcon leftB = new ImageIcon(UIMainIntro.class.getResource("../images/left.png"));
	private ImageIcon rightB = new ImageIcon(UIMainIntro.class.getResource("../images/right.png"));
	private ImageIcon humanB = new ImageIcon(UIMainIntro.class.getResource("../images/user.png"));
	private ImageIcon aiB = new ImageIcon(UIMainIntro.class.getResource("../images/ai.png"));
	// private Image selectedImage = new
	// ImageIcon(Main.class.getResource("../images/33.png")).getImage();

	private Image background = new ImageIcon(UIMainIntro.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(UIMainIntro.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton start = new JButton(startB);
	private JButton exit = new JButton(exitB);
	private JButton left = new JButton(leftB);
	private JButton right = new JButton(rightB);
	private JButton human = new JButton(humanB);
	private JButton ai = new JButton(aiB);
	private int mouseX, mouseY;

	private boolean mainScreen = false;

	ArrayList<Grid> gridList = new ArrayList<Grid>();
	private Image selectedImage;

	private int nowSelected = 0;

	public Dots() {
		setUndecorated(true);
		setTitle("Dots-And-Boxes");
		setSize(UIMainIntro.SCREEN_WIDTH, UIMainIntro.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		left.setVisible(false);
		right.setVisible(false);
		human.setVisible(false);
		ai.setVisible(false);
		gridList.add(new Grid("33.png"));
		gridList.add(new Grid("55.png"));
		gridList.add(new Grid("77.png"));

		exitButton.setBounds(1140, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);

		start.setBounds(1070, 600, 100, 100);
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setFocusPainted(false);
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
				selectGrid(0);
				start.setVisible(false);
				exit.setVisible(false);
				left.setVisible(true);
				right.setVisible(true);
				human.setVisible(true);
				ai.setVisible(true);
				background = new ImageIcon(UIMainIntro.class.getResource("../images/dotsnboxes.jpg")).getImage();
				mainScreen = true;
			}
		});
		add(start);

		left.setBounds(200, 300, 60, 60);
		left.setBorderPainted(false);
		left.setContentAreaFilled(false);
		left.setFocusPainted(false);
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				left.setIcon(leftB);
				left.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				left.setIcon(leftB);
				left.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// next page
				selectLeft();

			}
		});
		add(left);

		right.setBounds(900, 300, 60, 60);
		right.setBorderPainted(false);
		right.setContentAreaFilled(false);
		right.setFocusPainted(false);
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				right.setIcon(rightB);
				right.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				right.setIcon(rightB);
				right.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// next page
				selectRight();
			}
		});
		add(right);

		human.setBounds(370, 580, 187, 66);
		human.setBorderPainted(false);
		human.setContentAreaFilled(false);
		human.setFocusPainted(false);
		human.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				human.setIcon(humanB);
				human.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				human.setIcon(humanB);
				human.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// next page
				gameStart(nowSelected, "user");
			}
		});
		add(human);

		ai.setBounds(655, 580, 180, 60);
		ai.setBorderPainted(false);
		ai.setContentAreaFilled(false);
		ai.setFocusPainted(false);
		ai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ai.setIcon(aiB);
				ai.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ai.setIcon(aiB);
				ai.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// next page
				gameStart(nowSelected, "ai");

			}
		});
		add(ai);

		exit.setBounds(0, 600, 100, 100);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.setFocusPainted(false);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setIcon(exitB);
				exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exit.setIcon(exitB);
				exit.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// next page
				System.exit(0);

			}
		});
		add(exit);

		menuBar.setBounds(0, 0, 1170, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}

	public void paint(Graphics g) {
		screenImage = createImage(UIMainIntro.SCREEN_WIDTH, UIMainIntro.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		if (mainScreen) {
			g.drawImage(selectedImage, 385, 100, null);
		}
		paintComponents(g);
		this.repaint();
	}

	public void selectGrid(int nowSelected) {
		selectedImage = new ImageIcon(UIMainIntro.class.getResource("../images/" + gridList.get(nowSelected).getGridImage()))
				.getImage();

	}

	public void selectLeft() {
		if (nowSelected == 0) {
			nowSelected = gridList.size() - 1;
		} else {
			nowSelected--;
		}
		selectGrid(nowSelected);
	}

	public void selectRight() {
		if (nowSelected == gridList.size() - 1) {
			nowSelected = 0;
		} else {
			nowSelected++;
		}
		selectGrid(nowSelected);
	}

	public void gameStart(int nowSelected, String mode) {
		mainScreen = false;
		left.setVisible(false);
		right.setVisible(false);
		human.setVisible(false);
		ai.setVisible(false);
		background = new ImageIcon(UIMainIntro.class.getResource("../images/" + gridList.get(nowSelected).getGridImage()))
				.getImage();

	}

	public static void main(String[] args) {
		new Dots();
	}
}
