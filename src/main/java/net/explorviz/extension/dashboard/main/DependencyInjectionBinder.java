package net.explorviz.extension.dashboard.main;

import javax.inject.Singleton;
import kafka.KafkaLandscapeExchangeService;
import kafka.LandscapeSerializationHelper;
import net.explorviz.shared.common.injection.CommonDependencyInjectionBinder;

/**
 * The DependencyInjectionBinder is used to register Contexts and Dependency
 * Injection (CDI) aspects for this application.
 */
public class DependencyInjectionBinder extends CommonDependencyInjectionBinder {

	@Override
	public void configure() {

		// Common DI
		super.configure();

		// Service-specific DI
		this.bind(KafkaLandscapeExchangeService.class).to(KafkaLandscapeExchangeService.class).in(Singleton.class);
		this.bind(LandscapeSerializationHelper.class).to(LandscapeSerializationHelper.class).in(Singleton.class);
		this.bind(Dummy.class).to(Dummy.class).in(Singleton.class);

	}
}
