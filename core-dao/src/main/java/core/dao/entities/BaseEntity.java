/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import core.dao.dto.BaseDto;

/**
 * Base entity
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable, IEntity {
	public static final String ID = "id";
	
	
	private static final long serialVersionUID = 1L;
	public static final String SHORT_1 = "nvarchar(10)";
	public static final String SHORT_2 = "nvarchar(20)";
	public static final String SHORT_3 = "nvarchar(30)";
	public static final String SHORT_4 = "nvarchar(40)";
	public static final String SHORT_5 = "nvarchar(50)";

	public static final String MEDIUM_1 = "nvarchar(100)";
	public static final String MEDIUM_2 = "nvarchar(200)";
	public static final String MEDIUM_3 = "nvarchar(300)";
	public static final String MEDIUM_4 = "nvarchar(400)";
	public static final String MEDIUM_5 = "nvarchar(500)";

	public static final String LONG_1 = "nvarchar(1000)";
	public static final String LONG_2 = "nvarchar(2000)";
	public static final String LONG_3 = "nvarchar(3000)";
	public static final String LONG_4 = "nvarchar(4000)";
	public static final String LONG_5 = "nvarchar(5000)";

	public static final String BLOB = "BLOB";
//	public static final String BINARY = "binary";
	public static final String INT = "INT";
	public static final String LONG = "INT";
//	public static final String DECIMAL = "DECIMAL(10,2)";
	public static final String CURRENCY = "DECIMAL(13,2)";
//	public static final String BIG_CURRENCY = "DECIMAL(20,2)";
	public static final String DATE = "DATE";
	public static final String TIMESTAMP = "TIMESTAMP";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = LONG)
	protected Long id = 0L;

	@Version
	@Column(name = "version")
	private int version;
	
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void bind(BaseDto baseDto) {
		this.id = (Long) baseDto.getId();
	}

	@Override
	public void unBind(BaseDto baseDto) {
		baseDto.setId(id);
	}
	
	@Override
	public Object getEntityId() {
		return getId();
	}
	
	@Override
	public void setEntityId(Object id) {
		this.id = (Long) id;
	}
}