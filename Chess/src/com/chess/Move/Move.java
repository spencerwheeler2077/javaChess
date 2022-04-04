package com.chess.Move;

public class Move implements Comparable<Move>{
    private final int from;
    private final int to;
    public double eval;
    private String castle;
    public Move(int from, int to){
        if(to > 63 || from > 63){
            System.out.println("test failed");
        }
        this.from = from;
        this.to = to;
        this.castle = "";
    }
    public void setCanCastleLong(){this.castle = "long";}
    public void setCanCastleShort(){this.castle = "short";}
    public String getCastle(){return this.castle;}
    @Override
    public String toString() {
        return "Move(" +
                from +
                ", " + to + ") "
                ;
    }

    public int getFrom() {
        return from;
    }
    public int getTo(){
        return to;
    }

    public boolean equals(Move other){
        return other.getTo() == to && other.getFrom() == from;
    }

    @Override
    public int compareTo(Move o) {
        return (int) (eval - o.eval);
    }
}
