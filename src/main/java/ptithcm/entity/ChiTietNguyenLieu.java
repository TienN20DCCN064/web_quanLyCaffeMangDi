package ptithcm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
@Entity
@Table(name="CHI_TIET_NGUYEN_LIEU")
public class ChiTietNguyenLieu {
	@EmbeddedId
	private PK pk;
	@Column(name="SOLUONG")
	private BigDecimal SOLUONG;
	@Embeddable
	public static class PK implements Serializable{
		private static final long serialVersionUID = 1L;
		@ManyToOne
		@JoinColumns({
			@JoinColumn(name="MASP"),
			@JoinColumn(name="MASIZE")
		})
		private ChiTietSanPham CT_SANPHAM;
		@ManyToOne
		@JoinColumn(name="MANL")
		private NguyenLieu NGUYENLIEU;
		public PK() {
			
		}
		public NguyenLieu getNGUYENLIEU() {
			return NGUYENLIEU;
		}
		public void setNGUYENLIEU(NguyenLieu nGUYENLIEU) {
			this.NGUYENLIEU=nGUYENLIEU;
		}
		public ChiTietSanPham getCT_SANPHAM() {
			return CT_SANPHAM;
		}
		public void setCT_SANPHAM(ChiTietSanPham cT_SANPHAM) {
			this.CT_SANPHAM=cT_SANPHAM;
		}
		@Override
		public String toString() {
			return "Pk [MASP_SIZE=" + CT_SANPHAM + ", MANL=" + NGUYENLIEU + "]";
		}
	}
	public ChiTietNguyenLieu() {
		pk=new PK();
	}
	public PK getPk() {
		return pk;
	}
	public void setPk(PK pk) {
		this.pk=pk;
	}
	public NguyenLieu getNGUYENLIEU() {
		return pk.NGUYENLIEU;
	}
	public void setNGUYENLIEU(NguyenLieu nGUYENLIEU) {
		this.pk.setNGUYENLIEU(nGUYENLIEU);
	}
	public ChiTietSanPham getCT_SANPHAM() {
		return pk.CT_SANPHAM;
	}
	public void setCT_SANPHAM(ChiTietSanPham cT_SANPHAM) {
		this.pk.setCT_SANPHAM(cT_SANPHAM);
	}
	public BigDecimal getSOLUONG() {
		return SOLUONG;
	}
	public void setSOLUONG(Number sOLUONG) {
		this.SOLUONG=new BigDecimal(sOLUONG.toString());
	}
//	@Override
//	public String toString() {
//		return "CT_SANPHAM [pk=" + pk + ", SOLUONG=" + SOLUONG  + "]";
//	}
}
