package com.spring.demo.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Category")
public class Category implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private int id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_content")
    private String categoryContent;


    public Category(int id, String categoryName, String categoryContent) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryContent = categoryContent;
    }

    public Category() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryContent() {
        return categoryContent;
    }

    public void setCategoryContent(String categoryContent) {
        this.categoryContent = categoryContent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryContent='" + categoryContent + '\'' +
                '}';
    }
}
