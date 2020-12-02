package ui;

import java.io.IOException;

import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Thread.CircleThread;
import Thread.GroupThread;
import Thread.ImageThread;
import exception.RepeatedUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import model.VideoGame;



public class ControllerRegister implements Initializable {
	
	private Controller controller;

	@FXML
    private TextField txtName;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtGender;
   
	
	@FXML
	private Button continuar;
	
	 @FXML
	 private Circle circle;
	  @FXML
	 private AnchorPane pane;
	@FXML
	private Group group;
	
	@FXML
	private ImageView chicken;
	
	
    
    public void setMoodel(VideoGame game) {
		Main.getGame();
	}
    
   
	
	public void setController(Controller Controller) {
		this.controller = Controller;
	}
    @FXML
    void register(ActionEvent event) throws IOException, RepeatedUserException, NumberFormatException{
	
        Main.getGame().loadUser(txtName.getText(), Integer.parseInt(txtAge.getText()), txtGender.getText());
        
        
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Su contacto ha sido registrado satisfactoriamente");
		alert.setContentText("Desea continuar?");

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK){
			continuar.setVisible(true);
	        
		} else {
		    alert.close();
		}

    }
    @FXML
     void continuar(ActionEvent event) throws IOException {
    	
    	controller.initModeGame((Stage) ((Node) continuar).getScene().getWindow());

    }
    
    public void moveCircle() {
    		
    	double num = circle.getLayoutX();
    	if(num < 700) {
    		
    		circle.setLayoutX(circle.getLayoutX()+5);
    	}else {
    		circle.setLayoutX(circle.getLayoutX()-500);
    	}
    }
    
    public void moveGroup() {
		
    	double num = group.getLayoutY();
    	if(num > 0) {
    		
    		group.setLayoutY(group.getLayoutY()-5);
    	}else {
    		group.setLayoutY(group.getLayoutY()+300);
    	}
    }
    
    
 public void moveImage() {
		
    	double num = chicken.getLayoutY();
    	if(num > 0) {
    		
    		chicken.setLayoutY(chicken.getLayoutY()-5);
    	}else {
    		chicken.setLayoutY(chicken.getLayoutY()+100);
    	}
    }
    
    
      
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	CircleThread cT = new CircleThread(this);
    	cT.start();
    	
    	GroupThread gT = new GroupThread(this);
    	gT.start();
    	
    	ImageThread iT = new ImageThread(this);
    	iT.start();

		continuar.setVisible(false);
	}

}
