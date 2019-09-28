package widget.aggregatedresponsetime;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the AggregatedResponseTime Widget. The
 * resource can be accessed through http. the path is
 * "widgets/aggregatedresponsetime". The media type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/aggregatedresponsetime")
@PermitAll
public class AggregatedResponseTimeResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method is accessable over http and returns a list of
	 * AggregatedResponseTimeModel as json.
	 * 
	 * @param timestampLandscape needs the timestampLandscape to get the connected
	 *                           AggregatedResponseTimes
	 * @param limit              limit for the LastAggregatedResponseTime request
	 * @return returns a list of AggregatedResponseTimeModel as a json
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<AggregatedResponseTimeModel> getList(
			@DefaultValue("0") @QueryParam("timestampLandscape") long timestampLandscape,
			@DefaultValue("0") @QueryParam("limit") int limit) {
		if (limit == 0 && timestampLandscape != 0) {
			return AggregatedResponseTimeService.getInstance().getAggregatedResponseTimes(timestampLandscape);
		} else {
			return AggregatedResponseTimeService.getInstance().getLastAggregatedResponseTimes(limit);
		}

	}

}
