package widget.eventlog;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

/**
 * This is the model fot the EventLogInfo</br>
 * properties: </br>
 * {@link #timestampLandscape}</br>
 * {@link #amountEvents}
 * 
 * @author Florian Krippner
 *
 */
@Type("eventloginfo")
public class EventLogInfoModel extends BaseModel {

	private long timestampLandscape;
	private int amountEvents = 0;

	public EventLogInfoModel() {
		// default constructor for JSON API parsing
	}

	public EventLogInfoModel(long timestampLandscape, int amountEvents) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.amountEvents = amountEvents;

	}

	public long getTimestampLandscape() {
		return timestampLandscape;
	}

	public void setTimestampLandscape(long timestampLandscape) {
		this.timestampLandscape = timestampLandscape;
	}

	public int getAmountEvents() {
		return amountEvents;
	}

	public void setAmountEvents(int amountEvents) {
		this.amountEvents = amountEvents;
	}

	public String toString() {
		return "eventloginfo - timestampLandscape: " + timestampLandscape + " amountEvents: " + amountEvents;
	}

	/**
	 * This method convert a Map of a (string,object) pair with the models
	 * properties as keys and the right objects as values to a model.
	 * 
	 * @param map a Map of (String, Object) pairs, that should have the properties
	 *            of this model included.
	 * @return return a EventLogInfoModel
	 */
	public static EventLogInfoModel convert(Map<String, Object> map) {
		long timestampLandscape = (long) map.get("timestampLandscape");
		int amountEvents = (int) map.get("amountEvents");

		return new EventLogInfoModel(timestampLandscape, amountEvents);
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
		result.put("type", "eventloginfo");
		result.put("timestampLandscape", this.timestampLandscape);
		result.put("amountEvents", this.amountEvents);

		return result;
	}

}
