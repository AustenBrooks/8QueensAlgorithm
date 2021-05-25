/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8queens;

import java.util.Random;

/**
 *
 * @author Austen
 */
public class BoardAnalyzer {
    
    private Board currentState;
    private int currentConflicts;
    private int numQueens;
    private int numBetterBoards;
    private Board nextState;
    private int numResets;
    private int numChanges;
    
    public BoardAnalyzer(int numQueens){
        this.numQueens = numQueens;
        reset();
        currentConflicts = currentState.countConflicts();
        numBetterBoards = 0;
        numResets = 0;
        numChanges = 0;
    }
    
    public void reset(){
        ++numResets;
        currentState = new Board(numQueens);
        Random r = new Random();
        nextState = null;
        numBetterBoards = 0;
        for(int col=0; col<numQueens; col++){
            int row = r.nextInt(numQueens);
            currentState.placeQueen(row, col);
        }
        currentConflicts = currentState.countConflicts();
    }

    public int getCurrentConflicts() {
        return currentConflicts;
    }

    public int getNumBetterBoards() {
        return numBetterBoards;
    }
    
    public void findNextMove(){
        for(int col=0; col<numQueens; col++){
            int currentRow = currentState.findQueenRow(col);
            for(int i=0; i<numQueens; i++){
                if(i == currentRow){}
                else{    
                    Board tempState = new Board(currentState);
                    tempState.moveColumnQueen(col,i);
                    if(tempState.countConflicts() < currentConflicts){
                        numBetterBoards++;
                        if(numBetterBoards == 1){
                            nextState = new Board(tempState);
                        }
                        else if(tempState.getConflicts() < nextState.getConflicts()){
                            nextState = new Board(tempState);
                        }
                    }
                }
            }
        }
    }
    
    public void makeNextMove(){
        ++numChanges;
        currentState = new Board(nextState);
        numBetterBoards = 0;
        nextState = null;
        currentConflicts = currentState.getConflicts();
    }
    
    public void printBoardState(){
        System.out.println("Current conflicts: " + currentConflicts);
        System.out.println("Current State");
        System.out.println(currentState.boardToString());
    }
    
    public void printEndStats(){
        System.out.println("Current State");
        System.out.println(currentState.boardToString());
        System.out.println("Solution Found!");
        System.out.println("State changes: " + numChanges);
        System.out.println("Restarts: " + numResets);
    }
}
