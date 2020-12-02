package ui;

import java.io.File;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;

import exception.ScoreNotExistException;
import exception.TreeEmptyException;
import exception.UsersNotExistException;
import javafx.scene.text.Font;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Enemy;
import model.EnemyBoss;
import model.Ship;

public class GameController extends Scene {

	@FXML
	private Pane pane;
	private ImageView background;
	private ImageView imgUser;
	@FXML
	private ImageView laserU;
	private ImageView chicken;
	private ImageView laserBoss;

	private Timeline animacionD;
	private Timeline animacionI;
	private Timeline animacionB;
	private Timeline animacionBE;
	private Timeline animationE;
	private Timeline animationMB;

	private ProgressBar livesBoss;
	@FXML
	private BorderPane bP;
	@FXML
	private Canvas canvas;
	@FXML
	private Bounds bounds;

	@FXML
	private ArrayList<ImageView> enemys;

	public Timeline getAnimacionD() {
		return animacionD;
	}

	public Timeline getAnimacionI() {
		return animacionI;
	}

	public Timeline getAnimacionB() {
		return animacionB;
	}

	public GameController(BorderPane root, double i, double j) {
		super(root, i, j);
		bP = root;

		VBox flank = new VBox();

		Label l=new Label("Para guardar presione G");
		l.setTextFill(Color.WHITE);
		Label l1=new Label("Para ver puntajes presione P");
		l1.setTextFill(Color.WHITE);
		Label l2=new Label("Para disparar presione ESPACIO");
		l2.setTextFill(Color.WHITE);
		Label l3=new Label("Para moverse presione <- � ->");
		l3.setTextFill(Color.WHITE);
		Label l4=new Label("Para Buscar un Usuario presione B");
		l4.setTextFill(Color.WHITE);
		
		flank.getChildren().add(l);
		flank.getChildren().add(l1);
		flank.getChildren().add(l2);
		flank.getChildren().add(l3);
		flank.getChildren().add(l4);

		canvas = new Canvas();
		canvas.widthProperty().bind(bP.widthProperty());
		canvas.setHeight(50);

		primitive(canvas.getGraphicsContext2D());

		pane = new Pane();

		enemys = new ArrayList<ImageView>();
		laserBoss = new ImageView(new Image("img/laser.png"));
		laserBoss.setRotate(180);
		laserBoss.setVisible(false);

		initializeEnemys();

		laserU = new ImageView(new Image("img/laser.png"));
		laserU.setFitWidth(Main.getGame().getUser().getAvatar().getBullet().getWidth());
		laserU.setFitHeight(Main.getGame().getUser().getAvatar().getBullet().getLength());

		imgUser = new ImageView();
		imgUser.setImage(new Image(Main.getGame().getUser().getAvatar().getImage()));
		imgUser.setX(Main.getGame().getUser().getAvatar().getPosX());
		imgUser.setY(Main.getGame().getUser().getAvatar().getPosY());
		imgUser.setFitWidth(Main.getGame().getUser().getAvatar().getWidth());
		imgUser.setFitHeight(Main.getGame().getUser().getAvatar().getLonger());

		background = new ImageView();
		background.setImage(new Image("img/fondo.jpg"));
		background.setX(0);
		background.setY(0);

		pane.getChildren().add(background);
		pane.getChildren().add(imgUser);
		pane.getChildren().add(laserU);
		pane.getChildren().add(laserBoss);
		bP.setCenter(pane);
		bP.setTop(canvas);
		bP.setRight(flank);

		pane.setPrefSize(i, j);
		bounds = pane.getBoundsInLocal();

		addEnemys();

		laserU.setVisible(false);
		moveEnemys();
	}

	public void passBoss() {
		chicken = new ImageView(new Image(Main.getGame().geteB().getImagen(), 100, 100, true, true));
		livesBoss = new ProgressBar(0);
		pane.getChildren().add(chicken);
		pane.getChildren().add(livesBoss);
		moveBoss();
	}

	public void addEnemys() {
		for (int i = 0; i < getEnemys().size(); i++) {
			pane.getChildren().add(getEnemys().get(i));
			getEnemys().get(i).setVisible(true);
		}
	}

	public void moveBoss() {
		animationMB = new Timeline(new KeyFrame(Duration.millis(30), e -> {
			toCorrectEnemys(Main.getGame().geteB());
			chicken.setX(Main.getGame().geteB().getPosX());
			livesBoss.setLayoutX(chicken.getX());
			enemyShoot(Main.getGame().geteB());
		}));
		animationMB.setCycleCount(Timeline.INDEFINITE);
		animationMB.play();
	}

	public void moveEnemys() {
		animationE = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			for (int i = 0; i < getEnemys().size(); i++) {
				toCorrectEnemys(Main.getGame().getList().get(i));
				getEnemys().get(i).setX(Main.getGame().getList().get(i).getPosX());
			}
		}));
		animationE.setCycleCount(Timeline.INDEFINITE);
		animationE.play();
	}

	public void enemyShoot(EnemyBoss thisE) {
		animacionBE = new Timeline(new KeyFrame(Duration.millis(30), e -> {
			if (thisE.isShooting()) {
				laserBoss.setVisible(true);
				laserBoss.setX(Main.getGame().geteB().getShoot().getX());
				laserBoss.setY(Main.getGame().geteB().getShoot().getY());
			} else {
				laserBoss.setVisible(false);
			}
		}));
		animacionBE.setCycleCount(Timeline.INDEFINITE);
		animacionBE.play();
	}

	public void moverI() {
		animacionI = new Timeline(new KeyFrame(Duration.millis(10), e -> {
			Main.getGame().getUser().getAvatar().setImage(Ship.LEFT);
			Main.getGame().getUser().getAvatar().moveLeft();
			imgUser.setImage(new Image(Main.getGame().getUser().getAvatar().getImage()));
			imgUser.setX(Main.getGame().getUser().getAvatar().getPosX());
			toCorrectUser();
		}));
		animacionI.setCycleCount(Timeline.INDEFINITE);
		animacionI.play();
		toCorrectUser();
	}

	public void moveD() {
		animacionD = new Timeline(new KeyFrame(Duration.millis(10), e -> {
			Main.getGame().getUser().getAvatar().setImage(Ship.RIGHT);
			Main.getGame().getUser().getAvatar().moveRight();
			imgUser.setImage(new Image(Main.getGame().getUser().getAvatar().getImage()));
			imgUser.setX(Main.getGame().getUser().getAvatar().getPosX());
			toCorrectUser();
		}));
		animacionD.setCycleCount(Timeline.INDEFINITE);
		animacionD.play();
		toCorrectUser();
	}

	public void shoot() {
		animacionB = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			Main.getGame().getUser().getAvatar().fire();
			laserU.setX(Main.getGame().getUser().getAvatar().getBullet().getX());
			laserU.setY(Main.getGame().getUser().getAvatar().getBullet().getY() + 60);
			if (Main.getGame().getUser().getLevel() == 1) {
				shootLvl1();
			} else {
				shootLvl2();
			}
		}));
		animacionB.setCycleCount(Timeline.INDEFINITE);
		animacionB.play();
	}

	public void shootLvl1() {
		for (int i = 0; i < getEnemys().size(); i++) {
			if (getEnemys().get(i).contains(new Point2D(laserU.getX(), laserU.getY()))) {
				Main.getGame().getList().get(i).setALive(false);
				Main.getGame().getUser()
						.setPoint(Main.getGame().getUser().getPoint() + Main.getGame().getList().get(i).getPoints());
				getEnemys().get(i).setVisible(false);
				pane.getChildren().remove(getEnemys().get(i));
				moveLvl();
			}
		}
		laserU.setVisible(true);
		Main.getGame().getUser().getAvatar().reloadP();
		stopShoot();
	}

	public void shootLvl2() {
		if (chicken.contains(new Point2D(laserU.getX(), laserU.getY()))) {
			Main.getGame().geteB().setLives(
					Main.getGame().geteB().getLives() - Main.getGame().getUser().getAvatar().getBullet().getDamage());
			Main.getGame().getUser().setPoint(Main.getGame().getUser().getPoint() + Main.getGame().geteB().getPoints());
			livesBoss.setProgress(
					livesBoss.getProgress() + Main.getGame().getUser().getAvatar().getBullet().getDamage());
			System.out.println(livesBoss.getProgress());
		}
		laserU.setVisible(true);
		Main.getGame().getUser().getAvatar().reloadP();
		stopShoot();
	}

	public void moveLvl() {
		int muertos = 0;
		for (int i = 0; i < Main.getGame().getList().size(); i++) {
			if (Main.getGame().getList().get(i).isALive() == false) {
				muertos++;
			}
		}
		if (muertos == Main.getGame().getList().size()) {
			Main.getGame().getUser().setLevel(2);
			stop(animationE);
			passBoss();
		}
	}

	public void toCorrectEnemys(Enemy thisE) {
		if (thisE.getAddress() == 'D' && thisE.getPosX() < bounds.getMaxX()) {
			thisE.moveD();
		} else if (thisE.getAddress() == 'D' && thisE.getPosX() >= bounds.getMaxX()) {
			thisE.setAddress('I');
			thisE.moveI();
		} else if (thisE.getAddress() == 'I' && thisE.getPosX() > bounds.getMinX()) {
			thisE.moveI();
		} else if (thisE.getAddress() == 'I' && thisE.getPosX() <= bounds.getMinX()) {
			thisE.setAddress('D');
			thisE.moveD();
		}
	}

	public void stopShoot() {
		if (Main.getGame().getUser().getAvatar().isShooting() == false) {
			stop(getAnimacionB());
			laserU.setVisible(false);
		}
	}

	public void stop(Timeline animacion) {
		animacion.pause();
	}

	public void toCorrectUser() {
		if (imgUser.getX() > bounds.getMaxX() - 40) {
			stop(animacionD);
		} else if (imgUser.getX() < bounds.getMinX() + 40) {
			stop(animacionI);
		}
	}

	public void initializeEnemys() {

		int cont = 0;
		while (cont < Main.getGame().getList().size()) {
			getEnemys()
					.add(new ImageView(new Image(Main.getGame().getList().get(cont).getImagen(), 50, 50, true, true)));
			cont++;
		}
		positionEnemy();
	}

	public void positionEnemy() {
		for (int i = 0; i < getEnemys().size(); i++) {
			getEnemys().get(i).setX(Main.getGame().getList().get(i).getPosX());
			getEnemys().get(i).setY(Main.getGame().getList().get(i).getPosY());
		}
	}

	public void primitive(GraphicsContext g) {
		try {
			final Font f = Font.loadFont(new FileInputStream(new File("src/img/ARCADECLASSIC.TTF")), 20);
			g.setFill(Color.BLACK);
			g.setFont(f);
			g.fillText("SCORE", 5, 20);
			g.fillText("TOP SCORE", 100, 20);
			try {
				g.fillText(Integer.toString(Main.getGame().SearchHigherScore()), 130, 35);
			} catch (TreeEmptyException e) {
				g.fillText("3000", 130, 35);
			}

			g.drawImage(new Image("img/nave.png"), 270, 10, 30, 30);
			g.drawImage(new Image("img/nave.png"), 300, 10, 30, 30);
			g.drawImage(new Image("img/nave.png"), 330, 10, 30, 30);
			paintScore(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paintScore(GraphicsContext g) {
		Timeline scoreAnimation = new Timeline(new KeyFrame(Duration.millis(1), e -> {
			g.setFont(new Font("ARCADECLASSIC", 20));
			g.setFill(Color.BLACK);
			g.fillText(Integer.toString(Main.getGame().getUser().getPoint()), 5, 40);
		}));
		scoreAnimation.setCycleCount(Timeline.INDEFINITE);
		scoreAnimation.play();
	}

	public void seeScores(int x) {
		Alert scores = new Alert(AlertType.NONE);

		ButtonType accept = new ButtonType("Aceptar");

		scores.getButtonTypes().add(accept);

		scores.setHeaderText("PUNTAJES JUGADORES");
		scores.setTitle("This game");
		if (x > 0) {
			scores.setContentText(Main.getGame().arrayToString(Main.getGame().orderPoint()));
		} else {
			scores.setContentText(Main.getGame().arrayToString(Main.getGame().orderName()));
		}
		Optional<ButtonType> result = scores.showAndWait();
		if (result.get() == accept) {
			scores.close();
		}
	}

	public void orderSlection() {
		Alert order = new Alert(AlertType.CONFIRMATION);

		ButtonType names = new ButtonType("Orden Alfabetico");
		ButtonType scores = new ButtonType("Orden Puntaje");
		ButtonType ages = new ButtonType("Orden Edad ascendente");
		ButtonType ages1 = new ButtonType("Orden Edad descendente");
		ButtonType gender = new ButtonType("Orden genero ascendete");
		ButtonType gender1 = new ButtonType("Orden genero descendente");

		order.getButtonTypes().clear();
		order.getButtonTypes().addAll(names, scores, ages, ages1, gender, gender1);
		order.setHeaderText(null);
		order.setContentText("Seleccione un tipo de ordenamiento");
		Optional<ButtonType> result = order.showAndWait();
		if (result.get() == names) {
			seeScores(-1);
		} else {
			seeScores(1);
		}
	}

	public void save() {
		Main.getGame().saveUser();
	}

	public void search(int x) {

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Buscar usuario");
		dialog.setHeaderText(null);

		Alert scores = new Alert(AlertType.NONE);
		scores.setHeaderText("PUNTAJES DE LOS JUGADORES");
		scores.setTitle("This game");

		ButtonType accept = new ButtonType("Aceptar");

		scores.getButtonTypes().add(accept);
		if (x > 0) {
			dialog.setContentText("Escribe el nombre del usuario que quieres buscar");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				try {
					scores.setContentText(Main.getGame().getTreeUser()
							.search(Main.getGame().getTreeUser().getRoot(), result.get()).toString());
					Optional<ButtonType> result2 = scores.showAndWait();
					if (result2.get() == accept) {
						scores.close();
					}
				} catch (NumberFormatException | UsersNotExistException e) {
					e.printStackTrace();
				}
			}
		} else {
			dialog.setContentText("Escribe el puntaje del usuario que quieres buscar");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				try {
					scores.setContentText(Main.getGame().searchBinary(Integer.parseInt(result.get())));
					Optional<ButtonType> result2 = scores.showAndWait();
					if (result2.get() == accept) {
						scores.close();
					}
				} catch (NumberFormatException | ScoreNotExistException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void selectSearch() {
		Alert selectSearch = new Alert(AlertType.CONFIRMATION);
		selectSearch.setContentText("Seleccione el tipo de busqueda");
		selectSearch.setHeaderText(null);
		selectSearch.getButtonTypes().clear();

		ButtonType names = new ButtonType("Buscar por nombre");
		ButtonType scores = new ButtonType("Buscar por puntaje");

		selectSearch.getButtonTypes().addAll(names, scores);
		Optional<ButtonType> result = selectSearch.showAndWait();
		if (result.get() == names) {
			search(1);
		} else {
			search(-1);
		}

	}

	public ArrayList<ImageView> getEnemys() {
		return enemys;
	}

	public void setEnemys(ArrayList<ImageView> enemys) {
		this.enemys = enemys;
	}

}
