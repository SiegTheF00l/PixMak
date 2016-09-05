/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixmak;

import java.awt.Point;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cmason12
 */
public class SpriteSheet {

    public static GridPane sheet = new GridPane();
    private static int BOARD_SIZE;
    private static int SQUARE_SIZE;
    public static Color color = Color.TRANSPARENT;
    public static char[][] board;

    public static GridPane newSheet(int x, int y) {
        SQUARE_SIZE = x;
        BOARD_SIZE = y;
        board = new char[BOARD_SIZE][BOARD_SIZE];
        configureGridLayout(sheet);
        sheet.setStyle("-fx-background-color: DDD1E7; -fx-grid-lines-visible: true");

        

        sheet.setOnMouseClicked((MouseEvent event) -> {
            Point coord = new Point((int) (event.getX() / SQUARE_SIZE) - 1,
                    (int) (event.getY() / SQUARE_SIZE) - 1);
            Rectangle fill = new Rectangle(10, 10, color);
            fill.setStroke(Color.BLACK);

            try {
                if (coord.x < BOARD_SIZE
                        && coord.y <= BOARD_SIZE) {
                    fillArray(coord, color);
                    sheet.add(fill, coord.x, coord.y);
                }
        
                
            } catch (Exception e) {
            }

        });

        sheet.setOnMouseDragged((MouseEvent event) -> {
            Point coord = new Point((int) (event.getX() / SQUARE_SIZE) - 1,
                    (int) (event.getY() / SQUARE_SIZE) - 1);

            Rectangle fill;
            fill = new Rectangle(10, 10, color);
            fill.setStroke(Color.BLACK);

            try {
                if (coord.x < BOARD_SIZE
                        && coord.y <= BOARD_SIZE) {
                    fillArray(coord, color);
                    sheet.add(fill, coord.x, coord.y);
                }
                
            } catch (Exception e) {
            }

        });

        return sheet;
    }

    private static void configureGridLayout(GridPane gridPane) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(SQUARE_SIZE);
            rowConstraints.setPrefHeight(SQUARE_SIZE);
            rowConstraints.setMaxHeight(SQUARE_SIZE);
            rowConstraints.setValignment(VPos.CENTER);
            gridPane.getRowConstraints().add(rowConstraints);

            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setMinWidth(SQUARE_SIZE);
            colConstraints.setMaxWidth(SQUARE_SIZE);
            colConstraints.setPrefWidth(SQUARE_SIZE);
            colConstraints.setHalignment(HPos.CENTER);
            gridPane.getColumnConstraints().add(colConstraints);
        }
    }

    private static void fillArray(Point coord, Color color) {

        board[coord.x][coord.y]
                = color.equals(Color.SALMON) ? 'a'
                : color.equals(Color.RED) ? 'R'
                : color.equals(Color.DARKRED) ? 'Z'
                : color.equals(Color.LIGHTGREEN) ? 'd'
                : color.equals(Color.GREEN) ? 'e'
                : color.equals(Color.DARKGREEN) ? 'f'
                : color.equals(Color.LIGHTBLUE) ? 'g'
                : color.equals(Color.BLUE) ? 'B'
                : color.equals(Color.DARKBLUE) ? 'i'
                : color.equals(Color.LIGHTGREY) ? 'G'
                : color.equals(Color.DARKGREY) ? 'D'
                : color.equals(Color.BLACK) ? 'l'
                : color.equals(Color.WHITE) ? 'W'
                : '\u0000';

    }

    private static Color pickColor(Point coord) {

        Color rectColor
                = board[coord.x][coord.y] == 'a' ? Color.SALMON
                        : board[coord.x][coord.y] == 'R' ? Color.RED
                        : board[coord.x][coord.y] == 'X' ? Color.RED
                        : board[coord.x][coord.y] == 'Z' ? Color.DARKRED
                        : board[coord.x][coord.y] == 'd' ? Color.LIGHTGREEN
                        : board[coord.x][coord.y] == 'e' ? Color.GREEN
                        : board[coord.x][coord.y] == 'f' ? Color.DARKGREEN
                        : board[coord.x][coord.y] == 'g' ? Color.LIGHTBLUE
                        : board[coord.x][coord.y] == 'B' ? Color.BLUE
                        : board[coord.x][coord.y] == 'i' ? Color.DARKBLUE
                        : board[coord.x][coord.y] == 'G' ? Color.LIGHTGREY
                        : board[coord.x][coord.y] == 'D' ? Color.DARKGREY
                        : board[coord.x][coord.y] == 'l' ? Color.BLACK
                        : board[coord.x][coord.y] == 'W' ? Color.WHITE
                        : Color.web("DDD1E7");
        return rectColor;

    }

    public static void setBoard(int i, int count, boolean print) {

        Rectangle fill;
        Point loc = new Point(i, count);

        fill = new Rectangle(10, 10, pickColor(loc));
       
            fill.setStroke(Color.BLACK);
     sheet.setGridLinesVisible(true);
        try {
            if (i < BOARD_SIZE
                    && count < BOARD_SIZE) {
                sheet.add(fill, i, count);
            }
        } catch (Exception e) {
        }
    }
    public static GridPane print(){
        GridPane printSheet;
        printSheet = sheet;
        printSheet.setGridLinesVisible(false);
        printSheet.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
        printSheet.getChildren().clear();
        
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int count = 0; count < BOARD_SIZE; count++){
                 Point loc = new Point(i, count);

                 Rectangle fill;
                 if (board[i][count] != '-' && board[i][count] != '\u0000'){
                    fill = new Rectangle(10, 10, pickColor(loc));
                    
                    if (pickColor(loc) == Color.web("DDD1E7")) {
                        fill.setOpacity(0);
                        fill.setFill(null);
                    } else
                        fill.setOpacity(100);
                    if(SaveSheet.showGridLines)
                        //fill.setStroke(Color.BLACK);
                   // else 
                        fill.setStroke(Color.TRANSPARENT);
                 }
                 else 
                 {
                     fill = new Rectangle(10,10, Color.TRANSPARENT);
                     fill.setStroke(Color.TRANSPARENT);
                 }
                

                try {
                    if (i < BOARD_SIZE
                            && count < BOARD_SIZE) {
                        if (pickColor(loc) != Color.web("DDD1E7"))
                            sheet.add(fill, i, count);
                    }
                } catch (Exception e) {
                }
        }
        }
        return printSheet;
        
    }
    
    
    public static void flipHorizontal(int x, int y){
        SQUARE_SIZE = x;
        BOARD_SIZE = y;
        
        char flipBoardH[][] = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = BOARD_SIZE-1,  j = 0; i >= 0; i--, j++){
            for (int count = 0; count < BOARD_SIZE; count ++){
                flipBoardH[i][count] = board[j][count];
                //board[i][count] = flipBoardH[i][count];
            }
        }
        board = flipBoardH;
        sheet.getChildren().clear();
                for (int i = 0; i < BOARD_SIZE; i++){
                    for (int count = 0 ; count < BOARD_SIZE; count++){
                        SpriteSheet.setBoard(count, i, false);
                       
                    }
                }
    }
    
        
}
