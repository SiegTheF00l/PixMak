import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static pixmak.ColorOpt.getColorOpt;
import static pixmak.SpriteSheet.newSheet;
import  pixmak.SaveSheet;

/**
 *
 * @author cmason12
 */
public class PixMak extends Application {
    public static Boolean textPrint = true;
    
    @Override
    public void start(Stage primaryStage) {
       // Button btn = new Button();
        //btn.setText("Say 'Hello World'");
        /*btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
  
        int Board_Size = 30;
        
        StackPane root = new StackPane();
        GridPane sheet = newSheet(10, 30);
        ScrollPane sheetPane = new ScrollPane(sheet);
        MenuBar menuBar = new MenuBar();
        

        Menu file = new Menu("_File");
        Menu option = new Menu("_Options");
        //Menu edit = new Menu("_Edit");
        MenuItem clear = new MenuItem("_New");
        MenuItem pSave = new MenuItem("----Save----");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem pLoad = new MenuItem("----Load----");
        MenuItem loadFile = new MenuItem("Load File");
        CheckMenuItem cmi = new CheckMenuItem("Print To Text");
        cmi.setSelected(true);
        cmi.selectedProperty().addListener(new ChangeListener<Boolean>() {
        public void changed(ObservableValue ov,
        Boolean old_val, Boolean new_val) {
            SaveSheet.showGridLines = SaveSheet.showGridLines== false;
            System.out.println(textPrint.toString());
        }
        });
        CheckMenuItem gridLines = new CheckMenuItem("Show Grid Lines");
        gridLines.setSelected(true);
        gridLines.selectedProperty().addListener(new ChangeListener<Boolean>() {
        public void changed(ObservableValue ov,
        Boolean old_val, Boolean new_val) {
            textPrint = textPrint == false;
            System.out.println(textPrint.toString());
        }
        });
        MenuItem flipO = new MenuItem("---Flip Options----");
        MenuItem flipH = new MenuItem("Flip Horizontal");
        
        file.getItems().addAll(clear, pSave, save, saveAs, pLoad, loadFile);
        option.getItems().addAll(cmi, gridLines, flipO, flipH);
        menuBar.getMenus().addAll(file, option);
        
        VBox startMenu = new VBox();
        startMenu.getChildren().add(menuBar);
        sheetPane.setMaxWidth(325);
        sheetPane.setMaxHeight(325);
        sheetPane.setFitToHeight(true);
        sheetPane.setFitToWidth(true);
        sheet.setAlignment(Pos.CENTER);
        
        //sheetPane.setMinWidth(200);
        //sheetPane.setMinHeight(200);
        VBox borderBox = new VBox();
        borderBox.setMaxWidth(375);
        borderBox.setMaxHeight(375);
        borderBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.85); -fx-background-radius: 50;");
        borderBox.setAlignment(Pos.CENTER);
        borderBox.setTranslateX(-120);
        sheetPane.setTranslateX(-120);
        
        root.getChildren().addAll(startMenu, borderBox, sheetPane, getColorOpt());
        
        Scene scene = new Scene(root, 700, 600);
        
        primaryStage.setTitle("PixMak");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        clear.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        SaveSheet.name = "L3DGARD";
                        for(int i = 0; i < Board_Size; i++){
                            for (int count = 0; count < Board_Size; count++){
                                SpriteSheet.board[count][i] = '-';
                                SpriteSheet.setBoard(i, count, false);
                            }  
                        }
                    } catch (Exception ex) {
                        
                    }
                }
            });
        
        save.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        SaveSheet.saveFile(SpriteSheet.board, Board_Size);
                    } catch (Exception ex) {
                        
                    }
                }
            });
        
        
        saveAs.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        SaveSheet.name = "L3DGARD";
                        SaveSheet.saveFile(SpriteSheet.board, Board_Size);
                    } catch (Exception ex) {
                        
                    }
                }
            });
        
        loadFile.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        SaveSheet.loadFile(SpriteSheet.board, Board_Size);
                    } catch (Exception ex) {
                        
                    }
                }
            });
        
        
        
        flipH.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                SpriteSheet.flipHorizontal(10, Board_Size);
                }
            });
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          public void handle(WindowEvent we) {
              SaveSheet.askSave(SpriteSheet.board, Board_Size);
          }
      });  
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
