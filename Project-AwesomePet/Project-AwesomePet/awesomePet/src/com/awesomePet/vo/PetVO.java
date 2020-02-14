package com.awesomePet.vo;

import java.util.List;

public class PetVO {
	private int petID;
	private String subTypeName;
	private int age;
	private String gender;
	private int price;
	private String vaccination;
	private String neutralization;
	private List<PetContentsImageVO> imageList;
	
// 생성자
	public PetVO() { }
	
	public PetVO(String subTypeName,
				 int age,
				 String gender,
				 int price,
				 String vaccination,
				 String neutralization) {
		this(0,
			 subTypeName,
			 age,
			 gender,
			 price,
			 vaccination,
			 neutralization,
			 null);
	}
	
	public PetVO(int petID,
			 	 String subTypeName,
			 	 int age,
			 	 String gender,
			 	 int price,
			 	 String vaccination,
			 	 String neutralization) {
		this(petID,
			 subTypeName,
			 age,
			 gender,
			 price,
			 vaccination,
			 neutralization,
			 null);
	}
	
	public PetVO(int petID,
				 String subTypeName,
				 int age,
				 String gender,
				 int price,
				 String vaccination,
				 String neutralization,
				 List<PetContentsImageVO> imageList) {
		this.petID = petID;
		this.subTypeName = subTypeName;
		this.age = age;
		this.gender = gender;
		this.price = price;
		this.vaccination = vaccination;
		this.neutralization = neutralization;
		this.imageList = imageList;
	}

	
// petID
	public int getPetID() {
		return petID;
	}
	public void setPetID(int petID) {
		this.petID = petID;
	}

	
// subTypeName
	public String getSubTypeName() {
		return subTypeName;
	}
	public void setSubTypeName(String subTypeName) {
		this.subTypeName = subTypeName;
	}

	
// age
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	
// gender
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
// price
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
// vaccination
	public String getVaccination() {
		return vaccination;
	}
	public void setVaccination(String vaccination) {
		this.vaccination = vaccination;
	}

	
// neutralization
	public String getNeutralization() {
		return neutralization;
	}
	public void setNeutralization(String neutralization) {
		this.neutralization = neutralization;
	}
	
	
// imageList
	public List<PetContentsImageVO> getImageList() {
		return imageList;
	}
	public void setImageList(List<PetContentsImageVO> imageList) {
		this.imageList = imageList;
	}
}
