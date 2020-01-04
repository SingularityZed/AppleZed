package ${package}.domain;

import lombok.Data;
import javax.persistence.*;
<#if hasTimestamp>
import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;

/**
* ${className}
* @author ${author}
* @date ${date}
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "${tableName}")
public class ${className}  extends BaseEntity<${className}>  {
<#if columns??>
    <#list columns as column>

    <#if column.columnComment != ''>
    /**
     *   ${column.columnComment}
     */
    </#if>
    @Column(name = "${column.columnName}"<#if column.columnKey = 'UNI'>,unique = true</#if><#if column.isNullable = 'NO' && column.columnKey != 'PRI'>,nullable = false</#if>)
    private ${column.columnType} ${column.changeColumnName};
    </#list>
</#if>


}