/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8queens;

/**
 *
 * @author Austen
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoardAnalyzer game = new BoardAnalyzer(8);
        
        while(game.getCurrentConflicts() != 0){
            game.printBoardState();
            
            game.findNextMove();
            int numBetter = game.getNumBetterBoards();
            if(numBetter == 0){
                game.reset();
                System.out.println("Reseting\n\n");
                System.out.println(game.getCurrentConflicts());
            }            
            else{
                game.makeNextMove();
                System.out.println("Neighbors with less conflicts: " + numBetter);
                System.out.println("Setting new state\n\n");
            }
        }
        game.printEndStats();
    }
    
}
