package [(${referencePackage})];

/**
 * @author zed
 * @description generator by thymeleaf
 */
public class [(${core})]DataSourceConfig {
    public static final String PREFIX ="db";

    public static final String DB_[(${upperCore})] ="[(${midLineName})]";

    public static final String DB_[(${upperCore})]_PREFIX =PREFIX+"."+ DB_ACCOUNT;

    public static final String DB_[(${upperCore})]_SQL_SESSION_FACTORY ="[(${humpCore})]SqlSessionFactory";

    public static final String TX_[(${upperCore})] ="tx[(${core})]";

    public static final String [(${upperCore})]_DAO_PACKAGE ="[(${referencePackage})].dao";
}
