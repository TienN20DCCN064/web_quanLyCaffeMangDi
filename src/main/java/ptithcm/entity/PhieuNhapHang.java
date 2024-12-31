package ptithcm.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "PHIEUNHAPHANG")
public class PhieuNhapHang {
	@Id
	@Column(name = "MANH")
	private String MANH;

	@ManyToOne
	@JoinColumn(name = "MANV")
	NhanVien nhanvien;

//	@OneToMany(mappedBy = "phieunhap_ctphieunhap", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//	Collection<CT_PhieuNhap> ctphieunhap;

	@Column(name="NGAYNHAP")
	@CreationTimestamp
	private Timestamp NGAYNHAP;

	public String getMANH() {
		return MANH;
	}

	public void setMANH(String mANH) {
		MANH = mANH;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}
	public Date getNGAYNHAP() {
		return NGAYNHAP;
	}

	public void setNGAYNHAP(Timestamp nGAYNHAP) {
		NGAYNHAP = nGAYNHAP;
	}
//	public String getNguyenLieuNhap() {
//		
//	}

}
