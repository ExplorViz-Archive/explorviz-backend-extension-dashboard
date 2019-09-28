package widget.aggregatedresponsetime;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

/**
 * This is the model for the AggregatedResponseTimeInfo </br>
 * properties: </br>
 * {@link #timestampLandscape}</br>
 * {@link #entries}
 * 
 * @author Florian Krippner
 *
 */
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

	/**
	 * This method converts a Map of (string,object) pairs to a
	 * AggregatedResponseTimeInfoModel the map needs the keys timestampLandscape and
	 * entries
	 * 
	 * @param map
	 * @return returns a AggregatedResponseTimeInfoModel
	 */
	public static AggregatedResponseTimeInfoModel convert(Map<String, Object> map) {
		long timestampLandscape = (long) map.get("timestampLandscape");
		int entries = (int) map.get("entries");

		return new AggregatedResponseTimeInfoModel(timestampLandscape, entries);
	}

	/**
	 * This method convert this object to a key/value format in a map.
	 * 
	 * @return returns a map of (String, Object) pairs.
	 */
	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "aggregatedresponsetimeinfo");
		result.put("timestampLandscape", this.timestampLandscape);
		result.put("entries", this.entries);

		return result;
	}

}
