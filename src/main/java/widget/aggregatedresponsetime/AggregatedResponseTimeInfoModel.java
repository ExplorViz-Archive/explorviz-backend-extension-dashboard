package widget.aggregatedresponsetime;

import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

@Type("aggregatedresponsetimeinfo")
public class AggregatedResponseTimeInfoModel extends BaseModel {

	private long timestampLandscape;
	private int entrys;

	public AggregatedResponseTimeInfoModel() {
		// default constructor for JSON API parsing
	}

	public AggregatedResponseTimeInfoModel(long timestampLandscape, int entrys) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.entrys = entrys;
	}

	public long getTimestampLandscape() {
		return timestampLandscape;
	}

	public void setTimestampLandscape(long timestampLandscape) {
		this.timestampLandscape = timestampLandscape;
	}

	public int getEntrys() {
		return entrys;
	}

	public void setEntrys(int entrys) {
		this.entrys = entrys;
	}
	
	public String toString() {
		return "timestampLandscape: " + timestampLandscape + "   entrys: " + entrys;
	}

}
