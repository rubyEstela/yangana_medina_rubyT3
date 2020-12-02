package ui;
	
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

import javax.swing.JOptionPane;

import exception.RepeatedUserException;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Enemy;
import model.EnemyList;
import model.VideoGame;


public class Main extends Application {
	
	
	private static VideoGame game;
	
	public Main()   {
		game= new VideoGame();
		
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		
		Controller superControlador = new Controller();
		superControlador.initModeSelector(primaryStage);

	}
	
	public static void mostrar(String err) throws IOException, IOException {
		Alert alerta=new Alert(AlertType.NONE);
		
		ButtonType aceptar=new ButtonType("Aceptar");
		
		alerta.getButtonTypes().add(aceptar);
		
		alerta.setHeaderText("Ha ocurrido un error");
		alerta.setTitle("This game");
		alerta.setContentText(err);
		Optional<ButtonType> result=alerta.showAndWait();
		if(result.get()==aceptar) {
		}
	
	}
	
	public static VideoGame getGame() {
		return game;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
