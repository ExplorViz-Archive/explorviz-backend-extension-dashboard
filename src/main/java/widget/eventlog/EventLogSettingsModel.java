package widget.eventlog;

import com.github.jasminb.jsonapi.annotations.Type;

import net.explorviz.extension.dashboard.main.BaseModel;

@Type("eventlogsetting")
public class EventLogSettingsModel extends BaseModel {

	private int instanceID;
	private int entries;

	public EventLogSettingsModel() {
		// default constructor for JSON API parsing
	}

	public EventLogSettingsModel(int instanceID, int entries) {
		super();
		this.instanceID = instanceID;
		this.entries = entries;
	}

	public int getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(int instanceID) {
		this.instanceID = instanceID;
	}

	public int getEntries() {
		return entries;
	}

	public void setEntries(int entries) {
		this.entries = entries;
	}

	public String toString() {
		return "EventLogSetting - instanceID: " + this.instanceID + " entries: " + this.entries;
	}

}
