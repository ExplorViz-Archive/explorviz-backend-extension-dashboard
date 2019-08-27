package widget.ramcpu;

import java.util.ArrayList;
import java.util.List;
import persistence.MongoDashboardRepository;

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

	private List<RamCpuModel> currentModels = new ArrayList<RamCpuModel>();

	public void update(List<RamCpuModel> programmingLanguageModel) {

		for (RamCpuModel m : programmingLanguageModel) {

			MongoDashboardRepository.getInstance().save(m.convert(), this);
		}

		currentModels = new ArrayList<RamCpuModel>(programmingLanguageModel);

	}

	public List<RamCpuModel> getCurrentRamCpuModels() {
		return currentModels;

	}

}
