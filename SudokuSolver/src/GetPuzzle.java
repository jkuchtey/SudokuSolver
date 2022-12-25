//Created By Jason Kuchtey 12/25/22

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;


public class GetPuzzle {
	
	//Gets filename from user input
	public static String getFileName() {
		boolean valid = false;
		
		Scanner namescan = new Scanner(System.in);
		
		System.out.println("Welcome to Sudoku Solver!");
		System.out.println("Enter a puzzle filename: ");
		String filename = namescan.next();
		namescan.close();
		return filename;
		
	}
	
	//Converts a string into an array of ints
	public static int[] stringToArray(String puzzle) {
		int length = puzzle.length();
		int[] formatted = new int[length];
		char[] ch = puzzle.toCharArray();
		int iter = 0;
		
		for(int i = 0; i < length; i++) {
			if(Character.isDigit(ch[i])){
				int curr = (int)ch[i] - '0';
				formatted[iter] = curr;
				iter++;
			}
		}

		return formatted;
		
	}
	
	

	//Takes a file and converts it into a 1x1 array
	public static int[] getArray(String filename) {
		File file = new File(filename);
		Scanner sc;
		String puzzle = null;
		try {
			sc = new Scanner(file);
			puzzle = sc.next();
			
			while(sc.hasNext()) {
				puzzle += sc.next();
			}
			sc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Wrong Filename");
			getArray(getFileName());
			return null;
		

		}
		
	
		int[] formatted = GetPuzzle.stringToArray(puzzle);
		return formatted;
		
	}
	
	//Converts a 1x1 array into a 2x2 array
	public static int[][] getMatrix(String filename) {
		
		int[] puzzle = getArray(filename);
		int size = puzzle.length;
		int dim = (int) Math.sqrt(size);
		
		int [][]puzzleMatrix = new int[dim][dim];
		int iter = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				puzzleMatrix[i][j] = puzzle[iter];
				iter++;
			}
			
		}
		return puzzleMatrix;
		
	}
	

	
	

	
	public static void main(String[] args) {
		String filename = getFileName();
		System.out.println(filename);
		int puzzleMatrix[][] = getMatrix(filename);

		
		
		
		
	}

	
	
	


}



