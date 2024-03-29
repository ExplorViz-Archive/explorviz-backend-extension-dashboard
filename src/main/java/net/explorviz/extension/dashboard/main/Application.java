package net.explorviz.extension.dashboard.main;

import net.explorviz.shared.common.provider.GenericTypeFinder;
import net.explorviz.shared.common.provider.JsonApiListProvider;
import net.explorviz.shared.common.provider.JsonApiProvider;
import net.explorviz.shared.landscape.model.helper.TypeProvider;
import net.explorviz.shared.security.filters.AuthenticationFilter;
import net.explorviz.shared.security.filters.AuthorizationFilter;
import net.explorviz.shared.security.filters.CorsResponseFilter;
import widget.activeclassinstances.ActiveClassInstancesModel;
import widget.activeclassinstances.ActiveClassInstancesResource;
import widget.aggregatedresponsetime.AggregatedResponseTimeInfoModel;
import widget.aggregatedresponsetime.AggregatedResponseTimeInfoResource;
import widget.aggregatedresponsetime.AggregatedResponseTimeModel;
import widget.aggregatedresponsetime.AggregatedResponseTimeResource;
import widget.eventlog.EventLogModel;
import widget.eventlog.EventLogResource;
import widget.eventlog.EventLogSettingsModel;
import widget.eventlog.EventLogSettingsResource;
import widget.operationresponsetime.OperationResponseTimeInfoModel;
import widget.operationresponsetime.OperationResponseTimeInfoResource;
import widget.operationresponsetime.OperationResponseTimeModel;
import widget.operationresponsetime.OperationResponseTimeResource;
import widget.eventlog.EventLogInfoModel;
import widget.eventlog.EventLogInfoResource;
import widget.programminglanguage.ProgrammingLanguagesModel;
import widget.programminglanguage.ProgrammingLanguagesOccurrenceModel;
import widget.programminglanguage.ProgrammingLanguagesResource;
import widget.ramcpu.RamCpuModel;
import widget.ramcpu.RamCpuResource;
import widget.ramcpu.RamCpuSettingsModel;
import widget.ramcpu.RamCpuSettingsResource;
import widget.totaloverview.TotalOverviewModel;
import widget.totaloverview.TotalOverviewResource;
import widget.totalrequests.TotalRequestsModel;
import widget.totalrequests.TotalRequestsResource;
import org.glassfish.jersey.server.ResourceConfig;
import instantiatedwidgets.InstantiatedWidgetModel;
import instantiatedwidgets.InstantiatedWidgetResource;

public class Application extends ResourceConfig {

	public Application() {

		GenericTypeFinder.getTypeMap().put("TotalOverviewModel", TotalOverviewModel.class);
		GenericTypeFinder.getTypeMap().put("ActiveClassInstancesModel", ActiveClassInstancesModel.class);
		GenericTypeFinder.getTypeMap().put("TotalRequestsModel", TotalRequestsModel.class);
		GenericTypeFinder.getTypeMap().put("ProgrammingLanguagesModel", ProgrammingLanguagesModel.class);
		GenericTypeFinder.getTypeMap().put("ProgrammingLanguagesOccurrenceModel",
				ProgrammingLanguagesOccurrenceModel.class);
		GenericTypeFinder.getTypeMap().put("RamCpuModel", RamCpuModel.class);
		GenericTypeFinder.getTypeMap().put("InstantiatedWidgetModel", InstantiatedWidgetModel.class);
		GenericTypeFinder.getTypeMap().put("RamCpuSettingsModel", RamCpuSettingsModel.class);
		GenericTypeFinder.getTypeMap().put("EventLogModel", EventLogModel.class);
		GenericTypeFinder.getTypeMap().put("EventLogInfoModel", EventLogInfoModel.class);
		GenericTypeFinder.getTypeMap().put("EventLogSettingsModel", EventLogSettingsModel.class);
		GenericTypeFinder.getTypeMap().put("OperationResponseTimeModel", OperationResponseTimeModel.class);
		GenericTypeFinder.getTypeMap().put("OperationResponseTimeInfoModel", OperationResponseTimeInfoModel.class);
		GenericTypeFinder.getTypeMap().put("AggregatedResponseTimeModel", AggregatedResponseTimeModel.class);
		GenericTypeFinder.getTypeMap().put("AggregatedResponseTimeInfoModel", AggregatedResponseTimeInfoModel.class);

		// register Landscape Model classes, since we want to use them
		TypeProvider.getExplorVizCoreTypesAsMap().forEach((classname, classRef) -> {
			GenericTypeFinder.getTypeMap().put(classname, classRef);
		});

		// register DI
		register(new DependencyInjectionBinder());

		// Security
		this.register(AuthenticationFilter.class);
		this.register(AuthorizationFilter.class);
		this.register(CorsResponseFilter.class);

		// register providers
		this.register(JsonApiProvider.class);
		this.register(JsonApiListProvider.class);

		// register the Resources
		register(TotalOverviewResource.class);
		register(ActiveClassInstancesResource.class);
		register(TotalRequestsResource.class);
		register(ProgrammingLanguagesResource.class);
		register(RamCpuResource.class);
		register(InstantiatedWidgetResource.class);
		register(RamCpuSettingsResource.class);
		register(EventLogResource.class);
		register(EventLogInfoResource.class);
		register(EventLogSettingsResource.class);
		register(OperationResponseTimeResource.class);
		register(OperationResponseTimeInfoResource.class);
		register(AggregatedResponseTimeResource.class);
		register(AggregatedResponseTimeInfoResource.class);

		// Starting point for your DI-based extension
		this.register(SetupApplicationListener.class);
	}
}
