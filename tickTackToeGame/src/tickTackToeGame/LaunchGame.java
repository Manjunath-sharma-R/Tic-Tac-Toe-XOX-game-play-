package tickTackToeGame;

import java.util.Random;
import java.util.Scanner;

class TickTacToe
{
	static char[][] board;//2d array of character and it is called a board (only declaration is done)
	
	public TickTacToe() //constructor is to Initialize the object during object creation,came from down and init is called
	{
		board=new char[3][3];//int which store char ,Initialize value of char is u0000(null char)
		initBoard();
	}
	void initBoard() //iterate over the array,when init is called it fills with spaces 
	{
		for(int i=0;i<board.length;i++) //for rows
		{
			for(int j=0;j<board[i].length;j++) //col , read as board of 1 . length
			{
				board[i][j]=' ';//Initialize board with space,after this go up and create initboard() in public tictactoe
			}
		}
	}
	static void displayBoard() 
	{
		System.out.println("------------");
		for(int i=0;i<board.length;i++) //for rows
		{
			System.out.print("|");
			for(int j=0;j<board[i].length;j++) //col 
			{
				System.out.print(board[i][j] +" | ");// first print whats there in column and then attach pipe
			}
			System.out.println();//curser should come to next line
			System.out.println("------------");
		}
	}
	static void placeMark(int row,int col, char mark) 
	{
		if (row >=0 && row<=2 && col>=0 && col<=2) {
			board[row][col]=mark;
		}
		else {
			System.out.println("invalied position");
		}
	}
	static boolean checkColWin() 
	{
		for(int j=0;j<=2;j++) 
		{
			if (board[0][j]!=' '&&  board[0][j]==board[1][j] && board[1][j]==board[2][j] ) 
			{
				return true;
			}
			
		}
		return false;
	}
	static boolean checkRowWin() 
	{
		for(int i=0;i<=2;i++) 
		{
			if (board[i][0]!=' '&&  board[i][0]==board[i][1] && board[i][1]==board[i][2] ) 
			{
				return true;
			}
			
		}
		return false;
	}
	static boolean checkDigWin() {
		
		if (  board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] ||board[0][2]!=' '&& board[0][2]==board[1][1] && board[1][1]==board[2][0] ) 
		{
			return true;
		}
		return false;
	}
	static boolean chechDraw() 
	{
		for(int i=0;i<=2;i++) 
		{
			for(int j=0;j<=2;j++) 
			{
				if (board[i][j]==' ') 
				{
					return false;
				}
			}
		}
		return true;
	}

}

abstract class Player
{
	String name;
	char mark;
	
	abstract void makeMove();
	
	boolean isValidMove(int row,int col) 
	{
		if (row>=0 && row<=2 && col>=0 && col<=2) 
		{
			if (TickTacToe.board[row][col]==' ') 
			{
				return true;
			}
		}
		return false;
	}
}

class HumanPlayer extends Player
{
	
	HumanPlayer(String name,char mark)// par constructor
	{
		this.name=name;
		this.mark=mark;
	}
	void makeMove() 
	{
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		
		do 
		{
			System.out.println("enter row and column ");
			 row=scan.nextInt();
			 col=scan.nextInt();
		}
		while(!isValidMove(row,col)) ;//above condition should be repeating as long as the isValidMove  is true  
		
		TickTacToe.placeMark(row, col, mark);//as soon as is valid it place a mark
		
	}
	
	
}
class AIPlayer extends Player
{
	
	AIPlayer(String name,char mark)// par constructor
	{
		this.name=name;
		this.mark=mark;
	}
	void makeMove() 
	{
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		
		do 
		{
			/*System.out.println("enter row and column ");
			 row=scan.nextInt();
			 col=scan.nextInt();*/
			Random r=new Random();
			row =r.nextInt(3);
			col=r.nextInt(3);
		}
		while(!isValidMove(row,col)) ;//above condition should be repeating as long as the isValidMove  is true  
		
		TickTacToe.placeMark(row, col, mark);//as soon as is valid it place a mark
		
	}
	
	
}


public class LaunchGame {

	public static void main(String[] args) {
		
		TickTacToe t=new TickTacToe();//object is created constructor is called ,go up
		
		HumanPlayer p1=new HumanPlayer("bob",'x');
		//HumanPlayer p2=new HumanPlayer("priya",'o');
		AIPlayer p2= new AIPlayer("TAI", 'o');

		Player cp;
		cp=p1;
		
		while (true) {
			System.out.println(cp.name +"turn");
			cp.makeMove();
			TickTacToe.displayBoard();
			if (TickTacToe.checkColWin()||TickTacToe.checkRowWin()||TickTacToe.checkDigWin()) 
			{
				System.out.println(cp.name +"has WON");
				break;
			}
			else if (TickTacToe.chechDraw()) 
			{
				System.out.println("Game is a Draw");
				break;
			}
			else {
				if (cp==p1) {
					cp=p2;
				}
				else {
					cp=p1;
				}
			}
		}
		
	}

}
