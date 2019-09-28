package widget.operationresponsetime;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the OperationResponseTimeInfo. The resource
 * can be accessed through http. the path is
 * "widgets/operationresponsetimeinfo". The media type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/operationresponsetimeinfo")
@PermitAll
public class OperationResponseTimeInfoResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method is accessable through http. it returns a list of
	 * OperationResponseTimeInfoModels
	 * 
	 * @param limit this parameter limit the size of the list
	 * @return it returns a list of OperationResponseTimeInfoModels in json
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<OperationResponseTimeInfoModel> getInfoList(@DefaultValue("50") @QueryParam("limit") int limit) {
		List<OperationResponseTimeInfoModel> result = OperationResponseTimeService.getInstance()
				.getOperationResponseTimeInfo(limit);
		return result;

	}

}
