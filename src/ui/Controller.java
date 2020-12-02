package ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.VideoGame;

public class Controller {
	
	private ControllerRegister viewRegister;
	
	public Controller() {
	}

	public void initModeSelector(Stage primaryStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/ViewRegister.fxml"));
			Parent root = loader.load();
			
			viewRegister= (ControllerRegister) loader.getController();
			viewRegister.setMoodel(Main.getGame());
			viewRegister.setController(this);
			
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Chicken Invaders");
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void initModeGame(Stage primaryStage) throws IOException {
		try {
			BorderPane root=new BorderPane();
			GameController scene = new GameController(root, VideoGame.WIDTH_WINDOW+140, VideoGame.LONG_WINDOW+50);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Chicken Invaders");
			primaryStage.show();
			
			scene.setOnKeyPressed(e->{
				if(e.getCode()==KeyCode.SPACE) {
					if(Main.getGame().getUser().getAvatar().isShooting()==false) {
						Main.getGame().getUser().getAvatar().setShooting(true);
						Main.getGame().getUser().getAvatar().getBullet().setX(Main.getGame().getUser().getAvatar().getPosX());
						scene.shoot();
					}
				}else if(e.getCode()==KeyCode.LEFT) {
					scene.moverI();
				}else if(e.getCode()==KeyCode.RIGHT) {
					scene.moveD();
				}else if(e.getCode()==KeyCode.G) {
					scene.save();
				}else if(e.getCode()==KeyCode.P) {
					scene.orderSlection();
				}else if(e.getCode()==KeyCode.B) {
					scene.selectSearch();
				}
				});
			scene.setOnKeyReleased(e->{
				if(e.getCode()==KeyCode.LEFT) {
					scene.stop(scene.getAnimacionI());
				}else if(e.getCode()==KeyCode.RIGHT) {
					scene.stop(scene.getAnimacionD());
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
