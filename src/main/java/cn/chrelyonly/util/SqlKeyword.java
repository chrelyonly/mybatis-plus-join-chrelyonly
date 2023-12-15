package cn.chrelyonly.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;

import java.util.Map;

/**
 * @author chrelyonly
 */
public class SqlKeyword {

        private static final String SQL_REGEX = "'|%|--|insert|delete|select|sleep|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql";
        private static final String EQUAL = "_equal";
        private static final String NOT_EQUAL = "_notequal";
        private static final String LIKE = "_like";
        private static final String LIKE_LEFT = "_likeleft";
        private static final String LIKE_RIGHT = "_likeright";
        private static final String NOT_LIKE = "_notlike";
        private static final String GE = "_ge";
        private static final String LE = "_le";
        private static final String GT = "_gt";
        private static final String LT = "_lt";
        private static final String DATE_GE = "_datege";
        private static final String DATE_GT = "_dategt";
        private static final String DATE_EQUAL = "_dateequal";
        private static final String DATE_LT = "_datelt";
        private static final String DATE_LE = "_datele";
        private static final String IS_NULL = "_null";
        private static final String NOT_NULL = "_notnull";
        private static final String IGNORE = "_ignore";

    /**
     * 判断是否存在空对象
     * @param os 对象数组
     * @return 是否存在空对象
     */
        public static boolean hasEmpty(Object... os) {
            for (Object o : os) {
                if (ObjectUtil.isEmpty(o)) {
                    return true;
                }
            }
            return false;
        }

    /**
     * 构造查询条件
     * @param query 查询条件
     * @param qw 查询包装类
     */
        public static void buildCondition(Map<String, Object> query, MPJLambdaWrapper<?> qw) {
            if (!ObjectUtil.isEmpty(query)) {
                query.forEach((k, v) -> {
                    if (!hasEmpty(k, v) && !k.endsWith("_ignore")) {
                        k = filter(k);
                        k = "t." + k;
                        if (k.endsWith("_equal")) {
                            qw.eq(getColumn(k, "_equal"), v);
                        } else if (k.endsWith("_notequal")) {
                            qw.ne(getColumn(k, "_notequal"), v);
                        } else if (k.endsWith("_likeleft")) {
                            qw.likeLeft(getColumn(k, "_likeleft"), v);
                        } else if (k.endsWith("_likeright")) {
                            qw.likeRight(getColumn(k, "_likeright"), v);
                        } else if (k.endsWith("_notlike")) {
                            qw.notLike(getColumn(k, "_notlike"), v);
                        } else if (k.endsWith("_ge")) {
                            qw.ge(getColumn(k, "_ge"), v);
                        } else if (k.endsWith("_le")) {
                            qw.le(getColumn(k, "_le"), v);
                        } else if (k.endsWith("_gt")) {
                            qw.gt(getColumn(k, "_gt"), v);
                        } else if (k.endsWith("_lt")) {
                            qw.lt(getColumn(k, "_lt"), v);
                        } else if (k.endsWith("_datege")) {
                            qw.ge(getColumn(k, "_datege"), DateUtil.parse(String.valueOf(v), "yyyy-MM-dd HH:mm:ss"));
                        } else if (k.endsWith("_dategt")) {
                            qw.gt(getColumn(k, "_dategt"), DateUtil.parse(String.valueOf(v), "yyyy-MM-dd HH:mm:ss"));
                        } else if (k.endsWith("_dateequal")) {
                            qw.eq(getColumn(k, "_dateequal"), DateUtil.parse(String.valueOf(v), "yyyy-MM-dd HH:mm:ss"));
                        } else if (k.endsWith("_datele")) {
                            qw.le(getColumn(k, "_datele"), DateUtil.parse(String.valueOf(v), "yyyy-MM-dd HH:mm:ss"));
                        } else if (k.endsWith("_datelt")) {
                            qw.lt(getColumn(k, "_datelt"), DateUtil.parse(String.valueOf(v), "yyyy-MM-dd HH:mm:ss"));
                        } else if (k.endsWith("_null")) {
                            qw.isNull(getColumn(k, "_null"));
                        } else if (k.endsWith("_notnull")) {
                            qw.isNotNull(getColumn(k, "_notnull"));
                        } else {
                            qw.like(getColumn(k, "_like"), v);
                        }

                    }
                });
            }
        }

        private static String getColumn(String column, String keyword) {
            return humpToUnderline(StrUtil.removeSuffix(column, keyword));
        }

    /**
     * 驼峰转下划线
     * @param para 驼峰字符串
     * @return 下划线字符串
     */
    public static String humpToUnderline(String para) {
        if (StrUtil.isBlank(para)) {
            return "";
        } else {
            para = firstCharToLower(para);
            StringBuilder sb = new StringBuilder(para);
            int temp = 0;

            for (int i = 0; i < para.length(); ++i) {
                if (Character.isUpperCase(para.charAt(i))) {
                    sb.insert(i + temp, "_");
                    ++temp;
                }
            }

            return sb.toString().toLowerCase();
        }
    }

    /**
     * 首字母转小写
     * @param str 字符串
     * @return 首字母小写的字符串
     */
    public static String firstCharToLower(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] + 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    /**
     * 过滤sql
     * @param param sql参数
     * @return 过滤后的sql参数
     */
        public static String filter(String param) {
            return param == null ? null : param.replaceAll("(?i)'|%|--|insert|delete|select|sleep|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql", "");
        }
}
