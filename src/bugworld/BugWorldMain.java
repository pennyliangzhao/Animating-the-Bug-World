package bugworld;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.geometry.Bounds;

import javafx.geometry.Insets;

import javafx.stage.Stage;

import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;

import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;

import javafx.scene.text.Text;

public class BugWorldMain extends Application {
	private final Pane pane = new Pane();
	ArrayList<Bug> bugs = new ArrayList<>();
	ArrayList<Plant> plants = new ArrayList<>();
	KeyFrame frame = new KeyFrame(Duration.millis(0));
	private Timeline timeline = new Timeline();
	private final Slider slider = new Slider();
	Stage primaryStage = null;
	Scene scene;
	GridPane grid;

	double dx = 1;
	double dy = 1;


	@Override

	public void start(Stage primaryStage) throws Exception {

		BorderPane border = new BorderPane();

		HBox hbox = addHBox();

		border.setTop(hbox);

		border.setLeft(addVBox());

		this.grid = addGridPane();

		
		pane.setStyle("-fx-background-color: #3CB371;");

		border.setCenter(pane);

		border.setBottom(addBottomPane());

		Scene scene = new Scene(border, 600, 600);

		primaryStage.setTitle("Bug World");
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("bug title.png")));
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		Bounds boundsInScene = grid.localToScene(grid.getBoundsInLocal());

		Bounds boundsInScreen = grid.localToScreen(grid.getBoundsInLocal());

		System.out.println(grid.getLayoutBounds());

		System.out.println(boundsInScene);

		System.out.println(boundsInScreen);

		generateTimeine();

	}

	private void generateTimeine() {

		KeyFrame frame = new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (Bug b : bugs) {
					int direction = b.getDirection();
					if (direction == 2) {
						b.setDx(1);
					} else if (direction == 1) {
						b.setDx(-1);
					} else if (direction == 3) {
						b.setDy(-1);
					} else if (direction == 4) {
						b.setDy(1);

					}
	
					if (b.getTranslateX() + b.getX() + b.getFitWidth() >= pane.getWidth() - b.getFitWidth()
							|| b.getTranslateX() + b.getX()<= 0) {
						b.setDirection((int) (Math.random() * 4) + 1);
						b.setDx(-b.getDx());
					}
					if (b.getTranslateY() + b.getY()+b.getFitHeight() >= pane.getHeight() - b.getFitHeight()
							|| b.getTranslateY() + b.getY() <=0) {
						b.setDirection((int) (Math.random() * 4) + 1);
						b.setDy(-b.getDy());
					}

					b.setTranslateX(b.getTranslateX() + b.getDx());
					b.setTranslateY(b.getTranslateY() +b.getDy());
				
				}

			}
		});
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.getKeyFrames().add(frame);
	}

	public void addBugs() {
		for (Bug b : bugs) {
			if (!b.isAddedtoUI()) {
				pane.getChildren().add(b);
				b.setAddedtoUI(true);
			}
		}

	}

	public void addPlants() {
		pane.getChildren().removeAll(plants);
		for (Plant plants : plants) {
			pane.getChildren().addAll(plants);
		}
	}

	public void moveBugs() {
		timeline.play();
	}

	public void PauseMoving() {
		timeline.pause();
	}

	public void StopBugs() {
		timeline.stop();

	}

	public void restart() {
		pane.getChildren().removeAll(bugs);
		pane.getChildren().removeAll(plants);

	}

	public HBox addHBox() {

		HBox hbox = new HBox();

		hbox.setPadding(new Insets(15, 12, 15, 12));

		hbox.setSpacing(10);

		hbox.setStyle("-fx-background-color: #336699;");

		Button btnPlant = new Button("Show Plants");

		btnPlant.setPrefSize(80, 20);

		btnPlant.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addPlants());
		btnPlant.setCursor(Cursor.HAND);

		Button btnShow = new Button("Show Bugs");

		btnShow.setPrefSize(80, 20);

		btnShow.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addBugs());
		btnShow.setCursor(Cursor.HAND);
		Button btnMove = new Button("Start Moving");
		btnMove.setPrefSize(90, 20);

		btnMove.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> moveBugs());
		btnMove.setCursor(Cursor.HAND);

		Button btnPause = new Button("Pause");

		btnPause.setPrefSize(60, 20);

		btnPause.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> PauseMoving());
		btnPause.setCursor(Cursor.HAND);
		Button btnStop = new Button("Stop");

		btnStop.setPrefSize(60, 20);

		btnStop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> StopBugs());
		btnStop.setCursor(Cursor.HAND);
		Button restart = new Button("Restart");

		restart.setPrefSize(80, 20);

		restart.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> restart());
		restart.setCursor(Cursor.HAND);

		Button quit = new Button("Exit");

		quit.setPrefSize(60, 20);

		quit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
		quit.setCursor(Cursor.HAND);

		hbox.getChildren().addAll(btnPlant, btnShow, btnMove, btnPause, btnStop, restart, quit);

		return hbox;

	}

	public VBox addVBox() {

		VBox vbox = new VBox();

		vbox.setPadding(new Insets(10));

		vbox.setSpacing(8);

		Text title = new Text("What to add?");

		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		vbox.getChildren().add(title);

		Hyperlink options[] = new Hyperlink[] { new Hyperlink("Plants"), new Hyperlink("Bee"), new Hyperlink("LadyBug"),
				new Hyperlink("Ant"), new Hyperlink("Beetle") };

		for (int i = 0; i < 5; i++) {

			VBox.setMargin(options[i], new Insets(0, 0, 0, 8));

			vbox.getChildren().add(options[i]);

			final Hyperlink hyperlink = options[i];

			hyperlink.setOnAction(new EventHandler<ActionEvent>() {

				@Override

				public void handle(ActionEvent t) {

					double x = (Math.random() * pane.getPrefWidth()) + 100;
					double y = (Math.random() * pane.getPrefHeight()) + 100;

					switch (hyperlink.getText()) {
					case "Plants":
						try {
							Plant trees = new Plant(0, 0, 500, 500, "plants.png");
							plants.add(trees);


						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case "Bee":
						try {
							Bee bee = new Bee(50, 50, 60, 60, "bee.png");
							bugs.add(bee);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						break;

					case "LadyBug":
						try {
							Ladybug ladybug = new Ladybug(50, 150, 50, 50, "ladybug.png");
							bugs.add(ladybug);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case "Ant":
						try {
							Ant ant = new Ant(50, 250, 40, 40, "ant.png");
							bugs.add(ant);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case "Beetle":
						try {
							Beetle beetle = new Beetle(50, 350, 50, 50, "beetle.png");
							bugs.add(beetle);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						break;
					}

				}

			});

		}

		return vbox;

	}

	private HBox addBottomPane()

	{

		HBox hbox = new HBox();

		hbox.setPadding(new Insets(15, 12, 15, 12));

		hbox.setSpacing(10);

		hbox.setStyle("-fx-background-color: #336699;");
		Slider slider = new Slider(1, 10, 5);
		slider.setMin(1);
		slider.setMax(10);

		slider.setMinorTickCount(0);
		slider.setMajorTickUnit(1);
		slider.setSnapToTicks(true);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);

		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override

			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {

				timeline.setRate(slider.getValue());

			}

		});


		Text category = new Text("Welcome to Bug World...   Speed Controller");

		category.setFill(Color.BLACK);

		category.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		hbox.getChildren().addAll(category, slider);

		return hbox;

	}

	public GridPane addGridPane() {

		GridPane grid = new GridPane();

		grid.setStyle("-fx-background-color: #3CB371;");

		grid.setHgap(10);

		grid.setVgap(10);

		grid.setPadding(new Insets(0, 10, 0, 10));

		return grid;

	}

	public static void main(String[] args) {

		launch(args);

	}
}