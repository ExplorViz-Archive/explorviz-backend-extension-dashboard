package widget.programminglanguage;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("widgets/programminglanguagesoccurrence")
@PermitAll
public class ProgrammingLanguagesResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	@GET
	@Produces(MEDIA_TYPE)
	public List<ProgrammingLanguagesOccurrenceModel> getListModel(@DefaultValue("default") @QueryParam("action") String action) {

		

		return ProgrammingLanguagesService.getInstance().getCurrentProgrammingLanguagesOccurrence();

	}

}
