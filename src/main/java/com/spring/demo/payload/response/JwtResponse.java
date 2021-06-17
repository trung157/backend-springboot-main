package com.spring.demo.payload.response;

import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String identityCard;
    private String phone ;
    private List<String> roles;
	public JwtResponse(String accessToken, int id, String firstName, String lastName, String email,
			String identityCard, String phone,List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.identityCard = identityCard;
		this.phone = phone;
		this.roles = roles;
	}
	public String getAccessToken() {
		return token;
	}
	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}
	public String getTokenType() {
		return type;
	}
	public void setTokenType(String tokenType) {
		this.type = tokenType;
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
//	List<String> getRoles() {
//		return roles;
//	}
	public List<String> getRoles() {
        return roles;
    }
    
}
