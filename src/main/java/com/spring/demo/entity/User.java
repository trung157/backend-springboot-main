package com.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "identity_card") 
	})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "uid")
    private int id;
    @Column(name= "first_name")
    private String firstName;
    @Column(name= "last_name")
    private String lastName;
    @Column(name= "email")
    private String email;
    @Column(name= "identity_card")
    private String identityCard;
    @Column(name= "phone")
    private String phone ;
    @Column(name= "password")
    private String password;
    @Column(name= "lock")
    private int lock;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "User_Roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    public User() {
    	super();
    }

    public User(int id, String firstName, String lastName, String email, String identityCard, String phone, String password, int lock) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identityCard = identityCard;
        this.phone = phone;
        this.password = password;
        this.lock = lock;
    }
    public User(String firstName, String lastName, String email, String identityCard, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identityCard = identityCard;
        this.phone = phone;
        this.password = password;
    }
    public User(String email,String password) {
        this.email = email;
        this.password = password;
    }
    public User(String firstName, String lastName, String email, String identityCard, String phone, String password, int lock) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identityCard = identityCard;
        this.phone = phone;
        this.password = password;
        this.lock = lock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public int getLock() {
		return lock;
	}

	public void setLock(int lock) {
		this.lock = lock;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


}
