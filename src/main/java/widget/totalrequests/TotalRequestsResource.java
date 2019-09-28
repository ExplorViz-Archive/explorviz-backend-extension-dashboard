package widget.totalrequests;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the TotalRequests Widget. The resource can be
 * accessed through http. the path is "widgets/totalrequests". The media type is
 * json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/totalrequests")
@PermitAll
public class TotalRequestsResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method is accessable over http.
	 * 
	 * @param action a action can be "default", "all" or "recent". depending on that
	 *               other data is returned.
	 * @return "all" returns a list of all TotalRequestsModels, "recent" a list of
	 *         the last 10 landscapes and "default" gets the data of the last
	 *         landscape
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<TotalRequestsModel> getListModel(@DefaultValue("default") @QueryParam("action") String action) {

		List<TotalRequestsModel> list = new ArrayList<TotalRequestsModel>();

		if (action.equals("default")) {

			TotalRequestsModel model = TotalRequestsService.getInstance().getCurrentModel();
			if (model != null) {
				list.add(TotalRequestsService.getInstance().getCurrentModel());
			}
			return list;
		}

		if (action.equals("all")) {
			return TotalRequestsService.getInstance().getAllModels();
		}

		if (action.equals("recent")) {
			return TotalRequestsService.getInstance().getRecentModels();
		}

		return list;

	}

}
