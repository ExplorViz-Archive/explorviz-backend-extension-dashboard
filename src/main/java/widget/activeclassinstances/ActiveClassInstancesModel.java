package widget.activeclassinstances;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

/**
 * This clas is the model for the Active class instances Widget.<br>
 * 
 * properties:<br>
 * {@link #timestampLandscape}<br>
 * {@link #className}<br>
 * {@link #instances}<br>
 * 
 * @author Florian Krippner
 *
 */
@Type("activeclassinstances")
public class ActiveClassInstancesModel extends BaseModel {

	private long timestampLandscape;
	private String className;
	private int instances;

	public ActiveClassInstancesModel() {
		// default constructor for JSON API parsing
	}

	public ActiveClassInstancesModel(final long timestampLandscape, final String className, final int instances) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.className = className;
		this.instances = instances;
	}

	public long getTimestampLandscape() {
		return timestampLandscape;
	}

	public void setTimestampLandscape(long timestampLandscape) {
		this.timestampLandscape = timestampLandscape;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getInstances() {
		return instances;
	}

	public void setInstances(int instances) {
		this.instances = instances;
	}

}
