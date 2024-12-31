package ptithcm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHI_TIET_KHUYEN_MAI")
public class ChiTietKhuyenMai {
	@EmbeddedId
	private PK pk;
	
	@Column(name = "TILEGIAM")
	private double PHANTRAM;
	
	public ChiTietKhuyenMai() {
		pk=new PK();
	}
	
	public PK getPk() {
		return pk;
	}



	public void setPk(PK pk) {
		this.pk = pk;
	}



	public double getPHANTRAM() {
		return PHANTRAM;
	}



	public void setPHANTRAM(double pHANTRAM) {
		PHANTRAM = pHANTRAM;
	}

	public KhuyenMai getKHUYENMAI() {
		return pk.KHUYENMAI;
	}

	public void setKHUYENMAI(KhuyenMai kHUYENMAI) {
		pk.KHUYENMAI = kHUYENMAI;
	}

	public SanPham getSANPHAM() {
		return pk.SANPHAM;
	}

	public void setSANPHAM(SanPham sANPHAM) {
		pk.SANPHAM = sANPHAM;
	}

	public Size getSIZE() {
		return pk.SIZE;
	}

	public void setSIZE(Size sIZE) {
		pk.SIZE = sIZE;
	}	

	@Embeddable
	public static class PK implements Serializable {
		private static final long serialVersionUID = 1L;
		@ManyToOne
		@JoinColumn(name = "MAKM")
		private KhuyenMai KHUYENMAI;
		
		@ManyToOne
		@JoinColumn(name = "MASP")
		private SanPham SANPHAM;
		
		@ManyToOne
		@JoinColumn(name = "MASIZE")
		private Size SIZE;

		public KhuyenMai getKHUYENMAI() {
			return KHUYENMAI;
		}

		public void setKHUYENMAI(KhuyenMai kHUYENMAI) {
			KHUYENMAI = kHUYENMAI;
		}

		public SanPham getSANPHAM() {
			return SANPHAM;
		}

		public void setSANPHAM(SanPham sANPHAM) {
			SANPHAM = sANPHAM;
		}

		public Size getSIZE() {
			return SIZE;
		}

		public void setSIZE(Size sIZE) {
			SIZE = sIZE;
		}	
		@Override
		public String toString() {
			return SANPHAM.getMASP()+SIZE.getMASIZE()+KHUYENMAI.getMAKM();
		}
	}
}
