package net.explorviz.extension.dashboard.resources;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import net.explorviz.extension.dashboard.model.TotalOverviewWidgetModel;
import net.explorviz.extension.dashboard.services.TotalOverviewWidgetService;

@Path("widgets/totaloverviewwidget")
// @RolesAllowed({"admin"})
@PermitAll
public class TotalOverviewWidgetResource {

	// Access annotations can also be applied at method level

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@Inject
	private TotalOverviewWidgetService service;

	@GET
	@Produces(MEDIA_TYPE)
	public TotalOverviewWidgetModel getModel() {

		return service.getTotalOverviewWidgetModel();
	
	}

}
