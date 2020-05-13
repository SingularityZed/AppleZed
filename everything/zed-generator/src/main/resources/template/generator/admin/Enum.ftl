package ${package}.service.dto;

import lombok.Data;
<#if hasTimestamp>
    import java.sql.Timestamp;
</#if>
<#if hasBigDecimal>
    import java.math.BigDecimal;
</#if>
import java.io.Serializable;
<#if !auto && pkColumnType = 'Long'>
    import com.fasterxml.jackson.databind.annotation.JsonSerialize;
    import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
</#if>


/**
* 枚举类
* @author ${author}
* @date ${date}
*/
public enum ${className}Enum implements BaseEnum {
/**
* xxx
*/
XXXX(20001, "KKKK"),

/**
* xxx
*/
YYYY(20002, "CCCC");


private final Integer code;

private final String message;

${className}Enum(Integer code, String message) {
this.code = code;
this.message = message;
}

@Override
public Integer getValue() {
return this.code;
}

@Override
public String getMessage() {
return this.message;
}

}