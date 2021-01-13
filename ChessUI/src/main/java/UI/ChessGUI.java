package UI;

import DTO.DTOUser;
import Enums.PieceType;
import Game.ChessGame;
import Game.IChessGame;
import Enums.PlayerColor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ChessGUI extends Application implements IChessGUI {

    //functional fields
    private IChessGame game;
    private Pane[][] squares = new Pane[8][8];
    private String letterType = "Verdana";

    private int playerNr;


    // UI elements
    private String playerName;
    private String opponentPlayerName;
    private Label turnLabel;
    private TextField username;
    private TextField password;
    private Button submit;
    private TextField registername;
    private TextField registerpassword;
    private Button submitRegister;

    @Override
    public void start(Stage stage) throws Exception {
        Pane main = new Pane();
        main.getChildren().add(buildBoard());
        main.getChildren().add(buildLogin());
        main.getChildren().add(buildGameState());
        main.getChildren().add(buildRegister());
        Scene scene = new Scene(main, 50, 50);
        stage.setScene(scene);
        stage.setMinWidth(1300);
        stage.setMinHeight(1000);
        stage.setTitle("ChessGame: By Tim Wilms");
        game = new ChessGame(this);
        stage.show();

    }
    private Pane buildBoard() {
        Pane board = new Pane();
        board.setStyle("-fx-background-color: grey;");
        board.relocate(50  ,25);
        board.setPrefSize(850,900);
        int height = 850;
        int spacing = 100;
        int startwidth = 30;
        for(int i = 0; i < 8; i++){
            height -= 100;
            for(int y = 0; y < 8; y++){
                Pane square = new Pane();
                square.relocate(y * spacing + startwidth,height);
                square.setMinHeight(100);
                square.setMinWidth(100);
                int Yaxis = i;
                int Xaxis = y;
                square.addEventHandler(MouseEvent.MOUSE_PRESSED,event -> boardPressed(Xaxis,Yaxis));

                int yy = y;
                if(i % 2 == 0){
                    yy++;
                }
                if(yy % 2 == 0){
                    square.setStyle("-fx-background-color: white;");
                }
                else{
                    square.setStyle("-fx-background-color: brown;");

                }

                squares[Xaxis][Yaxis] = square;
                board.getChildren().addAll(square);
            }
        }
        return board;
    }


    @Override
    public void addPiece(PlayerColor color, PieceType type, int Xaxis, int Yaxis){
        Pane pane = getPane(Xaxis,Yaxis);
        pane.getChildren().removeAll();
        ImageView img =  getPieceImage(type, color);
        img.setFitHeight(100);
        img.setFitWidth(100);
        pane.getChildren().add(img);
    }

    @Override
    public void setOpponentName(String playerName) {
        this.opponentPlayerName = playerName;
    }

    @Override
    public void highlight(int Xaxis, int Yaxis){
        Pane pane = squares[Xaxis][Yaxis];
        Circle highlight = new Circle();
        highlight.setCenterX(50);
        highlight.setCenterY(50);
        highlight.setRadius(15);
        highlight.setStyle("-fx-background-color: grey;");
        highlight.setOpacity(0.2);
        pane.getChildren().add(highlight);

    }

    @Override
    public void removePiece(int Xaxis, int Yaxis){
        Pane pane = squares[Xaxis][Yaxis];

        while(!pane.getChildren().isEmpty()){
            pane.getChildren().remove(0);
        }
    }


    public void movePiece(int startX, int startY, PlayerColor color, PieceType type, int endX, int endY){
       removePiece(startX,startY);
       removePiece(endX,endY);
       addPiece(color,type,endX,endY);

    }

    @Override
    public void setPlayerData(int playerNr, String name) {
        this.playerNr = playerNr;

    }

    @Override
    public void notifyStartGame(int playerNr) {

    }

    @Override
    public void showErrorMessage(int playerNr, String errorMessage) {

    }
    public void removeAllHighlight(){
        for(int i = 0; i < 8; i++){
            for(int y = 0; y < 8; y++) {
                for(int x = 0; x < squares[i][y].getChildren().size(); x++){
                    if(squares[i][y].getChildren().get(x) instanceof Circle){
                        squares[i][y].getChildren().remove(x);
                    }
                }
            }
        }
    }

    @Override
    public void sendNotification(String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Chess Game");
                alert.setHeaderText("Message for " + playerName);
                alert.setContentText(message);
                alert.showAndWait();
            }
        });
    }

    @Override
    public void disableInput() {
            username.setDisable(true);
            registername.setDisable(true);
            password.setDisable(true);
            submit.setDisable(true);
            registerpassword.setDisable(true);
            submitRegister.setDisable(true);
    }

    @Override
    public void setTurn(PlayerColor p) {
        turnLabel.setText("it's " + p + "'s turn." );
    }


    private Pane getPane(int Xaxis, int Yaxis){
        return squares[Xaxis][Yaxis];
    }
    private ImageView getPieceImage(PieceType type, PlayerColor color) {
        Image image = null;
        if(color == PlayerColor.BLACK){
            switch (type){
                case KING:
                    image = new Image("/images/BlackKing.png");
                    break;
                case QUEEN:
                    image = new Image("/images/BlackQueen.png");
                    break;
                case BISHOP:
                    image = new Image("/images/BlackBishop.png");
                    break;
                case KNIGHT:
                    image = new Image("/images/BlackKnight.png");
                    break;
                case ROOK:
                    image = new Image("/images/BlackRook.png");
                    break;
                case PAWN: image = new Image("/images/BlackPawn.png");
                    break;
            }
        }
        if(color == PlayerColor.WHITE){
            switch (type){
                case KING:
                    image = new Image("/images/WhiteKing.png");
                    break;
                case QUEEN:
                    image = new Image("/images/WhiteQueen.png");
                    break;
                case BISHOP:
                    image = new Image("/images/WhiteBishop.png");
                    break;
                case KNIGHT:
                    image = new Image("/images/WhiteKnight.png");
                    break;
                case ROOK:
                    image = new Image("/images/WhiteRook.png");
                    break;
                case PAWN: image = new Image("/images/WhitePawn.png");
                    break;
            }
        }
        return new ImageView(image);
    }
    public void boardPressed( int Xaxis, int Yaxis){
        game.boardPressed(Xaxis, Yaxis);
        System.out.println("You clicked: on X:" + Xaxis + " and Y: " + Yaxis);
    }

    private Pane buildLogin(){
        Pane login = new Pane();

        Label titleLabel = new Label("Login");
        titleLabel.setFont(Font.font(letterType,FontWeight.BOLD, 24));
        titleLabel.relocate(140,12);

        Label usernameLabel = new Label("Username: ");
        usernameLabel.relocate(75,60);
        username = new TextField("test");
        username.relocate(75,85);

        Label passwordLabel = new Label("password: ");
        passwordLabel.relocate(75,150);

        password = new TextField("test");
        password.relocate(75,175);

        submit = new Button("login!");
        submit.relocate(100,250);
        submit.addEventHandler(MouseEvent.MOUSE_PRESSED,event -> {
            try {
                game.register(username.getText(),password.getText());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        login.getChildren().add(titleLabel);
        login.getChildren().add(usernameLabel);
        login.getChildren().add(username);
        login.getChildren().add(passwordLabel);
        login.getChildren().add(password);
        login.getChildren().add(submit);

        login.setPrefSize(350,300);
        login.setStyle("-fx-background-color:grey; -fx-border-color:#424242; -fx-border-width:2px;");
        login.relocate(910,25);
        return login;
    }
    private Pane buildGameState(){
        Pane gameState = new Pane();

        Label titleLabel = new Label("Game");
        titleLabel.setFont(Font.font(letterType,FontWeight.BOLD, 24));
        titleLabel.relocate(140,12);

        turnLabel = new Label("turnlabel");
        turnLabel.relocate(150,80);

        gameState.getChildren().add(titleLabel);
        gameState.getChildren().add(turnLabel);
        gameState.setPrefSize(350,300);
        gameState.setStyle("-fx-background-color:yellow; -fx-border-color:#424242; -fx-border-width:2px;");
        gameState.relocate(910,350);
        return gameState;
    }
    private Pane buildRegister(){
        Pane register = new Pane();

        Label titleLabel = new Label("Register");
        titleLabel.setFont(Font.font(letterType,FontWeight.BOLD, 24));
        titleLabel.relocate(140,12);

        Label usernameLabel = new Label("Username: ");
        usernameLabel.relocate(75,60);
        registername = new TextField("test");
        registername.relocate(75,85);

        Label passwordLabel = new Label("password: ");
        passwordLabel.relocate(75,150);

        registerpassword = new TextField("test");
        registerpassword.relocate(75,175);

        submitRegister = new Button("login!");
        submitRegister.relocate(100,250);
        submitRegister.addEventHandler(MouseEvent.MOUSE_PRESSED,mouseEvent -> {
            try {
                game.createAccount(new DTOUser(registername.getText(),registerpassword.getText()));
                registername.clear();
                registerpassword.clear();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        register.getChildren().add(titleLabel);
        register.getChildren().add(usernameLabel);
        register.getChildren().add(registername);
        register.getChildren().add(passwordLabel);
        register.getChildren().add(registerpassword);
        register.getChildren().add(submitRegister);
        register.setPrefSize(350,300);
        register.setStyle("-fx-background-color:yellow; -fx-border-color:#424242; -fx-border-width:2px;");
        register.relocate(910,675);
        return register;
    }

}
