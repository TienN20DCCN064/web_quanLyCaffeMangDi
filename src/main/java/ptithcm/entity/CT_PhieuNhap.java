package ptithcm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CT_PHIEUNHAP")
public class CT_PhieuNhap {
	@EmbeddedId
	private PK pk;

	@Column(name = "SOLUONG")
	private BigDecimal SOLUONG;

	@Column(name = "DONGIA")
	private BigDecimal DONGIA;

	@Embeddable
	public static class PK implements Serializable {
		private static final long serialVersionUID = 1L;
		@ManyToOne
		@JoinColumn(name = "MANH")
		private PhieuNhapHang phieunhaphang;

		@ManyToOne
		@JoinColumn(name = "MANL")
		private NguyenLieu nguyenlieu;

		public PK() {
		}

		public NguyenLieu getNGUYENLIEU() {
			return nguyenlieu;
		}

		public void setNGUYENLIEU(NguyenLieu nGUYENLIEU) {
			this.nguyenlieu = nGUYENLIEU;
		}

		public PhieuNhapHang getCT_SANPHAM() {
			return phieunhaphang;
		}

		public void setCT_SANPHAM(PhieuNhapHang phieunhaphang) {
			this.phieunhaphang = phieunhaphang;
		}
	}

	public PK getPk() {
		return pk;
	}

	public void setPk(PK pk) {
		this.pk = pk;
	}

	public BigDecimal getSOLUONG() {
		return SOLUONG;
	}

	public void setSOLUONG(BigDecimal sOLUONG) {
		SOLUONG = sOLUONG;
	}

	public BigDecimal getDONGIA() {
		return DONGIA;
	}

	public void setDONGIA(BigDecimal dONGIA) {
		DONGIA = dONGIA;
	}

}
