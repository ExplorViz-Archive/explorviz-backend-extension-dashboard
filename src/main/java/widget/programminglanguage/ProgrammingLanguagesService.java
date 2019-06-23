package widget.programminglanguage;

import java.util.ArrayList;
import java.util.List;

public class ProgrammingLanguagesService {

	private static ProgrammingLanguagesService instance;

	private ProgrammingLanguagesService() {
	}

	public static ProgrammingLanguagesService getInstance() {
		if (ProgrammingLanguagesService.instance == null) {
			ProgrammingLanguagesService.instance = new ProgrammingLanguagesService();
		}
		return ProgrammingLanguagesService.instance;
	}

	List<ProgrammingLanguagesOccurrenceModel> occurrence = new ArrayList<ProgrammingLanguagesOccurrenceModel>();

	public void update(List<ProgrammingLanguagesModel> programmingLanguageModel) {
		occurrence = new ArrayList<ProgrammingLanguagesOccurrenceModel>();
		
		for (int i = 0; i < programmingLanguageModel.size(); i++) {
			boolean occur = false;
			int index = 0;

			for (int j = 0; j < occurrence.size(); j++) {
				if (programmingLanguageModel.get(i).getProgramminglanguage() == occurrence.get(j)
						.getProgramminglanguage()) {
					occur = true;
					index = j;
				}

			}

			if (occur) {
				occurrence.get(index).setOccurs(occurrence.get(index).getOccurs() + 1);
			} else {
				occurrence.add(new ProgrammingLanguagesOccurrenceModel(programmingLanguageModel.get(i).getTimestamp(),
						1, (programmingLanguageModel.get(i).getProgramminglanguage())));
			}

		}

	}

	public List<ProgrammingLanguagesOccurrenceModel> getCurrentProgrammingLanguagesOccurrence() {
		return occurrence;

	}

}
