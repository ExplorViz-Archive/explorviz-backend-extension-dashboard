package widget.activeclassinstances;


import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("widgets/activeclassinstances")
@PermitAll
public class ActiveClassInstancesResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<ActiveClassInstancesModel> getModel(@DefaultValue("0") @QueryParam("amount") int amount) {

		return ActiveClassInstancesService.getInstance().getActiveClassInstances(amount);

	}

}
