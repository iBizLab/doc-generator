package cn.ibizlab.codegen.groovy.util;

public class JavaTypeUtil {

    public static String getJavaType(int nDataType) {
        return getJavaType(nDataType, false);
    }

    public static String getJavaType(int nDataType, boolean bFull) {


        /**
         * 判断是否为字符串类型
         *
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isStringType(nDataType))
            return "String";

        /**
         * 是否为长字符串类型
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isLongStringType(nDataType))
            return "String";


        /**
         * 是否为长字符串类型
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isDateTimeType(nDataType)) {
            if (bFull) {
                return "java.sql.Timestamp";
            }
            return "Timestamp";

        }


        /**
         * 是否为大整数类型
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isBigIntType(nDataType)) {
            if (bFull) {
                return "java.math.BigInteger";
            }
            return "BigInteger";
        }


        /**
         * 是否为整数类型
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isIntType(nDataType))
            return "Integer";


        /**
         * 是否为大数字类型
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isBigDecimalType(nDataType)) {
            if (bFull) {
                return "java.math.BigDecimal";
            }
            return "BigDecimal";
        }


        /**
         * 是否为长字符串类型
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isDoubleType(nDataType))
            return "Double";

        /**
         * 是否为二进制
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isBinaryType(nDataType))
            return "byte[]";

        /**
         * 是否为布尔值
         * @param dataType
         * @return
         */
        if (JavaTypeUtil.isBooleanType(nDataType))
            return "Boolean";


        return "Object";

    }


    /**
     * 判断是否为字符串的数据类型
     *
     * @param dataType
     * @return
     */
    public static boolean isStringDataType(int dataType) {
        switch (dataType) {
            case DataTypes.CHAR:
            case DataTypes.NCHAR:
            case DataTypes.NTEXT:
            case DataTypes.NVARCHAR:
            case DataTypes.SYSNAME:
            case DataTypes.TEXT:
            case DataTypes.VARCHAR:

                return true;
            default:
                return false;

        }
    }

    /**
     * 是否为时间相关类型
     *
     * @param dataType
     * @return
     */
    public static boolean isDateTimeDataType(int dataType) {
        switch (dataType) {
            case DataTypes.DATE:
            case DataTypes.DATETIME:
            case DataTypes.SMALLDATETIME:
            case DataTypes.TIME:
            case DataTypes.TIMESTAMP:
                return true;
            default:
                return false;
        }
    }


    /**
     * 判断是否为字符串类型
     *
     * @param dataType
     * @return
     */
    public final static boolean isStringType(int dataType) {
        return (dataType == DataTypes.CHAR || dataType == DataTypes.NCHAR || dataType == DataTypes.NTEXT || dataType == DataTypes.NVARCHAR || dataType == DataTypes.SYSNAME || dataType == DataTypes.TEXT || dataType == DataTypes.VARCHAR);
    }

    /**
     * 判断是否为字符串类型
     *
     * @param strDataType
     * @return
     */
    public final static boolean isStringType(String strDataType) {
        return isStringType(DataTypes.fromString(strDataType));
    }

    /**
     * 是否为长字符串类型
     *
     * @param dataType
     * @return
     */
    public final static boolean isLongStringType(int dataType) {
        //	return (dataType == DataTypes.IMAGE || dataType == DataTypes.NTEXT || dataType == DataTypes.NVARCHAR || dataType == DataTypes.TEXT);
        return (dataType == DataTypes.IMAGE || dataType == DataTypes.NTEXT || dataType == DataTypes.TEXT);
    }

    /**
     * 判断是否为时间日期类型
     *
     * @param dataType
     * @return
     */
    public final static boolean isDateTimeType(int dataType) {
        return (dataType == DataTypes.DATETIME || dataType == DataTypes.SMALLDATETIME || dataType == DataTypes.DATE || dataType == DataTypes.TIME);
    }

    /**
     * 判断是否为时间日期类型
     *
     * @param strDataType
     * @return
     */
    public final static boolean isDateTimeType(String strDataType) {
        return isDateTimeType(DataTypes.fromString(strDataType));
    }

    /**
     * 是否为整形
     *
     * @param dataType
     * @return
     */
    public final static boolean isIntType(int dataType) {
        return (dataType == DataTypes.INT || dataType == DataTypes.SMALLINT || dataType == DataTypes.BIGINT || dataType == DataTypes.TINYINT);
    }

    /**
     * 是否为整数类型
     *
     * @param strDataType
     * @return
     */
    public final static boolean isIntType(String strDataType) {
        return isIntType(DataTypes.fromString(strDataType));
    }

    /**
     * 是否为大整形
     *
     * @param dataType
     * @return
     */
    public final static boolean isBigIntType(int dataType) {
        return (dataType == DataTypes.BIGINT);
    }

    /**
     * 是否为大数值
     *
     * @param dataType
     * @return
     */
    public final static boolean isBigDecimalType(int dataType) {
        return (dataType == DataTypes.BIGDECIMAL || dataType == DataTypes.DECIMAL);
    }

    /**
     * 是否为大整数类型
     *
     * @param strDataType
     * @return
     */
    public final static boolean isBigIntType(String strDataType) {
        return isBigIntType(DataTypes.fromString(strDataType));
    }

    /**
     * 是否为大数值类型
     *
     * @param strDataType
     * @return
     */
    public final static boolean isBigDecimalType(String strDataType) {
        return isBigDecimalType(DataTypes.fromString(strDataType));
    }

    /**
     * 是否为Double
     *
     * @param dataType
     * @return
     */
    public final static boolean isDoubleType(int dataType) {
        return (dataType == DataTypes.FLOAT || dataType == DataTypes.MONEY || dataType == DataTypes.SMALLMONEY || dataType == DataTypes.NUMERIC || dataType == DataTypes.REAL);
    }


    /**
     * 是否为二进制流类型
     *
     * @param dataType
     * @return
     */
    public final static boolean isBinaryType(int dataType) {
        return (dataType == DataTypes.BINARY || dataType == DataTypes.VARBINARY);
    }

    /**
     * 是否为二进制流类型
     *
     * @param strDataType
     * @return
     */
    public final static boolean isBinaryType(String strDataType) {
        return isBinaryType(DataTypes.fromString(strDataType));
    }


    /**
     * 是否为Double
     *
     * @param dataType
     * @return
     */
    public final static boolean isBooleanType(int dataType) {
        return (dataType == DataTypes.BIT);
    }
}
