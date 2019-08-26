package widget.eventlog;

import java.util.Hashtable;
import java.util.Map;
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
		return "eventlogsetting - instanceID: " + this.instanceID + " entries: " + this.entries;
	}

	public static EventLogSettingsModel convert(Map<String, Object> map) {
		int instanceID = (int) map.get("instanceID");
		int entries = (int) map.get("entries");

		return new EventLogSettingsModel(instanceID, entries);
	}

	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "eventlogsetting");
		result.put("instanceID", this.instanceID);
		result.put("entries", this.entries);

		return result;
	}

}
