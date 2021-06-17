package com.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oid")
    private int id;
    @Column(name="status")
    private String status;
    @Column(name="total_amount")
    private double totalAmount;
    @Column(name="created_v")
    private Date createDate;
    @Column(name="order_user_id")
    private int orderUserId;

    public Order(){

    }

	public Order(int id, String status, double totalAmount, Date createDate, int orderUserId) {
		super();
		this.id = id;
		this.status = status;
		this.totalAmount = totalAmount;
		this.createDate = createDate;
		this.orderUserId = orderUserId;
	}

	public Order(String status, double totalAmount, Date createDate, int orderUserId) {
		super();
		this.status = status;
		this.totalAmount = totalAmount;
		this.createDate = createDate;
		this.orderUserId = orderUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(int orderUserId) {
		this.orderUserId = orderUserId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", totalAmount=" + totalAmount + ", createDate=" + createDate
				+ ", orderUserId=" + orderUserId + "]";
	}
    
}
