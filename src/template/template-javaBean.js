/*
 * FileName:    SysDataChange.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) ${curYear}
 * History:     ${curDate} (Shicx) 1.0 Create
 */

package ${packageName};

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.chaochuang.common.data.domain.LongIdEntity;

/**
 * @author Shicx
 *
 */
@SuppressWarnings("serial")
@Entity
@AttributeOverrides({ @AttributeOverride(name = "ID", column = @Column(name = "${javaBeanData.primaryColName}")) })
public class ${javaBeanData.tableNameFmt} extends LongIdEntity { 

<%
	/*循环字段*/
	for(columnDetail in javaBeanData.columnDetailList){
%>
	/***/
	<% 
		if(columnDetail.columnType=="Date"){
	%>
	@Temporal(TemporalType.TIMESTAMP)
	<%
		}
	%>
	private ${columnDetail.columnType} ${columnDetail.colNameFmt};
	
<%
	}
%>

<%
	/*生成 get set 方法*/
	for(columnDetail in javaBeanData.columnDetailList){
%>
	/**
	 * @param ${columnDetail.colNameFmt}
	 *            the ${columnDetail.colNameFmt} to set
	 */
	public void set${columnDetail.upColNameFmt}(${columnDetail.columnType} ${columnDetail.colNameFmt}){
		this.${columnDetail.colNameFmt}=${columnDetail.colNameFmt};
	}
	
	/**
     * @return the ${columnDetail.colNameFmt}
     */
	public ${columnDetail.columnType} get${columnDetail.upColNameFmt}(){
		return ${columnDetail.colNameFmt};
	}
	
<%
	}
%>

}

