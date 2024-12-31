package ptithcm.entity;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KHUYENMAI")
public class KhuyenMai {
	@Id
	@Column(name = "MAKM")
	private String MAKM;
	
	@Column(name="TGBD")
	private Timestamp TGBD;
	
	@Column(name="TGKT")
	private Timestamp TGKT;
	public KhuyenMai() {
		
	}
	// Constructor với tham số
    public KhuyenMai(String MAKM, Date TGBD, Date TGKT) {
        this.MAKM = MAKM;
        this.TGBD = Timestamp.valueOf(LocalDateTime.of(TGBD.toLocalDate(), LocalTime.of(0, 0, 0)));
        this.TGKT = Timestamp.valueOf(LocalDateTime.of(TGKT.toLocalDate(), LocalTime.of(0, 0, 0)));
    }

	public String getMAKM() {
		return MAKM;
	}

	public void setMAKM(String mAKM) {
		MAKM = mAKM;
	}

	public Timestamp getTGBD() {
		return TGBD;
	}

	public void setTGBD(Date tGBD) {
		TGBD = Timestamp.valueOf(LocalDateTime.of(tGBD.toLocalDate(), LocalTime.of(0, 0, 0)));
	}

	public Timestamp getTGKT() {
		return TGKT;
	}


	public void setTGKT(Date tGKT) {
		TGKT = Timestamp.valueOf(LocalDateTime.of(tGKT.toLocalDate(), LocalTime.of(0, 0, 0)));
	}
}
