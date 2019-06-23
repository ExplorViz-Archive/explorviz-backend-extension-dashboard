package net.explorviz.extension.dashboard.services;

import net.explorviz.extension.dashboard.model.TotalOverviewWidgetModel;
import net.explorviz.shared.landscape.model.landscape.Landscape;

public class TotalOverviewWidgetService {

	private TotalOverviewWidgetModel model;

	public void start() {
		model = new TotalOverviewWidgetModel("test", 0, 0, 0);
	}

	public void update(Landscape l) {
		int numberOfSystems = l.getSystems().size();

		int numberOfNodes = 0;

		int numberOfApplications = 0;

		for (int i = 0; i < numberOfSystems; i++) {
			numberOfNodes += l.getSystems().get(i).getNodeGroups().size();

			for (int j = 0; j < l.getSystems().get(i).getNodeGroups().size(); j++) {
				numberOfNodes += l.getSystems().get(i).getNodeGroups().get(j).getNodes().size();

				for (int k = 0; k < l.getSystems().get(i).getNodeGroups().get(j).getNodes().size(); k++) {
					numberOfApplications += l.getSystems().get(i).getNodeGroups().get(j).getNodes().get(k)
							.getApplications().size();
				}
			}

		}

		model.setNumberOfSystems(numberOfSystems);
		model.setNumberOfNodes(numberOfNodes);
		model.setNumberOfApplications(numberOfApplications);

	}

	public TotalOverviewWidgetModel getTotalOverviewWidgetModel() {
		return this.model;
	}

}