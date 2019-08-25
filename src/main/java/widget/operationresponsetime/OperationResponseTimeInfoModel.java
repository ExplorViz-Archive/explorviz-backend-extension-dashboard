package widget.operationresponsetime;

import com.github.jasminb.jsonapi.annotations.Type;

@Type("operationresponsetimeinfo")
public class OperationResponseTimeInfoModel {

	private long timestampLandscape;
	private int amount;
	
	public OperationResponseTimeInfoModel() {
		// default constructor for JSON API parsing
	}

	public OperationResponseTimeInfoModel(long timestampLandscape, int amount) {
		super();
		this.timestampLandscape = timestampLandscape;
		this.amount = amount;
	}

	public long getTimestampLandscape() {
		return timestampLandscape;
	}

	public void setTimestampLandscape(long timestampLandscape) {
		this.timestampLandscape = timestampLandscape;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String toString() {
		return "timestampLandscape: " + timestampLandscape + "   amount: " + amount;
	}
	
	
}
