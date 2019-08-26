package widget.eventlog;

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

}
