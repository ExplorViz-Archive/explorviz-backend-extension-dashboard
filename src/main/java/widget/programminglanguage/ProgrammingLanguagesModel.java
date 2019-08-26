package widget.programminglanguage;

import com.github.jasminb.jsonapi.annotations.Type;

import net.explorviz.extension.dashboard.main.BaseModel;
import net.explorviz.shared.landscape.model.helper.EProgrammingLanguage;

@Type("programminglanguage")
public class ProgrammingLanguagesModel extends BaseModel {

	// the timestamp is used as a id
	private long timestamp;

	private EProgrammingLanguage programminglanguage;
	
	private String applicationName;

	public ProgrammingLanguagesModel() {
		// default constructor for JSON API parsing
	}

	public ProgrammingLanguagesModel(final long timestamp, final String applicationName, final EProgrammingLanguage programminglanguage) {
		this.timestamp = timestamp;
		this.applicationName = applicationName;
		this.programminglanguage = programminglanguage;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public EProgrammingLanguage getProgramminglanguage() {
		return programminglanguage;
	}

	public void setProgramminglanguage(EProgrammingLanguage programminglanguage) {
		this.programminglanguage = programminglanguage;
	}

}
