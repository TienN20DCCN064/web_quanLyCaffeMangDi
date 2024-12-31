package ptithcm.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "QUYEN")
public class Quyen {
	@Id
	@Column(name = "MAQUYEN")
	private String MAQUYEN;
	
	@OneToMany(mappedBy="QUYEN", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	Collection<TaiKhoan> taikhoan;

	@Column(name = "TENCV")
	private String TENCV;

	public String getMAQUYEN() {
		return MAQUYEN;
	}

	public void setMAQUYEN(String mAQUYEN) {
		MAQUYEN = mAQUYEN;
	}

	public String getTENCV() {
		return TENCV;
	}
	

	public void setTENCV(String tENQUYEN) {
		TENCV = tENQUYEN;
	}
	

}
