package net.explorviz.extension.dashboard.model;

import com.github.jasminb.jsonapi.annotations.Type;

@Type("totaloverviewwidget")
public class TotalOverviewWidgetModel extends BaseModel {

	private String name;
	private int numberOfSystems;
	private int numberOfNodes;
	private int numberOfApplications;

	public TotalOverviewWidgetModel() {
		// default constructor for JSON API parsing
	}

	public TotalOverviewWidgetModel(final String name, final int numberOfSystems, final int numberOfNodes,
			final int numberOfApplications) {
		this.name = name;
		this.numberOfSystems = numberOfSystems;
		this.numberOfNodes = numberOfNodes;
		this.numberOfApplications = numberOfApplications;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
