# E_Commerce-RestfulApi

This api gives E_Commerce services 

# Given services are:

* get all users
* get user by Id
* add products
* delete product by product Id
* update product quantity
* add categories
* user can add items to shopping cart
* user can place an order
* user can delete his order
* user can proceed to checkout 
* user can add money to his wallet
* user can delete cart

# Handled Cases:


 1. When transaction is done :
    1. user cart items are deleted as well as to his order ,
    2. his money is decreased by the paid amount of money 
    3.the product quantity is decreased in stock
    
2. User get a message that can't add a product if the quantity is not enough

3. If two users added the same quantity in their order and one of them checked out:
    
    1. If product quantity gets zero a product out of stock message gets displayed
    2. If product quantity gets is less than the demanded the user get a message telling him that the quantity is not enough
4. If user to be deleted:
   
    1. If he has an order a message is sent as order should be deleted first due to the association
    2. If his order is deleted but he has a cart a message is sent as the cart should be deleted first due to the association
    3. If he doesn't have neither cart nor order he is deleted successfully



# Used in this project:

* JAVA language
* Maven
* MySQL DB
* JPA

# Data format

you can get to see data as an XML or JSON

# You can apply requests through POSTMAN collection file

# ER Diagram
![](/ER_DIAGRAM.png)
