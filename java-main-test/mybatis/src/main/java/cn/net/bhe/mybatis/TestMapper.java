package cn.net.bhe.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface TestMapper {

    public Map<String, Object> get(int id);

    @Select(" <script> SELECT * FROM test </script> ")
    public List<Map<String, Object>> list();

}
