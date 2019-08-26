package widget.aggregatedresponsetime;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("widgets/aggregatedresponsetime")
@PermitAll
public class AggregatedResponseTimeResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<AggregatedResponseTimeModel> getList(
			@DefaultValue("0") @QueryParam("timestampLandscape") long timestampLandscape) {
		return AggregatedResponseTimeService.getInstance().getAggregatedResponseTimes(timestampLandscape);

	}
	

}
