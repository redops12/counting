
import java.awt.*;
import java.util.Hashtable;
import javax.swing.*;

public class board extends JFrame
{
    final int PREGAME = 0, INGAME = 1, ENDGAME = 2;
    private int[][] brd;
    int gState = PREGAME;
    board(Difficulty diff) {
        int width;
        int height;
        switch (diff){
            case HARD:
                width = 30;
                height = 16;
                break;
            case MEDIUM:
                width = 16;
                height = 16;
                break;
            case EASY: 
                width = 9;
                height = 9;
                break;
            default:
                width = 30;
                height = 16;
                break;
        }    
        int[][] tbrd = new int[height][width];
        brd = tbrd;
        this.setTitle("Minesweeper");
        this.setSize(width*41+1,height*41+1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public void endGame(){
        gState = ENDGAME;
    }
    
    private void placeMines(Difficulty diff, int startX, int startY){
        int num;
        switch (diff){
            case HARD:
                num = 99;
                break;
            case MEDIUM:
                num = 40;
                break;
            case EASY: 
                num = 10;
                break;
            default:
                num = 99;
                break;
        }
        
        for (int i = 0; i < num; i++){
            int y = (int)(Math.random()*brd.length);
            int x = (int)(Math.random()*brd[0].length);
            int disX = Math.abs(startX-x);
            int disY = Math.abs(startY-y);
            if (brd[y][x] != -1 && disX > 1 && disY > 1)
                brd[y][x] = -1;
            else 
                i--;
        }
    }
}
