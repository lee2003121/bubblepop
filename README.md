
# 리듬게임(bubble pop)

## 프로젝트 주제

노래에 주어진 박자를 활용해 더 정확하게 노트를 클릭하여 점수를 얻고 자신의 점수를 랭킹에 올림으로 인해 다른 플레이어들과 경쟁을 하게어 게임의 몰입과 흥미를 상승시킨다.

![title](https://user-images.githubusercontent.com/60810332/119941229-49103f80-bfcb-11eb-982c-025464accacd.png)
![musicSelect](https://user-images.githubusercontent.com/60810332/119941233-4a416c80-bfcb-11eb-878f-efa90b06ded4.png)
![ingame](https://user-images.githubusercontent.com/60810332/119941238-4ada0300-bfcb-11eb-8d3d-f5d4b348dba0.png)
![점수 랭크](https://user-images.githubusercontent.com/60810332/119941240-4b729980-bfcb-11eb-908a-552f36f80de1.png)
![결과창](https://user-images.githubusercontent.com/60810332/119941242-4c0b3000-bfcb-11eb-9029-4ca700808107.png)

## 개발환경

JDK 15.0.1

IDE : Eclipse 2020_12

DB :  MariaDB 10.5.6


## 주요기능

- db에 자신의 이름과 기록 저장
- 비트에 따른 노트생성
- 노트의 정확성 판정
- db에 있는 사용자 랭킹 조회
- 특정 시간에 정확하게 노트 생성
- 노래 선택 기능
- 노래에 따른 비트 생성

## 시연영상

<div>
	<a href=https://youtu.be/PymbmaYfkkQ"><image src ="https://user-images.githubusercontent.com/60810332/119941229-49103f80-bfcb-11eb-982c-025464accacd.png")
"></a>

</div>

## 도큐먼트
![실행 방향](https://user-images.githubusercontent.com/60810332/119940595-655fac80-bfca-11eb-8faa-957d094daf72.png)
[Javadoc](https://lee2003121.github.io/bubblepop/Beatgame3/doc/index.html)

## 프로젝트의 특장점

1. 멀티 스레딩을 통해 여러 기능을 동시에 수행

   곡 플레이 스레드/점수 등록(확인) 쓰레드/노트 쓰레드/게임 스레드/총괄 스레드

2. 더블 버퍼링을 통한 자연스러운 그래픽 표현

   ```java
   screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
   screenGraphic = screenImage.getGraphics();
   ```

3. 데이터 베이스를 활용해 자신의 기록을 조회,등록 

   ```java
   //등록
   sql ="INSERT INTO Beat_Game(name,`score`) VALUES(?,?)";
   ps = conn.prepareStatement(sql);
   ps.setString(1, name);
   ps.setInt(2, score);
   			
   ps.execute();
   //조회
   ps = conn.prepareStatement("SELECT 	name,`score` FROM Beat_Game ORDER BY `score` DESC");		
   rs = ps.executeQuery();
   ```

   - 게임이 끝난후 자신의 점수를 기록한다

   ```java
   name = JOptionPane.showInputDialog("최종 점수:" + Score + "\n" + "저장하실 이름을 입력하세요","unknown");
   ```

   



## 어려웠던 점

### 1.총괄 스레드에에서 모든 스레드를 관리하는 것

총괄 스레드에서 모든 스레드에 관한 내용을 관리하고 전달하는 과정을 처음 해보다보니 

각자의 스레드에서 다른 스레드에게 내용을 전달하고 싶을때 총괄 스레드를 거치고 가는 과정이 헷갈렸다.

->시간이 지난후 다른 스레드에 대해 익숙해진 후 따로 관리하는 것보도 총괄 스레드가 있음으로 인해 더 편해진 느낌이였다.

### 2.다양한 음악을 관리하고 선택한 음악을 게임에서 실행해야하는 어려움

모든음악을 따로 관리하고 일일이 음악을 바꿀때마다 게임에 넣을 음악을 바꾸고 해당음악에 따른 비트또한 호출해줘야 하는 것이 번거로웠으나 music에대해 관리하는 클래스(스레드)를 만들어 관리 함으로서 간편하게 음악을 바꿀 수 있도록 만들었다.

### 3.음악에 관한 비트

선택한 음악에 따라 일일이 리듬별로 비트를 찍고 비트를 음별로 4개의 키로 바꿔주고 확인까지 하는 과정이 번거롭고 하나의 리듬이라도 빠르거나 늦으면 안되기 때문에 힘들었다.

### 4.joptionpane.showinputdialog 입력 안함

저장할떄 이용한 joptionpane.showinputdialog에 아무 값을 않넣고 확인을 눌렀을 시 null값이 반환되는것이 아닌 ""값이 반환되기 때문에 db에서 ""의 값을 unknown이라는 단어로 바꿔주기 어려워서 java에 입력을 받을때에 바꾸어주었다.

## 앞으로 개선할 것들

### 1.여러곡을 넣어주어 게임의 다양성을 높이고 easy나 hard모드 등 다양한 모드를 넣어준다.

노트의 좌표를 바꾸거나 비트를 꾹누르는등 다양한 노트의 종류를 추가 하고자 하였지만,시간상의 문제로 구현하지 못하였고,main에있는 좌표의 지점을 바꿔줌으로서 만들수 있다.

## 2.콤보에 관한 추가 점수

## 3.이팩트 추가

 리듬게임의 경우 리듬에 따라 키보드를 치는 재미도있지만 이팩트나 올라가는 점수에서 쾌감을 느끼는 경우가 대부분이다. 이에 대해 플레이어들에게 게임의 흥미를 높여주기 위해 다양한 이팩트와 콤보를 추가해야한다. 

## 2.통신을 이용한 대결

socket통신을 활용해 1대1 대결을 넣고 해당 곡에 대한 리듬을 나누어주면 혼자 플레이를 할때보다 리듬이 더많은 곡을 줌으로서 곡의 다양성을 높일수 있고 합주가 가능해진다.



