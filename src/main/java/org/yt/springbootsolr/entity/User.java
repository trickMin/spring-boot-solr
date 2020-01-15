package org.yt.springbootsolr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * ClassName: User <br/>
 * Description: 用户实体类<br/>
 * date: 2020/1/15 11:36 <br/>
 *
 * @author Min <br/>
 */
@SolrDocument(solrCoreName = "practice")
public class User {
    @Id
    @Field
    private Integer id;
    @Field
    private String username;
    @Field
    private String password;
    @Field
    private Integer age;
    @Field
    private Integer dptId;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", dptId=" + dptId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDptId() {
        return dptId;
    }

    public void setDptId(Integer dptId) {
        this.dptId = dptId;
    }
}
