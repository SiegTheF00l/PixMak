/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixmak;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cmason12
 */
public class ColorOpt {
    //red blue green black and white
    private static final Rectangle lRed =new Rectangle(40,40, Color.SALMON);
    private static final Rectangle Red =new Rectangle(40,40, Color.RED);
    private static final Rectangle dRed =new Rectangle(40,40, Color.DARKRED);
    
    private static final Rectangle lGreen =new Rectangle(40,40, Color.LIGHTGREEN);
    private static final Rectangle Green =new Rectangle(40,40, Color.GREEN);
    private static final Rectangle dGreen =new Rectangle(40,40, Color.DARKGREEN);

    private static final Rectangle lBlue =new Rectangle(40,40, Color.LIGHTBLUE);
    private static final Rectangle Blue =new Rectangle(40,40, Color.BLUE);
    private static final Rectangle dBlue =new Rectangle(40,40, Color.DARKBLUE);
    
    private static final Rectangle lGrey =new Rectangle(40,40, Color.LIGHTGREY);
    private static final Rectangle Black =new Rectangle(40,40, Color.BLACK);
    private static final Rectangle dGrey =new Rectangle(40,40, Color.DARKGREY);
    
    private static final Rectangle White =new Rectangle(40,40, Color.WHITE);
    private static final Rectangle ERASER =new Rectangle(40,40, Color.WHITE);
    
    public static VBox getColorOpt() {
        VBox optBox = new VBox();
        optBox.setMinHeight(700);
        optBox.setMaxWidth(200);
        optBox.setAlignment(Pos.TOP_RIGHT);
        optBox.setTranslateX(250);
        optBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); -fx-background-radius: 50");

        VBox colorBox = new VBox();
        colorBox.setMaxWidth(200);
        Label lblColor = new Label("Colors");
        lblColor.setFont(new javafx.scene.text.Font("Rockwell", 27));
        lblColor.setTextFill(Color.WHITE);
        HBox lightBox = new HBox(lRed, lGreen, lBlue, lGrey);
        HBox midBox = new HBox(Red, Green, Blue, dGrey);
        HBox darkBox = new HBox(dRed, dGreen, dBlue, Black);
        Pane noColor = new Pane();
        noColor.setMaxWidth(40);
        noColor.setMaxHeight(40);
        noColor.getChildren().add(ERASER);
        noColor.setStyle("-fx-background-color: white;");
        HBox lastBox = new HBox(White, noColor);
        ERASER.setFill(new ImagePattern(new Image("http://www.pd4pic.com/"
                + "images/red-sign-stop-symbol-cross-circle-signs-opposit"
                + "e.png"))); 
        
        //Color.
        colorBox.getChildren().addAll(lblColor, lightBox, midBox, darkBox, lastBox);
        colorBox.setTranslateX(25);
        colorBox.setTranslateY(150);
        optBox.getChildren().add(colorBox);
        //Rectangle fill =new Rectangle(SQUARE_SIZE,
                        //SQUARE_SIZE);
        lRed.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.SALMON;
        });
        Red.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.RED;
        });
        dRed.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.DARKRED;
        });
        
        
        lGreen.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.LIGHTGREEN;
        });
        Green.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.GREEN;
        });
        dGreen.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.DARKGREEN;
        });
        
        
        lBlue.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.LIGHTBLUE;
        });
        Blue.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.BLUE;
        });
        dBlue.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.DARKBLUE;
        });
        
        lGrey.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.LIGHTGREY;
        });
        dGrey.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.DARKGREY;
        });
        Black.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.BLACK;
        });
        
        
        White.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.WHITE;
        });
        ERASER.setOnMouseClicked((MouseEvent event) -> {
           SpriteSheet.color = Color.web("DDD1E7");
        });
        
        return optBox;
    }
}
