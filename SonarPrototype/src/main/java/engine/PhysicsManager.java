package engine;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import processing.core.PApplet;

public class PhysicsManager {

	private List<Model> entities;
	@Inject	private PApplet parent;
	
	public PhysicsManager() {
		entities = new ArrayList<Model>();
	}

	public void setup() {
		// TODO Auto-generated method stub
		
	}

	public PApplet getParent() {
		return parent;
	}
	
	public void addEntity(Model e) {
		entities.add(e);
	}
	

}
