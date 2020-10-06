package at.adridi.crmbackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The customer or client of the company.
 * @author A.Dridi
 *
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "customeridgenerator")
	@TableGenerator(name = "customeridgenerator", initialValue = 1000, allocationSize = 2000, table = "sequence_customerid")
	private Long customerId;

	private String companyName;
	private String forename;
	private String surname;
	@Column(unique=true)
	private String email;
	private Integer telephone;
	private String street;
	private String city;
	private Integer postCode;
	private String country;
	private String IBAN;
	private String BIC;
	private String bankInformation;
	private String website;
	private String facebookUrl;
	private String twitterUrl;
	private String linkedinUrl;
	private String xingUrl;
	private String socialmediaUrl;
	private String language;
	private String timezone;
	private String note;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="communicationmessage_customer")
	private List<CommunicationMessage> communicationMessageList = new ArrayList<>();
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="customernote_customer")
	private List<CustomerNote> customerNoteList = new ArrayList<>();
}
