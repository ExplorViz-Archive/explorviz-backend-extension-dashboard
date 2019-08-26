package widget.eventlog;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

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

	public static EventLogInfoModel convert(Map<String, Object> map) {
		long timestampLandscape = (long) map.get("timestampLandscape");
		int amountEvents = (int) map.get("amountEvents");

		return new EventLogInfoModel(timestampLandscape, amountEvents);
	}

	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "eventloginfo");
		result.put("timestampLandscape", this.timestampLandscape);
		result.put("amountEvents", this.amountEvents);

		return result;
	}

}
