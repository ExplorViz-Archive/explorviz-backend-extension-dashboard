package net.explorviz.extension.dashboard.resources;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.explorviz.extension.dashboard.model.ClazzCommunicationListModel;
import net.explorviz.extension.dashboard.model.ClazzCommunicationModel;
import net.explorviz.extension.dashboard.services.ClazzCommunicationWidgetService;


@Path("widgets/clazzcommunicationwidget")

// @RolesAllowed({"admin"})

@PermitAll
public class ClazzCommunicationWidgetResource {

	// Access annotations can also be applied at method level

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@Inject
	private ClazzCommunicationWidgetService service;

	@GET
	@Path("{id}")
	@Produces(MEDIA_TYPE)
	public ClazzCommunicationModel getModel(@PathParam("id") int id) {

		return service.getClazzCommunicationModel(id);

	}
	
	@GET
	@Path("all")
	@Produces(MEDIA_TYPE)
	public ClazzCommunicationListModel getAllClazzCommunications() 
	{
		return service.getClazzCommunicationListModel();
	}
	


	
	

}
