package com.cdac.entities;
/*
 * User - class
id : Long
first name , last name, email ,password - String
dob - LocalDate
role : Enum 
image : byte[]
 */
/*
 * Mandatory annotation for hibernate to manage entity 
 * @Entity - class level 
 * @Id - for PK (unique id property - typically - ref type for easy null 
 * checking) - field level | getter level
 */
import jakarta.persistence.*;//JPA package

import java.time.LocalDate;

@Entity //mandatory
@Table(name="users") //name of the table
public class User {
	@Id //mandatory , PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)//(strategy = GenerationType.AUTO) //=> auto generation of pks.
	//for adding auto_increment constraint - 
	//choose strategy = GenerationType.IDENTITY
	@Column(name="user_id") //column name
	private Long userId;
	
	//col name first_name , varchar(20)
	@Column(name="first_name",length = 20)
	private String firstName;
	@Column(name="last_name",length = 30)
	private String lastName;
	//col name - email , len=30 , unique constraint
	@Column(length = 30,unique = true)
	private String email;
	//col name - password , len=20 ,not null constraint
	@Column(length = 20, nullable = false)
	private String password;
	//col name dob , type : date
	private LocalDate dob;
	//col - enum , name=user_role
	//to store enum constants
	@Enumerated(EnumType.STRING)
	@Column(name="user_role")
	private UserRole role;
	@Lob //to customize col type - longblob
	private byte[] image;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String firstName, String lastName, String email, 
			String password, LocalDate dob, UserRole role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.role = role;
	}
	
	public User(String firstName, String lastName, LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", dob=" + dob + ", role=" + role + "]";
	}
	
	

}
