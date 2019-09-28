package instantiatedwidgets;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * This class defines the resource, which is accessable over http. the path for
 * this ressource is widgets/instantiatedwidget
 * 
 * @author Florian Krippner
 */
@Path("widgets/instantiatedwidget")
@PermitAll
public class InstantiatedWidgetResource {

	// The MEDIA TYPE is JSON
	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * 
	 * @param userID the id of the user inside explorviz
	 * @return returns the instantiated widgets for a given user as json
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<InstantiatedWidgetModel> getAll(@DefaultValue("0") @QueryParam("userID") String userID) {
		return InstantiatedWidgetService.getInstance().getInstantiatedWidgets(userID);

	}

	/**
	 * This method will be triggered with a post request and save a
	 * InstantiatedWidgetModel into the database. if the timestamp is set to -1 this
	 * method will delete a given InstantiatedWidgetModel from the database.
	 * 
	 * @param model this paramterer should be a InstantiatedWidgetModel
	 * @return
	 */
	@POST
	public Response postMsg(InstantiatedWidgetModel model) {

		// if timestamp is set to -1 => delete this model from database
		if (model.getTimestamp() == -1) {
			InstantiatedWidgetService.getInstance().deleteInstantiatedWidget(model.getUserID(), model.getInstanceID());
		} else {
			// else save the model into the database
			InstantiatedWidgetService.getInstance().setInstantiatedWidget(model);
		}
		return Response.status(200).entity(model).build();
	}

}
