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

@Path("widgets/instantiatedwidget")

// @RolesAllowed({"admin"})

@PermitAll
public class InstantiatedWidgetResource {

	// Access annotations can also be applied at method level

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<InstantiatedWidgetModel> getAll(@DefaultValue("0") @QueryParam("userID") long userID) {

		System.out.println("Get Request InstantiatedWidgetModel: UserID -> " + userID);

		return InstantiatedWidgetService.getInstance().getInstantiatedWidgets(userID);

	}
	
	@POST
    public Response postMsg(InstantiatedWidgetModel model) {
	 
		System.out.println("post: " + model.toString());

		
        InstantiatedWidgetService.getInstance().setInstantiatedWidget(model);
        return Response.status(200).entity(model).build();
    }

}
