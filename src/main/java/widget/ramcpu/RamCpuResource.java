package widget.ramcpu;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("widgets/ramcpu")
@PermitAll
public class RamCpuResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<RamCpuModel> getListModel(@DefaultValue("default") @QueryParam("action") String action) {

		return RamCpuService.getInstance().getCurrentRamCpuModels();

	}

}
