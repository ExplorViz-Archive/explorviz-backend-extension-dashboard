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
import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import instantiatedwidgets.InstantiatedWidgetModel;
import net.explorviz.shared.landscape.model.event.EEventType;
import widget.eventlog.EventLogModel;
import widget.ramcpu.RamCpuSettingsModel;

/**
 * This class holds functions for saving/deleting data inside the mongo
 * database. This class is a singelton
 * 
 * @author Florian Krippner
 *
 */
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

	public MongoHelper getMongoHelper() {
		return mongoHelper;
	}

	private Object instantiatedWidgetslock = new Object();

	/**
	 * This class saves a list of InstantiatedWidgetModels with a given userid into
	 * the database
	 * 
	 * @param userID              the id of the user in explorviz
	 * @param instantiatedWidgets a list of InstantiatedWidgetModels
	 */
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

	/**
	 * This function saves a InstantiatedWidgetModel into the databse
	 * 
	 * @param widget the widget to save
	 */
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

	/**
	 * This function returns a list of InstantiatedWidgetModels to a given userID
	 * 
	 * @param userID the userID to search for
	 * @return a list of InstantiatedWidgetModels
	 */
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

	/**
	 * This method deletes all InstantiatedWidgets to a given userID
	 * 
	 * @param userID the userID of the current user in ExplorViz
	 */
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

	/**
	 * This method delete a specific InstantiatedWidget to a given userID
	 * 
	 * @param userID     the userID of the current user in ExplorViz
	 * @param instanceID the ID of the widget to delete
	 */
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

	/**
	 * This method can save a RamCpuSettingsModel into the database.
	 * 
	 * @param setting require a RamCpuSettingsModel to save
	 */
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

	/**
	 * This method deletes a RamCpuSettingsModel from the database with a specific
	 * instanceID
	 * 
	 * @param instanceID required instanceID to delete the model
	 */
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

	/**
	 * This method returns a RamCpuSettingsModel to a given instanceID of a
	 * ramcpuwidget
	 * 
	 * @param instanceID the instanceID of a ramcpuwidget
	 * @return returns a RamCpuSettingsModel
	 */
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

	/**
	 * this method returns a list of EventLogModels from the database that are
	 * connected with a given timestamp of a landscape.
	 * 
	 * @param timestampLandscape a timestamp of a landscape
	 * @return returns a list of EventLogModels
	 */
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

	/**
	 * This function prints the whole database into a file.txt inside the project
	 * folder.
	 */
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

	/**
	 * This function saves a Map of String and Objects into the database. The String
	 * is for the key and the Object is the object to save
	 * 
	 * @param data a Map of a String,Object
	 * @param lock a Object to synchronize the function.
	 */
	public void save(Map<String, Object> data, Object lock) {
		synchronized (lock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();
			data.forEach((k, v) -> {
				document.append(k, v);
			});

			try {
				collection.insertOne(document);
			} catch (final MongoException e) {
				throw e;
			}
		}

	}

	/**
	 * This function can query for a given Map and sort the result
	 * 
	 * @param query a Map of String Objects to query for
	 * @param lock  a Object to synchronize on
	 * @return This function returns a List of Map (String, Object).
	 */
	public List<Map<String, Object>> querySort(Map<String, Object> query, Object lock) {
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

	/**
	 * This function can query for a given Map and sort the result. addionally it
	 * will limit the result to a given number
	 * 
	 * @param query a Map of String Objects to query for
	 * @param limit a number that limiot the result
	 * @param lock  a Object to synchronize on
	 * @return this function returns a List of Map (String, Object).
	 */
	public List<Map<String, Object>> querySort(Map<String, Object> query, int limit, Object lock) {
		synchronized (lock) {
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

	/**
	 * This function query in the database for a given Map of String,Object pairs.
	 * 
	 * @param query a map of (String,Object) pairs.
	 * @param lock  a object to synchronize the function
	 * @return returns a list of a map of (string,object) pairs
	 */
	public List<Map<String, Object>> query(Map<String, Object> query, Object lock) {
		synchronized (lock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();

			query.forEach((k, v) -> {
				document.append(k, v);
			});

			try {
				final FindIterable<Document> find = collection.find(document);

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

	/**
	 * This function query in the database for a given Map of String,Object pairs
	 * and will limit the result to a given number
	 * 
	 * @param query a map of (String,Object) pairs.
	 * @param limit a number to limit the result
	 * @param lock  a object to synchronize the function
	 * @return returns a list of a map of (string,object) pairs
	 */
	public List<Map<String, Object>> query(Map<String, Object> query, int limit, Object lock) {
		synchronized (lock) {
			final MongoCollection<Document> collection = mongoHelper.getDashboardCollection();

			final Document document = new Document();

			query.forEach((k, v) -> {
				document.append(k, v);
			});

			try {
				final FindIterable<Document> find = collection.find(document).limit(limit);

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

	/**
	 * This function deletes entries out of a databse with a given Map of string,
	 * object pairs.
	 * 
	 * @param query a map of string, object pairs to query for
	 * @param lock  a object to synchronize the function
	 */
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
