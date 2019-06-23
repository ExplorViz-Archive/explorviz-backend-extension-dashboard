package net.explorviz.extension.dashboard.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.explorviz.extension.dashboard.model.ClazzCommunicationListModel;
import net.explorviz.extension.dashboard.model.ClazzCommunicationModel;
import net.explorviz.shared.landscape.model.application.ClazzCommunication;
import net.explorviz.shared.landscape.model.landscape.Landscape;
import persistence.MongoDashboardRepository;

public class ClazzCommunicationWidgetService {

	private ClazzCommunicationModel model;

	public void start() {

		model = new ClazzCommunicationModel("test", 0, 0);

		// MongoDashboardRepository.getInstance().saveClazzCommunication(2, "bla", 2,
		// 150);

		// Optional<String> test =
		// MongoDashboardRepository.getInstance().getClazzCommunicationByLandscapeID(2);
		// System.out.println(" " + test.get());
	}

	// Muss ausgetestet werden mit einem größeren Landscape wo auch wirklich klassen
	// drin vo9rkommen. eventuell als nächstes erstmal von oben nach unten arbeiten
	// also mit SystemCommunikation, dann NodeCommunikation etc etc
	public void update(Landscape l) {
		// System.out.println("Landscape id: " + l.getId() + " Timestamp: " +
		// l.getTimestamp());

		// System.out.println("Systems: " + l.getSystems().size());

		// Systeme i
		for (int i = 0; i < l.getSystems().size(); i++) {
			// System.out.println("NodeGroups: " +
			// l.getSystems().get(i).getNodeGroups().size());
			// NodeGroups j
			for (int j = 0; j < l.getSystems().get(i).getNodeGroups().size(); j++) {
				// System.out.println("Nodes: " +
				// l.getSystems().get(i).getNodeGroups().get(j).getNodes().size());
				// Nodes k
				for (int k = 0; k < l.getSystems().get(i).getNodeGroups().get(j).getNodes().size(); k++) {
					// System.out.println("Applikationen: " +
					// l.getSystems().get(i).getNodeGroups().get(j).getNodes().get(k).getApplications().size());
					// Applikationen n
					for (int n = 0; n < l.getSystems().get(i).getNodeGroups().get(j).getNodes().get(k).getApplications()
							.size(); n++) {
						// System.out.println("Components: " +
						// l.getSystems().get(i).getNodeGroups().get(j).getNodes().get(k).getApplications().get(n).getComponents().size());
						// Components m
						for (int m = 0; m < l.getSystems().get(i).getNodeGroups().get(j).getNodes().get(k)
								.getApplications().get(n).getComponents().size(); m++) {
							// System.out.println("Clazzes: " +
							// l.getSystems().get(i).getNodeGroups().get(j).getNodes().get(k).getApplications().get(n).getComponents().get(m).getClazzes().size());
							// Clazzes o
							for (int o = 0; o < l.getSystems().get(i).getNodeGroups().get(j).getNodes().get(k)
									.getApplications().get(n).getComponents().get(m).getClazzes().size(); o++) {
								List<ClazzCommunication> temp = l.getSystems().get(i).getNodeGroups().get(j).getNodes()
										.get(k).getApplications().get(n).getComponents().get(m).getClazzes().get(o)
										.getClazzCommunications();

								for (int p = 0; p < temp.size(); p++) {
									MongoDashboardRepository.getInstance().saveClazzCommunication(temp.get(p).getId(),
											temp.get(p).getOperationName(), temp.get(p).getTotalRequests(),
											temp.get(p).getAverageResponseTime());

									Optional<String> test = MongoDashboardRepository.getInstance()
											.getClazzCommunicationByLandscapeID(temp.get(p).getId());
									// System.out.println(" " + test.get());
								}

							}
						}
					}
				}
			}

		}

	}

	public ClazzCommunicationModel getClazzCommunicationModel(int id) {
		return this.model;
	}

	public ClazzCommunicationListModel getClazzCommunicationListModel() {
		ClazzCommunicationModel m1 = new ClazzCommunicationModel("test", 0, 0);
		ClazzCommunicationModel m2 = new ClazzCommunicationModel("bla", 12, 69);
		ClazzCommunicationModel m3 = new ClazzCommunicationModel("bli", 65, 7);
		ClazzCommunicationModel m4 = new ClazzCommunicationModel("blub", 57, 0);

		List<ClazzCommunicationModel> temp = new ArrayList<ClazzCommunicationModel>();
		temp.add(m1);
		temp.add(m2);
		temp.add(m3);
		temp.add(m4);

		ClazzCommunicationListModel listmodel = new ClazzCommunicationListModel();

		return listmodel;
	}

}
