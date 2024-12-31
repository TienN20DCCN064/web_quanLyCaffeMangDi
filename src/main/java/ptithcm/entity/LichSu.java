package ptithcm.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
@Entity
@Table(name="LICHSU")
public class LichSu {
	@EmbeddedId
	private PK pk;
	
	@Column(name="GIATHAYDOI")
	private BigDecimal GIATHAYDOI;
	
	@Column(name="GIACU")
	private BigDecimal GIACU;
	@Embeddable
	public static class PK implements Serializable{
		private static final long serialVersionUID = 1L;
		@Column(name="THOIGIAN")
		@CreationTimestamp
		private Timestamp THOIGIAN;
		@ManyToOne
		@JoinColumns({
			@JoinColumn(name="MASP"),
			@JoinColumn(name="MASIZE")
			})
		private ChiTietSanPham CT_SANPHAM;
		public Timestamp getTHOIGIAN() {
			return THOIGIAN;
		}
		public void setTHOIGIAN(Timestamp tHOIGIAN) {
			this.THOIGIAN=tHOIGIAN;
		}
		public ChiTietSanPham getCT_SANPHAM() {
			return CT_SANPHAM;
		}
		public void setCT_SANPHAM(ChiTietSanPham cT_SANPHAM) {
			this.CT_SANPHAM=cT_SANPHAM;
		}
		public String toString() {
			return "Pk [THOIGIAN=" + THOIGIAN + ", CT_SANPHAM=" + CT_SANPHAM + "]";
		}
	}
	public LichSu() {
		
	}
	public LichSu(BigDecimal giaThayDoi,ChiTietSanPham cT_SANPHAM) {
		this.pk=new PK();
		pk.setTHOIGIAN(Timestamp.valueOf(LocalDateTime.now()));
		pk.setCT_SANPHAM(cT_SANPHAM);
//		setGIACU(getGIATHAYDOI());
//		setGIATHAYDOI(cT_SANPHAM.getGIAHIENTHOI());

		setGIACU(giaThayDoi);
		setGIATHAYDOI(cT_SANPHAM.getGIAHIENTHOI());
	}
	
	
	public PK getPK() {
		return pk;
	}
	public void setPK(PK pk) {
		this.pk=pk;
	}
	public Timestamp getTHOIGIAN() {
		return pk.getTHOIGIAN();
	}
	public void setTHOIGIAN(Timestamp tHOIGIAN) {
		this.pk.setTHOIGIAN(tHOIGIAN);
	}
	public ChiTietSanPham getCT_SANPHAM() {
		return pk.getCT_SANPHAM();
	}
	public void setCT_SANPHAM(ChiTietSanPham cT_SANPHAM) {
		this.pk.setCT_SANPHAM(cT_SANPHAM);
	}
	public BigDecimal getGIATHAYDOI() {
		return GIATHAYDOI;
	}
	public void setGIATHAYDOI(BigDecimal gIATHAYDOI) {
		this.GIATHAYDOI=gIATHAYDOI;
	}
	public BigDecimal getGIACU() {
		return GIACU;
	}
	public void setGIACU(BigDecimal gIACu) {
		this.GIACU=gIACu;
	}
}
