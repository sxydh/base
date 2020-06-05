package cn.net.bhe.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface TestMapper {

    public Map<String, Object> get(int id);

    @Select("<script> select t.* from test t </script>") // 注意mybatis3.2.2版本<script>前面不能有空格
    public List<Map<String, Object>> list();

}
