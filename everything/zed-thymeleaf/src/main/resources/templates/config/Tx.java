package [(${referencePackage})];

import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zed
 * @description generator by thymeleaf
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional([(${core})]DataSourceConfig.TX_[(${upperCore})])
public @interface Tx[(${core})] {
}
