package ptithcm.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
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
import java.util.List;

@Entity
@Table(name="LOAISP")
public class LoaiSP {
	@Id
	@Column(name = "MALOAI")
	private String MALOAI;
	@Column(name = "TENLOAI")
	private String TENLOAI;
	
    // Mối quan hệ OneToMany với SanPham
    @OneToMany(mappedBy = "LOAISP") // mappedBy trỏ đến tên trường trong lớp SanPham
    private List<SanPham> sanPhams;
	
	public LoaiSP() {
		
	}
	public String getMALOAI() {
		return MALOAI;
	}

	public void setMALOAI(String mALOAI) {
		MALOAI = mALOAI;
	}
	public String getTENLOAI() {
		return TENLOAI;
	}

	public void setTENLOAI(String tENLOAI) {
		TENLOAI = tENLOAI;
	}
//	public Collection<SanPham> getCacSanPham(){
//		return cacSanPham;
//	}
//	public void setCacSanPham(Collection<SanPham>cacSanPham) {
//		this.cacSanPham=cacSanPham;
//	}
}