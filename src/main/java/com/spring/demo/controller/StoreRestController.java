package com.spring.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.dao.RoleRepository;
import com.spring.demo.dao.UserRepository;
import com.spring.demo.entity.Category;
import com.spring.demo.entity.ERole;
import com.spring.demo.entity.Product;
import com.spring.demo.entity.Role;
import com.spring.demo.entity.User;
import com.spring.demo.payload.request.LoginRequest;
import com.spring.demo.payload.request.SignupRequest;
import com.spring.demo.payload.response.JwtResponse;
import com.spring.demo.payload.response.MessageResponse;
import com.spring.demo.security.jwt.JwtUtils;
import com.spring.demo.service.StoreService;
import com.spring.demo.service.UserDetailsImpl;
import com.spring.demo.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class StoreRestController {
	private final StoreService storeService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	UserRepository userRepository;

	@Autowired
	public StoreRestController(StoreService theStoreService) {
		this.storeService = theStoreService;
	}

	@GetMapping("/user")
	public List<User> getUsers() {
		return storeService.findAllUser();
	}

	@GetMapping("/usersnolock")
	public List<User> listUserNolock() {
		return storeService.listUserNolock();
	}

	@PostMapping("/user/lock/{uid}")
	public void lockUser(@PathVariable int uid) {
		storeService.lockUser(uid);
	}

	@PostMapping("/user/unlock/{uid}")
	public void unlockUser(@PathVariable int uid) {
		storeService.unlockUser(uid);
	}

	@PostMapping("/user")
	public void saveUser(@RequestBody User theUser) {
		storeService.saveUser(theUser);
	}

	@GetMapping("/user/{theUserId}")
	public User getUser(@PathVariable int theUserId) {
		User user = storeService.findByUserId(theUserId);
		if (user == null) {
			throw new RuntimeException("Not found this id" + theUserId);
		}
		return user;
	}

	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User userDetail) {
		User user = storeService.findByUserId(id);

		user.setFirstName(userDetail.getFirstName());
		user.setLastName(userDetail.getLastName());
		user.setEmail(userDetail.getEmail());
		user.setIdentityCard(userDetail.getIdentityCard());
		user.setPhone(userDetail.getPhone());
		user.setPassword(userDetail.getPassword());

		storeService.saveUser(user);
		return user;
	}

	@DeleteMapping("/user/{theUserId}")
	public String deleteUser(@PathVariable int theUserId) {
		User user = storeService.findByUserId(theUserId);
		if (user == null) {
			throw new RuntimeException("User id not found - " + theUserId);
		}
		storeService.deleteByUserId(theUserId);
		return "Delete customer id: " + user;
	}

//
//	@PutMapping("/user")
//	public User updateUser(@RequestBody User theUser) {
//		storeService.saveUser(theUser);
//		return theUser;
//	}

// Product
//	List All Product
	@GetMapping("/products")
	public List<Product> getProducts() {
		return storeService.getAllProducts();
	}

// 	Insert Product
	@PostMapping("/products")
	public void updateProduct(@RequestBody Product product) {
		storeService.saveProduct(product);
	}

//	Update Product
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable int id, @RequestBody Product productDetail) {
		Product product = storeService.findByProductId(id);

		product.setProductName(productDetail.getProductName());
		product.setProductPrice(productDetail.getProductPrice());
		product.setQuantity(productDetail.getQuantity());
		product.setProductImage(productDetail.getProductImage());
		product.setLocation(productDetail.getLocation());
		product.setProductDescription(productDetail.getProductDescription());

		storeService.saveProduct(product);
		return product;
	}

//	Delete Product
	@DeleteMapping("/products/{pid}")
	public void deleteProduct(@PathVariable int pid) {
		storeService.deleteByProductId(pid);
	}

// Find Product By ID
	@GetMapping("/products/{id}")
	public Product findByProductId(@PathVariable int id) {
		Product product = storeService.findByProductId(id);
		if (product == null) {
			throw new RuntimeException("Not found this id" + id);
		}
		return product;
	}

//
	@GetMapping("/category")
	public List<Category> getAllCategory() {
		return storeService.getAllCategory();
	}

	@GetMapping("/category/{theCategoryId}")
	public List<Product> listProductByCategory(@PathVariable int theCategoryId) {
		return storeService.getListProductByCategory(theCategoryId);
	}

//sign in
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(),
												 userDetails.getFirstName(),
												 userDetails.getLastName(),
												 userDetails.getUsername(), 
												 userDetails.getIdentityCard(),
												 userDetails.getPhone(), 
												 roles));
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
//		if (userRepository.existByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Username is already taken!"));
//		}
//
//		if (userRepository.existByPhone(signUpRequest.getPhone())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Phone is already in use!"));
//		}

		// Create new user's account
		User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(),
				signUpRequest.getIdentityCard(), signUpRequest.getPhone(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "staff":
					Role modRole = roleRepository.findByName(ERole.ROLE_STAFF)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
