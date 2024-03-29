package instantiatedwidgets;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

/**
 * This class represents the model of a instantiated widget.
 * 
 * @author Florian Krippner
 */
@Type("instantiatedwidget")
public class InstantiatedWidgetModel extends BaseModel {

	private String userID;
	private String widgetName;
	private int instanceID;

	// a timestamp is needed to delete older instantiated widgets if needed.
	private long timestamp;

	// this is the id for the placeorder on the dashboard itself.
	private int orderID;

	public InstantiatedWidgetModel() {
		// default constructor for JSON API parsing
	}

	public InstantiatedWidgetModel(String userID, String widgetName, int instanceID, long timestamp, int orderID) {
		super();
		this.userID = userID;
		this.widgetName = widgetName;
		this.instanceID = instanceID;
		this.timestamp = timestamp;
		this.orderID = orderID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
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

	public String toString() {
		return "instantiatedwidget: [userID: " + userID + ", widgetName: " + widgetName + ", instanceID: " + instanceID
				+ ", timestamp: " + timestamp + ", orderID: " + orderID + "]";
	}
}
