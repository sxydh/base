package fun.ehe.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MyBeanUtils {

    public static void ItProps(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            try {
                Method m = target.getClass()
                        .getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                String value = m.invoke(target) == null ? "null" : m.invoke(target).toString();
                T.t(MyBeanUtils.class, fieldName, value);
            } catch (Exception e) {

            }
        }
    }

    public static void copyNotEmptyProps(Object source, Object target) {
        if (source != null && target != null) {
            Field[] fs_s = source.getClass().getDeclaredFields();
            Field[] fs_t = target.getClass().getDeclaredFields();
            for (Field f_s : fs_s) {
                String fN_s = f_s.getName();
                try {
                    Method m_s = source.getClass()
                            .getMethod("get" + fN_s.substring(0, 1).toUpperCase() + fN_s.substring(1));
                    for (Field f_t : fs_t) {
                        String fN_t = f_t.getName();
                        if (fN_s.equals(fN_t) && MyStringUtils.notEmpty(m_s.invoke(source))) {
                            Method m_t = target.getClass().getMethod(
                                    "set" + fN_t.substring(0, 1).toUpperCase() + fN_t.substring(1), f_t.getType());
                            m_t.invoke(target, m_s.invoke(source));
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    public static void copyAllProps(Object source, Object target) {
        Field[] fs_s = source.getClass().getDeclaredFields();
        Field[] fs_t = target.getClass().getDeclaredFields();
        for (Field f_s : fs_s) {
            String fN_s = f_s.getName();
            try {
                Method m_s = source.getClass()
                        .getMethod("get" + fN_s.substring(0, 1).toUpperCase() + fN_s.substring(1));
                for (Field f_t : fs_t) {
                    String fN_t = f_t.getName();
                    if (fN_s.equals(fN_t)) {
                        Method m_t = target.getClass().getMethod(
                                "set" + fN_t.substring(0, 1).toUpperCase() + fN_t.substring(1), f_t.getType());
                        m_t.invoke(target, m_s.invoke(source));
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    public static Boolean fieldsNotAllEmpty(Object target) {
        Boolean flag = false;
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            try {
                Method m = target.getClass()
                        .getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                if (m.invoke(target) != null && MyStringUtils.notEmpty(m.invoke(target))) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {

            }
        }
        return flag;
    }

    public static Boolean fieldsNotEmptyExcept(Object target, String... fieldNames) {
        Boolean flag = true;
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            boolean need = true;
            for (String temp : fieldNames) {
                if (fieldName.equals(temp)) {
                    need = false;
                    break;
                }
            }
            if (need) {
                try {
                    Method m = target.getClass()
                            .getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                    if (m.invoke(target) == null || !MyStringUtils.notEmpty(m.invoke(target))) {
                        flag = false;
                    }
                } catch (Exception e) {

                }
            }
            if (!flag) {
                break;
            }
        }
        return flag;
    }

    public static Boolean fieldsEmptyExcept(Object target, String... fieldNames) {
        Boolean result = true;
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            boolean proceed = true;
            for (String temp : fieldNames) {
                if (fieldName.equals(temp)) {
                    proceed = false;
                    break;
                }
            }
            if (proceed) {
                try {
                    Method m = target.getClass()
                            .getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                    if (MyStringUtils.notEmpty(m.invoke(target))) {
                        result = false;
                    }
                } catch (Exception e) {

                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    public static Boolean fieldsNullExcept(Object target, String... fieldNames) {
        Boolean result = true;
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field f : fields) {
            String fieldName = f.getName();
            boolean proceed = true;
            for (String temp : fieldNames) {
                if (fieldName.equals(temp)) {
                    proceed = false;
                    break;
                }
            }
            if (proceed) {
                try {
                    Method m = target.getClass()
                            .getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                    if (m.invoke(target) != null) {
                        result = false;
                    }
                } catch (Exception e) {

                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    public static Object getFieldValue(Object object, String fieldName) {
        Object result = null;
        try {
            Method m = object.getClass()
                    .getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            result = m.invoke(object);
        } catch (Exception e) {
            T.t(MyBeanUtils.class, "errors in " + e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {

    }

    @SuppressWarnings("rawtypes")
    public static String getFieldNamesAndTypes(Class clazz) {
        String fieldNamesAndTypes = "";
        try {
            List<Field> fieldList = new ArrayList<Field>();
            Class tempClass = clazz;
            while (tempClass != null) {
                fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
            for (Field f : fieldList) {
                String fieldName = f.getName();
                String fieldType = f.getType().getSimpleName().toString();
                fieldNamesAndTypes += "," + fieldName + "-" + fieldType;
            }
            fieldNamesAndTypes = fieldNamesAndTypes.substring(1);
        } catch (Exception e) {

        }
        return fieldNamesAndTypes;
    }
}