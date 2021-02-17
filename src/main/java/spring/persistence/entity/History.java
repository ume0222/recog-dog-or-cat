package spring.persistence.entity;

public class History {
	private int id;
	private byte[] img;
	private String recogDate;
	private double resultCat;
	private double resultDog;
	private String imgStr;
	private String judge;

	public History() {}

	public History(byte[] img, double resultCat, double resultDog, String judge) {
		this.img = img;
		this.resultCat = resultCat;
		this.resultDog = resultDog;
		this.judge = judge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public String getRecogDate() {
		return recogDate;
	}

	public void setRecogDate(String recogDate) {
		this.recogDate = recogDate;
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

	public String getJudge() {
		return judge;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

}
