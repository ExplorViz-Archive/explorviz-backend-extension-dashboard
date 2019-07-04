package widget.ramcpu;

import java.util.List;

import persistence.MongoDashboardRepository;

public class RamCpuSettingsService {

	private static RamCpuSettingsService instance;

	private RamCpuSettingsService() {
	}

	public static RamCpuSettingsService getInstance() {
		if (RamCpuSettingsService.instance == null) {
			RamCpuSettingsService.instance = new RamCpuSettingsService();
		}
		return RamCpuSettingsService.instance;
	}

	/**
	 * returns a RamCpuSettingsModel for a certain instanceID. if theres no entry in
	 * the database a default setting will be saved in the database
	 * 
	 * @param instanceID
	 * @return RamCpuSettingsModel
	 */
	public RamCpuSettingsModel getSetting(int instanceID) {
		RamCpuSettingsModel result = MongoDashboardRepository.getInstance().getRamCpuSetting(instanceID);

		// setting default data for that widget instance
		if (result == null) {
			List<RamCpuModel> ramcpuModels = RamCpuService.getInstance().getCurrentRamCpuModels();

			if (ramcpuModels.size() != 0) {
				MongoDashboardRepository.getInstance()
						.saveRamCpuSetting(new RamCpuSettingsModel(ramcpuModels.get(0).getNodeName(), instanceID));

			}

			return MongoDashboardRepository.getInstance().getRamCpuSetting(instanceID);
		} else {
			return result;
		}

	}

	public void setSetting(RamCpuSettingsModel setting) {
		MongoDashboardRepository.getInstance().saveRamCpuSetting(setting);
	}

}
