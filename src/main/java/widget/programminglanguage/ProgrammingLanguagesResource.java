package widget.programminglanguage;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * This class is the resource for the ProgrammingLanguages Widget. The resource
 * can be accessed through http. the path is
 * "widgets/programminglanguagesoccurrence". The media type is json.
 * 
 * @author Florian Krippner
 *
 */
@Path("widgets/programminglanguagesoccurrence")
@PermitAll
public class ProgrammingLanguagesResource {

	private static final String MEDIA_TYPE = "application/vnd.api+json";

	/**
	 * This method is accessable over http.
	 * 
	 * @return It returns a list of ProgrammingLanguagesOccurrenceModels as json
	 */
	@GET
	@Produces(MEDIA_TYPE)
	public List<ProgrammingLanguagesOccurrenceModel> getListModel(
			@DefaultValue("default") @QueryParam("action") String action) {
		return ProgrammingLanguagesService.getInstance().getCurrentProgrammingLanguagesOccurrence();

	}

}
