package instantiatedwidgets;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.model.BaseModel;

@Type("instantiatedwidget")
public class InstantiatedWidgetModel extends BaseModel {

	
	private long userID;
	private String widgetName;
	private int instanceID;
	private long timestamp;

	public InstantiatedWidgetModel() {
		// default constructor for JSON API parsing
	}

	public InstantiatedWidgetModel(long userID, String widgetName, int instanceID, long timestamp) {
		super();
		this.userID = userID;
		this.widgetName = widgetName;
		this.instanceID = instanceID;
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
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


	public String toString() 
	{
		return "instantiatedwidget: [userID: "+userID + ", widgetName: " +widgetName+ ", instanceID: "+ instanceID + ", timestamp: " + timestamp+"]";
	}
}
