package at.adridi.crmbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * (Internal) notes of customers.
 * 
 * @author A.Dridi
 *
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class CustomerNote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerNoteId;

	private String title;
	private String description;
	private String link;
	private Date createdDate = new Date();
	private Date updatedDate;

}
