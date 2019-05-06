package dev.adiswami.minimax;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class MinMax {
	final int[] scores = {10,-10,0, -1};
	final int Human = 1, Comp = 0, Tie = 2, Empty = 3;
	boolean player = true;
	Scanner reader = new Scanner(System.in);
	int[] a = new int [9];
	//int[][] a =  {{1,0,2},{0,1,2},{2,1,2}};

	public MinMax()
	{
		initA();
		while(!gameOver())
		{
			set();
			pos();
			compMove();
		}
		set();
	}

	public int minimax(int k)
	{	int bests = 0;
		
		 //use the opposite end to ensure 1st score must be overwritten as your bestscore
	    if(k==Human)
	 		bests = scores[Comp];  
		else bests = scores[Human];
		
		if(gameOver())
			return checkWin();  // -10, 10, 0
		
		for(int x=0;x<9;x++)
		{

			   if(a[x] == Empty)
			   {
			       a[x] = k;  // place next guess
			       int current =0;
			   	   if(k==Human)
			   	   	current = minimax(Comp);
			   	   else current = minimax(Human);
					a[x] = Empty;   // remove place
			   	   if(current>bests && k==Comp)
			   	   	   bests = current;
			   	   if(current<bests && k==Human)
			   		   bests = current;
			   }
			}
		
		return bests;
	}
	public void compMove()
	{
		int bestscore=-100;
		int score =0;
		int bestmovex=0;
		for(int x=0;x<9;x++)
		{
				if(a[x]==Empty)
				{	
					a[x] = Comp;
					score = minimax(Human);
					if(score>bestscore)
					{
						bestmovex = x;
						bestscore = score;
					}
					a[x]= Empty;
				}
		}
		a[bestmovex]=Comp;
	}
	
	public void initA()
	{
		for(int x=0;x<a.length;x++)
		{
			a[x]=Empty;
		}
	}
	
	public int checkWin()
	{
		int emptyc=0;
		if((a[0] == Human && a[1] == Human && a[2] == Human) ||(a[3] == Human && a[4] == Human && a[5] == Human)||(a[6] == Human && a[7] == Human && a[8] == Human))
			return scores[Human];
		else if((a[0] == Comp && a[1] == Comp && a[2] == Comp) ||(a[3] == Comp && a[4] == Comp && a[5] == Comp)||(a[6] == Comp && a[7] == Comp && a[8] == Comp))
			return scores[Comp];
		else if((a[0] == Human && a[3] == Human && a[6] == Human)||(a[1] == Human && a[4] == Human && a[7] == Human)||(a[2] == Human && a[5] == Human && a[8] == Human))
			return scores[Human];
		else if((a[0] == Comp && a[3] == Comp && a[6] == Comp)||(a[1] == Comp && a[4] == Comp && a[7] == Comp)||(a[2] == Comp && a[5] == Comp && a[8] == Comp))
			return scores[Comp];
		else if ((a[0] == Human && a[4] == Human && a[8] == Human)||(a[2] == Human && a[4] == Human && a[6] == Human))
			return scores[Human];
		else if((a[0] == Comp && a[4] == Comp && a[8] == Comp)||(a[2] == Comp && a[4] == Comp && a[6] == Comp))
			return scores[Comp];
		for(int x=0;x<9;x++)
		{
			if(a[x] == Empty)
				emptyc++;
		}
		if(emptyc==0)
			return scores[Tie];
		return scores[Empty];
	}
	
	public boolean gameOver()
	{
		return (checkWin()!=scores[Empty]);
	}
	
	public void set()
	{
		System.out.println(drawer(0)+"  |"+drawer(1)+"  |"+drawer(2)+"  ");
		System.out.println("-- -- -- --");
		System.out.println(drawer(3)+"  |"+drawer(4)+"  |"+drawer(5)+"  ");
		System.out.println("-- -- -- --");
		System.out.println(drawer(6)+"  |"+drawer(7)+"  |"+drawer(8)+"  ");
	}
	
	public int pos()
	{
		System.out.println("Which position do you desire(0-8)?");
		int s = reader.nextInt();
		while((s>8 || s<0)|| check(s) == false)
		{
			System.out.println("Which position do you desire(0-8)?");
			int k = reader.nextInt();
			s=k;
		}
		return s;
	}

	public boolean check(int s)
	{
		if(player && checkFilled(s) == -1)
		{
			a[s] = Human;
			return true;
		}
		else if(!player && checkFilled(s) == -1)
		{
			a[s] = Comp;
			return true;
		}
		return false;
	}
	public int checkFilled(int x)
	{
		if(a[x] ==Human)
			return 1;
		else if(a[x] ==Comp)
			return 0;
		else return -1;
	}
	public char drawer(int x)
	{
		if(a[x] == Human)
		{
			return 'X';
		}
		else if(a[x] ==Comp)
			return 'O';
		else return ' ';
	}
	public static void main(String[] args)
	{
		MinMax app = new MinMax();
	}
}
