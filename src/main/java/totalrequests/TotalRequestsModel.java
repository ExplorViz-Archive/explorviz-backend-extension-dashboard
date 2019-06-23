package totalrequests;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.model.BaseModel;

@Type("totalrequests")
public class TotalRequestsModel extends BaseModel {

	private String landscapeID;
	private int totalRequests;
	private long timestamp;

	public TotalRequestsModel() {
		// default constructor for JSON API parsing
	}

	public TotalRequestsModel(final String landscapeID, final int totalRequests, final long timestamp) {
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

}
