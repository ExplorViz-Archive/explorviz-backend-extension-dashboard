package widget.programminglanguage;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the service class for the ProgrammingLanguages widget. This class is
 * a singelton.
 * 
 * @author Florian Krippner
 *
 */
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

	// a list of ProgrammingLanguagesOccurrenceModels of the latest landscape
	List<ProgrammingLanguagesOccurrenceModel> occurrence = new ArrayList<ProgrammingLanguagesOccurrenceModel>();

	/**
	 * This method updates the model with newer data.
	 * 
	 * @param programmingLanguageModel a list of ProgrammingLanguagesModels that
	 *                                 need to be updated
	 */
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

	/**
	 * 
	 * @return returns a list of the latest ProgrammingLanguagesOccurrenceModels
	 */
	public List<ProgrammingLanguagesOccurrenceModel> getCurrentProgrammingLanguagesOccurrence() {
		return occurrence;

	}

}
