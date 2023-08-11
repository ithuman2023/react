package com.tistory.itbaewom.SeleniumProject.vo;

import java.util.List;

import lombok.Data;
@Data
public class MapAreaVO {
	private String mainImg;
	private String mainArea;
	private List<Area> areaList;
	@Data
	public static class Area{
		private String shape;
		private String code;
		private String coords;
		private String area;
		private String selimg;
		private String alt;
	}
}
