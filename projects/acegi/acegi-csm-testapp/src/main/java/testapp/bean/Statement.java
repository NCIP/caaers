package testapp.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "STATEMENTS")
@GenericGenerator(name = "id-generator", 
		strategy = "native", 
		parameters = { @Parameter(name = "sequence", 
				value = "SEQ_STATEMENTS_ID") })
public class Statement {

	private Long id;

	private Date date;

	private String value;

	private Person person;

	private Set<Statement> relatedStatements = new HashSet<Statement>();

	@ManyToMany(targetEntity = Statement.class, 
			cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinTable(name = "STATEMENT_STATEMENT", 
			joinColumns = { @JoinColumn(name = "STATEMENT_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "RELATED_STATEMENT_ID") })
	public Set<Statement> getRelatedStatements() {
		return relatedStatements;
	}

	public void setRelatedStatements(Set<Statement> relatedStatements) {
		this.relatedStatements = relatedStatements;
	}

	public Statement() {
	}

	@Temporal(TemporalType.TIME)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Id
	@GeneratedValue(generator = "id-generator")
	@Column(name = "STATEMENT_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
