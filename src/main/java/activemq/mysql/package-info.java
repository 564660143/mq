
/**
 * @author Administrator
 * activemq�־ò��Ϊmysql
 */
package activemq.mysql;

/*
activemq�汾��5.1.4

���jar����
  mysql-connector-java-5.1.21.jar
  commons-dbcp-1.4.jar
  commons-pool-1.6.jar
  
active.xml�������ã�
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
  