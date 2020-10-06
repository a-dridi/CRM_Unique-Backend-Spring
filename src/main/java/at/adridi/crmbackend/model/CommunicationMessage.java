package at.adridi.crmbackend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A written communication between a customer and the company. 
 * @author A.Dridi
 *
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class CommunicationMessage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long communicationMessageId;
	@ManyToOne(optional=true, cascade=CascadeType.ALL)
	private CommunicationType type;
	private String message;
	private String tag1;
	private String tag2;
	private String tag3;
	private String tag4;
	private Date createdDate = new Date();
	private Date updatedDate;
	
}
