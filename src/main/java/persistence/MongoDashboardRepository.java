package persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.operation.OrderBy;

import instantiatedwidgets.InstantiatedWidgetModel;
import widget.totalrequests.TotalRequestsModel;

public class MongoDashboardRepository {

	private static MongoHelper mongoHelper;
	private static MongoDashboardRepository mongoDashboardRepository;

	private MongoDashboardRepository() {
	}

	public static MongoDashboardRepository getInstance() {

		if (mongoDashboardRepository == null) {
			MongoDashboardRepository.mongoHelper = new MongoHelper("localhost", "7018", "dashboard");
			mongoDashboardRepository = new MongoDashboardRepository();
		}
		return mongoDashboardRepository;
	}

	public void saveClazzCommunication(final String landscapeID, final String operationName, final int totalRequests,
			final float averageResponse) {

		final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

		final Document dashboardDocument = new Document();

		dashboardDocument.append("landscapeID", landscapeID);
		dashboardDocument.append("operationName", operationName);
		dashboardDocument.append("totalRequests", totalRequests);
		dashboardDocument.append("averageResponse", averageResponse);

		try {
			dashboardCollection.insertOne(dashboardDocument);
		} catch (final MongoException e) {
			throw e;
		}

		System.out.println("savedClazzCommunication");

	}

	public Optional<String> getClazzCommunicationByLandscapeID(final String landscapeID) {

		final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

		final Document dashboardDocument = new Document();

		dashboardDocument.append("landscapeID", landscapeID);

		final FindIterable<Document> result = dashboardCollection.find(dashboardDocument);

		if (result.first() == null) {
			return Optional.empty();
		} else {
			return Optional.of("" + result.first().get("landscapeID"));
		}
	}

	public void saveTotalRequests(final String landscapeID, final int totalRequests, final long timestamp) {

		final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

		final Document dashboardDocument = new Document();

		dashboardDocument.append("totalrequests", "totalrequests");
		dashboardDocument.append("landscapeID", landscapeID);
		dashboardDocument.append("totalRequests", totalRequests);
		dashboardDocument.append("timestamp", timestamp);

		try {
			dashboardCollection.insertOne(dashboardDocument);
		} catch (final MongoException e) {
			throw e;
		}

		System.out.println("MongoDB: (function saveTotalRequests)  saved a model.");

	}

	public TotalRequestsModel getTotalRequests(final String landscapeID) {

		final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

		final Document dashboardDocument = new Document();

		dashboardDocument.append("totalrequests", "totalrequests");
		dashboardDocument.append("landscapeID", landscapeID);

		final FindIterable<Document> result = dashboardCollection.find(dashboardDocument);

		if (result.first() == null) {
			return null;
		} else {

			if (result.first().get("landscapeID") != null && result.first().get("totalRequests") != null
					&& result.first().get("timestamp") != null) {

				String id = "" + result.first().get("landscapeID");
				int totalRequests = Integer.parseInt(result.first().get("totalRequests").toString());
				Long timestamp = Long.parseLong(result.first().get("timestamp").toString());

				return new TotalRequestsModel(id, totalRequests, timestamp);

			} else {
				return null;
			}

		}
	}

	public TotalRequestsModel getLastTotalRequests() {

		final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

		final Document dashboardDocument = new Document();

		dashboardDocument.append("totalrequests", "totalrequests");

		final FindIterable<Document> result = dashboardCollection.find(dashboardDocument).limit(1)
				.sort(new BasicDBObject("_id", OrderBy.DESC.getIntRepresentation())).limit(1);

		if (result.first() == null) {
			return null;
		} else {

			if (result.first().get("landscapeID") != null && result.first().get("totalRequests") != null
					&& result.first().get("timestamp") != null) {

				String landscapeID = "" + result.first().get("landscapeID");
				int totalRequests = Integer.parseInt(result.first().get("totalRequests").toString());
				Long timestamp = Long.parseLong(result.first().get("timestamp").toString());

				return new TotalRequestsModel(landscapeID, totalRequests, timestamp);

			} else {
				return null;
			}

		}
	}

	public List<TotalRequestsModel> getAllTotalRequests() {

		final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

		final Document dashboardDocument = new Document();

		dashboardDocument.append("totalrequests", "totalrequests");

		final FindIterable<Document> result = dashboardCollection.find(dashboardDocument);

		if (result.first() == null) {
			return null;
		} else {

			List<TotalRequestsModel> modelList = new ArrayList<TotalRequestsModel>();

			for (Iterator i = result.iterator(); i.hasNext();) {

				Document temp = (Document) i.next();

				if (temp.get("landscapeID") != null && temp.get("totalRequests") != null
						&& temp.get("timestamp") != null) {

					String landscapeID = "" + temp.get("landscapeID");
					int totalRequests = Integer.parseInt(temp.get("totalRequests").toString());
					Long timestamp = Long.parseLong(temp.get("timestamp").toString());

					modelList.add(new TotalRequestsModel(landscapeID, totalRequests, timestamp));

				} else {
					return null;
				}

			}

			return modelList;

		}
	}

	public void saveInstantiatedWidgets(long userID, List<InstantiatedWidgetModel> instantiatedWidgets) {
		final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

		for (int i = 0; i < instantiatedWidgets.size(); i++) {
			final Document document = new Document();

			document.append("userID", instantiatedWidgets.get(i).getUserID());
			document.append("widgetName", instantiatedWidgets.get(i).getWidgetName());
			document.append("instanceID", instantiatedWidgets.get(i).getInstanceID());

			try {
				deleteInstantiatedWidgets(userID);
				collection.insertOne(document);
			} catch (final MongoException e) {
				throw e;
			}
		}

		System.out.println("MongoDB: (function saveInstantiatedWidgets)  saved widget instances.");
	}

	public List<InstantiatedWidgetModel> getInstantiatedWidgets(long userID) {
		final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

		final Document document = new Document();

		document.append("userID", userID);

		final FindIterable<Document> result;

		try {
			result = collection.find(document);
		} catch (final MongoException e) {
			throw e;
		}

		if (result.first() == null) {
			return null;
		} else {

			List<InstantiatedWidgetModel> list = new ArrayList<InstantiatedWidgetModel>();

			for (Iterator i = result.iterator(); i.hasNext();) {

				Document temp = (Document) i.next();

				if (temp.get("userID") != null && temp.get("widgetName") != null && temp.get("instanceID") != null) {

					String widgetName = "" + temp.get("widgetName").toString();
					int instanceID = Integer.parseInt(temp.get("instanceID").toString());

					list.add(new InstantiatedWidgetModel(instanceID, widgetName, instanceID));

				} else {
					return null;
				}

			}

			return list;

		}
	}

	public void deleteInstantiatedWidgets(long userID) {
		final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();
		final Document document = new Document();

		document.append("userID", userID);

		try {
			collection.deleteMany(document);
		} catch (final MongoException e) {
			throw e;
		}
	}
}
