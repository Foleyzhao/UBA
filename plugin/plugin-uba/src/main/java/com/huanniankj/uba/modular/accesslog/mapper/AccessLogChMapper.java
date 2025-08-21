package com.huanniankj.uba.modular.accesslog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huanniankj.common.annotation.ClickHouseDataSource;
import com.huanniankj.uba.modular.accesslog.entity.AccessLogCh;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 访问日志Mapper接口
 *
 * @author happynewyear
 */
@ClickHouseDataSource
public interface AccessLogChMapper extends BaseMapper<AccessLogCh> {

    @Select("SELECT * FROM access_log WHERE request_id = #{requestId}")
    AccessLogCh selectById(String requestId);

    @Update("ALTER TABLE access_log DELETE WHERE request_id = #{requestId}")
    int removeById(String requestId);

    @Update({
            "<script>",
            "ALTER TABLE access_log DELETE WHERE request_id IN",
            "<foreach item='id' collection='list' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    int removeByIds(@Param("list") List<String> requestIds);

}
