# E_Commerce-RestfulApi

This api gives E_Commerce services 

# Given services are:
    
    *  get all users
    *  get user by userId
    *  delete user by userId
    *  add user by sending userDto 
    *  delete user by userId
    *  update user wallet



    *  get all products
    *  get product by productId
    *  get product by productName queryparam
    *  get list of product's categories by productId
    *  create product by sending productDto
    *  delete product by productId
    *  update productQunatity by sending productId and the required quantity


    
    *  get all categories
    *  get category by categoryId
    *  get list of category's products by categoryId
    *  create cateogry by sending categoryDto
   


    *  get all carts
    *  get list of user's cart items by userId
    *  add to cart by sending userId, productName, the required quantity of that product
    *  delete cart by userId
    *  delete product from cart by userId and productId



    *  get all orders
    *  get user's order by userId
    *  make order by user's id
    *  update order by user's id
    *  checkout by userId
    *  delete order by userId

# Handled Cases:


 1. When transaction is done :
    1. user cart items are deleted as well as to his order ,
    2. his money is decreased by the paid amount of money 
    3.the product quantity is decreased in stock
    
2. User get a message that he can't add a product if the quantity is not enough

3. If two users added the same quantity in their order and one of them checked out:
    
    1. If product quantity gets zero a product out of stock message gets displayed
    2. If product quantity becomes less than the demanded the user get a message telling him that the quantity is not enough
    
4. If user is to be deleted:
   
    1. If he has an order a message is sent as order should be deleted first due to the association
    2. If his order is deleted but he has a cart a message is sent as the cart should be deleted first due to the association
    3. If he doesn't have neither cart nor order he is deleted successfully

5. If product is to be deleted:

    1. If product is in any cart a a message is sent with users Ids to delete the product from their carts 
    2. If there is not any cart that contains this product then the product  is deleted successfully


# MySQL Schema

* Create a database user using MySQL Workbench:

    1.log in to the MySQL Server using root user
    
    2.Create an empty schema eithe the provided name :ecommerce_schema
   
    3.Once you are logged in to the MySQL Database Server, in the left-hand window, under the ‘MANAGEMENT’ menu, 
    there is a link for ‘Users and Privileges’. Open the Users and Privileges.
    
    4.click on the Users and Privileges
    
    5.to add a new user, click on the ‘Add Account’ button

    6.give him a username :api and password api 
    
    7.in Limit to hosts matching write: localhost
    
    8.Then select the Account Limits 
    
    9.Then select the Schema Privileges tab
    
    10.choose ecommerce_schema from add Entry and give the user all the privileges
    
    11.click on the Apply button to save the new user

*  Go to (resources/META-INF/persistence.xml)and make the value of " hibernate.hbm2ddl.auto " property equals to  "create".
*  Run App.java file  using the following maven command: => mvn clean compile exec:java
*  After making sure the schema is created
*  Go back to (resources/META-INF/persistence.xml)and make the value of " hibernate.hbm2ddl.auto " property equals to  "update".

# In Maven 

* Change the configuration of tomcat in `pom.xml` in your project with your localhost port.
* Deploy your application using the following maven command: => mvn clean compile tomcat7:redeploy
    



# Used in this project:

* JAVA language
* Maven
* MySQL DB
* JPA

# Data format

you can get to see data as an XML or JSON

# Postman documentation 
[Postman_documentation](https://documenter.getpostman.com/view/20768348/UyxdL99k)


# ER Diagram
![](/ER_DIAGRAM.png)
