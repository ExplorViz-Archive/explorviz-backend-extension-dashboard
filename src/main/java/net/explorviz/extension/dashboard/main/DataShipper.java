package net.explorviz.extension.dashboard.main;

import java.util.ArrayList;
import java.util.List;
import net.explorviz.shared.landscape.model.application.AggregatedClazzCommunication;
import net.explorviz.shared.landscape.model.application.Clazz;
import net.explorviz.shared.landscape.model.application.ClazzCommunication;
import net.explorviz.shared.landscape.model.application.Component;
import net.explorviz.shared.landscape.model.landscape.Landscape;
import net.explorviz.shared.landscape.model.landscape.Node;
import net.explorviz.shared.landscape.model.landscape.NodeGroup;
import net.explorviz.shared.landscape.model.landscape.System;
import widget.activeclassinstances.ActiveClassInstancesModel;
import widget.activeclassinstances.ActiveClassInstancesService;
import widget.aggregatedresponsetime.AggregatedResponseTimeModel;
import widget.aggregatedresponsetime.AggregatedResponseTimeService;
import widget.eventlog.EventLogService;
import widget.operationresponsetime.OperationResponseTimeModel;
import widget.operationresponsetime.OperationResponseTimeService;
import widget.programminglanguage.ProgrammingLanguagesModel;
import widget.programminglanguage.ProgrammingLanguagesService;
import widget.ramcpu.RamCpuModel;
import widget.ramcpu.RamCpuService;
import widget.totaloverview.TotalOverviewModel;
import widget.totaloverview.TotalOverviewService;
import widget.totalrequests.TotalRequestsModel;
import widget.totalrequests.TotalRequestsService;

/**
 * This class is for matching the data from the landscape to all other widgets
 * This needs to be done, so we only itterate throw the landscape once. This
 * class is a singelton
 * 
 * @author Florian Krippner
 */
public class DataShipper {

	private static DataShipper instance;

	private DataShipper() {
	}

	public static DataShipper getInstance() {
		if (DataShipper.instance == null) {
			DataShipper.instance = new DataShipper();
		}
		return DataShipper.instance;
	}

	// setting up empty list of models for the widget update functions. the list
	// will be filled inside the landscape iteration.
	private List<ActiveClassInstancesModel> activeClassInstances = new ArrayList<ActiveClassInstancesModel>();
	private List<OperationResponseTimeModel> operationResponseTimes = new ArrayList<OperationResponseTimeModel>();
	private List<ProgrammingLanguagesModel> programmingLanguageList = new ArrayList<ProgrammingLanguagesModel>();
	private List<RamCpuModel> ramCpuModelList = new ArrayList<RamCpuModel>();
	private List<AggregatedResponseTimeModel> aggregatedResponseTimes = new ArrayList<AggregatedResponseTimeModel>();

	public void update(Landscape l) {
		// saving the current time, to measure up the executing time
		long startTime = java.lang.System.currentTimeMillis();

		// send all the events to the EventLogService
		EventLogService.getInstance().update(l.getTimestamp().getTimestamp(), l.getEvents());

		// Send all total requests to the TotalRequestsService
		TotalRequestsService.getInstance().update(new TotalRequestsModel(l.getId(), l.getTimestamp().getTotalRequests(),
				l.getTimestamp().getTimestamp()));

		// set all lists empty again
		activeClassInstances = new ArrayList<ActiveClassInstancesModel>();
		operationResponseTimes = new ArrayList<OperationResponseTimeModel>();
		programmingLanguageList = new ArrayList<ProgrammingLanguagesModel>();
		ramCpuModelList = new ArrayList<RamCpuModel>();
		aggregatedResponseTimes = new ArrayList<AggregatedResponseTimeModel>();

		TotalOverviewModel totalOverviewModel = new TotalOverviewModel(0, 0, 0, 0);
		totalOverviewModel.setTimestamp(l.getTimestamp().getTimestamp());
		List<System> systems = l.getSystems();
		totalOverviewModel.setNumberOfSystems(systems.size());

		// Iterate through all the existing Systems
		for (int i = 0; i < systems.size(); i++) {

			List<NodeGroup> nodeGroups = systems.get(i).getNodeGroups();

			// Iterate through all the nodegroups inside a system
			for (int j = 0; j < nodeGroups.size(); j++) {

				List<Node> nodes = nodeGroups.get(j).getNodes();

				totalOverviewModel.setNumberOfNodes(nodes.size() + totalOverviewModel.getNumberOfNodes());

				// Iterate through all the nodes inside a nodegroup
				for (int k = 0; k < nodes.size(); k++) {

					long timestamp = l.getTimestamp().getTimestamp();
					String nodeName = nodes.get(k).getDisplayName();
					double cpuUtilization = nodes.get(k).getCpuUtilization();
					long freeRam = nodes.get(k).getFreeRAM();
					long usedRam = nodes.get(k).getUsedRAM();

					ramCpuModelList.add(new RamCpuModel(timestamp, nodeName, cpuUtilization, freeRam, usedRam));

					List<net.explorviz.shared.landscape.model.application.Application> applications = nodes.get(k)
							.getApplications();

					totalOverviewModel.setNumberOfApplications(
							applications.size() + totalOverviewModel.getNumberOfApplications());

					// Iterate through all the applications inside a node
					for (int n = 0; n < applications.size(); n++) {

						// iterate through the aggregatedclazzcommunication
						checkAggregatedClazzCommunications(applications.get(n).getAggregatedClazzCommunications(), l);

						List<Component> components = applications.get(n).getComponents();

						ProgrammingLanguagesModel programmingLanguagesModel = new ProgrammingLanguagesModel(
								l.getTimestamp().getTimestamp(), applications.get(n).getName(),
								applications.get(n).getProgrammingLanguage());
						programmingLanguageList.add(programmingLanguagesModel);

						// Iterate through all the compontens inside a application
						checkComponents(components, l);

					}
				}
			}

		}

		// Update all the widgets with the needed and set data
		ActiveClassInstancesService.getInstance().update(activeClassInstances);
		OperationResponseTimeService.getInstance().update(operationResponseTimes);
		ProgrammingLanguagesService.getInstance().update(programmingLanguageList);
		RamCpuService.getInstance().update(ramCpuModelList);
		TotalOverviewService.getInstance().update(totalOverviewModel);
		AggregatedResponseTimeService.getInstance().update(aggregatedResponseTimes);

		// calculate the executing time
		long stopTime = java.lang.System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		java.lang.System.out.println("Datashipper - Measured execution time: " + elapsedTime + "ms");
	}

	/**
	 * This method runs through all the AggregatedClazzCommunications
	 * 
	 * @param list the list of the AggregatedClazzCommunications
	 * @param l    the current landscape
	 */
	private void checkAggregatedClazzCommunications(List<AggregatedClazzCommunication> list, Landscape l) {
		List<AggregatedResponseTimeModel> aggregatedResponseTimes = new ArrayList<AggregatedResponseTimeModel>();

		for (int i = 0; i < list.size(); i++) {

			long timestampLandscape = l.getTimestamp().getTimestamp();
			int totalRequests = list.get(i).getTotalRequests();
			float averageResponseTime = list.get(i).getAverageResponseTime();
			String sourceClazzFullName = list.get(i).getSourceClazz().getFullQualifiedName();
			String targetClazzFullName = list.get(i).getTargetClazz().getFullQualifiedName();

			// quickfix. kieker give us sometimes invalid respone times like -1 sometimes
			if (averageResponseTime != -1) {
				AggregatedResponseTimeModel temp = new AggregatedResponseTimeModel(timestampLandscape, totalRequests,
						averageResponseTime, sourceClazzFullName, targetClazzFullName);
				aggregatedResponseTimes.add(temp);
			}
		}

		this.aggregatedResponseTimes.addAll(aggregatedResponseTimes);

	}

	/**
	 * This method iterates through all the components recursive
	 * 
	 * @param c a list of all the components
	 * @param l the current landscape
	 */

	private void checkComponents(List<Component> c, Landscape l) {
		// Iterate through the component list
		for (int i = 0; i < c.size(); i++) {

			// if a class is inside a component
			if (c.get(i).getClazzes() != null) {

				List<Clazz> clazzes = c.get(i).getClazzes();
				// Iterate through the clazzes
				for (int j = 0; j < clazzes.size(); j++) {

					Clazz clazz = clazzes.get(j);

					if (clazz.getInstanceCount() != 0) {
						activeClassInstances.add(new ActiveClassInstancesModel(l.getTimestamp().getTimestamp(),
								clazz.getName(), clazz.getInstanceCount()));
					}
					// if clazzcommucations exists
					if (clazz.getClazzCommunications() != null) {
						List<ClazzCommunication> clazzCommunications = clazz.getClazzCommunications();

						// Iterate through the clazzCommunications
						for (int k = 0; k < clazzCommunications.size(); k++) {
							ClazzCommunication clazzCommunication = clazzCommunications.get(k);
							OperationResponseTimeModel temp = new OperationResponseTimeModel(
									l.getTimestamp().getTimestamp(), clazzCommunication.getOperationName(),
									clazzCommunication.getAverageResponseTime(),
									clazzCommunication.getSourceClazz().getFullQualifiedName(),
									clazzCommunication.getTargetClazz().getFullQualifiedName());

							// hotfix - kieker is generating sometimes a response time of -1
							if (temp.getAverageResponseTime() != -1) {
								operationResponseTimes.add(temp);

							}
						}
					}

				}
			}
			// if some component is inside this component
			if (c.get(i).getChildren() != null) {

				// check childcomponents recursive
				checkComponents(c.get(i).getChildren(), l);
			}

		}
	}

}
