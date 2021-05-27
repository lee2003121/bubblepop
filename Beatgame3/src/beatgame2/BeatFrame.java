package beatgame2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * 
 * 비트프레임 클래스. 게임의 모든 클래스 관리 및 draw
 * 주요 기능
 * <ol>
 * 	<li>게임 구현 클래스 관리,이미지 관리</li>
 *  <li></li>
 * </ol>

 *
 */
public class BeatFrame extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
		
	private ImageIcon ingameButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/ingameButtonEntered.png"));
	private ImageIcon ingameButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ingameButtonBasic.png"));
	private ImageIcon homeButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/homeButtonEntered.png"));
	private ImageIcon homeButtonBasicImage = new ImageIcon(Main.class.getResource("../images/homeButtonBasic.png"));
	private ImageIcon saveButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/saveButtonEntered.png"));
	private ImageIcon saveButtonBasicImage = new ImageIcon(Main.class.getResource("../images/saveButtonBasic.png"));
	private ImageIcon rankButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rankButtonEntered.png"));
	private ImageIcon rankButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rankButtonBasic.png"));
		
	private Image Ranking = new ImageIcon(Main.class.getResource("../images/SSRank.png")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/Background_intro.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	/**
	 * 화면 종료 버튼
	 */
	private JButton exitButton = new JButton(exitButtonBasicImage);
	/**
	 * 인트로 화면 시작하기 버튼
	 */
	private JButton startButton = new JButton(startButtonBasicImage);
	/**
	 * 인트로 화면 종료하기 버튼
	 */
	private JButton quitButton = new JButton(quitButtonBasicImage);
	/**
	 * 인트로 화면 종료하기 버튼
	 */
	private JButton leftButton = new JButton(leftButtonBasicImage);
	/**
	 * 곡선택 왼쪽 버튼
	 */
	private JButton rightButton = new JButton(rightButtonBasicImage);
	/**
	 * 곡선택 오른쪽 버튼
	 */
	private JButton  ingameButton = new JButton(ingameButtonBasicImage);
	/**
	 * 곡 선택후 게임하기 버튼
	 */
	private JButton backButton = new JButton(backButtonBasicImage);
	/**
	 * 게임 종료후 인트로로 돌아가기 버튼
	 */
	private JButton homeButton = new JButton(homeButtonBasicImage);
	/**
	 * 인트로로 돌아가기전 게임 결과 저장하기 버튼
	 */
	private JButton saveButton = new JButton(saveButtonBasicImage);
	
	private JButton rankButton = new JButton(rankButtonBasicImage);
	
	/**
	 * 화면 상단 메뉴 바 생성을 위한 마우스 위치
	 */
	private int mouseX, mouseY;
	/**
	 * 메인 화면을 그려주기위한 확인 변수
	 */
	private boolean isMainScreen =false;
	/**
	 * 게임 화면을 그려주기위한 확인 변수
	 */
	private boolean isGameScreen =false;
	/**
	 * 화면 상단 메뉴 바 생성을 위한 마우스 위치
	 */
	
	
	
	ArrayList<Track> trackList=new ArrayList<Track>();



	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private int nowSelected =0;
	private Music introMusic = new Music("introMusic.mp3", true);
	private int Score, ShowScore;
	private String name;

	public static Game game;
	public Ranking rank;
	public static Beatgame_jdbc jdbc=new Beatgame_jdbc();
	/**
	 * 비트 프레임의 생성자
	 */
	public BeatFrame() 
	{
		Ranking rank=new Ranking();
		rank.setLocationRelativeTo(this);
		trackList.add(new Track("Another-time TitleImage.png","music2-1.png","music2-2.png","time Select.mp3","Another-time.mp3","Another-time"));
		trackList.add(new Track("Ikson-Dreamer TitleImage.png","music3-1.png","music3-2.png","Dreamer Select.mp3","Ikson-Dreamer.mp3","Ikson-Dreamer"));
		trackList.add(new Track("RetroVision_Campfire TitleImage.png","music1-1.png","music1-2.png","Campfire Select.mp3","RetroVision_Campfire.mp3","RetroVision_Campfire"));
			
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		introMusic.start();

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				
			}
		});
		add(exitButton);
		
		startButton.setBounds(850, 450, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterMain();
			}
		});
		add(startButton);
		
		quitButton.setBounds(850, 580, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
				
			}
		});
		add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 580, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();	
				selectLeft();
			}
		});
		add(leftButton);
		
		

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 580, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();			
				selectRight();
			}
		});
		add(rightButton);
		
		
		ingameButton.setVisible(false);
		ingameButton.setBounds(510, 580, 250, 67);
		ingameButton.setBorderPainted(false);
		ingameButton.setContentAreaFilled(false);
		ingameButton.setFocusPainted(false);
		ingameButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ingameButton.setIcon(ingameButtonEnteredImage);
				ingameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ingameButton.setIcon(ingameButtonBasicImage);
				ingameButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();		
				gameStart(nowSelected);
			}
		});
		add(ingameButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();		
				backMain();
			}
		});
		add(backButton);
		
		
		homeButton.setVisible(false);
		homeButton.setBounds(800, 500, 300, 100);
		homeButton.setBorderPainted(false);
		homeButton.setContentAreaFilled(false);
		homeButton.setFocusPainted(false);
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				homeButton.setIcon(homeButtonEnteredImage);
				homeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				homeButton.setIcon(homeButtonBasicImage);
				homeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				returnStart();
			}
		});
		add(homeButton);
		
		saveButton.setVisible(false);
		saveButton.setBounds(800, 600, 300, 100);
		saveButton.setBorderPainted(false);
		saveButton.setContentAreaFilled(false);
		saveButton.setFocusPainted(false);
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				saveButton.setIcon(saveButtonEnteredImage);
				saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				saveButton.setIcon(saveButtonBasicImage);
				saveButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				save();
				returnStart();
			}
		});
		add(saveButton);
		
		rankButton.setVisible(false);
		rankButton.setBounds(30, 30, 150, 50);
		rankButton.setBorderPainted(false);
		rankButton.setContentAreaFilled(false);
		rankButton.setFocusPainted(false);
		rankButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rankButton.setIcon(rankButtonEnteredImage);
				rankButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rankButton.setIcon(rankButtonBasicImage);
				rankButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				rank.drawRank();
				rank.showRank();
			}
		});
		add(rankButton);
		
		
		
		
		menuBar.setBounds(0, 0, 1280, 30);
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
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	int i=0;
	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if(isMainScreen)
		{
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if(isGameScreen)
		{
			game.ScreenDraw(g);
		}
		if(Game.gameEnd==true)
		{
			result(g);
		}

		paintComponents(g);
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	public void selectTrack(int nowSelected)
	{
		if(selectedMusic!=null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getStartImage())).getImage();
		
		selectedMusic  = new Music(trackList.get(nowSelected).getStartMusic(),true);
		selectedMusic.start();
	}
	public void selectLeft()
	{
		if(nowSelected==0)
			
			nowSelected=trackList.size()-1;
		
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	public void selectRight()
	{
		if(nowSelected==trackList.size()-1)
			nowSelected=0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	
	public void gameStart(int nowSelected)
	{
		game.gameEnd=false;
		if(selectedMusic!=null)
			selectedMusic.close();
		isMainScreen=false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		ingameButton.setVisible(false);
		rankButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen=true;
		//isCountdown=true;
		
		game=new Game(trackList.get(nowSelected).getTitleName(),trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);
	
	}
	public void backMain()
	{
		isMainScreen=true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		ingameButton.setVisible(true);
		rankButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/Background_main.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		
		isGameScreen=false;
	}
	public void rank() 
	{
		Score = Game.totalScore;
		if (Score > 10000)
			Ranking = new ImageIcon(Main.class.getResource("../images/SSRank.png")).getImage();
		else if (Score > 8000)
			Ranking = new ImageIcon(Main.class.getResource("../images/SRank.png")).getImage();
		else if (Score > 6000)
			Ranking = new ImageIcon(Main.class.getResource("../images/ARank.png")).getImage();
		else if (Score > 4000)
			Ranking = new ImageIcon(Main.class.getResource("../images/BRank.png")).getImage();
		else if (Score > 2000)
			Ranking = new ImageIcon(Main.class.getResource("../images/CRank.png")).getImage();
		else if(Score > 2000)
			Ranking = new ImageIcon(Main.class.getResource("../images/DRank.png")).getImage();
		else if (Score > 2000)
			Ranking = new ImageIcon(Main.class.getResource("../images/ERank.png")).getImage();
		else
			Ranking = new ImageIcon(Main.class.getResource("../images/FRank.png")).getImage();

	}
	public void result(Graphics2D g)
	{
		
		
		isGameScreen=false;
		selectTrack(nowSelected);
		game.close();
		background = new ImageIcon(Main.class.getResource("../images/ResultBackground.jpg")).getImage();
		rank();
		
		g.drawImage(Ranking, 200, 100, null);
		backButton.setVisible(false);
		homeButton.setVisible(true);
		saveButton.setVisible(true);
		
		g.setFont(new Font("굴림체",Font.BOLD,50));
		g.setColor(new Color(255,255,255));
		g.drawString("최종점수:" + Integer.toString(Result.ShowScore), 730, 300);
		
		setFocusable(true);
	}
	public void save()
	{
		name = JOptionPane.showInputDialog("최종 점수:" + Score + "\n" + "저장하실 이름을 입력하세요","unknown");

		jdbc.saveInfo(name,Score);
	}
	public void returnStart()
	{
		Game.gameEnd=false;
		isMainScreen=true;
		homeButton.setVisible(false);
		saveButton.setVisible(false);
		backButton.setVisible(false);
		startButton.setVisible(true);
		quitButton.setVisible(true);
		isMainScreen=false;
		background = new ImageIcon(Main.class.getResource("../images/Background_intro.jpg")).getImage();
		selectedMusic.close();
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();

	}
	public void enterMain()
	{
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/Background_main.jpg")).getImage();
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		ingameButton.setVisible(true);
		rankButton.setVisible(true);
		isMainScreen=true;
		selectTrack(0);
		introMusic.close();
	}
	
}
    