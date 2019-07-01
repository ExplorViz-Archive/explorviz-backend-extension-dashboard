package instantiatedwidgets;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.model.BaseModel;

@Type("instantiatedwidget")
public class InstantiatedWidgetModel extends BaseModel {

	private long userID;
	private String widgetName;
	private int instanceID;

	public InstantiatedWidgetModel(long userID, String widgetName, int instanceID) {
		super();
		this.userID = userID;
		this.widgetName = widgetName;
		this.instanceID = instanceID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getWidgetName() {
		return widgetName;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	public int getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(int instanceID) {
		this.instanceID = instanceID;
	}

}
