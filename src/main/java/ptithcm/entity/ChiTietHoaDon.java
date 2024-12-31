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
@Table(name = "CT_HOADON")
public class ChiTietHoaDon {
	@EmbeddedId
	private PK pk;
	
	@Column(name = "SOLUONG")
	private int SOLUONG;
	  // Constructor cho ChiTietHoaDon
	public ChiTietHoaDon() {
		
	}
    public ChiTietHoaDon(PK pk, int SOLUONG) {
        this.pk = pk;
        this.SOLUONG = SOLUONG;
    }
	public PK getPk() {
		return pk;
	}

	public void setPk(PK pk) {
		this.pk = pk;
	}

	public int getSOLUONG() {
		return SOLUONG;
	}

	public void setSOLUONG(int sOLUONG) {
		SOLUONG = sOLUONG;
	}
	@Override
	public String toString() {
	    String hoaDonId = (pk.getHOADON() != null) ? pk.getHOADON().getID() : "Không có ID hóa đơn";  // Kiểm tra null và cung cấp giá trị mặc định nếu null
	    String sanPhamTen = (pk.getSANPHAM() != null) ? pk.getSANPHAM().getTENSP() : "Không có tên sản phẩm";  // Kiểm tra null và cung cấp giá trị mặc định nếu null
	    String sizeMa = (pk.getSIZE() != null) ? pk.getSIZE().getMASIZE() : "Không có mã size";  // Kiểm tra null và cung cấp giá trị mặc định nếu null
	    String maKm = (pk.getMAKM() != null) ? pk.getMAKM().getMAKM() : "Không có mã khuyến mãi";  // Kiểm tra null và cung cấp giá trị mặc định nếu null

	    return "\nChiTietHoaDon{" +
	            "HOADON=" + hoaDonId +
	            ", SANPHAM=" + sanPhamTen +
	            ", SIZE=" + sizeMa +
	            ", MAKM=" + maKm +
	            ", SOLUONG=" + SOLUONG +
	            '}';
	}


	@Embeddable
	public static class PK implements Serializable {
		private static final long serialVersionUID = 1L;
		@ManyToOne
		@JoinColumn(name = "ID")
		private HoaDon HOADON;
		
		@ManyToOne
		@JoinColumn(name = "MASP")
		private SanPham SANPHAM;
		
		@ManyToOne
		@JoinColumn(name = "MASIZE")
		private Size SIZE;
		
		@ManyToOne
		@JoinColumn(name = "MAKM")
		private KhuyenMai MAKM;
		 // Constructor cho PK
		public PK() {
			
		}
        public PK(HoaDon HOADON, SanPham SANPHAM, Size SIZE, KhuyenMai MAKM) {
            this.HOADON = HOADON;
            this.SANPHAM = SANPHAM;
            this.SIZE = SIZE;
            this.MAKM = MAKM;
        }

		public HoaDon getHOADON() {
			return HOADON;
		}

		public void setHOADON(HoaDon hOADON) {
			HOADON = hOADON;
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
		  public KhuyenMai getMAKM() {
		        return MAKM;
		    }

		    public void setMAKM(KhuyenMai MAKM) {
		        this.MAKM = MAKM;
		    }
	}
	
}
