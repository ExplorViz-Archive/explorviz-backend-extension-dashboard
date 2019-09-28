package widget.totaloverview;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * This class is the resource for the TotalOverview Widget. The resource can be
 * accessed through http. the path is "widgets/totaloverviewwidget". The media
 * type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/totaloverviewwidget")
@PermitAll
public class TotalOverviewResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method is accessable though http .
	 * 
	 * @return returns the latest TotalOverviewModel
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public TotalOverviewModel getModel() {

		return TotalOverviewService.getInstance().getTotalOverviewWidgetModel();

	}

}
