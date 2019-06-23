package net.explorviz.extension.dashboard.model;

import com.github.jasminb.jsonapi.annotations.Type;

@Type("clazzcommunicationwidget")
public class ClazzCommunicationModel extends BaseModel {

	private String operationName;
	private int totalRequests;
	private float averageResponseTime;


	public ClazzCommunicationModel() {
		// default constructor for JSON API parsing
	}

	public ClazzCommunicationModel(final String operationName, final int totalRequests, final float averageResponseTime) {
		this.operationName = operationName;
		this.totalRequests = totalRequests;
		this.averageResponseTime = averageResponseTime;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
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
	
	


}
