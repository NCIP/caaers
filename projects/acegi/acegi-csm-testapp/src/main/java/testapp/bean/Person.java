/**
 * 
 */
package testapp.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * @author joshua
 *
 */
@Entity
@Table(name="PERSONS")
@GenericGenerator(name="id-generator", strategy = "native",
    parameters = {
        @Parameter(name="sequence", value="SEQ_PERSONS_ID")
    }
)
public class Person {
	
	private Long id;
	private String name;
	
	private Set<Statement> statements = new HashSet<Statement>();
	
	public Person(){}
	
	@Id @GeneratedValue(generator="id-generator")
	@Column(name="PERSON_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany (mappedBy = "person", fetch=FetchType.LAZY)
    @Cascade (value = { CascadeType.ALL })
	public Set<Statement> getStatements() {
		return statements;
	}

	public void setStatements(Set<Statement> statements) {
		this.statements = statements;
	}

}
