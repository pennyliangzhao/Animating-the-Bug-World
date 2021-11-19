package bugworld;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plant extends ImageView {
	private boolean isAddedtoUI = false;
	private double dx = 0, dy = 0;
	private int direction = 0;

	public Plant(double x, double y, double height, double width, String url) throws FileNotFoundException {
		setImage(new Image(new FileInputStream(url)));
		setX(x);
		setY(y);
		setFitHeight(height);
		setFitWidth(width);
	}
}