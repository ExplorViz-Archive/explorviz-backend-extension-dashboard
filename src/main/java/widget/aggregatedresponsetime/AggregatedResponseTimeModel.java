package widget.aggregatedresponsetime;

import java.util.Hashtable;
import java.util.Map;

import com.github.jasminb.jsonapi.annotations.Type;

import net.explorviz.extension.dashboard.main.BaseModel;

@Type("aggregatedresponsetime")
public class AggregatedResponseTimeModel extends BaseModel {

	private long timestampLandscape;
	private int totalRequests;
	private float averageResponseTime;
	private String sourceClazzFullName;
	private String targetClazzFullName;

	public AggregatedResponseTimeModel() {
		// default constructor for JSON API parsing
	}

	public AggregatedResponseTimeModel(long timestampLandscape, int totalRequests, float averageResponseTime,
			String sourceClazzFullName, String targetClazzFullName) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.totalRequests = totalRequests;
		this.averageResponseTime = averageResponseTime;
		this.sourceClazzFullName = sourceClazzFullName;
		this.targetClazzFullName = targetClazzFullName;
	}

	public long getTimestampLandscape() {
		return timestampLandscape;
	}

	public void setTimestampLandscape(long timestampLandscape) {
		this.timestampLandscape = timestampLandscape;
	}

	public int getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(int totalRequests) {
		this.totalRequests = totalRequests;
	}

	public float getAverageResponseTime() {
		return averageResponseTime;
	}

	public void setAverageResponseTime(float averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}

	public String getSourceClazzFullName() {
		return sourceClazzFullName;
	}

	public void setSourceClazzFullName(String sourceClazzFullName) {
		this.sourceClazzFullName = sourceClazzFullName;
	}

	public String getTargetClazzFullName() {
		return targetClazzFullName;
	}

	public void setTargetClazzFullName(String targetClazzFullName) {
		this.targetClazzFullName = targetClazzFullName;
	}

	public String toString() {
		return "aggregatedresponsetime - timestampLandscape: " + timestampLandscape + " totalRequests: " + totalRequests
				+ " averageResponseTime" + averageResponseTime + " sourceClazzFullName: " + sourceClazzFullName
				+ " targetClazzFullName: " + targetClazzFullName;
	}

	public static AggregatedResponseTimeModel convert(Map<String, Object> map) {
		long timestampLandscape = (long) map.get("timestampLandscape");
		int totalRequests = (int) map.get("totalRequests");
		float averageResponseTime = Float.parseFloat(map.get("averageResponseTime").toString());
		String sourceClazzFullName = (String) map.get("sourceClazzFullName");
		String targetClazzFullName = (String) map.get("targetClazzFullName");

		return new AggregatedResponseTimeModel(timestampLandscape, totalRequests, averageResponseTime,
				sourceClazzFullName, targetClazzFullName);
	}

	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "aggregatedresponsetime");
		result.put("timestampLandscape", this.timestampLandscape);
		result.put("totalRequests", this.totalRequests);
		result.put("averageResponseTime", this.averageResponseTime);
		result.put("sourceClazzFullName", this.sourceClazzFullName);
		result.put("targetClazzFullName", this.targetClazzFullName);

		return result;
	}

}
