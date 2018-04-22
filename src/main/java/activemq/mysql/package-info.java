
/**
 * @author Administrator
 * activemq持久层改为mysql
 */
package activemq.mysql;

/*
activemq版本：5.1.4

添加jar包：
  mysql-connector-java-5.1.21.jar
  commons-dbcp-1.4.jar
  commons-pool-1.6.jar
  
active.xml增加配置：
  persistenceAdapter>
    <!-- <kahaDB directory="${activemq.data}/kahadb"/> -->
    <jdbcPersistenceAdapter dataSource="#mysql-ds"/>
  </persistenceAdapter>

  <bean id="mysql-ds" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/testdb"/>
    <property name="username" value="root"/>
    <property name="password" value="root1234"/>
    <property name="maxActive" value="200"/>
  </bean>
  */
  