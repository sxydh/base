package cn.net.bhe.springbootmybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface TestMapper {

    @Select({ " <script> "
            + " SELECT id \"id\", name \"name\", ctime \"ctime\", utime \"utime\" FROM t_member WHERE id = #{id} "
            + " </script> " })
    @Results({ @Result(property = "ctime", column = "ctime", jdbcType = JdbcType.TIMESTAMP)
            , @Result(property = "utime", column = "utime", jdbcType = JdbcType.TIMESTAMP) })
    public Map<String, Object> get(Map<String, Object> rq_m);

    @Select({ " <script> SELECT * FROM ( SELECT rs_.*, rownum rownum_ FROM ( "
            + " SELECT id \"id\", name \"name\", ctime \"ctime\", utime \"utime\" FROM t_member "
            + " ) rs_ ) WHERE rownum_ &gt; #{offset} AND rownum_ &lt;= (#{offset} + #{limit}) </script> " })
    @Results({ @Result(property = "ctime", column = "ctime", jdbcType = JdbcType.TIMESTAMP)
            , @Result(property = "utime", column = "utime", jdbcType = JdbcType.TIMESTAMP) })
    public List<Map<String, Object>> list(Map<String, Object> rq_m);
    
    @Select({ " <script> "
            + " UPDATE test SET val = dbms_random.random WHERE id = #{id} "
            + " </script> " })
    public Integer update(Map<String, Object> rq_m);

}
