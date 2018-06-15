import java.util.Scanner;
import java.text.*;
import java.io.*;
import java.awt.*;

class main{
    private static boolean[][] cpy(boolean[][] map){
        boolean[][] tempMap = new boolean[map.length][map[0].length];
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j< map[0].length; j++){
                    tempMap[i][j] = map[i][j];
            }
        }
        return tempMap;
    }
    
    private static void print(boolean[][] a){
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a[i].length; j++){
                if (a[i][j]){
                    System.out.print("#");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
    
    private static boolean[][] readIn(String fileName)  throws IOException{
        Scanner inFile = new Scanner(new FileReader(fileName));
        int y = inFile.nextInt();
        int x = inFile.nextInt();
        inFile.nextLine();
        boolean[][] map = new boolean[y][x];
        for (int i = 0; i < y; i++){
            String temp = inFile.nextLine();
            for (int j = 0; j < temp.length(); j++){
                map[i][j] = temp.charAt(j) != '0';
            }
        }
        return map;
    }
    
    private static boolean[][] chk(boolean[][] map, int y, int x){
        boolean[][] tempMap = cpy(map);
        if (tempMap[y][x]) {
            tempMap[y][x] = false;
            //print(map);
            if (y < map.length-1)
                tempMap = chk(tempMap, y+1, x);
            if (y > 0)
                tempMap = chk(tempMap, y-1, x);
            if (x < map[y].length-1)
                tempMap = chk(tempMap, y, x+1);
            if (x > 0)
                tempMap = chk(tempMap, y, x-1);
        }
        return tempMap;
    }
    
    public static void main(String fileName) throws IOException{
        boolean[][] map = readIn(fileName);
        System.out.println(countStars(map));
    }
    
    public static int countStars(boolean[][] map){
        //print(map);
        int numStars = 0;
        boolean[][] tempMap = cpy(map);
        for (int i = 0; i < tempMap.length; i++){
            for (int j = 0; j < tempMap[i].length; j++){
                if(tempMap[i][j]){
                    // System.out.println("map b4");
                    // print(map);
                    // System.out.println("temp map before");
                    // print(tempMap);
                    numStars++;
                    tempMap = chk(tempMap, i, j);
                    // map[0][0] = true;
                    // System.out.println("map");
                    // print(map);
                    // System.out.println("temp map");
                    // print(tempMap);
                }
            }
        }
        //print(map);
        return numStars;
    }
    
    static public void makeImage(){
        int x = (int) (Math.random()*80) + 10;
        int y = (int) (x+((Math.random()-1.0)*10));
        // System.out.println(x + "," + y);
        int totalStars = (int) (Math.random()*x*y/20.0) + 1;
        // totalStars = 2;
        boolean[][] map = new boolean[y][x];
        for (int i = 1; i < totalStars; i++){
            int xTemp = (int)(Math.random()*x);
            int yTemp = (int)(Math.random()*y);
            int width = (int)((Math.random()*x+10.0)/10.0);
            int height = (int)((Math.random()*y+10.0)/10.0);
            // System.out.println(xTemp + "," + yTemp + "," + width + "," + height);
            boolean[][] tempMap = drawStar(cpy(map), yTemp, xTemp, height, width);
            // tempMap = drawStar(map, yTemp, xTemp, height, width);
            //print(map);
            //System.out.println(i);
            //boolean[][] tempMap = cpy(map);
            int star = countStars(tempMap);
            //System.out.println(star);
            if (i == star){
                map = cpy(tempMap);
            } else {
                i--;    
            }
        }
        print(map);
    }
    
    private static boolean[][] drawStar(boolean[][] map, int y, int x,  int height, int width){
        return drawPixel(cpy(map), y, x, (height*height),(width*width), y, x);
    }
    
    private static boolean[][] drawPixel(boolean[][] map, int y, int x, double a, double b, int k, int h){
        // System.out.println((((y-k)*(y-k))/a+((x-h)*(x-h))/b));
        if ((1>(((y-k)*(y-k))/a+((x-h)*(x-h))/b)) && !map[y][x]) {
            map[y][x] = true;
            //print(map);
            if (y < map.length-1)
                map = drawPixel(cpy(map), y+1, x, a, b, k, h);
            if (y > 0)
                map = drawPixel(cpy(map), y-1, x, a, b, k, h);
            if (x < map[y].length-1)
                map = drawPixel(cpy(map), y, x+1, a, b, k, h);
            if (x > 0)
                map = drawPixel(cpy(map), y, x-1, a, b, k, h);
        }
        return map;
    }
}