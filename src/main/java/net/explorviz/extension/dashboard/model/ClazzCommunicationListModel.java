package net.explorviz.extension.dashboard.model;

import java.util.List;

import com.github.jasminb.jsonapi.annotations.Type;

@Type("clazzcommunicationlistmodel")
public class ClazzCommunicationListModel extends BaseModel {

	private List<ClazzCommunicationModel> list;



	public ClazzCommunicationListModel() {
		// default constructor for JSON API parsing
	}

	public ClazzCommunicationListModel(final List<ClazzCommunicationModel> list) {
		this.list = list;
	}

	public List<ClazzCommunicationModel> getList() {
		return list;
	}

	public void setList(List<ClazzCommunicationModel> list) {
		this.list = list;
	}


}
