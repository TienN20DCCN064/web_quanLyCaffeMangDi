package ptithcm.entity;
// có 3 size L,S,M
import javax.persistence.*;
@Entity
@Table(name="SIZE")
public class Size {
	@Id
	@Column(name="MASIZE")
	private String MASIZE;
	@Column(name="TENSIZE")
	private String TENSIZE;
	
	public Size() {
		
	}

    // Constructor có tham số để khởi tạo đối tượng với các giá trị cụ thể
    public Size(String MASIZE, String TENSIZE) {
        this.MASIZE = MASIZE;
        this.TENSIZE = TENSIZE;
    }

	public String getMASIZE() {
		return MASIZE;
	}
	public void setMASIZE(String mASIZE) {
		this.MASIZE=mASIZE;
	}
	public String getTENSIZE() {
		return TENSIZE;
	}
	public void setTENSIZE(String tENSIZE) {
		this.TENSIZE=tENSIZE;
	}
	
	 // Phương thức layTenSize
    public String layTenSize(String maSize) {
        if (this.MASIZE.equalsIgnoreCase(maSize)) {
            return this.TENSIZE;
        }
        return "Mã size không hợp lệ";
    }
}
