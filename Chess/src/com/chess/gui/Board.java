package com.chess.gui;
import com.chess.Move.MoveStatus;
import com.chess.board.GameBoard;
import com.chess.Move.Move;
import com.chess.board.Pieces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.SwingUtilities.*;

public class Board {
    private final JFrame gameFrame;
    private BoardPanel boardPanel;
    private static int WIDTH_HEIGHT = 800;
    private static Dimension OUTER_FRAME_DIMENSION = new Dimension(WIDTH_HEIGHT, WIDTH_HEIGHT);
    private static Dimension BOARD_PANAL_DIMENSION = new Dimension(400, 400);
    private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);
    private final Color darkTilecolor = Color.decode("#593E1A");
    private final Color lightTilecolor = Color.decode("#FFFACD");
    private GameBoard gameBoard;
    private TilePanel sourceTile;
    private TilePanel secondTile;
    private MoveStatus status;

    public Board(GameBoard gameBoard, MoveStatus status){
        this.status = status;
        this.gameBoard = gameBoard;
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);
        this.boardPanel = new BoardPanel(this.gameBoard);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        gameFrame.validate();
    }
    public void updateBoard(GameBoard board){
        boardPanel.update(board);
        //take the gameBoard and update GUI to have the chess pieces where in the right spot.

    }

    private class BoardPanel extends JPanel {
        final ArrayList<TilePanel> boardTiles;
        GameBoard gameBoard;
        BoardPanel(GameBoard gameBoard){
            super(new GridLayout(8,8));
            this.boardTiles = new ArrayList<>();
            this.gameBoard = gameBoard;
            boolean white = true;
            for(int i=0; i<64; i++){
                Pieces piece = this.gameBoard.getIndex(i);
                final TilePanel tilePanel = new TilePanel(this, i, white, piece);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
                if(i%8 != 7){
                    white = !white;
                }
            }
            setPreferredSize(BOARD_PANAL_DIMENSION);
            validate();
        }
        private void update(GameBoard board){
            this.gameBoard = board;
            int i = 0;
            for(TilePanel tile : boardTiles){
                tile.assignPieceIcon(board.getIndex(i));
                i++;
            }
            validate();
            this.repaint();
        }
        public TilePanel getTile(int index){return boardTiles.get(index);}
    }
    private class TilePanel extends JPanel{
        private final int tileId;
        TilePanel(final BoardPanel boardPanel, final int tileId, boolean white, Pieces piece){
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSION);
            setBackground(white ? lightTilecolor:darkTilecolor);
            assignPieceIcon(piece);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    if(isLeftMouseButton(event)){
                        if(sourceTile == null){
                            sourceTile = boardPanel.getTile(tileId);
                        }
                        else if(secondTile== null){
                            secondTile = boardPanel.getTile(tileId);
                            Move playerMove = new Move(sourceTile.tileId, secondTile.tileId);
                            if(playerMove.getFrom() == 60
                                    && gameBoard.getIndex(60).getType().equals("king")
                                    && playerMove.getTo() - 60 == 2){
                                playerMove.setCanCastleShort();
                            }
                            else if(playerMove.getFrom() == 60
                                    && gameBoard.getIndex(60).getType().equals("king")
                                    && playerMove.getTo() - 60 == -2){
                                playerMove.setCanCastleLong();
                            }
                            else if(playerMove.getFrom() == 4
                                    && gameBoard.getIndex(4).getType().equals("king")
                                    && playerMove.getTo() - 4 == 2){
                                playerMove.setCanCastleShort();
                            }
                            else if(playerMove.getFrom() == 4
                                    && gameBoard.getIndex(4).getType().equals("king")
                                    && playerMove.getTo() - 4 == -2){
                                playerMove.setCanCastleLong();
                            }

                            System.out.println(playerMove.getFrom() + "");
                            status.setMove(playerMove);
                            sourceTile = null;
                            secondTile = null;
                        }
                    }
                    else if(isRightMouseButton(event)){
                        sourceTile = null;
                        secondTile = null;
                    }
                }

                //Dont think any of these will be needed but will leave them here just in case.
                @Override
                public void mousePressed(MouseEvent e) {}
                @Override
                public void mouseReleased(MouseEvent e) {}
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
            });

            validate();
        }
        private void assignPieceIcon(Pieces piece){
            removeAll();
            if(piece != null) {
                try {
                    File file = new File(piece.getImagePath());
                    final BufferedImage image = ImageIO.read(file);
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            validate();
            repaint();
        }
    }
}
