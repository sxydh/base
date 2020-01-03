package fun.ehe.utils;

import java.util.ArrayList;
import java.util.List;

public class MySortUtils {

    /**
     * Sort by specified field, and the field type must be one of the following:
     * <p>
     * Integer, Long, Float, Double, String
     * </p>
     *
     * @param elements
     *            a list of beans
     * @param fieldName
     * @param order
     *            1: ASC; -1: DESC
     * @return
     * @throws Exception
     */
    public static <X> List<X> byField(List<X> elements, String fieldName, int order) throws Exception {
        if (elements != null && elements.size() > 0) {
            for (int i = 0; i < elements.size() - 1; i++) {
                boolean discontinue = true;
                for (int j = 0; j < elements.size() - i - 1; j++) {
                    boolean swap = false;
                    X former = elements.get(j);
                    X later = elements.get(j + 1);
                    Object compareVal_former = MyBeanUtils.getFieldValue(former, fieldName);
                    Object compareVal_later = MyBeanUtils.getFieldValue(later, fieldName);
                    if (order == 1) {
                        if (compare(compareVal_former, compareVal_later) > 0) {
                            swap = true;
                        }
                    } else if (order == -1) {
                        if (compare(compareVal_former, compareVal_later) < 0) {
                            swap = true;
                        }
                    }
                    if (swap) {
                        X temp = former;
                        elements.set(j, later);
                        elements.set(j + 1, temp);
                        discontinue = false;
                    }
                }
                if (discontinue) {
                    break;
                }
            }
        }
        return elements;
    }

    private static int compare(Object a, Object b) throws Exception {
        if (a instanceof Integer) {
            Integer ai = (Integer) a;
            Integer bi = (Integer) b;
            return ai.equals(bi) ? 0 : ai > bi ? 1 : -1;
        } else if (a instanceof Long) {
            Long ai = (Long) a;
            Long bi = (Long) b;
            return ai.equals(bi) ? 0 : ai > bi ? 1 : -1;
        } else if (a instanceof Float) {
            Float ai = (Float) a;
            Float bi = (Float) b;
            return ai.equals(bi) ? 0 : ai > bi ? 1 : -1;
        } else if (a instanceof Double) {
            Double ai = (Double) a;
            Double bi = (Double) b;
            return ai.equals(bi) ? 0 : ai > bi ? 1 : -1;
        } else if (a instanceof String) {
            String ai = (String) a;
            String bi = (String) b;
            if (ai.equals(bi)) {
                return 0;
            }
            return ai.compareTo(bi) > 0 ? 1 : -1;
        } else {
            throw new Exception("wrong field type");
        }
    }

    public static void main(String[] args) throws Exception {
        List<Test> tests = new ArrayList<Test>();
        tests.add(new Test("aaa", 20));
        tests.add(new Test("g", -1));
        tests.add(new Test("aa", 20));
        T.t(null, byField(tests, "integer", 1));
    }

    static class Test {

        private String string;
        private Integer integer;

        public Test(String string, Integer integer) {
            this.string = string;
            this.integer = integer;
        }

        public String getString() {
            return string;
        }

        public Integer getInteger() {
            return integer;
        }

        public void setString(String string) {
            this.string = string;
        }

        public void setInteger(Integer integer) {
            this.integer = integer;
        }

        @Override
        public String toString() {
            return string + " " + integer;
        }

    }
}
