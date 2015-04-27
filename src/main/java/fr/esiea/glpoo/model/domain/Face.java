package fr.esiea.glpoo.model.domain;

public class Face {

	private FaceType face_type;

	private int face_id;

	private String bg_color;

	private String form;

	private String form_color;

	private String img_name;

	@Override
	public String toString() {
		return face_type.getCode();
	}

	public FaceType getFace_type() {
		return face_type;
	}

	public void setFace_type(FaceType face_type) {
		this.face_type = face_type;
	}

	public int getFace_id() {
		return face_id;
	}

	public void setFace_id(int face_id) {
		this.face_id = face_id;
	}

	public String getBg_color() {
		return bg_color;
	}

	public void setBg_color(String bg_color) {
		this.bg_color = bg_color;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getForm_color() {
		return form_color;
	}

	public void setForm_color(String form_color) {
		this.form_color = form_color;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

}
