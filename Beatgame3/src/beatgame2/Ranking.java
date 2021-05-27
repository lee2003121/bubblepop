package beatgame2;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Ranking extends JFrame{
	private JTextArea textArea;
	private JPanel bigPanel;
	public Ranking()
	{
		textArea=new JTextArea();
		bigPanel=new JPanel();
		textArea.setFont(new Font("굴림체",Font.BOLD,20));
		bigPanel.setLayout(new BorderLayout());
		bigPanel.add(textArea,BorderLayout.CENTER);
		add(bigPanel);
		textArea.setEditable(false);
		setSize(400,400);
	}
	public void showRank()
	{
		setVisible(true);
	}
	public void drawRank()
	{
		textArea.setText(BeatFrame.jdbc.getInfo()); 
	}
	
}
