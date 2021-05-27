package beatgame2;

/**
 * 
 * 메인 클래스. 게임의 기본 설정 초기화 주요 기능
 * <ol>
 * <li>프로그램 시작</li>
 * <li>해상도 SCREEN_WIDTH(너비)와 SCREEN_HEIGHT(높이)를 1280x720으로 초기화</li>
 * <li>NOTE_SPEED (판정노트)의 속도 초기화</li>
 * <li>SLEEP_TIME(판정노트)의 줄어듬 빈도 초기화</li>
 * <li>REACH_TIME(판정노트)의 시간 균일하게 초기화</li>
 * <li>posX,posY(노트)의 위치 초기화</li>
 * </ol>
 * 
 * @author lee jong wan
 * @version 1.0.0
 * 
 *
 */
public class Main {

	/**
	 * 해상도x
	 */
	public static final int SCREEN_WIDTH = 1280;
	/**
	 * 해상도y
	 */
	public static final int SCREEN_HEIGHT = 720;
	/**
	 * 노트가 줄어드는 속도
	 */
	public static final int NOTE_SPEED = 1;
	/**
	 * 노트가 줄어드는 시간
	 */
	public static final int SLEEP_TIME = 10;
	/**
	 * 게임 시작 시간 조절 변수
	 */
	public static final int REACH_TIME = 1;

	/**
	 * 노트A의 X좌표
	 */
	public static int ABposX = 250;// a button X position
	/**
	 * 노트A의 Y좌표
	 */
	public static int ABposY = 300;// a button Y position
	/**
	 * 노트S의 X좌표
	 */
	public static int SBposX = 500;
	/**
	 * 노트S의 Y좌표
	 */
	public static int SBposY = 300;
	/**
	 * 노트K의 X좌표
	 */
	public static int KBposX = 750;
	/**
	 * 노트K의 Y좌표
	 */
	public static int KBposY = 300;
	/**
	 * 노트L의 X좌표
	 */
	public static int LBposX = 1000;
	/**
	 * 노트L의 Y좌표
	 */
	public static int LBposY = 300;

	/**
	 * 시작 지점
	 * @param args 메인기본값
	 */
	public static void main(String[] args) {

		new BeatFrame();

	}
}
