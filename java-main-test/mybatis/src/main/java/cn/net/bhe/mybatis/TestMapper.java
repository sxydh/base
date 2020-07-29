package cn.net.bhe.mybatis;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 注意mybatis3.2.2版本<script>前面不能有空格
 */
public interface TestMapper {

    public Map<String, Object> get(int id);

    @Select("<script> select t.* from test t </script>") 
    public List<Map<String, Object>> list();
    
    @Select("<script> ${sql} </script>")
    public Object execute(Map<String, Object> req); // 经未完全测试，此处参数只能用map，且当返回为Object时，只支持Select

    @Update(" <script> " +
            " <foreach collection = 'list' item = 'item' open = 'begin' separator = ';' close = '; end;'> " +
            " update test set value = #{item.value} " +
            " where id = #{item.id} " +
            " </foreach> " +
            " </script> ")
    public int update(Collection<Map<String, Object>> reqs); // size必须大于0
    
    @Select("<script> select 1 from test t where rownum = 1 and (cast(#{date} as date) - nvl(utime, sysdate)) > #{dif} </script>") 
    public Object compareTime(Map<String, Object> req); // 必须cast一遍date
    
    @Update(" <script> " +
            " update test set id = id " +
            " <if test='value != null and value != \"\"'>, value = #{value} </if> " +
            " where id = #{id} " +
            " </script> ")
    public int test(Map<String, Object> req);
}
