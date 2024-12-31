package ptithcm.entity;

import javax.persistence.*;


@Entity
@Table(name="SANPHAM")
public class SanPham {
	@Id
	@Column(name = "MASP")
	private String MASP;
	
	@Column(name="TENSP")
	private String TENSP;
	
	@Column (name="HINHANH")
	private String HINHANH;
	
	@ManyToOne
	@JoinColumn(name="MALOAI")
	private LoaiSP LOAISP; 
	
	public SanPham() {
		
	}

    
    // Constructor với các tham số
    public SanPham(String MASP, String TENSP, String HINHANH, LoaiSP LOAISP) {
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.HINHANH = HINHANH;
        this.LOAISP = LOAISP;
    }

	public String getMASP() {
		return MASP;
	}
	public void setMASP(String MASP) {
		this.MASP=MASP;
	}
	public String getTENSP() {
		return TENSP;
	}
	public void setTENSP(String TENSP) {
		this.TENSP=TENSP;
	}
	public String getHINHANH() {
		return HINHANH;
	}
	public void setHINHANH(String hINHANH) {
		this.HINHANH=hINHANH;
	}
	public LoaiSP getLOAISP() {
		return LOAISP;
	}
	public void setLOAISP(LoaiSP lOAISP) {
		this.LOAISP=lOAISP;
	}
//	public Collection <ChiTietSanPham> getCacChiTietSanPham(){
//		return cacChiTietSanPham;
//	}
//	public void setCacChiTietSanPham(Collection<ChiTietSanPham> cacChiTietSanPham) {
//		this.cacChiTietSanPham=cacChiTietSanPham;
//	}
}
