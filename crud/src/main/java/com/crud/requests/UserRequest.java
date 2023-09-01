package com.crud.requests;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
	
	@NotNull(message = "ce champ ne doit pas etre null ") // firstname not null
	@Size(min=3, message ="il faut au moins 3 lettres")
	private String firstName;
	@NotNull(message = "ce champ ne doit pas etre null ") 
	@Size(min=3, message ="il faut au moins 3 lettres")
	private String lastName;
	@NotNull(message = "ce champ ne doit pas etre null ") 
	@Size(min=3, message ="il faut au moins 3 lettres")
	private String address;
	@NotNull(message = "ce champ ne doit pas etre null ") 
	@Size(min=3, message ="il faut au moins 3 lettres")
	private String mobile;
	@NotNull(message = "ce champ ne doit pas etre null ") 
	@Email(message = "ce champ doit respecter le format email ")
	private String email;
	@NotNull(message = "ce champ ne doit pas etre null ") 
	@Size(min=8, max=12, message ="entre 8 et 12 lettres")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$", message="at least 1 MAJ and 1 MIN and a number and a symbole and Min 8 chars and Max 12 chars ")
	private String password;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

}
