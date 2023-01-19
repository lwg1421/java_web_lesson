package com.oraclejava.bean;

public class Bmi {
	
	//변수생성
	private double height,weight,bmi;

	
	// height, weight -> 게터, 세터 다만들고
    // bmi -> 게터만 작성(읽기만 가능)

	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public double getBmi() {
		bmi = (weight/(height*height))*10000;
		return Math.round(bmi*100)/100.0;
	}

	
	
	
}
