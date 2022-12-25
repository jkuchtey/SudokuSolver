//Created By Jason Kuchtey 12/25/22


class CompletePuzzle {
	//The final solution
	public static int[][] complete = new int[9][9];
	
	
	//checks if a row contains a certain number, not including the specified square.
	public static boolean checkRow(int[][] puzzle, int row, int col, int k) {		
		int check[][] = puzzle;
		check[row][col] = k;
		
		
		int iter = 0;
		for(int i = 0; i < 9; i++) {
			if(check[row][iter] == k) {
				if(iter != col) {
					return false;
				}
			}
			iter++;
		}
		return true;
		
		
	}
	
	//checks if a column contains a certain number, not including the specified square.
	public static boolean checkCol(int[][] puzzle, int row, int col, int k) {
		int check[][] = puzzle;
		check[row][col] = k;
		
		int iter = 0;
		for(int i = 0; i < 9; i++) {
			if(k == check[iter][col]) {
				if(iter != row) {
					return false;
				}
			}
			iter++;
		}
		
		return true;
		
		
	}
	
	//checks if the 3x3 box containing a specified square contains a certain number
	public static boolean checkBox(int[][] puzzle, int row, int col, int k) {
		int check[][] = puzzle;
		check[row][col] = k;
		int startRow = Math.floorDiv(row, 3) * 3;
		int startCol = Math.floorDiv(col, 3) * 3;


		
		
		int iter = 0;
		for(int r = startRow; r < (startRow + 3); r++) {
			for(int c = startCol; c < (startCol + 3); c++) {



				if(r != row || c != col) {
					if(check[r][c] == k) {

						return false;
					}
				}


			}
		}
		return true;
		
	}
	
	//Uses the above 3 functions to check the validity of a given number at a given square
	public static boolean isValid(int[][] puzzle, int row, int col, int i) {
		
		if(checkRow(puzzle, row, col, i) && checkCol(puzzle, row, col, i) && checkBox(puzzle, row, col, i)) {
			return true;
		}
		return false;
		
	}
	
	//Prints a given puzzle in a readable format
	public static void printPuzzle(int[][] puzzle) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(j != 2 && j != 5) {
					System.out.print(puzzle[i][j] + " ");
				}
				else {
					System.out.print(puzzle[i][j] + "|");

					
				}

				
			}
			if(i != 2 && i != 5) {
				System.out.println("");
			}
			else {
				System.out.println("\n" + "-----|-----|-----");
			}
		}
	}
	
	
	//Solves the given puzzle via recursive backtracking
	public static boolean solve(int[][] puzzle, int r, int c){
		
		if(r == 9) {
			
			return true;
		}
		else if(c == 9) {
			return solve(puzzle, r + 1, 0);
		}
		else if(puzzle[r][c] != 0) {
			return solve(puzzle, r, c+1);
		}
		else {
			for(int i = 1; i < 10; i++) {
				if(isValid(puzzle, r, c, i)) {
					puzzle[r][c] = i;
					
					if(solve(puzzle, r, c+1)) {
						return true;
					}
				}	
				puzzle[r][c] = 0;

			}
			return false;
		}
		
	}
	


	
	public static void main(String[] args) {
		
		//Gets the puzzle and creates the 2x2 matrix
		String filename = GetPuzzle.getFileName();
		int puzzle[][] = GetPuzzle.getMatrix(filename);
		
		//Sets the class variable to the given puzzle and prints out the unsolved version
		complete = puzzle;
		System.out.println("Unsolved Puzzle: " + "\n");
		printPuzzle(complete);
		System.out.println("\n");

		
		//Solves the puzzle and prints it out
		solve(complete, 0, 0);
		System.out.println("Solved puzzle: " + "\n");
		printPuzzle(complete);
		
		
	}
	

}
