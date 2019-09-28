package widget.operationresponsetime;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the OperationResponseTime Widget. The resource
 * can be accessed through http. the path is "widgets/operationresponsetime".
 * The media type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/operationresponsetime")
@PermitAll
public class OperationResponseTimeResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method is accessable through http and returns a list of
	 * OperationResponseTimeModels
	 * 
	 * @param limit this parameter can limit the size of the list
	 * @return returns a list of OperationResponseTimeModels as json
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<OperationResponseTimeModel> getList(@DefaultValue("5") @QueryParam("limit") int limit) {
		return OperationResponseTimeService.getInstance().getOperationResponseTimes(limit);
	}

	/**
	 * this method is accessable though http on the path
	 * "widgets/operationresponsetime/search"
	 * 
	 * @param limit              this parameter limit the size of the returned list
	 * @param timestampLandscape the timestamp of the landscape
	 * @return returns a list of OperationResponseTimeModels as json
	 */
	@GET
	@Path("search")
	@Produces(MEDIA_TYPE)
	public List<OperationResponseTimeModel> getSearchedList(@DefaultValue("50") @QueryParam("limit") int limit,
			@DefaultValue("0") @QueryParam("timestampLandscape") long timestampLandscape) {
		return OperationResponseTimeService.getInstance().getOperationResponseTimes(timestampLandscape, limit);

	}

}
