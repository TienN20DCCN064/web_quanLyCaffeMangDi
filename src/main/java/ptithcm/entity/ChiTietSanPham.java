package ptithcm.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
@Entity
@Table(name="CHI_TIET_SAN_PHAM")
public class ChiTietSanPham {
	@EmbeddedId
	private PK pk;
	@ManyToOne
	@JoinColumn(name="MACT", nullable=true)
	private CongThuc CONGTHUC;
	
	@Column(name="GIAHIENTHOI")
	private BigDecimal GIAHIENTHOI;
	
//	@OneToMany(mappedBy="pkLS.CT_SANPHAM",fetch=FetchType.EAGER)
//	private Collection<LichSu> lichSu;
//	@OneToMany(mappedBy="pk.CT_SANPHAM",fetch=FetchType.EAGER)
//	private Collection<ChiTietNguyenLieu> cacChiTietNguyenLieu;
	@Embeddable
	public static class PK implements Serializable{
		private static final long serialVersionUID = 1L;
		@ManyToOne
		@JoinColumn(name="MASP")
		private SanPham SANPHAM;
		@ManyToOne
		@JoinColumn(name="MASIZE")
		private Size SIZE;
		public PK() {
			
		}
		public SanPham getSANPHAM() {
			return SANPHAM;
		}
		public void setSANPHAM(SanPham sANPHAM) {
			this.SANPHAM=sANPHAM;
		}
		public Size getSIZE() {
			return SIZE;
		}
		public void setSIZE(Size sIZE) {
			this.SIZE=sIZE;
		}
		@Override
		public String toString() {
			return SANPHAM.getMASP()+"|" + SIZE.getMASIZE();
		}
	}
	public ChiTietSanPham() {
		
	}
	public PK getPk() {
		return pk;
	}
	public void setPk(PK pk) {
		this.pk=pk;
	}
	public SanPham getSANPHAM() {
		return pk.SANPHAM;
	}
	public void setSANPHAM(SanPham sANPHAM) {
		this.pk.setSANPHAM(sANPHAM);
	}
	public Size getSIZE() {
		return pk.SIZE;
	}
	public void setSIZE(Size sIZE) {
		this.pk.setSIZE(sIZE);
	}
	public CongThuc getCONGTHUC() {
		return CONGTHUC;
	}
	public void setCONGTHUC(CongThuc cONGTHUC) {
		this.CONGTHUC=cONGTHUC;
	}
	public BigDecimal getGIAHIENTHOI() {
		return GIAHIENTHOI;
	}
	public void setGIAHIENTHOI(Number gIAHIENTHOI) {
		this.GIAHIENTHOI=new BigDecimal(gIAHIENTHOI.toString());
	}
	 @Override
	    public String toString() {
	        return "ChiTietSanPham [PK=" + pk.toString() + ", CONGTHUC=" + (CONGTHUC != null ? CONGTHUC.toString() : "null") + ", GIAHIENTHOI=" + GIAHIENTHOI + "]";
	    }
//	public Collection <LichSu> getLichSu(){
//		return lichSu;
//	}
//	public void setLichSu(Collection<LichSu> lichSu) {
//		this.lichSu=lichSu;
//	}
//	public Collection <ChiTietNguyenLieu> getCacChiTietNguyenLieu(){
//		return cacChiTietNguyenLieu;
//	}
//	public void setCacChiTietNguyenLieu(Collection<ChiTietNguyenLieu> cacChiTietNguyenLieu) {
//		this.cacChiTietNguyenLieu=cacChiTietNguyenLieu;
//	}
}
