/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8queens;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Austen
 */
public class Board {
    private int numQueens;
    private int[][] boardArray;
    
    private int conflicts;

    public Board(int numQueens) {
        this.numQueens = numQueens;
        boardArray = new int[numQueens][numQueens];
    }
    
    public Board(Board copy){
        this.numQueens = copy.numQueens;
        this.boardArray = new int[numQueens][numQueens];
        for(int i=0; i<numQueens; i++){
            for(int j=0; j<numQueens; j++){
                boardArray[i][j] = copy.boardArray[i][j];
            }
        }
        countConflicts();
    }
    
    public int countConflicts(){
        conflicts = 0;
        for(int i=0; i<numQueens; i++){
            for(int j=0; j<numQueens; j++){
                if(boardArray[i][j] == 1)
                    conflicts += checkConflicts(i, j);
            }
        }
        return conflicts;
    }
    
    private int checkConflicts(int row, int col){
        int count = 0;
        
        //check horizontal squares to the right
        for(int j = col+1; j<numQueens; j++){
            if(boardArray[row][j] == 1)
                ++count;
        }
        
        //check diagnol squares to the right
        for(int j = 1; j<numQueens - col; j++){
            int upRow = row - j;
            int downRow = row + j;
            
            if(upRow >= 0){
                if(boardArray[upRow][j+col] == 1)
                    ++count;
            }
            
            if(downRow < numQueens){
                if(boardArray[downRow][j+col] == 1)
                    ++count;
            }
        }
        return count;
    }

    public int getConflicts() {
        return conflicts;
    }
    
    public void placeQueen(int row, int col){
        boardArray[row][col] = 1;
    }
    public int findQueenRow(int column){
        for(int i=0; i<numQueens; i++){
            if(boardArray[i][column] == 1){
                return i;
            }
        }
        return -1;
    }
    
    public void moveColumnQueen(int column, int row){
        int oldRow = findQueenRow(column);
        boardArray[oldRow][column] = 0;
        boardArray[row][column] = 1;
    }
    
    public String boardToString(){
        String res = "";
        for(int i=0; i<numQueens; i++){
            for(int j=0; j<numQueens; j++){
                res += boardArray[i][j] + ",";
            }
            res += "\n";
        }
        return res;
    }
}
