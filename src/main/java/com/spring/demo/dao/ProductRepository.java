package com.spring.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query(value = "select * from Product p left join Category c on p.category_id = c.cid where c.cid = ", nativeQuery = true)
	public List<Product> getListProductByCategory(int categoryId);
}