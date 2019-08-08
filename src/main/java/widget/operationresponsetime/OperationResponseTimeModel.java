package widget.operationresponsetime;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.model.BaseModel;

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
		return "OperationResponseTimeModel - timestampLandscape: " + timestampLandscape + " operationName: "
				+ operationName + " averageResponseTime: " + averageResponseTime;
	}
}
