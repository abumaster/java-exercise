package com.abumaster.example.springworkredis.server;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.DefaultNodeParser;
import cn.hutool.core.lang.tree.parser.NodeParser;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.*;

/**
 * 测试
 *
 * @author zhangguofeng
 * @version 1.0
 * @date 2021/7/19
 */
public class TreeUtilsTest {

    public static void main(String[] args) throws Exception{
        // 原始数据
        List<Entity> query = Db.use().query("select id, parentId, name, weight from opt_info");
        // 添加额外的数据
        for (Entity one : query) {
            one.put("xx",RandomUtil.randomInt(1,10));
            one.put("module",100);
        }
        // 构造树
        List<Tree<Integer>> tree = TreeUtil.build(query, -1,
                new MyNodeParser<Integer>());
        // 排序字段
        tree.sort((o1, o2) -> o2.get("xx").toString().compareTo(o1.get("xx").toString()));
        List<String> headers= Arrays.asList("module,默默","xx,哈希");
        Tree<Integer> t = tree.get(0);
        sumTreeField(t);
        System.out.println(JSONUtil.toJsonStr(t));
    }
    /** 求某一字段的值与其孩子累加*/
    public static Integer sumTreeField(Tree<Integer> t) {
        if (null == t) {
            return 0;
        }
        int now = Integer.parseInt(t.get("weight").toString());
        // 获取所有的孩子节点
        List<Tree<Integer>> children = t.getChildren();

        if(null != children) {
            // 遍历孩子，累加
            for (Tree<Integer> x : children) {
                now += sumTreeField(x);
            }
        }
        // 设置值
        t.put("weight",now);
        return now;
    }

    /** 自定义解析器，将额外的数据添加到树的节点中*/
     static class MyNodeParser<T> implements NodeParser<cn.hutool.db.Entity, T> {
         @Override
         public void parse(cn.hutool.db.Entity object, Tree<T> treeNode) {
             treeNode.setId((T)object.get("id"));
             treeNode.setParentId((T)object.get("parentId"));
             treeNode.setName(object.get("name").toString());
             treeNode.putExtra("module",object.get("module"));
             treeNode.putExtra("weight", object.getInt("weight"));
         }
     }

     /** 将树保存到文件中*/
     public static void saveToExcel(Tree<Integer> tree, List<String> headers) {
         String destPath="C:\\Users\\zgf\\Desktop\\out.xlsx";
         FileUtil.del(destPath);
         ExcelWriter writer = ExcelUtil.getWriter(destPath);
         List<Map<String,Object>> result = new ArrayList<>();
         int i =1;

         recursionTree(tree, headers, i, result);
        writer.write(result,true);
        writer.setSheet("2");
         System.out.println(result.size());
         writer.close();

     }

    private  static void recursionTree(Tree<Integer> t, List<String> h, int i,
                                       List<Map<String,Object>> result) {
        if (t==null) {
            return;
        }
        // 父节点先写入
        String prefix = StrUtil.padAfter("", i,' ');
        Map<String,Object> item = new LinkedHashMap<>();
        item.put("部门id", t.getId());
        item.put("部门名称", prefix+t.getName());
        // 额外的字段
        for (String head:h) {
            String[] split = head.split(",", -1);
            item.put(split[split.length-1], t.get(split[0]));
        }
        result.add(item);
        // 孩子列表
        List<Tree<Integer>> children = t.getChildren();
        i*=2;
        if(null==children) {
            return;
        }
        for (Tree<Integer> x:children) {

            recursionTree(x, h, i,result);
        }

    }
}
