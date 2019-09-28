package widget.aggregatedresponsetime;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the AggregatedResponseTimeInfo. The resource
 * can be accessed through http. the path is
 * "widgets/aggregatedresponsetimeinfo". The media type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/aggregatedresponsetimeinfo")
@PermitAll
public class AggregatedResponseTimeInfoResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method can be accessed over http and returns a list of the
	 * AggregatedResponseTimeInfoModels as json.
	 * 
	 * @param limit this paramter can limit the amount of models returned
	 * @return returns a list of AggregatedResponseTimeInfoModels as a json.
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<AggregatedResponseTimeInfoModel> getInfoList(@DefaultValue("50") @QueryParam("limit") int limit) {
		return AggregatedResponseTimeService.getInstance().getAggregatedResponseTimeInfos(limit);

	}

}
