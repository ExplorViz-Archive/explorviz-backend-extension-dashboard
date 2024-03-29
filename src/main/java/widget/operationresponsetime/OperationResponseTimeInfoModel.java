package widget.operationresponsetime;

import java.util.Hashtable;
import java.util.Map;
import com.github.jasminb.jsonapi.annotations.Type;
import net.explorviz.extension.dashboard.main.BaseModel;

/**
 * This is the model for the OperationResponseTimeInfo</br>
 * properties:</br>
 * {@link #timestampLandscape}</br>
 * {@link #amount}
 * 
 * @author Florian Krippner
 *
 */
@Type("operationresponsetimeinfo")
public class OperationResponseTimeInfoModel extends BaseModel {

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
		return "operationresponsetimeinfo - timestampLandscape: " + timestampLandscape + " amount: " + amount;
	}

	/**
	 * This method convert a Map of a (string,object) pair with the models
	 * properties as keys and the right objects as values to a model.
	 * 
	 * @param map a Map of (String, Object) pairs, that should have the properties
	 *            of this model included.
	 * @return return a OperationResponseTimeInfoModel
	 */
	public static OperationResponseTimeInfoModel convert(Map<String, Object> map) {
		long timestampLandscape = (long) map.get("timestampLandscape");
		int amount = (int) map.get("amount");

		return new OperationResponseTimeInfoModel(timestampLandscape, amount);
	}

	/**
	 * This method convert this model into a Map of (String, Object)
	 * reprensentation. The key/string is a propertiename of this model and the
	 * value/Object is the content.
	 * 
	 * @return returns a Map of (String,Object) pairs, which represent this model.
	 */
	public Map<String, Object> convert() {
		Map<String, Object> result = new Hashtable<>();
		result.put("type", "operationresponsetimeinfo");
		result.put("timestampLandscape", this.timestampLandscape);
		result.put("amount", this.amount);

		return result;
	}

}
