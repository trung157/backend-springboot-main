package com.spring.demo.service;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String identityCard;
	private String phone;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;


	public UserDetailsImpl(int id, String firstname, String lastname, String email, String identityCard, String phone,
			String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.identityCard = identityCard;
		this.phone = phone;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getIdentityCard(),
				user.getPhone(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public String getPassword() {
		return password;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}


}