package widget.ramcpu;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the RamCpu Widget. The resource can be
 * accessed through http. the path is "widgets/ramcpu". The media type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/ramcpu")
@PermitAll
public class RamCpuResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method is accessable over http.
	 * 
	 * @return it returns a list of RamCpuModels as json
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<RamCpuModel> getListModel(@DefaultValue("default") @QueryParam("action") String action) {

		return RamCpuService.getInstance().getCurrentRamCpuModels();

	}

}
