package net.explorviz.extension.dashboard.main;

import javax.inject.Inject;
import javax.servlet.annotation.WebListener;

import net.explorviz.extension.dashboard.model.ClazzCommunicationModel;
import net.explorviz.extension.dashboard.model.DummyModel;
import net.explorviz.extension.dashboard.model.TotalOverviewWidgetModel;
import net.explorviz.extension.dashboard.services.ClazzCommunicationWidgetService;
import net.explorviz.extension.dashboard.services.DummyService;
import net.explorviz.extension.dashboard.services.KafkaLandscapeExchangeService;
import net.explorviz.extension.dashboard.services.TotalOverviewWidgetService;
import net.explorviz.shared.common.idgen.IdGenerator;
import totalrequests.TotalRequestsModel;

import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEvent.Type;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import activeclassinstances.ActiveClassInstancesModel;
import activeclassinstances.ActiveClassInstancesService;

/**
 * Primary starting class - executed, when the servlet context is started.
 */
@WebListener
public class SetupApplicationListener implements ApplicationEventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(SetupApplicationListener.class);

	@Inject
	private KafkaLandscapeExchangeService landscapeExchangeService;

	@Inject
	private DummyService dummyService;

	@Inject
	private TotalOverviewWidgetService totalOverviewWidgetService;

	@Inject
	private IdGenerator idGenerator;

	@Inject
	private ClazzCommunicationWidgetService clazzCommunicationWidgetService;


	@Override
	public void onEvent(final ApplicationEvent event) {

		// After this type, CDI (e.g. injected LandscapeExchangeService) has been
		// fullfilled
		final Type t = Type.INITIALIZATION_FINISHED;

		if (event.getType().equals(t)) {
			startExtension();
		}

	}

	@Override
	public RequestEventListener onRequest(final RequestEvent requestEvent) {
		return null;
	}

	private void startExtension() {
		LOGGER.info("* * * * * * * * * * * * * * * * * * *\n");
		LOGGER.info("Dummy Extension Servlet initialized.\n");
		LOGGER.info("* * * * * * * * * * * * * * * * * * *");

		// add your DI injected code here for full DI context access

		new Thread(this.landscapeExchangeService).start();

		// erstmal so !?
		DummyModel.initialize(this.idGenerator);
		dummyService.startMyDummyStuff();

		TotalOverviewWidgetModel.initialize(this.idGenerator);
		totalOverviewWidgetService.start();

		ClazzCommunicationModel.initialize(this.idGenerator);
		clazzCommunicationWidgetService.start();

		ActiveClassInstancesModel.initialize(this.idGenerator);
		ActiveClassInstancesService.getInstance().start();
		
		TotalRequestsModel.initialize(this.idGenerator);
		ActiveClassInstancesService.getInstance().start();

	}

}
