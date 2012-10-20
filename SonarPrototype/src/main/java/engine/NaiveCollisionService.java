package engine;

import java.util.ArrayList;
import java.util.List;

/**
 * A naïve collision service that uses all-vs-all radial collision test. Gotta
 * get everything working before premature optimization, right?
 * 
 * @author shrein
 * 
 */
public abstract class NaiveCollisionService implements CollisionService {

	private List<Model> models;

	public NaiveCollisionService() {
		super();
		this.models = new ArrayList<Model>();
	}

	@Override
	public void addModel(Model m) {
		models.add(m);
	}

	@Override
	public void performCollisions() {
		int size = models.size();
		for (int i = 0; i < size - 1; i++) {
			Model m1 = models.get(i);
			for (int j = i + 1; j < size; j++) {
				Model m2 = models.get(j);
				double diffX = m1.getX()-m2.getX();
				double diffY = m1.getY()-m2.getY();
				double distance = Math.sqrt(diffX*diffX+diffY*diffY);
				double radiusSum = m1.getRadius() + m2.getRadius();
				if(distance < radiusSum) {
					triggerCollision(m1, m2);
				}
			}
		}
	}

}
