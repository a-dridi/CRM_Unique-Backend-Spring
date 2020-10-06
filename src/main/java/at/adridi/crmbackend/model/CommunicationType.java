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
 * The (custom created) type of the communication with a customer. 
 * @author A.Dridi
 *
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class CommunicationType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long communicationTypeId;

	private String title;
	private String colorHex;
}
