package customer;
import java.util.List;

public class Cust {  
	private int partyId;  
	protected String firstName,lastName,address,city,state,country,password;
	protected int phone,zip;
	
	public Cust(int partyId,String firstName,String lastName, String address,String city,String state,String country,String password,
	int phone, int zip) {
		// TODO Auto-generated constructor stub
	}
	   
public int getpartyId() {  
    return partyId;  
}  
public void setpartyId(int partyId) {  
    this.partyId = partyId;  
}  
public String getfirstName() {  
    return firstName;  
}  
public void setfirstName(String firstName) {  
    this.firstName = firstName;  
} 

public String getlastName() {  
    return lastName;  
}  
public void setlastName(String lastName) {  
    this.lastName = lastName;  
}
public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}


public String getcity() {  
    return city;  
}  
public void setcity(String city) {  
    this.city = city;  
}  
public String getstate() {  
    return state;  
}  
public void setstate(String state) {  
    this.state = state;  
}  
public String getCountry() {  
    return country;  
}  
public void setCountry(String country) {  
    this.country = country;  
}  
public String getPassword() {  
    return password;  
}  
public void setPassword(String password) {  
    this.password = password;  
}  

public int getPhone() {
	return phone;
}

public void setPhone(int phone) {
	this.phone = phone;
}

public int getzip() {
	return zip;
}

public void setzip(int zip) {
	this.zip = zip;
}
public static List<Cust> selectAllUsers() {
	// TODO Auto-generated method stub
	return null;
}
}  