package cn.net.bhe.basics.stream;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.net.bhe.utils.main.JacksonUtils;
import cn.net.bhe.utils.main.SerializeUtils;

@SuppressWarnings("unchecked")
public class Group {
    static final Logger log = LoggerFactory.getLogger(Group.class);

    private static final String str = "rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAMdwQAAAAMc3IAEWphdmEudXRpbC5IYXNoTWFwBQfawcMWYNEDAAJGAApsb2FkRmFjdG9ySQAJdGhyZXNob2xkeHA/QAAAAAAADHcIAAAAEAAAAAd0AAJHQXNyABRqYXZhLm1hdGguQmlnRGVjaW1hbFTHFVf5gShPAwACSQAFc2NhbGVMAAZpbnRWYWx0ABZMamF2YS9tYXRoL0JpZ0ludGVnZXI7eHIAEGphdmEubGFuZy5OdW1iZXKGrJUdC5TgiwIAAHhwAAAAAHNyABRqYXZhLm1hdGguQmlnSW50ZWdlcoz8nx+pO/sdAwAGSQAIYml0Q291bnRJAAliaXRMZW5ndGhJABNmaXJzdE5vbnplcm9CeXRlTnVtSQAMbG93ZXN0U2V0Qml0SQAGc2lnbnVtWwAJbWFnbml0dWRldAACW0J4cQB+AAf///////////////7////+AAAAAXVyAAJbQqzzF/gGCFTgAgAAeHAAAAABAnh4dAACSURzcQB+AAUAAAAAc3EAfgAJ///////////////+/////gAAAAF1cQB+AAwAAAABAXh4dAACR0J0AANnYjF0AAJWQXNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAELeHh0AAJHQ3NyABJqYXZhLnNxbC5UaW1lc3RhbXAmGNXIAVO/ZQIAAUkABW5hbm9zeHIADmphdmEudXRpbC5EYXRlaGqBAUtZdBkDAAB4cHcIAAABbIu1JAB4AAAAAHQAAlZCdAAgNUVBMzNFMEJDNThDNEI2Mjg3MDY1NTE3NEFCODEyQTB0AAJWQ3NxAH4AGXcIAAABbIu1JAB4AAAAAHhzcQB+AAI/QAAAAAAADHcIAAAAEAAAAAdxAH4ABHNxAH4ABQAAAABxAH4AC3hxAH4ADnNxAH4ABQAAAABxAH4AC3hxAH4AEnQAA2diMnEAfgAUc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAABdXEAfgAMAAAAAgS+eHhxAH4AGHNxAH4AGXcIAAABbIu1JAB4AAAAAHEAfgAcdAAgMkNEMkNGMzA3NzJDNDBBNTlBMEEyRjY3Q0M1NDc2OTFxAH4AHnNxAH4AGXcIAAABbIu1JAB4AAAAAHhzcQB+AAI/QAAAAAAADHcIAAAAEAAAAAdxAH4ABHNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAEIeHhxAH4ADnNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAEDeHhxAH4AEnQAA2diMnEAfgAUc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAABdXEAfgAMAAAAAXh4eHEAfgAYc3EAfgAZdwgAAAFsi7UkAHgAAAAAcQB+ABx0ACAxMDI0N0RFNUNGNEI0OTQwQUEzNzRDMUM4QkNFNjU1Q3EAfgAec3EAfgAZdwgAAAFsi7UkAHgAAAAAeHNxAH4AAj9AAAAAAAAMdwgAAAAQAAAAB3EAfgAEc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAAAdXEAfgAMAAAAAHh4cQB+AA5zcQB+AAUAAAAAc3EAfgAJ///////////////+/////gAAAAF1cQB+AAwAAAABBXh4cQB+ABJ0AANnYjNxAH4AFHNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAFkeHhxAH4AGHNxAH4AGXcIAAABbHcbtAB4AAAAAHEAfgAcdAAgMTIzQjZCRTAzQjZBNEM3N0JCNDU4MEQxODNGQUE3RjZxAH4AHnNxAH4AGXcIAAABbIu1JAB4AAAAAHhzcQB+AAI/QAAAAAAADHcIAAAAEAAAAAdxAH4ABHNxAH4ABQAAAABxAH4AL3hxAH4ADnNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAEGeHhxAH4AEnQAA2diMXEAfgAUc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAABdXEAfgAMAAAAAwEhfnh4cQB+ABhzcQB+ABl3CAAAAWx8QhAAeAAAAABxAH4AHHQAIEFENjM3RUQyQzhENzRENjI5Q0RFODA1N0E3NDk3MUMzcQB+AB5zcQB+ABl3CAAAAWyLtSQAeAAAAAB4c3EAfgACP0AAAAAAAAx3CAAAABAAAAAHcQB+AARzcQB+AAUAAAAAcQB+ACx4cQB+AA5zcQB+AAUAAAAAc3EAfgAJ///////////////+/////gAAAAF1cQB+AAwAAAABB3h4cQB+ABJ0AANnYjFxAH4AFHNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAGGeHhxAH4AGHNxAH4AGXcIAAABbHcbtAB4AAAAAHEAfgAcdAAgODAwMTE0QkJEMDkwNEMwNjhCMjdENjMwRUI5RjM3MURxAH4AHnNxAH4AGXcIAAABbJDbgAB4AAAAAHhzcQB+AAI/QAAAAAAADHcIAAAAEAAAAAdxAH4ABHNxAH4ABQAAAABxAH4AC3hxAH4ADnNxAH4ABQAAAABxAH4ALHhxAH4AEnQAA2diMXEAfgAUc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAABdXEAfgAMAAAAAgGceHhxAH4AGHNxAH4AGXcIAAABbHcbtAB4AAAAAHEAfgAcdAAgRDcwNkMwODVEM0UwNDhBQkE1Q0U1MjUxQUI1RENCRTFxAH4AHnNxAH4AGXcIAAABbJDbgAB4AAAAAHhzcQB+AAI/QAAAAAAADHcIAAAAEAAAAAdxAH4ABHNxAH4ABQAAAABxAH4ALHhxAH4ADnNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAEJeHhxAH4AEnQAA2diM3EAfgAUc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAABdXEAfgAMAAAAAgGoeHhxAH4AGHNxAH4AGXcIAAABbHcbtAB4AAAAAHEAfgAcdAAgNjI5Njc3RjE4MkM0NERGNEJDODc1NTg5NDBDNDM3NkVxAH4AHnNxAH4AGXcIAAABbJDbgAB4AAAAAHhzcQB+AAI/QAAAAAAADHcIAAAAEAAAAAdxAH4ABHNxAH4ABQAAAABxAH4ALHhxAH4ADnNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAEKeHhxAH4AEnQAA2diM3EAfgAUc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAABdXEAfgAMAAAAAi7geHhxAH4AGHNxAH4AGXcIAAABbKV08AB4AAAAAHEAfgAcdAAgNTE5QzU1RjQ4RTEwNDVEQTg5QkQ3QjY3Mzg1Qjc2MjRxAH4AHnNxAH4AGXcIAAABbJDbgAB4AAAAAHhzcQB+AAI/QAAAAAAADHcIAAAAEAAAAAdxAH4ABHNxAH4ABQAAAABxAH4AL3hxAH4ADnNxAH4ABQAAAABxAH4AFnhxAH4AEnQAA2diMnEAfgAUc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAABdXEAfgAMAAAAAQR4eHEAfgAYc3EAfgAZdwgAAAFsdxu0AHgAAAAAcQB+ABx0ACBBMjg0MDU0ODgzOUQ0MkREOEYyMEEwN0U2OTk5RUExOXEAfgAec3EAfgAZdwgAAAFskNuAAHgAAAAAeHNxAH4AAj9AAAAAAAAMdwgAAAAQAAAAB3EAfgAEcQB+ADlxAH4ADnNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAEMeHhxAH4AEnQAA2diM3EAfgAUc3EAfgAFAAAAAHNxAH4ACf///////////////v////4AAAABdXEAfgAMAAAAAgHJeHhxAH4AGHNxAH4AGXcIAAABbHcbtAB4AAAAAHEAfgAcdAAgQ0Y3MTIwRDU5M0U3NDdBRUExQkE0NTU4MTY4MkVFMkFxAH4AHnNxAH4AGXcIAAABbJDbgAB4AAAAAHhzcQB+AAI/QAAAAAAADHcIAAAAEAAAAAdxAH4ABHEAfgA5cQB+AA5zcQB+AAUAAAAAc3EAfgAJ///////////////+/////gAAAAF1cQB+AAwAAAABDXh4cQB+ABJ0AANnYjJxAH4AFHNxAH4ABQAAAABzcQB+AAn///////////////7////+AAAAAXVxAH4ADAAAAAEreHhxAH4AGHNxAH4AGXcIAAABbKV08AB4AAAAAHEAfgAcdAAgRTg2QjNFN0QzN0VCNDkwMEIxMjhGQzVDMjI1MjI1RDdxAH4AHnNxAH4AGXcIAAABbJDbgAB4AAAAAHh4";
    private static List<Map<String, Object>> list;
    static {
        try {
            list = SerializeUtils.toObj(List.class, str);
            log.info("原始列表Json串：{}", JacksonUtils.objToJsonStr(list));
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 以单个字段值分组
     * 
     * A classification function is applied to each element of the stream. The
     * value that is returned by the function is used as a key to the map that
     * we get from the groupingBy collector.
     * <p/>
     * The classification function is not limited to returning only a scalar or
     * String value. The key of the resulting map could be any object as long as
     * we make sure that we implement the necessary equals and hashcode methods.
     */
    @Test
    public void groupingByOneCol() {
        Map<String, List<Map<String, Object>>> group = list.stream().collect(Collectors.groupingBy(e -> e.get("GA").toString()));
        log.info("分组后Json串：{}", JacksonUtils.objToJsonStr(group));
    }

    @Test
    public void groupingByMultiCol() {
        Map<Object, Map<Object, Map<Object, List<Map<String, Object>>>>> group = list.stream()
                .collect(Collectors.groupingBy(e -> e.get("GA").toString(),
                        Collectors.groupingBy(e -> e.get("GB").toString(),
                                Collectors.groupingBy(e -> e.get("GC").toString()))));
        log.info("分组后Json串：{}", JacksonUtils.objToJsonStr(group));
    }

    @Test
    public void groupingByAndSum() {
        Map<Object, Long> group = list.stream()
                .collect(Collectors.groupingBy(e -> e.get("GA").toString(),
                        Collectors.counting()));
        log.info("分组后Json串：{}", JacksonUtils.objToJsonStr(group));
    }

    /**
     * 指定返回的map类型
     * 
     * When using the groupingBy collector, we cannot make assumptions about the
     * type of the returned Map. If we want to be specific about which type of
     * Map we want to get from the group by then we can use the third variation
     * of the groupingBy method that allows us to change the type of the Map by
     * passing a Map supplier function.
     */
    @Test
    public void groupingByAndSpecifyReturnType() {
        Map<Object, List<Map<String, Object>>> group = list.stream()
                .collect(Collectors.groupingBy(e -> e.get("GA").toString(),
                        LinkedHashMap::new, Collectors.toList())); // LinkedHashMap按遍历顺序保存，防止分组后顺序被打乱
        log.info("分组后Json串：{}", JacksonUtils.objToJsonStr(group));
    }

}
