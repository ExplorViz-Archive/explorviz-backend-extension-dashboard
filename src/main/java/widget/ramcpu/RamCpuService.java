package widget.ramcpu;

import java.util.ArrayList;
import java.util.List;
import persistence.MongoDashboardRepository;

/**
 * This is the service class of the ram cpu widget. This class is a singelton.
 * 
 * @author Florian Krippner
 *
 */
public class RamCpuService {

	private static RamCpuService instance;

	private RamCpuService() {
	}

	public static RamCpuService getInstance() {
		if (RamCpuService.instance == null) {
			RamCpuService.instance = new RamCpuService();
		}
		return RamCpuService.instance;
	}

	// this list holds the RamCpuModels of the latest landscape.
	private List<RamCpuModel> currentModels = new ArrayList<RamCpuModel>();

	/**
	 * This function update the RamCpuModels with newer data.
	 * 
	 * @param programmingLanguageModel require a list of new RamCpuModels
	 */
	public void update(List<RamCpuModel> programmingLanguageModel) {

		for (RamCpuModel m : programmingLanguageModel) {

			MongoDashboardRepository.getInstance().save(m.convert(), this);
		}

		currentModels = new ArrayList<RamCpuModel>(programmingLanguageModel);

	}

	/**
	 * 
	 * @return This method returns the RamCpuModels of the latest landscape.
	 */
	public List<RamCpuModel> getCurrentRamCpuModels() {
		return currentModels;

	}

}
