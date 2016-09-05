/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pixmak;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import static java.lang.String.format;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import static pixmak.SpriteSheet.sheet;

/**
 *
 * @author cmason
 */
public class SaveSheet {

    public static String name = "L3DGARD";
    public static boolean showGridLines = true;
    
    public static void saveFile(char array[][], int boardSize) {
        PrintStream P;

        try {
            String fileName;
            if ("L3DGARD".equals(name)) {
                fileName = saveName();
                P = new PrintStream(fileName);
            }
            else {
                fileName = name;
                P = new PrintStream(name);
            }
            if (PixMak.textPrint == true) {
            for (int i = 0; i < boardSize; i++) {
                for (int count = 0; count < boardSize; count++){
                    if (array[count][i] == '\u0000')
                       P.print('-' + " ");
                    else
                        P.print(array[count][i] + " ");
                }
                P.println();
            }
            P.close();
            }
       
       WritableImage image = SpriteSheet.print().snapshot(new SnapshotParameters(), null);

    // TODO: probably use a file chooser here
    File file = new File(fileName+".png");

    try {
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        
     
        
                SpriteSheet.sheet.setStyle("-fx-background-color: DDD1E7; -fx-grid-lines-visible: true");
                SpriteSheet.sheet.setGridLinesVisible(true);
        
        
                SpriteSheet.sheet.getChildren().clear();
                for (int i = 0; i < boardSize; i++){
                    for (int count = 0 ; count < boardSize; count++){
                        SpriteSheet.setBoard(count, i, true);
                       
                    }
                }
    } catch (IOException e) {
        // TODO: handle exception here
    }

        } catch (Exception e) {
        }

        
    }

    public static void askSave(char array[][], int boardSize) {
        Alert alert = new Alert(null,
                "Save File?",
                ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult()
                == ButtonType.YES) {
            try {
                saveFile(array, boardSize);
            } catch (Exception ex) {

            }
        }
        
    }
    
    
    public static void msgBox(String dialogue) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("");
        alert.setContentText(dialogue); 
        
        alert.showAndWait();
    }
    
    public static String inpBox(String dialogue) {
        String input;
        
        input = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("");
        dialog.setContentText(dialogue);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        input = result.get();
        
        }
        return input;
}
    
    
    public static String saveName() {
        String name;
        int numLessThan;
        
        name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                while (name.length() > 8) {
                    name = inpBox("File Name: ");
                    try {
                        if (name.length() > 8) {
                            msgBox("Name too long, Maximum size 8 "
                                    + "characters");
                        }
                    } catch (Exception e) {
                        name = "        ";
                    }
                }
                if (name.length() < 8) {
                    numLessThan = 8 - (8 - name.length());
                    for (int i = numLessThan; i < 8; i++) {
                        name += " ";
                    }
                }
                return name + ".txt";
    }
    
    
        private static File getOpenPath() {
        File path;

        final JFileChooser fc = new JFileChooser();
        int returnVal;
        
        returnVal = fc.showOpenDialog(null);

        path = null;
        if (returnVal == 0) {
            path = fc.getSelectedFile();
        }

        
        return path;
    }
        
        
        public static void loadFile(char array[][], int boardSize) throws IOException {
        File Save;
        name = getOpenPath().toString();
 
        Save = new File(name);
        Scanner fileIn;
        boolean valid;

        try {
            
            
            valid = true;
            if (Save.exists()) {
                fileIn = new Scanner(Save);


                for (int i =0; i < boardSize; i++){
                    for (int count = 0; count < boardSize; count++){
                        array[count][i] = fileIn.next().charAt(0);
                    }
                }
                
                SpriteSheet.sheet.getChildren().clear();
                for (int i = 0; i < boardSize; i++){
                    for (int count = 0 ; count < boardSize; count++){
                        SpriteSheet.setBoard(count, i, false);
                       
                    }
                }
                
            } else {
                valid = false;
                msgBox("File Does Not Exist!");
            }
        } catch (Exception e) {
        }

    }
}
