package gov.nih.nci.cabig.caaers.security;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.User;

public class CaaersUser extends User{
	
	private String firstName;
	
	private String lastName;
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getFullName(){
		StringBuffer printableNameBuffer = new StringBuffer();
		if(firstName != null && !firstName.equals("")){
			printableNameBuffer.append(firstName);
			printableNameBuffer.append(" ");
		}
		if(lastName != null && !lastName.equals("")){
			printableNameBuffer.append(lastName);
		}
		return printableNameBuffer.toString();
	}
	
	CaaersUser(String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            GrantedAuthority[] authorities){

		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
}