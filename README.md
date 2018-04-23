# Spring-Hibernate5-Annotation

Download hibernate5 annotations zip from following and add it to project along with connector and other hibernate depended jars:
https://sourceforge.net/projects/hibernate/files/hibernate-annotations/3.4.0.GA/hibernate-annotations-3.4.0.GA.zip/download

# //////////////////////////////////////////////////////////
# For Sample table operations using hibernate annotations
# //////////////////////////////////////////////////////////
	  CREATE TABLE employee1 (
      id int(11), 
	  first_name varchar(100),
      last_name varchar(100), 
      salary int(11));
      
# //////////////////////////////////////////////////////////	  
#	One to One mapping
# //////////////////////////////////////////////////////////

CREATE TABLE newemployee (
      id int(11), 
      EMAIL varchar(100), 
	  first_name varchar(100),
      last_name varchar(100), 
      account_id int(11));     	 
	  
	  create table account(
	  id int(11),
	  acc_number varchar(100));
# //////////////////////////////////////////////////////////
# Many to One mapping
# //////////////////////////////////////////////////////////	  
	  One to many mapping is made between two entities where first entity can have relation with multiple second entity instances
	  but second can be associated with only one instance of first entity. Its 1 to n relationship. 
	  For example, in any company an employee can register multiple bank accounts but one bank account will be associated
	  with one and only one employee
	  
	  This problem can be solved in two different ways. One is to have a foreign key column in account table
	  i.i EMPLOYEE_ID. This column will refer to primary key of Employee table. 
	  This way no two accounts can be associated with multiple employees. 
	  Obviously, account number needs to be unique for enforcing this restriction.
	  
	  CREATE TABLE NewEmployee2 (
      id int(11), 
      EMAIL varchar(100), 
	  first_name varchar(100),
      last_name varchar(100));
	  
	  create table account2(
	  id int(11),
	  acc_number varchar(100),
	  employee_id int(11));
	  
# //////////////////////////////////////////////////////////
# Many to One mapping
# //////////////////////////////////////////////////////////	 
This approach uses a join table to store the associations between account and employee entities. 
@JoinTable annotation has been used to make this association.

	  CREATE TABLE NewEmployee3 (
      emp_id int(11), 
      EMAIL varchar(100), 
	  first_name varchar(100),
      last_name varchar(100));
	  
	  create table employee_account(
	  employee_id int(11),
	  account_id int(11));
	  
	  create table account3(
	  acc_id int(11),
	  acc_number varchar(100));

# //////////////////////////////////////////////////////////
# Many to One mapping
# //////////////////////////////////////////////////////////
	  create table subscription(
	  id int(11),
	  subs_name varchar(100));
	  
	  create table reader_subscriptions(
	  readers_id int(11),
	  subscriptions_id int(11));
	  
	  create table reader(
	  id int(11),
	  EMAIL varchar(100), 
	  first_name varchar(100),
      last_name varchar(100));
	  
	  
AUTO: Hibernate selects the generation strategy based on the used dialect,
IDENTITY: Hibernate relies on an auto-incremented database column to generate the primary key,
SEQUENCE: Hibernate requests the primary key value from a database sequence,
TABLE: Hibernate uses a database table to simulate a sequence.

# Hibernate Cache working

Whenever hibernate session try to load an entity, the very first place it look for cached copy of entity in first level cache (associated with particular hibernate session).

If cached copy of entity is present in first level cache, it is returned as result of load method.

If there is no cached entity in first level cache, then second level cache is looked up for cached entity.

If second level cache has cached entity, it is returned as result of load method. But, before returning the entity, it is stored in first level cache also so that next invocation to load method for entity will return the entity from first level cache itself, and there will not be need to go to second level cache again.

If entity is not found in first level cache and second level cache also, then database query is executed and entity is stored in both cache levels, before returning as response of load() method.

Second level cache validate itself for modified entities, if modification has been done through hibernate session APIs.

If some user or process make changes directly in database, the there is no way that second level cache update itself until “timeToLiveSeconds” duration has passed for that cache region. In this case, it is good idea to invalidate whole cache and let hibernate build its cache once again. You can use below code snippet to invalidate whole hibernate second level cache.

# References:
Download  hibernate annotations zip from following and add it to project along with connector and other hibernate depended jars:
https://sourceforge.net/projects/hibernate/files/hibernate-annotations/3.4.0.GA/hibernate-annotations-3.4.0.GA.zip/download
https://www.thoughts-on-java.org/jpa-generate-primary-keys/
https://howtodoinjava.com/hibernate/hibernate-one-to-one-mapping-using-annotations/
https://dzone.com/tutorials/java/hibernate/hibernate-example/hibernate-mapping-one-to-many-using-annotations-1.html
https://howtodoinjava.com/hibernate/hibernate-one-to-many-mapping-using-annotations/
https://howtodoinjava.com/hibernate/hibernate-many-to-many-mapping-using-annotations/
https://howtodoinjava.com/hibernate/hibernate-ehcache-configuration-tutorial/
