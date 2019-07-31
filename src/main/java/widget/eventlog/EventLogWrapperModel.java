package widget.eventlog;

import java.util.List;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.model.BaseModel;

@Type("eventlogwrapper")
public class EventLogWrapperModel extends BaseModel {

	private long timestampLandscape;
	private int amountEvents = 0;
	private List<EventLogModel> eventlogs;

	public EventLogWrapperModel() {
		// default constructor for JSON API parsing
	}

	public EventLogWrapperModel(long timestampLandscape, int amountEvents, List<EventLogModel> eventlogs) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.amountEvents = amountEvents;
		this.eventlogs = eventlogs;
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

	public List<EventLogModel> getEventlogs() {
		return eventlogs;
	}

	public void setEventlogs(List<EventLogModel> eventlogs) {
		this.eventlogs = eventlogs;
	}

}
