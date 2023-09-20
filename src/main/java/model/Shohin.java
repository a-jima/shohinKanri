package model;

import java.io.Serializable;

public class Shohin implements Serializable {
	private String id;
	private String name;
	private String bunrui;
	private int hanbai;
	private int shiire;
	
	public Shohin() {
		;
	}
	public Shohin(String id,String name,String bunrui,int hanbai,int shiire) {
		this.id=id;
		this.name=name;
		this.bunrui =bunrui;
		this.hanbai=hanbai;
		this.shiire=shiire;
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
	public String getBunrui() {
		return bunrui;
	}
	public void setBunrui(String bunrui) {
		this.bunrui = bunrui;
	}
	public int getHanbai() {
		return hanbai;
	}
	public void setHanbai(int hanbai) {
		this.hanbai = hanbai;
	}
	public int getShiire() {
		return shiire;
	}
	public void setShiire(int shiire) {
		this.shiire = shiire;
	}
	

}
