package org.yt.springbootsolr.test;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yt.springbootsolr.entity.User;
import org.yt.springbootsolr.entity.UserWithDpt;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SolrTest <br/>
 * Description: solr的测试类，涉及到的实体类都打上注解<br/>
 * date: 2020/1/15 11:32 <br/>
 *
 * @author Min <br/>
 */
@Component
public class SolrTest {
    @Autowired
    private SolrClient solrClient;

    /**
     * 根据用户所需的条件
     * 查User
     * @param queryCondition
     * @return
     */
    public List<User> testFindByQueryCondition(String queryCondition){

        //创建变量
        List<User> userList = null;
        User user = null;
        SolrQuery query = null;
        QueryResponse response = null;
        SolrDocumentList results = null;

        try {
            //初始化变量
            query = new SolrQuery();
            //添加搜索条件
            query.set("q",queryCondition);
            //通过搜索条件获取响应
            response = solrClient.query(query);
            //通过响应response获取结果
            results = response.getResults();
            //用标准容量初始化集合容器
            userList = new ArrayList<>(results.size());
            //由于结果是一个List<SolrDocument>集合，因此遍历集合文档，获取单独文档，文档中以KV形式存储数据
            for (SolrDocument document:results){
                user = new User();
                user.setId(Integer.valueOf((String) document.get("id")));
                user.setUsername((String) document.get("username"));
                user.setPassword((String) document.get("password"));
                user.setAge((Integer) document.get("age"));
                user.setDptId((Integer) document.get("dptId"));
                userList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return userList;
        }
    }

    /**
     * 查询所有
     * @return
     */
    public List<User> testFindAll(){

        List<User> userList = null;

        try {
            userList = testFindByQueryCondition("*:*");
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;

    }

    /**
     * 通过SolrDocument注解简化实体类封装
     * @param queryCondition
     * @return
     */
    public List<UserWithDpt> testFindUserWithDptByQueryCondition(String queryCondition){

        //创建变量
        List<UserWithDpt> userList = null;
        UserWithDpt user = null;
        SolrQuery query = null;
        QueryResponse response = null;
        SolrDocumentList results = null;

        try {
            //初始化变量
            query = new SolrQuery();
            //添加搜索条件
            query.set("q",queryCondition);
            //通过搜索条件获取响应
            response = solrClient.query(query);
            //利用response直接获得JavaBeans（前提是实体类已经注解打好，id的数据类型得是String）
            userList = response.getBeans(UserWithDpt.class);
            //用标准容量初始化集合容器
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return userList;
        }
    }

    /**
     * 添加文档对象并提交（增删改操作务必commit）
     * @param user
     */
    public void add(User user){
        //生成新增型文档对象
        SolrInputDocument document = new SolrInputDocument();
        //为文档对象添加属性
        document.addField("id",user.getId());
        document.addField("username",user.getUsername());
        document.addField("password",user.getPassword());
        document.addField("age",user.getAge());
        document.addField("dptId",user.getDptId());
        try {
            solrClient.add(document);
            solrClient.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 从内存中删除
     * @param condition
     */
    public void deleteByCondition(String condition){
        try {
            solrClient.deleteByQuery(condition);
            solrClient.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 更新内存中的User
     * @param user
     */
    public void update(User user){
        try{
            solrClient.addBean(user);
            solrClient.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * ----------------------------------查询的额外内容（params就是query）--------------------------------------
     *             //过滤条件
     *             params.set("fq", "product_price:[100 TO 100000]");
     *
     *             //排序
     *             params.addSort("product_price", SolrQuery.ORDER.asc);
     *
     *             //分页
     *             params.setStart(0);
     *             params.setRows(20);
     *
     *             //默认域
     *             params.set("df", "product_title");
     *
     *             //只查询指定域
     *             params.set("fl", "id,product_title,product_price");
     *
     *             //高亮
     *             //打开开关
     *             params.setHighlight(true);
     *             //指定高亮域
     *             params.addHighlightField("product_title");
     *             //设置前缀
     *             params.setHighlightSimplePre("<span style='color:red'>");
     *             //设置后缀
     *             params.setHighlightSimplePost("</span>");
     */


}
