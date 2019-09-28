package widget.eventlog;

import java.util.Hashtable;
import java.util.Map;

import com.github.jasminb.jsonapi.annotations.Type;

import net.explorviz.extension.dashboard.main.BaseModel;
import net.explorviz.shared.landscape.model.event.EEventType;

@Type("eventlog")
public class EventLogModel extends BaseModel {

	private long timestampLandscape;
	private long timestampEvent;
	private EEventType eventType;
	private String eventMessage;

	public EventLogModel() {
		// default constructor for JSON API parsing
	}

	public EventLogModel(long timestampLandscape, long timestampEvent, EEventType eventType, String eventMessage) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.timestampEvent = timestampEvent;
		this.eventType = eventType;
		this.eventMessage = eventMessage;
	}

	public long getTimestampLandscape() {
		return timestampLandscape;
	}

	public void setTimestampLandscape(long timestampLandscape) {
		this.timestampLandscape = timestampLandscape;
	}

	public long getTimestampEvent() {
		return timestampEvent;
	}

	public void setTimestampEvent(long timestampEvent) {
		this.timestampEvent = timestampEvent;
	}

	public EEventType getEventType() {
		return eventType;
	}

	public void setEventType(EEventType eventType) {
		this.eventType = eventType;
	}

	public String getEventMessage() {
		return eventMessage;
	}

	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}

	public String toString() {
		return "eventlog - timestampEvent: " + timestampEvent + " eventType: " + eventType.toString()
				+ " eventMessage: " + eventMessage;
	}

	/**
	 * This method convert a Map of a (string,object) pair with the models properties as keys and the right objects as values to a model.
	 * @param map a Map of (String, Object) pairs, that should have the properties of this model included.
	 * @return return a EventLogModel
	 */
	public static EventLogModel convert(Map<String, Object> map) {
		long timestampLandscape = (long) map.get("timestampLandscape");
		long timestampEvent = (long) map.get("timestampEvent");
		EEventType eventType =  EEventType.valueOf(map.get("eventType").toString());
		String eventMessage = (String) map.get("eventMessage");

		return new EventLogModel(timestampLandscape, timestampEvent, eventType, eventMessage);
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
		result.put("type", "eventlog");
		result.put("timestampLandscape", this.timestampLandscape);
		result.put("timestampEvent", this.timestampEvent);
		result.put("eventType", this.eventType.toString());
		result.put("eventMessage", this.eventMessage);

		return result;
	}

}
