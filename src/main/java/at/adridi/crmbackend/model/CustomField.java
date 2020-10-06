package at.adridi.crmbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * A custom field for a customer (information). Example -> Field: Birth place - Type: String
 * 
 * @author A.Dridi
 *
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class CustomField {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customFieldId;
	
	private String fieldName;
	private String fieldType;
}
