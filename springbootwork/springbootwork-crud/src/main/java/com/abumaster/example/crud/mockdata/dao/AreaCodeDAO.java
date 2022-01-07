package com.abumaster.example.crud.mockdata.dao;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 查询
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2022/1/7
 */
@Repository
@Slf4j
public class AreaCodeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void processData() {
        String sql = "select * from area_code_2021";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        TreeNodeConfig config = new TreeNodeConfig();
        config.setIdKey("code");
        config.setDeep(3);
        config.setNameKey("name");
        config.setParentIdKey("pcode");

        List<Tree<Long>> tree = TreeUtil.build(maps, 0L, config,
                (node, t)->{
                    t.setId(MapUtil.getLong(node, "code"));
                    t.setParentId(MapUtil.getLong(node, "pcode"));
                    t.setName(MapUtil.getStr(node, "name"));
                });

        System.out.println(tree.size());
        FileWriter fileWriter = new FileWriter(new File("/home/area-code.properties"),"UTF-8");
        for (Tree<Long> p:tree) {
            Map<Long, String > res = Maps.newHashMap();
            getFullName(p, res);
            res.forEach((k, v) -> fileWriter.append("" + k + "=" + v+"\n"));
        }

    }

    private void getFullName(Tree<Long> root, Map<Long, String> result) {
        List<Tree<Long>> children = root.getChildren();
        if (children == null || children.isEmpty()) {
            List<CharSequence> parentsName = root.getParentsName(true);
            Collections.reverse(parentsName);
            String name=StringUtils.join(parentsName,"");
            name = StringUtils.removeEnd(name, "村委会");
            name = StringUtils.removeEnd(name, "居委会");
            name = StringUtils.removeEnd(name, "居民委员会");
            result.put(root.getId(),name);
        } else {
            for (Tree<Long> one:children) {
                getFullName(one, result);
            }
        }
    }
}
