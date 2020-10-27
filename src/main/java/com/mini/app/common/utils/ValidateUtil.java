package com.mini.app.common.utils;


import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ValidateUtil
 * @Description: 数据字段校验类，此类作用： 1、为了防止垃圾数据 2、保证数据正确性 3、主要用于过滤并校验数据
 */

public class ValidateUtil {

    private final static String ParaIsNotExist = "ParaNotExist"; // 某个参数不存在
    private final static String ParaValueIsNull = "ParaValueIsNull"; // 某个参数为空
    private final static String ParaMaxLength = "ParaOverLength"; // 某个参数长度超过了
    private final static String ParaMismatching = "ParaMismatching"; // 字段类型的值不匹配

    @SuppressWarnings("unused")
    private final static String emptyStr = "";
    private final static String CheckType_Number = "number"; // 是否为纯数据

    private final static String Term_Y = "y"; // 是否必填 是
    @SuppressWarnings("unused")
    private final static String Term_N = "n"; // 是否必填 否 默认为不必填

    /**
     * 同时验证非空和长度限制和数据类型
     *
     * @param map 传入参数的MAP
     * @param p   参数名,是否必填,长度限制 可不填时为不限制,number为数字类型代表当前字段只允许出现数字
     *            String[][]{{"username" ,"y","5","number"} , {"phone" ,"y","11"},
     *            {"sex" ,"y","1"}}
     * @throws Exception
     */
    public static Map<String, String> validate(Map<String, String> map, String[][] p) {
        if (map == null) {
            return null;
        }
        if (p == null) {
            return null;
        }

        Map<String, String> valMap = new HashMap<>();
        try {
            String values;
            for (int i = 0; i < p.length; i++) {// 遍历待验证的属性
                String param = p[i][0];// 待验证的参数名称
                String nullRule = p[i][1];// 非空的规则
                String lengthRule = "";
                if (p[i].length > 2) {
                    lengthRule = p[i][2];// 长度的规则
                }
                String valuecheck = "";
                if (p[i].length > 3) {
                    valuecheck = p[i][3];// 验证字段的类型
                }

                // 判断是否存在这个参数
                if (!map.containsKey(param)) {
                    if (valMap.containsKey(ParaIsNotExist)) {
                        valMap.put(ParaIsNotExist, valMap.get(ParaIsNotExist) + "&" + param);
                    } else {
                        valMap.put(ParaIsNotExist, "字段不存在:" + param);
                    }
                    continue;
                }

                // 判断值是否必填
                values = ObjectUtils.toString(map.get(param));

                if (StringUtils.isBlank(values)) {
                    // 如果改值是必填的,则值不符合要求
                    if (nullRule.equalsIgnoreCase(Term_Y)) {
                        if (valMap.containsKey(ParaValueIsNull)) {
                            valMap.put(ParaValueIsNull, valMap.get(ParaValueIsNull) + "&" + param);
                        } else {
                            valMap.put(ParaValueIsNull, "字段值为空:" + param);
                        }
                    }
                } else {
                    //检查类型和长度要建立在长度不为空的基础上

                    //检查类型
                    if (StringUtils.isNotBlank(valuecheck)) {
                        boolean isCheckPass;
                        try {
                            Long.parseLong(values);
                            isCheckPass = true;
                        } catch (Exception e) {
                            isCheckPass = false;
                        }

                        // 如果没有检查通过，则提示字段类型不匹配
                        if (!isCheckPass) {
                            if (valMap.containsKey(ParaMismatching)) {
                                valMap.put(ParaMismatching, valMap.get(ParaMismatching) + "&" + param
                                        + "期望类型为：" + CheckType_Number);
                            } else {
                                valMap.put(ParaMismatching, "字段值类型不匹配:" + param + "期望类型为：" + CheckType_Number);
                            }

                        }
                    }


                    // 检查长度，如果大于长度则需要提示
                    int ilengthRule = 0;
                    int ivalueLength = values.length();
                    try {
                        ilengthRule = Integer.valueOf(lengthRule);
                    } catch (NumberFormatException e) {
                    }
                    if (ivalueLength > ilengthRule && ilengthRule > 0) {
                        if (valMap.containsKey(ParaMaxLength)) {
                            valMap.put(ParaMaxLength, valMap.get(ParaMaxLength) + "&" + param + " 限制长度为" + ilengthRule + " ，实际长度为" + ivalueLength);
                        } else {
                            valMap.put(ParaMaxLength, "不符合字段限制长度:" + param + " 限制长度为" + ilengthRule + " ，实际长度为" + ivalueLength);
                        }
                    }

                }


            }

            if (valMap.size() > 0) {
                return valMap;
            }

            return new HashMap<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static void main(String[] args) {
        System.out.println(Long.parseLong("1111111111111111111"));
    }

}
