package nill.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TRESOURCE", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tresource implements Serializable {

	private String pid;// 虚拟属性，用于获得当前资源的父资源ID

	private String id;
	private Date createdatetime;
	private Date updatedatetime;
	private String name;
	private String url;
	private String description;
	private String iconCls;
	private Integer seq;
	private String target;
	private Tresourcetype tresourcetype;
	private Tresource tresource;
	private Set<Trole> troles = new HashSet<Trole>(0);
	private Set<Torganization> torganizations = new HashSet<Torganization>(0);
	private Set<Tresource> tresources = new HashSet<Tresource>(0);
	
	@Transient
	public String getPid() {
		if (tresource != null && !StringUtils.isBlank(tresource.getId())) {
			return tresource.getId();
		}
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 36)
	public String getId() {
		if (!StringUtils.isBlank(this.id)) {
			return this.id;
		}
		return UUID.randomUUID().toString();
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 7)
	public Date getCreatedatetime() {
		if (this.createdatetime != null)
			return this.createdatetime;
		return new Date();
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATETIME", length = 7)
	public Date getUpdatedatetime() {
		if (this.updatedatetime != null)
			return this.updatedatetime;
		return new Date();
	}
	public void setUpdatedatetime(Date updatedatetime) {
		this.updatedatetime = updatedatetime;
	}
	
	
	@Column(name = "NAME", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Column(name = "URL", length = 200)
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "ICONCLS", length = 100)
	public String getIconCls() {
		return this.iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	
	@Column(name = "SEQ", precision = 8, scale = 0)
	public Integer getSeq() {
		return this.seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	
	@Column(name = "TARGET", length = 100)
	public String getTarget() {
		return this.target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRESOURCETYPE_ID")
	public Tresourcetype getTresourcetype() {
		return tresourcetype;
	}
	public void setTresourcetype(Tresourcetype tresourcetype) {
		this.tresourcetype = tresourcetype;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TRESOURCE_ID")
	public Tresource getTresource() {
		return tresource;
	}
	public void setTresource(Tresource tresource) {
		this.tresource = tresource;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TROLE_TRESOURCE", schema = "", joinColumns = { @JoinColumn(name = "TRESOURCE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "TROLE_ID", nullable = false, updatable = false) })
	public Set<Trole> getTroles() {
		return troles;
	}
	public void setTroles(Set<Trole> troles) {
		this.troles = troles;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TORGANIZATION_TRESOURCE", schema = "", joinColumns = { @JoinColumn(name = "TRESOURCE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "TORGANIZATION_ID", nullable = false, updatable = false) })
	public Set<Torganization> getTorganizations() {
		return torganizations;
	}
	public void setTorganizations(Set<Torganization> torganizations) {
		this.torganizations = torganizations;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tresource", cascade = CascadeType.ALL)
	public Set<Tresource> getTresources() {
		return tresources;
	}
	public void setTresources(Set<Tresource> tresources) {
		this.tresources = tresources;
	}

}

