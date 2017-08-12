import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.MouseInfo;
public class Main {

	static Robot clicker;
	static int pos1x = 345;
	static int pos1y = 704;
	static int pos2x = 586;
	static int pos2y = 701;
	static int pos3x = 388;
	static int pos3y = 881;
	static int pos4x = 745;
	static int pos4y = 870;
	static int waitingCount = 0;
	static boolean running = true;
	
    
	public static void main(String[] args) throws AWTException, InterruptedException {
		
		System.out.println("Quizup Bot Version 1.2");
		System.out.println("Written by Evan Greavu - January 2016\n");
		System.out.println("Instructions:\nClose all Windows.\nClose this window to stop.\n");
		System.out.println("Note: This bot will take over the computer,\nand you will be unable to use it until you close this box.");
		System.out.println("\nPress enter when you are ready...\n");
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		System.out.println("Bot initiating in 3...\nMinimize this window (don't close it until you're done.)");
		Thread.sleep(3000);
		clicker = new Robot();
		System.out.println("Bot initiated. Loading Quizup page...");
		int count = 0;
		Thread.sleep(1000);
		searchHotkey();
		Thread.sleep(1000);
		typer("google chrome");
		enter();
		Thread.sleep(2000);
		clicker.keyPress(KeyEvent.VK_BACK_SPACE);
		typer("www.quizup.com/topics/name-the-pokemon/");
		clicker.keyPress(KeyEvent.VK_BACK_SPACE);
		enter();
		Thread.sleep(1000);
		clicker.keyPress(KeyEvent.VK_ALT);
		clicker.keyPress(KeyEvent.VK_SPACE);
		Thread.sleep(200);
		clicker.keyPress(KeyEvent.VK_X);
		Thread.sleep(200);
		clicker.keyRelease(KeyEvent.VK_ALT);
		clicker.keyRelease(KeyEvent.VK_SPACE);
		clicker.keyRelease(KeyEvent.VK_X);
		Thread.sleep(2000);
		while (running) {
			Thread.sleep(1000);
			String state = getState();
			System.out.println("Tick. Count: " + count + ". State: " + state);
			if (state.equals("State1")){ //Not right now
				move(1027, 703);
				leftClick();
			}else if (state.equals("State2")){ //Play now
				move(908, 420);
				leftClick();				
			}else if (state.equals("State3")){ //Answer a question
				
				int rando = ((int) (Math.random() * 4)) + 1;
				if (rando == 1){
					move(pos1x, pos1y);
				}else if (rando == 2){
					move(pos2x, pos2y);
				}else if (rando == 3){
					move(pos3x, pos3y);
				}else if (rando == 4){
					move(pos4x, pos4y);
				}
				leftClick();				
			}else if (state.equals("State4")){ //Play again
				move(906, 888);
				leftClick();				
			}else if (state.equals("State5")){ //Alt tab warning
				move(1088, 510);
				leftClick();
			}else if (state.equals("State6")){ //Network error
				move(1856, 122);
				leftClick();
			}else if (state.equals("State7")){ //Home screen of name that pokemon
				move(641, 385);
				leftClick();
			}else if (state.equals("State8")){ //API Limit
				Thread.sleep(15000);
				move(1858, 123);
				leftClick();
			}else if (state.equals("State9")){ //Opponent left
				move(807, 692);
				leftClick();
			}else if (state.equals("State10")){ //Searching for opponent
				waitingCount ++;
				if (waitingCount >= 120){
					System.out.println("Searching for too long. F5'ing.");
					f5();
				}
			}
			count++;
		}
		scan.close();
	}
	
	public static void fastmove(int a, int b){
		clicker.mouseMove(a, b);
	}
	
	public static void typer(String string) throws InterruptedException {
		String[] list = string.split("");
		for (String c : list){
			Thread.sleep(100);
			int charid = (int) c.charAt(0);
			if (charid == 32){ // ' '
			}else if (charid == 46){ // .
				charid = KeyEvent.VK_PERIOD;
			}else if (charid == 47){ // /
				charid = KeyEvent.VK_SLASH;
			}else if (charid == 45){
				charid = KeyEvent.VK_MINUS; // -
			}else{
				charid -= 32;
			}
			clicker.keyPress(charid);
			clicker.keyRelease(charid);
		}
	}
	
	public static void move(int a, int b) throws InterruptedException{
		waitingCount = 0;
		int x = MouseInfo.getPointerInfo().getLocation().x;
		int y = MouseInfo.getPointerInfo().getLocation().y;
		int operationx = 0, operationy = 0;
		if (x > a){
			operationx = -1;
		}else if (x < a){
			operationx = 1;
		}
		if (y < b){
			operationy = 1;
		}else if (y > b){
			operationy = -1;
		}
		boolean locationmet = false;
		while (! locationmet){
			x += operationx;
			y += operationy;
			clicker.mouseMove(x, y);
			if (MouseInfo.getPointerInfo().getLocation().x == a){
				operationx = 0;
			}
			if (MouseInfo.getPointerInfo().getLocation().y == b){
				operationy = 0;
			}
			if (MouseInfo.getPointerInfo().getLocation().x == a && MouseInfo.getPointerInfo().getLocation().y == b){
				locationmet = true;
			}
		}
	}
	
	public static void searchHotkey() throws InterruptedException {
		clicker.keyPress(KeyEvent.VK_WINDOWS); 
		Thread.sleep(200);
		clicker.keyRelease(KeyEvent.VK_WINDOWS);
	}
	
	public static void f5(){
		waitingCount = 0;
		clicker.keyPress(116);
		clicker.keyRelease(116);
	}
	
	public static void f6(){
		waitingCount = 0;
		clicker.keyPress(117);
		clicker.keyRelease(117);
	}
	
	public static void enter() throws InterruptedException {
		clicker.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(100);
		clicker.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public static void leftClick() throws InterruptedException {
		waitingCount = 0;
		Thread.sleep(250);
		clicker.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(50);
		clicker.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public static Color getColor(int a, int b){
		return clicker.getPixelColor(a, b);
	}
	
	public static String getState(){
		String finalState = "supererror";
		Color state = getColor(1027, 703);
		int red = state.getRed();
		int green = state.getGreen();
		int blue = state.getBlue();
		if (red == 153 && green == 50 && blue == 50){
			finalState = "State1"; //Not right now
			return finalState;
		}
		state = getColor(908, 420);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if (red == 255 && green == 255 && blue == 255){
			finalState = "State2"; //Play button
			return finalState;
		}
		state = getColor(936, 140);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if (red == 0 && green == 109 && blue == 130){
			finalState = "State3"; //Answering
			return finalState;
		}
		state = getColor(906, 888);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if (red == 255 && green == 84 && blue == 84){
			finalState = "State4"; //Play button
			return finalState;
		}
		state = getColor(822, 515);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if (red == 255 && green == 255 && blue == 255){
			finalState = "State5"; //Tab warning
			return finalState;
		}
		state = getColor(800, 706);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if (red == 0 && green == 109 && blue == 130){ //Opponent left
			finalState = "State9";
			return finalState;	
		}
		state = getColor(173, 100);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if (red == 255 && green == 84 && blue == 84){ //Home screen
			finalState = "State7";
			return finalState;
		}
		state = getColor(927, 1009);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if (red == 153 && green == 77 && blue == 31){ //API Limit
			finalState = "State8";
			return finalState;
		}
		state = getColor(827, 340);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if ((red == 2 && green == 209 && blue == 116) || (red == 255 && green == 84 && blue == 84) || (red == 255 && green == 255 && blue == 255)){ //Network error
			finalState = "State6";
			return finalState;
			
		}
		state = getColor(721, 924);
		red = state.getRed(); green = state.getGreen(); blue = state.getBlue();
		if ((red == 0) && (green == 109) && (blue == 130)){ //Searching 
			finalState = "State10";
			return finalState;
		}else{
			System.out.println("No state. Color: " + red + ", " + green + ", " + blue);
			finalState = "error";
			return finalState;
		}
	}
}
