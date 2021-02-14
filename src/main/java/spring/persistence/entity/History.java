package spring.persistence.entity;

public class History {
	private int id;
	private String recogDate;
	private byte[] img;
	private double resultCat;
	private double resultDog;
	private String imgStr;

	public History() {}

	public History(String recogDate,byte[] img, double resultCat, double resultDog) {
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

	public String getRecogDate() {
		return recogDate;
	}

	public void setRecogDate(String recogDate) {
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
