package com.chess.Move;

import java.io.File;
import java.util.Scanner;

public class EvalList {
    private final Double[] pawn = new Double[64];
    private final Double[] knight = new Double[64];
    private final Double[] bishop = new Double[64];
    private final Double[] rook = new Double[64];
    private final Double[] queen = new Double[64];
    private final Double[] king = new Double[64];

    public EvalList(String filePath){

        System.out.println("hi");
        setLists(filePath);
        System.out.println("hi");
    }

    public Double getBishop(int index) {return bishop[index];}
    public Double getPawn(int index) {return pawn[index];}
    public Double getKing(int index) {return king[index];}
    public Double getKnight(int index){return knight[index];}
    public Double getRook(int index) {return rook[index];}
    public Double getQueen(int index) {return queen[index];}

    private void setLists(String filePath){
        File file = new File(filePath);
        try(Scanner input = new Scanner(file)){
            //This runs 6 for loops, one for each piece's evaluation list.
            int count = 0;
            input.nextLine();
            for(int line = 0;line<8;line++){
                String[] row = input.nextLine().split(";");
                for(String item: row){
                    pawn[count] = Double.parseDouble(item);
                    count++;
                }
            }
            input.nextLine();
            count = 0;
            for(int line = 0;line<8;line++){
                String[] row = input.nextLine().split(";");
                for(String item: row){
                    knight[count] = Double.parseDouble(item);
                    count++;
                }
            }
            input.nextLine();
            count = 0;
            for(int line = 0;line<8;line++){
                String[] row = input.nextLine().split(";");
                for(String item: row){
                    bishop[count] = Double.parseDouble(item);
                    count++;
                }
            }
            input.nextLine();
            count = 0;
            for(int line = 0;line<8;line++){
                String[] row = input.nextLine().split(";");
                for(String item: row){
                    rook[count] = Double.parseDouble(item);
                    count++;
                }
            }
            input.nextLine();
            count = 0;
            for(int line = 0;line<8;line++){
                String[] row = input.nextLine().split(";");
                for(String item: row){
                    queen[count] = Double.parseDouble(item);
                    count++;
                }
            }
            input.nextLine();
            count = 0;
            for(int line = 0;line<8;line++){
                String[] row = input.nextLine().split(";");
                for(String item: row){
                    king[count] = Double.parseDouble(item);
                    count++;
                }
            }
        }
        catch(java.io.IOException ex){
            System.out.println("Invalid file given for Eval list");

        }
    }
}
