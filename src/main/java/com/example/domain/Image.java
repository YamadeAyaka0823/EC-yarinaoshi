package com.example.domain;

public class Image {
	
	/** 幅 */
	int width;
	/** URL */
	String url;
	/** タイトル */
	String title;
	/** 高さ */
	int height;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "Image [width=" + width + ", url=" + url + ", title=" + title + ", height=" + height + "]";
	}
	
	

}
