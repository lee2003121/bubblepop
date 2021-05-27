package beatgame2;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{

	

	private  static Image aButtonImage = new ImageIcon(Main.class.getResource("../images/aButton.png")).getImage();
	private  static Image sButtonImage = new ImageIcon(Main.class.getResource("../images/sButton.png")).getImage();
	private  static Image kButtonImage = new ImageIcon(Main.class.getResource("../images/kButton.png")).getImage();
	private  static Image lButtonImage = new ImageIcon(Main.class.getResource("../images/lButton.png")).getImage();

	public static Image noteClickAImage = new ImageIcon(Main.class.getResource("../images/ButtonNull.png")).getImage();
	public static Image noteClickSImage = new ImageIcon(Main.class.getResource("../images/ButtonNull.png")).getImage();
	public static Image noteClickKImage = new ImageIcon(Main.class.getResource("../images/ButtonNull.png")).getImage();
	public static Image noteClickLImage = new ImageIcon(Main.class.getResource("../images/ButtonNull.png")).getImage();
	public static Image scoreEffect;
	public static Image judgeImage;
	
	public BeatFrame beatFrame;
	
	private String titleName;
	private String musicTitle;
	public Music gameMusic;
	public static int totalScore=0;

	public static boolean gameEnd;

	public Result R1=new Result(); 
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	int i=0;
	public Game(String titleName,String musicTitle) {
		totalScore=0;
		this.titleName=titleName;	
		this.musicTitle=musicTitle;	
		i=1;
		gameMusic=new Music(this.musicTitle,false);
		
	}
	public void pressA() {
		judge("A");
		//aButtonImage = new ImageIcon(Main.class.getResource("../images/aButtonPressed.png")).getImage();
		
		noteClickAImage = new ImageIcon(Main.class.getResource("../images/ButtonPressed.png")).getImage();
		new Music("drum1.mp3", false).start();
			
	}

	public void releaseA() {
		noteClickAImage = new ImageIcon(Main.class.getResource("../images/ButtonNull.png")).getImage();
		//aButtonImage = new ImageIcon(Main.class.getResource("../images/aButton.png")).getImage();
	}
	

	public void pressS() {
		judge("B");
		//sButtonImage = new ImageIcon(Main.class.getResource("../images/sButtonPressed.png")).getImage();
		noteClickSImage = new ImageIcon(Main.class.getResource("../images/ButtonPressed.png")).getImage();
		new Music("drum5.mp3", false).start();
	}

	public void releaseS()
	 {
		noteClickSImage = new ImageIcon(Main.class.getResource("../images/ButtonNull.png")).getImage();
		//sButtonImage = new ImageIcon(Main.class.getResource("../images/sButton.png")).getImage();
	 }

	public void pressK() {
		judge("K");
		//kButtonImage = new ImageIcon(Main.class.getResource("../images/kButtonPressed.png")).getImage();	
		noteClickKImage = new ImageIcon(Main.class.getResource("../images/ButtonPressed.png")).getImage();
		new Music("drum3.mp3", false).start();
	}
	
	public void releaseK()
	{
		noteClickKImage = new ImageIcon(Main.class.getResource("../images/ButtonNull.png")).getImage();
		//kButtonImage = new ImageIcon(Main.class.getResource("../images/kButton.png")).getImage();
	}

	public void pressL() {
		judge("L");
		//lButtonImage = new ImageIcon(Main.class.getResource("../images/lButtonPressed.png")).getImage();
		noteClickLImage = new ImageIcon(Main.class.getResource("../images/ButtonPressed.png")).getImage();	
		new Music("drum4.mp3", false).start();
	}

	public void releaseL() 
	{
		noteClickLImage = new ImageIcon(Main.class.getResource("../images/ButtonNull.png")).getImage();
		//lButtonImage = new ImageIcon(Main.class.getResource("../images/lButton.png")).getImage();
	
	}
	public void ScreenDraw(Graphics2D g) {

		for(int i=0;i<noteList.size();i++)
		{
			Note note=noteList.get(i);
			if(note.getSize()<=0)
			{	
				scoreEffect = new ImageIcon(Main.class.getResource("../images/particle_Effect.png")).getImage();
				judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			}
			if(!note.isProceeded())
			{
				
				noteList.remove(i);
			}
			else
			{
				note.screenDraw(g);
			}
			note.screenDraw(g);
		}
		
			
		g.drawImage(aButtonImage, Main.ABposX, Main.ABposY, null);
		g.drawImage(sButtonImage, Main.SBposX, Main.SBposY, null);
		g.drawImage(kButtonImage, Main.KBposX, Main.KBposY, null);
		g.drawImage(lButtonImage, Main.LBposX, Main.LBposY, null);
		

		g.drawImage(noteClickAImage, Main.ABposX-150, Main.ABposY-150, null);
		g.drawImage(noteClickSImage, Main.SBposX-150, Main.SBposY-150, null);
		g.drawImage(noteClickKImage, Main.KBposX-150, Main.KBposY-150, null);
		g.drawImage(noteClickLImage, Main.LBposX-150, Main.LBposY-150, null);
		
		
		g.setColor(Color.WHITE);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(titleName, 20, 702);
		g.drawImage(scoreEffect,470, 370, null);
		g.drawImage(judgeImage,470, 430, null);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(Integer.toString(totalScore), 10, 150);
		
	}
	
	 
	@Override
	
	public void run() {
		smallNotes(this.titleName);
		if(!gameMusic.interrupted())
		{
			gameEnd=true;
			R1.start();
			this.close();
		}
		
	}
	
	public void close()
	{
		
		gameMusic.close();
		this.interrupt();
	}
	

	
	public void smallNotes(String titleName)
	{
		
		Beat beats[]=null;

		int rpm=125;
		if(titleName.equals("Ikson-Dreamer")) {
			int startTime=1000-Main.REACH_TIME*1000;
			System.out.println("Ikson-Dreamer");
			beats=new Beat[] {
					new Beat(startTime+rpm*1,"A"),
					new Beat(startTime+rpm*2,"S"),
					new Beat(startTime+rpm*3,"K"),
					new Beat(startTime+rpm*4,"L"),

					new Beat(startTime+rpm*13,"A"),
					new Beat(startTime+rpm*14,"S"),
					new Beat(startTime+rpm*15,"K"),
					new Beat(startTime+rpm*16,"L"),
					
					new Beat(startTime+rpm*17,"A"),
					new Beat(startTime+rpm*18,"S"),
					new Beat(startTime+rpm*19,"K"),
					new Beat(startTime+rpm*20,"L"),

					new Beat(startTime+rpm*21,"A"),
					new Beat(startTime+rpm*22,"S"),
					new Beat(startTime+rpm*23,"K"),
					new Beat(startTime+rpm*24,"L"),
					
					new Beat(startTime+rpm*25,"A"),
					new Beat(startTime+rpm*26,"S"),
					new Beat(startTime+rpm*27,"K"),
					new Beat(startTime+rpm*28,"L"),

					new Beat(startTime+rpm*29,"A"),
					new Beat(startTime+rpm*30,"S"),
					new Beat(startTime+rpm*31,"K"),
					new Beat(startTime+rpm*32,"L"),
					new Beat(60000,"L"),
					new Beat(70000,"END"),
			};
		}
		else if(titleName.equals("RetroVision_Campfire")) {
			int startTime=1000-Main.REACH_TIME*1000;
			beats=new Beat[] {
					new Beat(startTime,"A"),

					new Beat(startTime,"S"),

					new Beat(startTime,"A"),

					new Beat(startTime,"A"),

					new Beat(startTime,"A"),

					new Beat(startTime,"A"),

					new Beat(startTime,"A"),

					new Beat(startTime,"A"),

					new Beat(startTime,"A"),

					new Beat(startTime,"A"),
					
			};
		}
		else if(titleName.equals("Another-time")) {
			int startTime=1000-Main.REACH_TIME*1000;
			beats=new Beat[] {
					new Beat(startTime,"A"),
			};
		}
		int i=0;
		gameMusic.start();
		while(beats.length>i&&!isInterrupted())
		{
			boolean dropped=false;
			if(beats[i].getTime()<=gameMusic.getTime())
			{
				Note note=new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped=true;
			}
			if(!dropped)
			{
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	public void judge(String input)
	{
		for(int i=0;i<noteList.size();i++)
		{
			Note note=noteList.get(i);
			if(input.equals(note.getNoteType()))
			{
				judgeEvent(note.judge());
				break;
			}
		}
	}
	public void judgeEvent(String judge)
	{
		if(!judge.equals("None"))
		{
			scoreEffect = new ImageIcon(Main.class.getResource("../images/particle_Effect.png")).getImage();
		
		}
		if(judge.equals("Perfect"))
		{
			
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
			totalScore+=1000;
		}
		else if(judge.equals("Great"))
		{
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
			totalScore+=1000;
		}
		
		else if(judge.equals("Good"))
		{
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
			totalScore+=1000;
		}
		else if(judge.equals("Bad"))
		{
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeBad.png")).getImage();
			totalScore+=1000;
		}
		else if(judge.equals("Miss"))
		{
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			totalScore+=1000;
		}
	}
}
