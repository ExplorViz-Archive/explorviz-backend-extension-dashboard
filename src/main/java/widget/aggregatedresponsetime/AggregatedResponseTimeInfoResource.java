package widget.aggregatedresponsetime;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("widgets/aggregatedresponsetimeinfo")
@PermitAll
public class AggregatedResponseTimeInfoResource {
	
	private static final String MEDIA_TYPE = "application/vnd.api+json";
	
	
	@GET
	@Produces(MEDIA_TYPE)
	public List<AggregatedResponseTimeInfoModel> getInfoList(@DefaultValue("50") @QueryParam("limit") int limit) {
		return AggregatedResponseTimeService.getInstance().getAggregatedResponseTimeInfos(limit);

	}

}
