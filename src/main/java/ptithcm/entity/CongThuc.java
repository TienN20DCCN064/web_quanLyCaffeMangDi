package ptithcm.entity;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="CONGTHUC")
public class CongThuc {
	@Id
	@Column(name="MACT")
	private String MACT;
	@Column(name="NGAYLAP")
	@CreationTimestamp//Tự động insert ngày hiện tại
	private Date NGAYLAP;
	@Column(name="CONGTHUC")
	private String CONGTHUC;
	public CongThuc() {
		setNGAYLAP(Date.valueOf(LocalDate.now()));
	}
	public String getMACT() {
		return MACT;
	}
	public void setMACT(String mACT) {
		this.MACT=mACT;
	}
	public Date getNGAYLAP() {
		return NGAYLAP;
	}
	public void setNGAYLAP(Date nGAYLAP) {
		this.NGAYLAP=nGAYLAP;
	}
	public String getCONGTHUC() {
		return CONGTHUC;
	}
	public void setCONGTHUC(String cONGTHUC) {
		this.CONGTHUC=cONGTHUC;
	}
}
