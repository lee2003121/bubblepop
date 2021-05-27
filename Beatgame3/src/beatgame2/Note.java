package beatgame2;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
public class Note extends Thread {


	public static Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();

	private int size=(1000/Main.SLEEP_TIME*Main.NOTE_SPEED)*Main.REACH_TIME, x, y, width = 360, height = 360;
	private String Notetype;
	private boolean proceeded=true;
	
	public String getNoteType()
	{
		return Notetype;
	}
	
	public boolean isProceeded()
	{
		return proceeded;
	}
	public void close()
	{
		proceeded=false;
	}

	//Ű ������ ���� x��ǥ��y��ǥ ��
		private int posX()
		{
			if(Notetype=="A")
			{
				x= Main.ABposX-150;
			}
			else if(Notetype=="S")
			{
				x= Main.SBposX-150;
			}
			else if(Notetype=="K")
			{
				x= Main.KBposX-150;
			}
			else if(Notetype=="L")
			{
				x= Main.LBposX-150;
			}
			
			return 0;
		}
		
		private int posY()
		{
			if(Notetype=="A")
			{
				y= Main.ABposY-150;
			}
			else if(Notetype=="S")
			{
				y= Main.SBposY-150;
			}
			else if(Notetype=="K")
			{
				y= Main.KBposY-150;
			}
			else if(Notetype=="L")
			{
				y= Main.LBposY-150;
			}
			return 0;
		}
	public void checkpress(String ButtonType)
	{
		if(Notetype==ButtonType)
		{
			noteBasicImage=new ImageIcon(Main.class.getResource("../images/noteClick.png")).getImage();
		}
	}
	public void checkRelease(String ButtonType)
	{
		if(Notetype==ButtonType)
		{
			noteBasicImage=new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
		}
	}
	

	public Note(String Notetype) {
		if(Notetype=="END")
		{
			System.out.println("끝남");
			BeatFrame.game.gameMusic.close();
		}
		this.Notetype = Notetype;
		posX();
		posY();
	}

	
	public void screenDraw(Graphics2D g) {
		
		g.drawImage(noteBasicImage, x+(width-width * size / 100)/2, y+(height-height * size / 100)/2, width * size / 100, height * size / 100, null);
		
	}
	//��Ʈ �پ���
	public void small() {
		size -=Main.NOTE_SPEED;
		if(size<=0)
		{
			x=9999;
			y=9999;
			close();
		}
	}

	@Override
	public void run() {
		try {
			
			while (true) {
				small();
				if(proceeded)
				{
					BeatFrame.game.totalScore++;
					Thread.sleep(Main.SLEEP_TIME);
				}
				else
				{
					interrupt();
					break;
				}
				Thread.sleep(Main.SLEEP_TIME);
				if(size<=0)
					break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String judge()
	{
		if(size<15)
		{
			
			close();
			return "Late";
		}
		else if(size<15)
		{
			close();
			return "Great";
		}else if(size<26)
		{
			
			close();
			return "Perfect";
		}
		else if(size<35)
		{
			close();
			return "Good";
		}
		else if(size<100)
		{
			close();
			return "Bad";
		}
		else if(size<=0)
		{
			close();
			return "Miss";
		}
		return "None";
	}
	public int getSize()
	{
		return size;
	}

}
