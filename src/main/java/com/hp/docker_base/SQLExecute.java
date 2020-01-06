package com.hp.docker_base;

import com.hp.docker_base.bean.Role;
import com.hp.docker_base.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;
import com.hp.docker_base.util.BeanUtil;

/**
 * 执行sql脚本
 */
@Service
public class SQLExecute {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    @PostConstruct//启动后自己执行
    public  void insertTxt() throws IOException {

        //jdbcTemplate.update("INSERT INTO tb_user (`id`, `name`) VALUES ('2', 'bee')");
    }


    @PostConstruct//启动后自己执行
    public  void createTable() throws Exception {

        //jdbcTemplate.update("INSERT INTO tb_user (`id`, `name`) VALUES ('2', 'bee')");
        String tname = "tb_role";
        boolean isExsits = getAllTableName(jdbcTemplate, tname);

        User user = new User();
        user.setId(7);
        user.setName("jj");

        Role role = new Role();
        role.setRole_id(1);
        int re = 0;
        if(isExsits == true){
            System.out.println("已经存在");
            re = saveObj(jdbcTemplate,tname,role);
        }else{
            System.out.println("不存在");
            re = createTable(jdbcTemplate,tname,role);
            re = saveObj(jdbcTemplate,tname,role);
        }

       // return re;
    }

    /**
     * 保存方法，注意这里传递的是实际的表的名称
     */
    public static int saveObj(JdbcTemplate jt,String tableName,Object obj){
        int re = 0;
        try{
            String sql = " insert into " + tableName + " (";
            Map<String, Object> map = BeanUtil.bean2map(obj);
            Set<String> set = map.keySet();
            for(String key : set){
                sql += (key + ",");
            }
            sql += " tableName ) ";
            sql += " values ( ";
            for(String key : set){
                sql += ("'" + map.get(key) + "',");
            }
            sql += ("'" + tableName + "' ) ");
            re = jt.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return re;
    }

    /**
     * 根据表名称创建一张表
     * @param tableName
     */
    public static int createTable(JdbcTemplate jt,String tableName,Object obj) throws Exception {
        StringBuffer sb = new StringBuffer("");
        sb.append("CREATE TABLE `" + tableName + "` (");
        sb.append(" `id` int(11) NOT NULL AUTO_INCREMENT,");
       // Map<String,String> map = ObjectUtil.getProperty(obj);
        Map<String, Object> map = BeanUtil.bean2map(obj);
        Set<String> set = map.keySet();
        for(String key : set){
            sb.append("`" + key + "` varchar(255) DEFAULT '',");
        }
        sb.append(" `tableName` varchar(255) DEFAULT '',");
        sb.append(" PRIMARY KEY (`id`)");
        sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        try {
            jt.update(sb.toString());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询数据库是否有某表
     * @param jt
     * @param tableName
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean getAllTableName(JdbcTemplate jt,String tableName) throws Exception {
        Connection conn = jt.getDataSource().getConnection();
        ResultSet tabs = null;
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            String[]   types   =   { "TABLE" };
            tabs = dbMetaData.getTables(null, null, tableName, types);
            if (tabs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            tabs.close();
            conn.close();
        }
        return false;
    }
}
