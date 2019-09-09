package widget.operationresponsetime;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("widgets/operationresponsetime")
@PermitAll
public class OperationResponseTimeResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<OperationResponseTimeModel> getList(@DefaultValue("5") @QueryParam("limit") int limit) {
		return OperationResponseTimeService.getInstance().getOperationResponseTimes(limit);
	}


	@GET
	@Path("search")
	@Produces(MEDIA_TYPE)
	public List<OperationResponseTimeModel> getSearchedList(@DefaultValue("50") @QueryParam("limit") int limit,
			@DefaultValue("0") @QueryParam("timestampLandscape") long timestampLandscape) {
		return OperationResponseTimeService.getInstance().getOperationResponseTimes(timestampLandscape, limit);

	}

}
