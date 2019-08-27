package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.operation.OrderBy;
import instantiatedwidgets.InstantiatedWidgetModel;
import net.explorviz.shared.landscape.model.event.EEventType;
import widget.eventlog.EventLogInfoModel;
import widget.eventlog.EventLogModel;
import widget.ramcpu.RamCpuSettingsModel;
import widget.totalrequests.TotalRequestsModel;

public class MongoDashboardRepository {

	private static MongoHelper mongoHelper;
	private static MongoDashboardRepository mongoDashboardRepository;

	private MongoDashboardRepository() {
	}

	public static synchronized MongoDashboardRepository getInstance() {

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

	private Object totalRequestslock = new Object();

	public void saveTotalRequests(final String landscapeID, final int totalRequests, final long timestamp) {
		synchronized (totalRequestslock) {
			final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

			final Document dashboardDocument = new Document();

			dashboardDocument.append("type", "totalrequests");
			dashboardDocument.append("landscapeID", landscapeID);
			dashboardDocument.append("totalRequests", totalRequests);
			dashboardDocument.append("timestamp", timestamp);

			try {
				dashboardCollection.insertOne(dashboardDocument);
			} catch (final MongoException e) {
				throw e;
			}
		}

	}

	public TotalRequestsModel getTotalRequests(final String landscapeID) {
		synchronized (totalRequestslock) {
			final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

			final Document dashboardDocument = new Document();

			dashboardDocument.append("type", "totalrequests");
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
	}

	public TotalRequestsModel getLastTotalRequests() {

		synchronized (totalRequestslock) {
			final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

			final Document dashboardDocument = new Document();

			dashboardDocument.append("type", "totalrequests");

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
	}

	public List<TotalRequestsModel> getAllTotalRequests() {

		synchronized (totalRequestslock) {
			final MongoCollection<Document> dashboardCollection = mongoHelper.getDashboardCollection();

			final Document dashboardDocument = new Document();

			dashboardDocument.append("type", "totalrequests");

			final FindIterable<Document> result = dashboardCollection.find(dashboardDocument);

			if (result.first() == null) {
				return null;
			} else {

				List<TotalRequestsModel> modelList = new ArrayList<TotalRequestsModel>();

				for (Iterator<Document> i = result.iterator(); i.hasNext();) {

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
	}

	private Object instantiatedWidgetslock = new Object();

	public void saveInstantiatedWidgets(String userID, List<InstantiatedWidgetModel> instantiatedWidgets) {
		synchronized (instantiatedWidgetslock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			for (int i = 0; i < instantiatedWidgets.size(); i++) {
				final Document document = new Document();

				document.append("type", "instantiatedwidget");
				document.append("userID", instantiatedWidgets.get(i).getUserID());
				document.append("widgetName", instantiatedWidgets.get(i).getWidgetName());
				document.append("instanceID", instantiatedWidgets.get(i).getInstanceID());
				document.append("timestamp", instantiatedWidgets.get(i).getTimestamp());
				document.append("orderID", instantiatedWidgets.get(i).getOrderID());

				try {
					deleteAllInstantiatedWidgets(userID);
					collection.insertOne(document);
				} catch (final MongoException e) {
					throw e;
				}
			}
		}

	}

	public void saveInstantiatedWidget(InstantiatedWidgetModel widget) {
		synchronized (instantiatedWidgetslock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();

			document.append("type", "instantiatedwidget");
			document.append("userID", widget.getUserID());
			document.append("widgetName", widget.getWidgetName());
			document.append("instanceID", widget.getInstanceID());
			document.append("timestamp", widget.getTimestamp());
			document.append("orderID", widget.getOrderID());

			List<InstantiatedWidgetModel> temp = getInstantiatedWidgets(widget.getUserID());

			// delete old entries if we get data with a newer timestamp.
			if (temp != null && temp.get(0).getTimestamp() != widget.getTimestamp()) {
				deleteAllInstantiatedWidgets(widget.getUserID());
			}

			try {
				collection.insertOne(document);
			} catch (final MongoException e) {
				throw e;
			}
		}

	}

	public List<InstantiatedWidgetModel> getInstantiatedWidgets(String userID) {
		synchronized (instantiatedWidgetslock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();

			document.append("type", "instantiatedwidget");
			document.append("userID", userID);

			final FindIterable<Document> result;
			List<InstantiatedWidgetModel> list = new ArrayList<InstantiatedWidgetModel>();

			try {
				result = collection.find(document);
			} catch (final MongoException e) {
				throw e;
			}

			if (result.first() == null) {
				// return null;
				list.add(new InstantiatedWidgetModel(userID, "empty", 0, 0, 0));
				return list;
			} else {

				for (Iterator<Document> i = result.iterator(); i.hasNext();) {

					Document temp = (Document) i.next();

					if (temp.get("userID") != null && temp.get("widgetName") != null && temp.get("instanceID") != null
							&& temp.get("timestamp") != null && temp.get("orderID") != null) {

						String widgetName = "" + temp.get("widgetName").toString();
						int instanceID = Integer.parseInt(temp.get("instanceID").toString());
						long timestamp = Long.parseLong(temp.get("timestamp").toString());
						int orderID = Integer.parseInt(temp.get("orderID").toString());

						list.add(new InstantiatedWidgetModel(userID, widgetName, instanceID, timestamp, orderID));

					} else {

						return null;
					}

				}

				return list;

			}
		}
	}

	public void deleteAllInstantiatedWidgets(String userID) {
		synchronized (instantiatedWidgetslock) {
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

	public void deleteInstantiatedWidget(String userID, int instanceID) {
		synchronized (instantiatedWidgetslock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();
			final Document document = new Document();

			document.append("type", "instantiatedwidget");
			document.append("userID", userID);
			document.append("instanceID", instanceID);

			try {
				collection.deleteMany(document);
			} catch (final MongoException e) {
				throw e;
			}
		}
	}

	private Object ramcpusettingslock = new Object();

	public void saveRamCpuSetting(RamCpuSettingsModel setting) {
		synchronized (ramcpusettingslock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();

			document.append("type", "ramcpusetting");
			document.append("nodeName", setting.getNodeName());
			document.append("instanceID", setting.getInstanceID());

			deleteRamCpuSetting(setting.getInstanceID());

			try {
				collection.insertOne(document);
			} catch (final MongoException e) {
				throw e;
			}
		}
	}

	public void deleteRamCpuSetting(int instanceID) {
		synchronized (ramcpusettingslock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();
			final Document document = new Document();

			document.append("type", "ramcpusetting");
			document.append("instanceID", instanceID);

			try {
				collection.deleteMany(document);
			} catch (final MongoException e) {
				throw e;
			}
		}
	}

	public RamCpuSettingsModel getRamCpuSetting(int instanceID) {
		synchronized (ramcpusettingslock) {

			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();
			final Document document = new Document();

			document.append("type", "ramcpusetting");
			document.append("instanceID", instanceID);

			final FindIterable<Document> result;

			try {
				result = collection.find(document);
			} catch (final MongoException e) {
				throw e;
			}

			if (result.first() == null) {
				return null;

			} else {

				Document temp = result.first();

				String nodeName = "" + temp.get("nodeName").toString();

				return new RamCpuSettingsModel(nodeName, instanceID);

			}
		}

	}

	private Object eventloglock = new Object();


	public List<EventLogModel> getEventLogs(String timestampLandscape) {
		synchronized (eventloglock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			BasicDBObject orQuery = new BasicDBObject();
			List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
			obj.add(new BasicDBObject("type", "eventlog"));
			obj.add(new BasicDBObject("timestampLandscape", Long.parseLong(timestampLandscape)));
			orQuery.put("$and", obj);
			FindIterable<Document> result = collection.find(orQuery);

			List<EventLogModel> list = new ArrayList<EventLogModel>();

			for (Iterator<Document> i = result.iterator(); i.hasNext();) {

				Document temp = (Document) i.next();

				if (temp.get("timestampLandscape") != null && temp.get("timestampEvent") != null
						&& temp.get("eventType") != null && temp.get("eventMessage") != null) {

					long rtimestampLandscape = Long.parseLong(temp.get("timestampLandscape").toString());
					long timestampEvent = Long.parseLong(temp.get("timestampEvent").toString());
					EEventType eventType = EEventType.valueOf(temp.get("eventType").toString());
					String eventMessage = temp.get("eventMessage").toString();

					list.add(new EventLogModel(rtimestampLandscape, timestampEvent, eventType, eventMessage));

				} else {

					return null;
				}

			}

			return list;

		}
	}

	

	

	public void printDatabase() {
		PrintStream ps_console = System.out;
		try {
			File file = new File("file.txt");
			FileOutputStream fos;

			fos = new FileOutputStream(file);

			PrintStream ps = new PrintStream(fos);

			System.setOut(ps);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		MongoCursor<Document> cursor = mongoHelper.getDashboardCollection().find().iterator();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next().toJson());
			}
		} finally {
			cursor.close();
		}

		System.setOut(ps_console);
	}


	public void save(Map<String, Object> data, Object lock) {
		synchronized (lock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();
			data.forEach((k, v) -> {
				// System.out.println("save: " + k + ": " + v);
				document.append(k, v);
			});

			try {
				collection.insertOne(document);
			} catch (final MongoException e) {
				throw e;
			}
		}

	}

	public List<Map<String, Object>> query(Map<String, Object> query, Object lock) {
		synchronized (lock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();

			query.forEach((k, v) -> {
				document.append(k, v);
			});

			try {
				final FindIterable<Document> find = collection.find(document).sort(new BasicDBObject("_id", -1));

				List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

				for (Iterator<Document> i = find.iterator(); i.hasNext();) {
					Document temp = (Document) i.next();
					Map<String, Object> map = new Hashtable<>();

					temp.forEach((k, v) -> {
						if (!k.equals("_id")) {
							map.put(k, v);
						}
					});
					result.add(map);
				}
				return result;
			} catch (final MongoException e) {
				throw e;
			}

		}
	}

	public List<Map<String, Object>> query(Map<String, Object> query, int limit, Object lock) {
		synchronized (lock) {
			System.out.println("query");
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();

			query.forEach((k, v) -> {
				document.append(k, v);
			});

			try {
				final FindIterable<Document> find = collection.find(document).sort(new BasicDBObject("_id", -1))
						.limit(limit);

				List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

				for (Iterator<Document> i = find.iterator(); i.hasNext();) {
					Document temp = (Document) i.next();
					Map<String, Object> map = new Hashtable<>();

					temp.forEach((k, v) -> {
						if (!k.equals("_id")) {
							System.out.println("query - key: " + k + "value: " + v);
							map.put(k, v);
						}
					});
					result.add(map);
				}
				return result;
			} catch (final MongoException e) {
				throw e;
			}

		}
	}

	public void delete(Map<String, Object> query, Object lock) {
		synchronized (lock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();
			final Document document = new Document();

			query.forEach((k, v) -> {
				document.append(k, v);
			});

			try {
				collection.deleteMany(document);
			} catch (final MongoException e) {
				throw e;
			}
		}
	}
}
