package widget.activeclassinstances;

import java.util.List;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.model.BaseModel;

@Type("activeclassinstances")
public class ActiveClassInstancesModel extends BaseModel {

	public static List<ActiveClassInstancesModel> ActiveClassInstances;

	private String landscapeID;
	private String className;
	private int instances;

	public ActiveClassInstancesModel() {
		// default constructor for JSON API parsing
	}

	public ActiveClassInstancesModel(final String landscapeID, final String className, final int instances) {
		this.landscapeID = landscapeID;
		this.className = className;
		this.instances = instances;
	}

	public String getLandscapeID() {
		return landscapeID;
	}

	public void setLandscapeID(String landscapeID) {
		this.landscapeID = landscapeID;
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
