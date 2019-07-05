package widget.totaloverview;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("widgets/totaloverviewwidget")
// @RolesAllowed({"admin"})
@PermitAll
public class TotalOverviewResource {

	// Access annotations can also be applied at method level

	private static final String MEDIA_TYPE = "application/vnd.api+json";


	@GET
	@Produces(MEDIA_TYPE)
	public TotalOverviewModel getModel() {

		return TotalOverviewService.getInstance().getTotalOverviewWidgetModel();
	
	}

}
