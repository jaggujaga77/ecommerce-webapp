package com.abhicodes.datapoem.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", productPrice=" + productPrice +
				", productDesc='" + productDesc + '\'' +
				", productMfgDate='" + productMfgDate + '\'' +
				", productStock=" + productStock +
				", productBrand='" + productBrand + '\'' +
				'}';
	}

	@Id
	@Column(name="id")
	private long productId;
	@Column(name="name")
	private String productName;
	@Column(name="price")
	private int productPrice;
	@Column(name="desc")
	private String productDesc;
	@Column(name="mfg_date")
	private String productMfgDate;
	@Column(name="quantity")
	private int productStock;
	@Column(name="brand")
	private String productBrand;
	
}
