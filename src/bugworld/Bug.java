package bugworld;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bug extends ImageView {
	private boolean isAddedtoUI = false;
	private double dx = 0, dy = 0;
	private int direction = 0;

	public Bug(double x, double y, double height, double width, String url) throws FileNotFoundException {
		setImage(new Image(new FileInputStream(url)));
		setX(x);
		setY(y);
		setFitHeight(height);
		setFitWidth(width);
		direction = (int) (Math.random() * 4) + 1;
	}

	public boolean isAddedtoUI() {
		return isAddedtoUI;
	}

	public void setAddedtoUI(boolean isAddedtoUI) {
		this.isAddedtoUI = isAddedtoUI;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
