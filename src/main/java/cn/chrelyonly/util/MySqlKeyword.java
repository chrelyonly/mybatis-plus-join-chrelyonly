package cn.chrelyonly.util;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;

import java.util.Map;

/**
 * warpper处理转换sql
 */
public class MySqlKeyword {
    private static final String SQL_REGEX = "'|%|--|insert|delete|select|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql";
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
     * 获取字段名并且动态替换
     * 根据字段名判断拼接什么类型
     * 默认返回模糊查询字段
     */
    public static void buildCondition(Map<String, Object> query, MPJLambdaWrapper<?> qw) {
        if (!Func.isEmpty(query)) {
            query.forEach((k, v) -> {
                if (!Func.hasEmpty(k, v) && !k.endsWith("_ignore")) {
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

    /**
     * 获取字段
     */
    private static String getColumn(String column, String keyword) {
        return StringUtil.humpToUnderline(StringUtil.removeSuffix(column, keyword));
    }

    /**
     * 过滤sql关键字
     * @param param 参数
     */
    public static String filter(String param) {
        return param == null ? null : param.replaceAll("(?i)'|%|--|insert|delete|select|count|group|union|drop|truncate|alter|grant|execute|exec|xp_cmdshell|call|declare|sql", "");
    }
}
