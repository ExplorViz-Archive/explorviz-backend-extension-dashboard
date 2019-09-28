package widget.totalrequests;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

/**
 * This is the model of the total requests widget.</br>
 * properties:</br>
 * {@link #landscapeID}</br>
 * {@link #totalRequests}</br>
 * {@link #timestamp}
 * 
 * @author Florian Krippner
 *
 */
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

	/**
	 * This method convert a Map of a (string,object) pair with the models
	 * properties as keys and the right objects as values to a model.
	 * 
	 * @param map a Map of (String, Object) pairs, that should have the properties
	 *            of this model included.
	 * @return return a TotalRequestsModel
	 */
	public static TotalRequestsModel convert(Map<String, Object> map) {
		String landscapeID = (String) map.get("landscapeID");
		int totalRequests = (int) map.get("totalRequests");
		long timestamp = (long) map.get("timestamp");

		return new TotalRequestsModel(landscapeID, totalRequests, timestamp);
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
		result.put("type", "totalrequests");
		result.put("landscapeID", this.landscapeID);
		result.put("totalRequests", this.totalRequests);
		result.put("timestamp", this.timestamp);

		return result;
	}

}
