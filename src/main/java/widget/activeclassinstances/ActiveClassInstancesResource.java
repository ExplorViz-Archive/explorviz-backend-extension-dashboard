package widget.activeclassinstances;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the Active class instances widget. The
 * resource can be accessed through http. the path is
 * "widgets/activeclassinstances". The media type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/activeclassinstances")
@PermitAll
public class ActiveClassInstancesResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method returns a list of ActiveClassInstancesModels as a json.
	 * 
	 * @param amount the amount of ActiveClassInstancesModels that should be maximal
	 *               returned
	 * @return returns a list of ActiveClassInstancesModels in the MEDIA_TYPE json.
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<ActiveClassInstancesModel> getModel(@DefaultValue("0") @QueryParam("amount") int amount) {

		return ActiveClassInstancesService.getInstance().getActiveClassInstances(amount);

	}

}
