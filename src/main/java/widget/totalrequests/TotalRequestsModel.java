package widget.totalrequests;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;


@Type("totalrequests")
public class TotalRequestsModel extends BaseModel {

	private String landscapeID;
	private int totalRequests;
	private long timestamp;

	public TotalRequestsModel() {
		// default constructor for JSON API parsing
	}

	public TotalRequestsModel(final String landscapeID, final int totalRequests, final long timestamp) {
		super();
		this.landscapeID = landscapeID;
		this.totalRequests = totalRequests;
		this.timestamp = timestamp;
	}

	public String getLandscapeID() {
		return landscapeID;
	}

	public void setLandscapeID(String landscapeID) {
		this.landscapeID = landscapeID;
	}

	public int getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(int totalRequests) {
		this.totalRequests = totalRequests;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String toString() {
		return "totalrequests - landscapeID: " + landscapeID + " totalRequests: " + totalRequests + " timestamp: "
				+ timestamp;
	}

	public static TotalRequestsModel convert(Map<String, Object> map) {
		String landscapeID = (String) map.get("landscapeID");
		int totalRequests = (int) map.get("totalRequests");
		long timestamp = (long) map.get("timestamp");

		return new TotalRequestsModel(landscapeID, totalRequests, timestamp);
	}

	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "totalrequests");
		result.put("landscapeID", this.landscapeID);
		result.put("totalRequests", this.totalRequests);
		result.put("timestamp", this.timestamp);

		return result;
	}

}
