package widget.operationresponsetime;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("widgets/operationresponsetimeinfo")
@PermitAll
public class OperationResponseTimeInfoResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	

	@GET
	@Produces(MEDIA_TYPE)
	public List<OperationResponseTimeInfoModel> getModel(@DefaultValue("50") @QueryParam("limit") int limit) {
		List<OperationResponseTimeInfoModel> result = OperationResponseTimeService.getInstance().getOperationResponseTimeInfo(limit);

		for(OperationResponseTimeInfoModel m : result) {
			//System.out.println(m.toString());
		}
		return result;

	}



}
