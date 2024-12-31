package ptithcm.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="HOADON")
public class HoaDon {
	@Id
	@Column(name = "IDHD")
	private String ID;
	
	@Column(name = "NGAYLAP")
	@CreationTimestamp
	private Date NGAYLAP;

	@ManyToOne
	@JoinColumn(name = "MANV")
	private NhanVien MANV;
	
//	@ManyToOne
//	@JoinColumn(name = "MAKM")
//	private KhuyenMai MAKM;
//	
//	
	@Column(name = "TONGTIEN")
	private BigDecimal TONGTIEN;
	
	@Column(name = "PHANLOAI")
	private String PHANLOAI;
	
    public HoaDon() {
    }
	  public HoaDon(String ID, Date NGAYLAP, NhanVien MANV, BigDecimal TONGTIEN, String PHANLOAI) {
	        this.ID = ID;
	        this.MANV = MANV;
	        this.NGAYLAP = NGAYLAP;
	      //  this.MAKM = MAKM;
	        this.TONGTIEN = TONGTIEN;
	        this.PHANLOAI = PHANLOAI;
	    }
	


	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Date getNGAYLAP() {
		return NGAYLAP;
	}

	public void setNGAYLAP(Date nGAYLAP) {
		NGAYLAP = nGAYLAP;
	}

	public BigDecimal getTONGTIEN() {
		return TONGTIEN;
	}

	public void setTONGTIEN(BigDecimal tONGTIEN) {
		TONGTIEN = tONGTIEN;
	}

	public String getPHANLOAI() {
		return PHANLOAI;
	}

	public void setPHANLOAI(String pHANLOAI) {
		PHANLOAI = pHANLOAI;
	}

	public NhanVien getMANV() {
		return MANV;
	}

	public void setMANV(NhanVien mANV) {
		MANV = mANV;
	}


//	public KhuyenMai getMAKM() {
//		return MAKM;
//	}
//
//	public void setMAKM(KhuyenMai mAKM) {
//		MAKM = mAKM;
//	}
}
