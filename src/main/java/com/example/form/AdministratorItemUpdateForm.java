package com.example.form;


import org.springframework.web.multipart.MultipartFile;

public class AdministratorItemUpdateForm {
	
	/** ID */
	private String id;
	/** 商品名 */
	private String name;
	/** 商品説明 */
	private String description;
	/** PriceM */
	private String priceM;
	/** PriceL */
	private String priceL;
	/** 画像 */
	private MultipartFile imagePath;
	
	private Boolean deleted;
	
	public Integer getIntPriceM() {
		return Integer.parseInt(priceM);
	}
	
	public Integer getIntPriceL() {
		return Integer.parseInt(priceL);
	}
	
	public Integer getIntId() {
		return Integer.parseInt(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriceM() {
		return priceM;
	}

	public void setPriceM(String priceM) {
		this.priceM = priceM;
	}

	public String getPriceL() {
		return priceL;
	}

	public void setPriceL(String priceL) {
		this.priceL = priceL;
	}

	public MultipartFile getImagePath() {
		return imagePath;
	}

	public void setImagePath(MultipartFile imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "AdministratorItemUpdateForm [id=" + id + ", name=" + name + ", description=" + description + ", priceM="
				+ priceM + ", priceL=" + priceL + ", imagePath=" + imagePath + ", deleted=" + deleted + "]";
	}













	

	

}
