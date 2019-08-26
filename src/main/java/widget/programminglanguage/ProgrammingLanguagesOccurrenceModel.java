package widget.programminglanguage;

import com.github.jasminb.jsonapi.annotations.Type;

import net.explorviz.extension.dashboard.main.BaseModel;
import net.explorviz.shared.landscape.model.helper.EProgrammingLanguage;

@Type("programminglanguagesoccurrence")
public class ProgrammingLanguagesOccurrenceModel extends BaseModel {

	// the timestamp is used as a id
	private long timestamp;

	private EProgrammingLanguage programminglanguage;

	private int occurs;

	public ProgrammingLanguagesOccurrenceModel() {
		// default constructor for JSON API parsing
	}

	public ProgrammingLanguagesOccurrenceModel(final long timestamp, final int occurs,
			final EProgrammingLanguage programminglanguage) {
		this.timestamp = timestamp;
		this.occurs = occurs;
		this.programminglanguage = programminglanguage;
	}

	public int getOccurs() {
		return occurs;
	}

	public void setOccurs(int occurs) {
		this.occurs = occurs;
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
