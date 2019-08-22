package widget.totaloverview;

import com.github.jasminb.jsonapi.annotations.Type;

import net.explorviz.extension.dashboard.model.BaseModel;

@Type("totaloverviewwidget")
public class TotalOverviewModel extends BaseModel {

	private long timestamp;
	private int numberOfSystems;
	private int numberOfNodes;
	private int numberOfApplications;

	public TotalOverviewModel() {
		// default constructor for JSON API parsing
	}

	public TotalOverviewModel(final long timestamp, final int numberOfSystems, final int numberOfNodes,
			final int numberOfApplications) {
		super();
		this.timestamp = timestamp;
		this.numberOfSystems = numberOfSystems;
		this.numberOfNodes = numberOfNodes;
		this.numberOfApplications = numberOfApplications;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getNumberOfSystems() {
		return numberOfSystems;
	}

	public void setNumberOfSystems(int numberOfSystems) {
		this.numberOfSystems = numberOfSystems;
	}

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public void setNumberOfNodes(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}

	public int getNumberOfApplications() {
		return numberOfApplications;
	}

	public void setNumberOfApplications(int numberOfApplications) {
		this.numberOfApplications = numberOfApplications;
	}

	public String toString() {
		return "{timestamp: " + timestamp + ", numberOfSystems: " + numberOfSystems + ", numberOfNodes: "
				+ numberOfNodes + ", numberOfApplications: " + numberOfApplications + "}";
	}

}
