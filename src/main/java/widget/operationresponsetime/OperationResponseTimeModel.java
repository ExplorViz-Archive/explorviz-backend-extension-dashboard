package widget.operationresponsetime;

import java.util.Hashtable;
import java.util.Map;

import com.github.jasminb.jsonapi.annotations.Type;

import net.explorviz.extension.dashboard.main.BaseModel;

@Type("operationresponsetime")
public class OperationResponseTimeModel extends BaseModel {

	private long timestampLandscape;
	private String operationName;
	private float averageResponseTime;
	private String sourceClazzFullName;
	private String targetClazzFullName;

	public OperationResponseTimeModel() {
		// default constructor for JSON API parsing
	}

	public OperationResponseTimeModel(long timestampLandscape, String operationName, float averageResponseTime,
			String sourceClazzFullName, String targetClazzFullName) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.operationName = operationName;
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

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
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
		return "operationresponsetime - timestampLandscape: " + timestampLandscape + " operationName: " + operationName
				+ " averageResponseTime: " + averageResponseTime + " sourceClazzFullName: " + sourceClazzFullName
				+ " targetClazzFullName: " + targetClazzFullName;
	}

	public static OperationResponseTimeModel convert(Map<String, Object> map) {
		long timestampLandscape = (long) map.get("timestampLandscape");
		String operationName = (String) map.get("operationName");
		float averageResponseTime = Float.parseFloat(map.get("averageResponseTime").toString());
		String sourceClazzFullName = (String) map.get("sourceClazzFullName");
		String targetClazzFullName = (String) map.get("targetClazzFullName");

		return new OperationResponseTimeModel(timestampLandscape, operationName, averageResponseTime,
				sourceClazzFullName, targetClazzFullName);
	}

	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "operationresponsetime");
		result.put("timestampLandscape", this.timestampLandscape);
		result.put("operationName", this.operationName);
		result.put("averageResponseTime", this.averageResponseTime);
		result.put("sourceClazzFullName", this.sourceClazzFullName);
		result.put("targetClazzFullName", this.targetClazzFullName);

		return result;
	}
}
