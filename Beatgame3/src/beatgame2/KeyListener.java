package beatgame2;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{
	@Override
	public void keyPressed(KeyEvent e) {
		if(BeatFrame.game==null||BeatFrame.game.isInterrupted())
		{
			return;
		}
		if(e.getKeyCode()==KeyEvent.VK_A)
		{
			BeatFrame.game.pressA();
		}
		if(e.getKeyCode()==KeyEvent.VK_S)
		{
			BeatFrame.game.pressS();
		}
		if(e.getKeyCode()==KeyEvent.VK_K)
		{
			BeatFrame.game.pressK();
		}
		if(e.getKeyCode()==KeyEvent.VK_L)
		{
			BeatFrame.game.pressL();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(BeatFrame.game==null)
		{
			return;
		} 
		if(e.getKeyCode()==KeyEvent.VK_A)
		{
			BeatFrame.game.releaseA();
		}
		if(e.getKeyCode()==KeyEvent.VK_S)
		{
			BeatFrame.game.releaseS();
		}
		if(e.getKeyCode()==KeyEvent.VK_K)
		{
			BeatFrame.game.releaseK();
		}
		if(e.getKeyCode()==KeyEvent.VK_L)
		{
			BeatFrame.game.releaseL();
		}
	}
}
