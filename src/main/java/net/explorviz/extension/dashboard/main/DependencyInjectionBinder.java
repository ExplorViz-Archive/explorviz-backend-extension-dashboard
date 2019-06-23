package net.explorviz.extension.dashboard.main;

import javax.inject.Singleton;

import net.explorviz.extension.dashboard.services.ClazzCommunicationWidgetService;
import net.explorviz.extension.dashboard.services.DummyService;
import net.explorviz.extension.dashboard.services.KafkaLandscapeExchangeService;
import net.explorviz.extension.dashboard.services.LandscapeSerializationHelper;
import net.explorviz.extension.dashboard.services.TotalOverviewWidgetService;
import net.explorviz.shared.common.injection.CommonDependencyInjectionBinder;
import widget.activeclassinstances.ActiveClassInstancesService;

/**
 * The DependencyInjectionBinder is used to register Contexts and Dependency Injection (CDI) aspects
 * for this application.
 */
public class DependencyInjectionBinder extends CommonDependencyInjectionBinder {

  @Override
  public void configure() {

    // Common DI
    super.configure();

    // Service-specific DI
    
    this.bind(DummyService.class).to(DummyService.class).in(Singleton.class);
    
    this.bind(TotalOverviewWidgetService.class).to(TotalOverviewWidgetService.class).in(Singleton.class);
    
    this.bind(ClazzCommunicationWidgetService.class).to(ClazzCommunicationWidgetService.class).in(Singleton.class);
    
    //this.bind(ActiveClassInstancesService.class).to(ActiveClassInstancesService.class).in(Singleton.class);
    
    
    
    
    

    this.bind(KafkaLandscapeExchangeService.class).to(KafkaLandscapeExchangeService.class)
        .in(Singleton.class);

    this.bind(LandscapeSerializationHelper.class).to(LandscapeSerializationHelper.class)
        .in(Singleton.class);

  }
}
