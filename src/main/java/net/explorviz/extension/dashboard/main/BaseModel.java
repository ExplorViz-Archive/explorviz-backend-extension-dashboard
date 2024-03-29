package net.explorviz.extension.dashboard.main;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.jasminb.jsonapi.annotations.Id;
import net.explorviz.shared.common.idgen.IdGenerator;

/**
 * This is the BaseModel for all other Models in this project.
 * 
 * Needed for cyclical serialization
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.StringIdGenerator.class, property = "id")
public class BaseModel {

	private static IdGenerator idGenerator;

	@Id
	private String id;

	public BaseModel() {

		if (idGenerator == null) {
			throw new IllegalStateException("No id generator set. Call BaseEntity.initialize() first");
		}

		this.id = idGenerator.generateId();

	}

	public static void initialize(IdGenerator idGenerator) {
		BaseModel.idGenerator = idGenerator;
	}

	public String getId() {
		return this.id;
	}

	public void updateId() {
		this.id = idGenerator.generateId();
	}

}
