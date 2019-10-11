package cn.com.sparknet.common.bean;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 用户行为监控
 * @author chenxy
 */
@Entity
@Table(name = "T_PT_USER_BEHAVIOR")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserBehavior implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="USER_BEHAVIOR_ID")
	private String userBehaviorId;
	
	@ManyToOne(targetEntity=User.class,fetch=FetchType.EAGER)
    @JoinColumn(name="USER_ID" )
    private User user;
	
	@Column(name="MODULE_NAME")
	private String moduleName;
	
	@Column(name="OPERATE_IP")
	private String operateIp;
	
	@Column(name="OPERATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
	private Date operateDate;
	
	@Column(name="OPERATE_DESCRIBE")
	private String operateDescribe;
	
	@Column(name="OPERATE_BROWSER_TYPE")
	private String operateBrowserType;
	
	@Column(name="OPERATE_BROWSER_VERSION")
	private String operateBrowserVersion;
	
	@Column(name="TOTAL_TIME_SECONDS")
	private Double totalTimeSeconds;
	
	@Column(name="CLASS_NAME")
	private String className;
	
	@Column(name="METHOD_NAME")
	private String methodName;
	
	/***********************GET&SET方法**************************/

	public String getUserBehaviorId() {
		return userBehaviorId;
	}

	public void setUserBehaviorId(String userBehaviorId) {
		this.userBehaviorId = userBehaviorId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOperateIp() {
		return operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperateDescribe() {
		return operateDescribe;
	}

	public void setOperateDescribe(String operateDescribe) {
		this.operateDescribe = operateDescribe;
	}

	public String getOperateBrowserType() {
		return operateBrowserType;
	}

	public void setOperateBrowserType(String operateBrowserType) {
		this.operateBrowserType = operateBrowserType;
	}

	public String getOperateBrowserVersion() {
		return operateBrowserVersion;
	}

	public void setOperateBrowserVersion(String operateBrowserVersion) {
		this.operateBrowserVersion = operateBrowserVersion;
	}

	public Double getTotalTimeSeconds() {
		return totalTimeSeconds;
	}

	public void setTotalTimeSeconds(Double totalTimeSeconds) {
		this.totalTimeSeconds = totalTimeSeconds;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
