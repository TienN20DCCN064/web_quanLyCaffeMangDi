package ptithcm.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import java.text.SimpleDateFormat;


@Entity
@Table(name="NHANVIEN")
public class NhanVien {
	@Id
	@Column(name = "MANV", columnDefinition = "nvarchar(10)")
	private String MANV;
	
	@OneToOne(mappedBy = "nhanvien", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private TaiKhoan taikhoan;

//	@OneToMany(mappedBy="nhanvien", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//	Collection<PhieuNhapHang> phieunhaphang;
	@Column(name = "HOTENNV", columnDefinition = "nvarchar(100)")
	private String HOTENNV;
//	private String HONV;
//	private String TENNV;

//	@Column(name = "HO", columnDefinition = "nvarchar(50)")
//	private String HO;
//	
//	@Column(name = "TEN", columnDefinition = "nvarchar(30)")
//	private String TEN;
	
	@Column(name = "GIOITINH", columnDefinition = "nvarchar(4)")
	private String GT;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "NGAYSINH")
	private Date NGAYSINH;
	
	@Column(name = "SDT", columnDefinition = "nchar(10)")
	private String SDT;
	
//	@Column(name = "SDT2", columnDefinition = "nchar(10)")
//	private String SDT2;
//	
	@Column(name = "DIACHI", columnDefinition = "nvarchar(400)")
	private String DIACHI;

	@Column(name = "EMAIL", columnDefinition = "nvarchar(100)")
	private String EMAIL;

//	@Column(name = "EMAIL2", columnDefinition = "nvarchar(40)")
//	private String EMAIL2;



	public String getMANV() {
		return MANV;
	}

	public void setMANV(String mANV) {
		MANV = mANV;
	}

	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}

	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}
	public String getHOTENNV() {
	return HOTENNV;
}

public void setHOTENNV(String tENNV) {
	HOTENNV = tENNV;
}
//public String getHONV() {
//	
//	return HONV;
//}
//
//public void setHONV(String hoNV) {
//	HONV = hoNV;
//}
//public String getTENNV() {
//	return TENNV;
//}
//
//public void setTENNV(String tenNV) {
//	TENNV = tenNV;
//}
	
	
//	public String getHO() {
//		return HO;
//	}
//
//	public void setHO(String hO) {
//		HO = hO;
//	}
//
//	public String getTEN() {
//		return TEN;
//	}
//
//	public void setTEN(String tEN) {
//		TEN = tEN;
//	}

	public String getGT() {
		return GT;
	}

	public void setGT(String gT) {
		GT = gT;
	}

	public Date getNGAYSINH() {
		return NGAYSINH;
	}

	public void setNGAYSINH(Date nGAYSINH) {
		NGAYSINH = nGAYSINH;
	}

	public String getDIACHI() {
		return DIACHI;
	}

	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

//	public String getEMAIL2() {
//		return EMAIL2;
//	}
//
//	public void setEMAIL2(String eMAIL2) {
//		EMAIL2 = eMAIL2;
//	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}
	
	@Override
	public String toString() {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 String ngaySinhStr = NGAYSINH != null ? dateFormat.format(NGAYSINH) : "null";
		 String gender = (GT != null) ? GT.toUpperCase() : "Chưa xác định";
	    return "NhanVien{" +
	            "MANV='" + MANV.toUpperCase() + '\'' +	
	            ", HOTENNV='" + HOTENNV.toUpperCase() + '\'' +
	            ", GT='" + gender + '\'' +
	            ", SDT='" + SDT + '\'' +
	            ", DIACHI='" + DIACHI.toUpperCase() + '\'' +
	            ", EMAIL='" + EMAIL + '\'' +
	               ", NgaySinh' " + ngaySinhStr +'\'' + 
	            '}';
	}

//	public String getSDT2() {
//		return SDT2;
//	}
//
//	public void setSDT2(String sDT2) {
//		SDT2 = sDT2;
//	}

}
