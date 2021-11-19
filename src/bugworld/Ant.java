package bugworld;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class Ant extends Bug {
	
//	private List<BugWorldObject>food = new ArrayList<BugWorldObject>();

	public Ant(double x, double y, double height, double width, String url) throws FileNotFoundException {
		super(x, y, height, width, url);
	}

}
