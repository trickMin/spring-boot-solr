package org.yt.springbootsolr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

/**
 * ClassName: UserWithDpt <br/>
 * Description: 含部门信息的用户实体类<br/>
 * date: 2020/1/15 14:40 <br/>
 *
 * @author Min <br/>
 */
@SolrDocument(solrCoreName = "practice")
public class UserWithDpt implements Serializable {
    @Id
    @Field
    private String id;
    @Field
    private String username;
    @Field
    private String password;
    @Field
    private Integer age;
    @Field
    private Integer dptId;
    @Field
    private String dptName;

    @Override
    public String toString() {
        return "UserWithDpt{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", dptId=" + dptId +
                ", dptName='" + dptName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }
}
