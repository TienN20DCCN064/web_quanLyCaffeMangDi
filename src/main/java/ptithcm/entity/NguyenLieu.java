package ptithcm.entity;

import java.math.BigDecimal;

import javax.persistence.*;


@Entity
@Table(name = "NGUYENLIEU")
public class NguyenLieu {
	@Id
	@Column(name = "MANL")
	private String MANL;
	
	@Column(name = "TENNL")
	private String TENNL;
	
//	@Column(name = "HINHANH")
//	private String HINHANH;
//	
	@Column(name = "DONVI")
	private String DONVI;
	
	@Column(name = "SLTON")
	private BigDecimal SLTON;
	
	public NguyenLieu() {
	}

	public NguyenLieu(String mANL, String tEN, String dONVI, BigDecimal sLTON) {
		this.MANL = mANL;
		this.TENNL = tEN;
		this.DONVI = dONVI;
		this.SLTON = sLTON;
	}

	public String getMANL() {
		return MANL;
	}

	public void setMANL(String mANL) {
		this.MANL = mANL;
	}

	public String getTENNL() {
		return TENNL;
	}

	public void setTENNL(String tEN) {
		this.TENNL = tEN;
	}

	public String getDONVI() {
		return DONVI;
	}

	public void setDONVI(String dONVI) {
		this.DONVI = dONVI;
	}

	public BigDecimal getSLTON() {
		return SLTON;
	}

	public void setSLTON(Number sLTON) {
		this.SLTON = new BigDecimal(sLTON.toString());
	}

	public void setSLTON(BigDecimal sLTON) {
		SLTON = sLTON;
	}

}
