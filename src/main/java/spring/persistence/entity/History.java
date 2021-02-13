package spring.persistence.entity;

import java.time.LocalDateTime;

public class History {
	private int id;
	private LocalDateTime recogDate;
	private byte[] img;
	private double resultCat;
	private double resultDog;
	private String imgStr;

	public History() {}

	public History(LocalDateTime recogDate,byte[] img, double resultCat, double resultDog) {
		this.recogDate = recogDate;
		this.img = img;
		this.resultCat = resultCat;
		this.resultDog = resultDog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getRecogDate() {
		return recogDate;
	}

	public void setRecogDate(LocalDateTime recogDate) {
		this.recogDate = recogDate;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public double getResultCat() {
		return resultCat;
	}

	public void setResultCat(double resultCat) {
		this.resultCat = resultCat;
	}

	public double getResultDog() {
		return resultDog;
	}

	public void setResultDog(double resultDog) {
		this.resultDog = resultDog;
	}

	public String getImgStr() {
		return imgStr;
	}

	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}

}
