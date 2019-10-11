package cn.com.sparknet.common.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 用户信息
 * @author chenxy
 *
 */
@Entity
@Table(name = "TUSER")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USER_ID")
	private String USER_ID;
	
	@JsonIgnore
	@OneToMany(targetEntity=UserBehavior.class,fetch=FetchType.LAZY)
    @JoinColumn(name="USER_ID" ,updatable=false)
    private Set<UserBehavior> userBehaviors = new HashSet<UserBehavior>();
	
	
	public String getUSER_ID() {
		return USER_ID;
	}


	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}


	public String getDEPARTMENT_ID() {
		return DEPARTMENT_ID;
	}


	public void setDEPARTMENT_ID(String dEPARTMENT_ID) {
		DEPARTMENT_ID = dEPARTMENT_ID;
	}


	public String getMECHANISM_ID() {
		return MECHANISM_ID;
	}


	public void setMECHANISM_ID(String mECHANISM_ID) {
		MECHANISM_ID = mECHANISM_ID;
	}


	public String getUSER_NAME() {
		return USER_NAME;
	}


	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}


	public String getUSER_PWD() {
		return USER_PWD;
	}


	public void setUSER_PWD(String uSER_PWD) {
		USER_PWD = uSER_PWD;
	}


	public String getUSER_START() {
		return USER_START;
	}


	public void setUSER_START(String uSER_START) {
		USER_START = uSER_START;
	}


	public String getUSER_PHONE() {
		return USER_PHONE;
	}


	public void setUSER_PHONE(String uSER_PHONE) {
		USER_PHONE = uSER_PHONE;
	}


	public String getUSER_GENDER() {
		return USER_GENDER;
	}


	public void setUSER_GENDER(String uSER_GENDER) {
		USER_GENDER = uSER_GENDER;
	}


	public Date getUSER_CREATETIME() {
		return USER_CREATETIME;
	}


	public void setUSER_CREATETIME(Date uSER_CREATETIME) {
		USER_CREATETIME = uSER_CREATETIME;
	}


	public String getUSER_PARID() {
		return USER_PARID;
	}


	public void setUSER_PARID(String uSER_PARID) {
		USER_PARID = uSER_PARID;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	private String DEPARTMENT_ID;
	private String MECHANISM_ID;
	private String USER_NAME;
	private String USER_PWD;
	private String USER_START;
	private String USER_PHONE;
	private String USER_GENDER;
	private Date USER_CREATETIME;
	private String USER_PARID;
	private byte[] USER_PORTRAIT;

	public byte[] getUSER_PORTRAIT() {
		return USER_PORTRAIT;
	}


	public void setUSER_PORTRAIT(byte[] uSER_PORTRAIT) {
		USER_PORTRAIT = uSER_PORTRAIT;
	}


	public User() {
        super();
    }

    public User(String USER_ID,String USER_NAME, String USER_PWD,String USER_START) {
        super();
        this.USER_ID = USER_ID;
        this.USER_NAME = USER_NAME;
        this.USER_PWD = USER_PWD;
        this.USER_START = USER_START;
        
    }
    public User(String USER_ID, String DEPARTMENT_ID, String MECHANISM_ID,String USER_NAME,String USER_PWD,String USER_START,String USER_PHONE,String USER_GENDER,Date USER_CREATETIME,String USER_PARID) {
        super();
        this.USER_ID = USER_ID;
        this.DEPARTMENT_ID = DEPARTMENT_ID;
        this.MECHANISM_ID = MECHANISM_ID;
        this.USER_NAME = USER_NAME;
        this.USER_PWD = USER_PWD;
        this.USER_START = USER_START;
        this.USER_PHONE = USER_PHONE;
        this.USER_GENDER = USER_GENDER;
        this.USER_CREATETIME = USER_CREATETIME;
        this.USER_PARID = USER_PARID;
    }
    

   
    @Override
    public String toString() {
        return "[id=" + USER_ID + ", username=" + USER_NAME + ", password=" + USER_PWD + "]";
    }
    
    public Set<UserBehavior> getUserBehaviors() {
		return userBehaviors;
	}

	public void setUserBehaviors(Set<UserBehavior> userBehaviors) {
		this.userBehaviors = userBehaviors;
	}
	
}
