package beatgame2;

public class Result	extends Thread {

	int Score;
	public static int ShowScore=0;
	public void run()
	{
		int i=1;
		Score=BeatFrame.game.totalScore;
		ShowScore=0;
		
		while (ShowScore != Score) {
			System.out.println(ShowScore);
			try {
				
				if (Score % (i * 10) != ShowScore % (i * 10)) {
					ShowScore += i;
				} else {
					i = i * 10;
				}
				Thread.sleep(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("종료됨");
		this.interrupt();
		
	}
}
