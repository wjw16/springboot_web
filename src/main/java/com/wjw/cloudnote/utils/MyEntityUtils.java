package com.it.code.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyEntityUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public MyEntityUtils() {
    }

    public static String toStringUtils(Object clazz) {
        if (clazz == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer(64);
            Class c = clazz.getClass();
            Field[] fs = c.getDeclaredFields();
            Field[] var7 = fs;
            int var6 = fs.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                Field f = var7[var5];
                if (!"serialVersionUID".equals(f.getName())) {
                    f.setAccessible(true);
                    sb.append(f.getName() + ":");
                    String mName = "get" + String.valueOf(f.getName().charAt(0)).toUpperCase() + f.getName().substring(1);

                    try {
                        Method m = c.getDeclaredMethod(mName);
                        if (m != null) {
                            sb.append(m.invoke(clazz)).append(",");
                        } else {
                            sb.append(",");
                        }
                    } catch (Exception var10) {
                        ;
                    }
                }
            }

            return sb.toString();
        }
    }
    public static List<Field> getFields(Class clazz) {
        List<Field> fList = new ArrayList();
        if (clazz != null && clazz != Object.class) {
            Field[] fs = clazz.getDeclaredFields();
            Field[] var6 = fs;
            int var5 = fs.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                Field f = var6[var4];
                fList.add(f);
            }

            if (clazz != Object.class) {
                fList.addAll(getFields(clazz.getSuperclass()));
            }

            return fList;
        } else {
            return fList;
        }
    }

    public static Map map2map(Map source, Map target) {
        if (source != null && target != null) {
            Set<String> keys = source.keySet();
            Iterator var4 = keys.iterator();

            while(var4.hasNext()) {
                String _k = (String)var4.next();
                target.put(_k, target.get(_k));
            }

            return target;
        } else {
            return null;
        }
    }

    public static Map map2map2(Map source, Map target) {
        if (source != null && target != null) {
            Set<String> keys = source.keySet();
            Iterator var4 = keys.iterator();

            while(var4.hasNext()) {
                String _k = (String)var4.next();
                if (source.get(_k) == null) {
                    target.put(_k, "");
                } else {
                    target.put(_k, source.get(_k));
                }
            }

            return target;
        } else {
            return null;
        }
    }
    public static <T>List<T>map2Bean(List<Map> mapList, Class<T> obj){
        List<T>objList=new ArrayList<T>();
        for(Map map:mapList){
            try {
                T t=obj.newInstance();
                map2Bean(t,map);
                objList.add(t);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return objList;
    }
    public static Object map2Bean(Object o, Map<String, Object> params) {
        if (o != null && params != null && params.size() != 0) {
            Class c = o.getClass();
            List<Field> fs = getFields(c);
            Set<String> keys = params.keySet();
            Iterator var6 = keys.iterator();

            while(true) {
                while(var6.hasNext()) {
                    String _k = (String)var6.next();
                    Object tvalue = params.get(_k);
                    Iterator var9 = fs.iterator();

                    while(var9.hasNext()) {
                        Field _f = (Field)var9.next();
                        if (!"serialVersionUID".equals(_f.getName()) && _k.equalsIgnoreCase(_f.getName())) {
                            try {
                                _f.setAccessible(true);
                                Class _cf = _f.getType();
                                if (_cf == BigDecimal.class) {
                                    _f.set(o, new BigDecimal(tvalue != null && !"".equals(tvalue) ? tvalue.toString().trim() : "0"));
                                } else if (_cf != Integer.TYPE && _cf != Integer.class) {
                                    if (_cf != Boolean.TYPE && _cf != Boolean.class) {
                                        if (_cf == Character.class) {
                                            _f.set(o, tvalue != null && !"".equals(tvalue) ? tvalue.toString().charAt(0) : "");
                                        } else if (_cf == Short.class) {
                                            _f.set(o, Short.parseShort(tvalue != null && !"".equals(tvalue) ? tvalue.toString().trim() : "0"));
                                        } else if (_cf == Long.class) {
                                            _f.set(o, Long.parseLong(tvalue != null && !"".equals(tvalue) ? tvalue.toString().trim() : "0"));
                                        } else if (_cf == Double.class) {
                                            if(_cf.getName().equals("double")){
                                                _f.set(o,tvalue==null?0:Double.parseDouble(tvalue.toString().trim()));
                                            }
                                            else{
                                                _f.set(o,tvalue==null?null:Double.parseDouble(tvalue.toString().trim()));
                                            }
                                        } else if (_cf == Float.class) {
                                            _f.set(o, Float.parseFloat(tvalue != null && !"".equals(tvalue) ? tvalue.toString().trim() : "0"));
                                        } else if (_cf != Byte.class) {
                                            if (_cf == String.class) {
                                                _f.set(o, tvalue == null ? "" : tvalue.toString().trim());
                                            } else if (_cf == Date.class) {
                                                _f.set(o, (Date)tvalue);
                                            } else {
                                                _f.set(o, params.get(_k));
                                            }
                                        } else {
                                            _f.set(o, Byte.parseByte(tvalue != null && !"".equals(tvalue) ? tvalue.toString().trim() : "0"));
                                        }
                                    } else {
                                        _f.set(o, Boolean.parseBoolean(tvalue != null && !"".equals(tvalue) ? tvalue.toString().trim() : ""));
                                    }
                                } else {
                                    if(_cf.getName().equals("int")){
                                        _f.set(o,tvalue==null?0:Integer.parseInt(tvalue.toString().trim()));
                                    }
                                    else{
                                        _f.set(o,tvalue==null?null:Integer.parseInt(tvalue.toString().trim()));
                                    }
                                }
                            } catch (Exception var11) {
                                ;
                            }
                            break;
                        }
                    }
                }

                return o;
            }
        }
        return  o;
    }
    public static void setBean2Bean(Object source, Object target) {
        if (source != null && target != null) {
            Class sClazz = source.getClass();
            Class tClazz = target.getClass();
            Method[] tms = tClazz.getDeclaredMethods();
            Method[] var8 = tms;
            int var7 = tms.length;

            for(int var6 = 0; var6 < var7; ++var6) {
                Method tm = var8[var6];
                if (tm.getName().startsWith("set")) {
                    String sget = "get" + String.valueOf(tm.getName().charAt(3)).toUpperCase() + tm.getName().substring(4);

                    try {
                        Method sm = sClazz.getDeclaredMethod(sget);
                        if (sm != null) {
                            tm.invoke(target, sm.invoke(source));
                        }
                    } catch (Exception var11) {
                        ;
                    }
                }
            }

        }
    }
    public static void setBeanNotNull2Bean(Object source, Object target) {
        if (source != null && target != null) {
            Class sClazz = source.getClass();
            Class tClazz = target.getClass();
            Method[] tms = tClazz.getDeclaredMethods();
            Method[] var8 = tms;
            int var7 = tms.length;

            for(int var6 = 0; var6 < var7; ++var6) {
                Method tm = var8[var6];
                if (tm.getName().startsWith("set")) {
                    String sget = "get" + String.valueOf(tm.getName().charAt(3)).toUpperCase() + tm.getName().substring(4);

                    try {
                        Method sm = sClazz.getDeclaredMethod(sget);
                        if (sm != null) {
                            Object tmp = sm.invoke(source);
                            if (tmp != null) {
                                tm.invoke(target, tmp);
                            }
                        }
                    } catch (Exception var12) {
                        ;
                    }
                }
            }

        }
    }
    public static String tokenize(String text, String delimiter) {
        if (text != null && delimiter != null) {
            StringBuffer sb = new StringBuffer();
            String[] ts = text.split(delimiter);
            String[] var7 = ts;
            int var6 = ts.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String t = var7[var5];
                sb.append("'").append(t).append("',");
            }

            return sb.length() > 0 ? sb.substring(0, sb.length() - 1) : "";
        } else {
            return "";
        }
    }
    public static void setObjBlank(Object o) {
        if (o != null && o.getClass() != Object.class) {
            Class clazz = o.getClass();
            List fs = getFields(clazz);

            try {
                Iterator var4 = fs.iterator();

                while (true) {
                    while (true) {
                        Field _f;
                        Class _cf;
                        Object _v;
                        do {
                            do {
                                if (!var4.hasNext()) {
                                    return;
                                }

                                _f = (Field) var4.next();
                            } while ("serialVersionUID".equals(_f.getName()));

                            _f.setAccessible(true);
                            _cf = _f.getType();
                            _v = _f.get(o);
                        } while (_v != null);

                        if (_cf == BigDecimal.class) {
                            _f.set(o, new BigDecimal(0));
                        } else if (_cf == Integer.class) {
                            _f.set(o, (Object) null);
                        } else if (_cf != Boolean.TYPE && _cf != Boolean.class) {
                            if (_cf == Character.class) {
                                _f.set(o, new Character(' '));
                            } else if (_cf == Short.class) {
                                _f.set(o, (Object) null);
                            } else if (_cf == Long.class) {
                                _f.set(o, (Object) null);
                            } else if (_cf == Double.class) {
                                _f.set(o, (Object) null);
                            } else if (_cf == Float.class) {
                                _f.set(o, (Object) null);
                            } else if (_cf == Byte.class) {
                                _f.set(o, (Object) null);
                            } else if (_cf == String.class) {
                                _f.set(o, "");
                            }
                        } else {
                            _f.set(o, false);
                        }
                    }
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }
        }
    }
        public static void setObjTrim(Map<String,Object> map){
            if (map != null && map.size() != 0) {
                Set<String> keys = map.keySet();
                Iterator var3 = keys.iterator();

                while (var3.hasNext()) {
                    String k = (String) var3.next();
                    map.put(k, map.get(k) == null ? "" : map.get(k).toString().trim());
                }

            }
        }

        private static Object getUserColValue (String columnStr, Object u) throws Exception {
            Object colValue = null;
            if (u == null) {
                return colValue;
            } else {
                if (columnStr != null) {
                    String[] cs = columnStr.split("\\.");
                    Field field = u.getClass().getDeclaredField(cs[0]);
                    field.setAccessible(true);
                    colValue = field.get(u);
                    if (cs.length == 1) {
                        return colValue;
                    }

                    if (cs.length > 1) {
                        return getUserColValue(columnStr.substring(columnStr.indexOf(".") + 1), colValue);
                    }
                }

                return colValue;
            }
        }
        public static String formatChange (String format){
            int len1 = format.length();
            int len2 = format.replaceAll("'", "").length();
            int count = len1 - len2;
            if (count > 0) {
                if (count % 2 != 0) {
                    System.out.println("设置的[" + format + "]格式有错");
                    return "";
                }

                String[] ss = format.split("'");
                StringBuffer sb = new StringBuffer();
                int i = 1;
                String[] var11 = ss;
                int var10 = ss.length;

                for (int var9 = 0; var9 < var10; ++var9) {
                    String s = var11[var9];
                    if (i % 2 == 0) {
                        sb.append("'").append(s).append("'");
                    } else {
                        String tmp = s.replaceAll("Y", "y");
                        tmp = tmp.replaceAll("D", "d");
                        sb.append(tmp);
                    }

                    ++i;
                }

                format = sb.toString();
            } else {
                String tmp = format.replaceAll("Y", "y");
                tmp = tmp.replaceAll("D", "d");
                format = tmp;
            }

            return format;
        }


        public static String unicode2String (String unicode){
            StringBuffer string = new StringBuffer();
            String[] hex = unicode.split("\\\\u");

            for (int i = 1; i < hex.length; ++i) {
                int data = Integer.parseInt(hex[i], 16);
                string.append((char) data);
            }

            return string.toString();
        }

        public static Map<String, Object> objectToMap (Object obj){
            HashMap map = new HashMap();

            try {
                if (obj == null) {
                    return null;
                }

                Field[] declaredFields = obj.getClass().getDeclaredFields();
                Field[] var6 = declaredFields;
                int var5 = declaredFields.length;

                for (int var4 = 0; var4 < var5; ++var4) {
                    Field field = var6[var4];
                    field.setAccessible(true);
                    if (field.get(obj) instanceof String) {
                        if (field.get(obj) != null && !field.get(obj).equals("")) {
                            map.put(field.getName(), field.get(obj));
                        }
                    } else if (field.get(obj) instanceof Long) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Integer) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Date) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), sdf.format((Date) field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Double) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Float) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof BigInteger) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Short) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof BigInteger) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) != null) {
                        map.put(field.getName(), field.get(obj));
                    }
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            return map;
        }
        public static Map<String, Object> objectToMap2 (Object obj){
            HashMap map = new HashMap();

            try {
                if (obj == null) {
                    return null;
                }

                Field[] declaredFields = obj.getClass().getDeclaredFields();
                Field[] var6 = declaredFields;
                int var5 = declaredFields.length;

                for (int var4 = 0; var4 < var5; ++var4) {
                    Field field = var6[var4];
                    field.setAccessible(true);
                    if (field.get(obj) instanceof String) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), field.get(obj));
                        }
                    } else if (field.get(obj) instanceof Long) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Integer) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Date) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), sdf.format((Date) field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Double) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Float) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof BigInteger) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof Short) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) instanceof BigInteger) {
                        if (field.get(obj) != null) {
                            map.put(field.getName(), String.valueOf(field.get(obj)));
                        }
                    } else if (field.get(obj) != null) {
                        map.put(field.getName(), field.get(obj));
                    }
                }
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            return map;
        }
        public static Map<String, Object> getRequestParams (HttpServletRequest request){
            if (request == null) {
                return null;
            } else {
                Map<String, Object> retmap = new HashMap();
                Map<String, String[]> params = request.getParameterMap();
                Set<String> ks = params.keySet();

                String s;
                for (Iterator var5 = ks.iterator(); var5.hasNext(); retmap.put(s, arrayToString((String[]) params.get(s)))) {
                    s = (String) var5.next();
                    if (s.indexOf("[") != -1) {
                        s = s.replace("[", "");
                        s = s.replace("]", "");
                    }
                }

                return retmap;
            }
        }
        public static String getRequestJson (HttpServletRequest request){
            Map<String, Object> m = getRequestParams(request);
            return JSON.toJSONString(m);
        }

        public static String arrayToString (String[]ss){
            if (ss != null && ss.length != 0) {
                StringBuffer sb = new StringBuffer();
                String[] var5 = ss;
                int var4 = ss.length;

                for (int var3 = 0; var3 < var4; ++var3) {
                    String s = var5[var3];
                    if (s != null && !"".equals(s)) {
                        sb.append(s).append(",");
                    }
                }

                if (sb.length() > 0) {
                    return sb.substring(0, sb.length() - 1);
                } else {
                    return "";
                }
            } else {
                return "";
            }
        }
        public static Map<String, Object> getRequestParamsNotBlank (HttpServletRequest request){
            if (request == null) {
                return null;
            } else {
                Map<String, Object> retmap = new HashMap();
                Map<String, String[]> params = request.getParameterMap();
                Set<String> ks = params.keySet();
                Iterator var5 = ks.iterator();

                while (var5.hasNext()) {
                    String s = (String) var5.next();
                    if (!"".equals(arrayToString((String[]) params.get(s)))) {
                        retmap.put(s, arrayToString((String[]) params.get(s)));
                    }
                }

                return retmap;
            }
        }

        public static String getRequestJsonNotBlank (HttpServletRequest request){
            Map<String, Object> m = getRequestParamsNotBlank(request);
            return JSON.toJSONString(m);
        }

        public static String dofilter (String str){
            if (str != null && !"".equals(str.trim())) {
                String strtmp = "";
                strtmp = str.replace("'", "‘");
                return strtmp;
            } else {
                return "";
            }
        }
        public static boolean ipmatch (String ip, String ipt){
            boolean ret = true;
            if (ip != null && ipt != null && !"".equals(ip) && !"".equals(ipt)) {
                String[] ipts = ipt.split("\\.");
                String[] ips = ip.split("\\.");
                if (ips.length == 4 && ipts.length == 4) {
                    int index = 0;
                    boolean var6 = false;

                    try {
                        String[] var10 = ipts;
                        int var9 = ipts.length;

                        for (int var8 = 0; var8 < var9; ++var8) {
                            String s = var10[var8];
                            String a = ips[index++];
                            int tmpnum = Integer.parseInt(a);
                            if ((!"*".equals(s) || tmpnum < 0 || tmpnum >= 255) && !s.equals(a)) {
                                ret = false;
                                break;
                            }
                        }
                    } catch (NumberFormatException var12) {
                        ret = false;
                    }

                    return ret;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

    }
