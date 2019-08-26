package widget.aggregatedresponsetime;

import java.util.Hashtable;
import java.util.Map;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

@Type("aggregatedresponsetimeinfo")
public class AggregatedResponseTimeInfoModel extends BaseModel {

	private long timestampLandscape;
	private int entries;

	public AggregatedResponseTimeInfoModel() {
		// default constructor for JSON API parsing
	}

	public AggregatedResponseTimeInfoModel(long timestampLandscape, int entries) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.entries = entries;
	}

	public long getTimestampLandscape() {
		return timestampLandscape;
	}

	public void setTimestampLandscape(long timestampLandscape) {
		this.timestampLandscape = timestampLandscape;
	}

	public int getEntries() {
		return entries;
	}

	public void setEntries(int entries) {
		this.entries = entries;
	}

	public String toString() {
		return "aggregatedresponsetimeinfo - timestampLandscape: " + timestampLandscape + " entries: " + entries;
	}

	public static AggregatedResponseTimeInfoModel convert(Map<String, Object> map) {
		long timestampLandscape = (long) map.get("timestampLandscape");
		int entries = (int) map.get("entries");

		return new AggregatedResponseTimeInfoModel(timestampLandscape, entries);
	}

	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "aggregatedresponsetimeinfo");
		result.put("timestampLandscape", this.timestampLandscape);
		result.put("entries", this.entries);

		return result;
	}

}
