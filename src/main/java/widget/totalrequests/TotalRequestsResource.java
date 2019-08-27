package widget.totalrequests;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("widgets/totalrequests")
@PermitAll
public class TotalRequestsResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<TotalRequestsModel> getListModel(@DefaultValue("default") @QueryParam("action") String action) {

		List<TotalRequestsModel> list = new ArrayList<TotalRequestsModel>();

		if (action.equals("default")) {
			//System.out.println("default");
			TotalRequestsModel model = TotalRequestsService.getInstance().getCurrentModel();
			if (model != null) {
				list.add(TotalRequestsService.getInstance().getCurrentModel());
			}
			return list;
		}

		if (action.equals("all")) {
			//System.out.println("all");
			return TotalRequestsService.getInstance().getAllModels();
		}
		
		/*
		if (action.equals("reduced")) {
			//System.out.println("all");
			return TotalRequestsService.getInstance().getReducedModels();
		}
		*/
		
		
		if(action.equals("recent")) 
		{
			return TotalRequestsService.getInstance().getRecentModels();
		}
		
		

		return list;

	}

}
