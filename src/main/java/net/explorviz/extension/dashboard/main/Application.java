package net.explorviz.extension.dashboard.main;

import net.explorviz.extension.dashboard.model.ClazzCommunicationListModel;
import net.explorviz.extension.dashboard.model.ClazzCommunicationModel;
import net.explorviz.extension.dashboard.model.DummyModel;
import net.explorviz.extension.dashboard.model.SubDummyModel;
import net.explorviz.extension.dashboard.model.TotalOverviewWidgetModel;
import net.explorviz.extension.dashboard.resources.ClazzCommunicationWidgetResource;
import net.explorviz.extension.dashboard.resources.TestResource;
import net.explorviz.extension.dashboard.resources.TotalOverviewWidgetResource;
import net.explorviz.shared.common.provider.GenericTypeFinder;
import net.explorviz.shared.common.provider.JsonApiListProvider;
import net.explorviz.shared.common.provider.JsonApiProvider;
import net.explorviz.shared.landscape.model.helper.TypeProvider;
import net.explorviz.shared.security.filters.AuthenticationFilter;
import net.explorviz.shared.security.filters.AuthorizationFilter;
import net.explorviz.shared.security.filters.CorsResponseFilter;
import totalrequests.TotalRequestsModel;
import totalrequests.TotalRequestsResource;

import org.glassfish.jersey.server.ResourceConfig;

import activeclassinstances.ActiveClassInstancesModel;
import activeclassinstances.ActiveClassInstancesResource;

public class Application extends ResourceConfig {

  public Application() {

    GenericTypeFinder.getTypeMap().put("DummyModel", DummyModel.class);
    GenericTypeFinder.getTypeMap().put("SubDummyModel", SubDummyModel.class);
    
    GenericTypeFinder.getTypeMap().put("TotalOverviewWidgetModel", TotalOverviewWidgetModel.class);
    GenericTypeFinder.getTypeMap().put("ClazzCommunicationModel", ClazzCommunicationModel.class);
    GenericTypeFinder.getTypeMap().put("ClazzCommunicationListModel", ClazzCommunicationListModel.class);
    GenericTypeFinder.getTypeMap().put("ActiveClassInstancesModel", ActiveClassInstancesModel.class);
    GenericTypeFinder.getTypeMap().put("TotalRequestsModel", TotalRequestsModel.class);
    

    

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

    // register the TestResource
    register(TestResource.class);
    register(TotalOverviewWidgetResource.class);
    register(ClazzCommunicationWidgetResource.class);
    register(ActiveClassInstancesResource.class);
    register(TotalRequestsResource.class);

    // Starting point for your DI-based extension
    this.register(SetupApplicationListener.class);
  }
}
