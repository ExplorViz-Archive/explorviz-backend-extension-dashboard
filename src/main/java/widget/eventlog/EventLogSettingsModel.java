package widget.eventlog;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

/**
 * This is the model for the EventLogSettings
 * 
 * @author Florian Krippner
 *
 */
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

	/**
	 * This method convert a Map of a (string,object) pair with the models
	 * properties as keys and the right objects as values to a model.
	 * 
	 * @param map a Map of (String, Object) pairs, that should have the properties
	 *            of this model included.
	 * @return return a EventLogSettingsModel
	 */
	public static EventLogSettingsModel convert(Map<String, Object> map) {
		int instanceID = (int) map.get("instanceID");
		int entries = (int) map.get("entries");

		return new EventLogSettingsModel(instanceID, entries);
	}

	/**
	 * This method convert this model into a Map of (String, Object)
	 * reprensentation. The key/string is a propertiename of this model and the
	 * value/Object is the content.
	 * 
	 * @return returns a Map of (String,Object) pairs, which represent this model.
	 */
	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "eventlogsetting");
		result.put("instanceID", this.instanceID);
		result.put("entries", this.entries);

		return result;
	}

}
