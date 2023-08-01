
package com.image.imageEntity;

public class ImageDataEntity {

	private String fileName;
	private String message;

	public ImageDataEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageDataEntity(String fileName, String message) {
		super();
		this.fileName = fileName;
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ImageDataEntity [fileName=" + fileName + ", message=" + message + "]";
	}

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 * 
	 * @Lob private byte[] imageData;
	 * 
	 * public ImageDataEntity() { super(); }
	 * 
	 * public ImageDataEntity(Long id, String imageName, byte[] imageData) {
	 * super(); this.id = id; this.imageData = imageData; }
	 * 
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public byte[] getimageData() { return imageData; }
	 * 
	 * public void setimageData(byte[] imageData) { this.imageData = imageData; }
	 * 
	 * @Override public String toString() { return "ImageDataEntity [id=" + id +
	 * ", imageData=" + Arrays.toString(imageData) + "]"; }
	 */
}
