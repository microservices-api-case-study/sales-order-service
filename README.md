# Sales Order Service (Composite)

## Operations

1. Sales order customer – event subscription
	a. When a “CustomerCreated” event is published, sales order service needs to subscribe to it. Fetch the customer details(customer id, email, first name and last name) and insert it into the local customer table.

	Table: Customer_SOS (cust_id, cust_first_name, cust_last_name, cust_email)

2. Create Order – create an order and return an order id
	Post url: http://port/service3/orders

	Input: Order Description, Order Date, customer id, list of item names
	Output: Order Id

	a. validate customer by verifying the table “customer_sos” with cust_id.
	b. validate items by calling item service with item name
	c. create order by inserting the order details in order table and items for the order details in the order_line_item table.

	Table: 
	1. sales_order – id, order_date, cust_id, order_desc, total_price
	2. order_line_item – id, item_name, item_quantity, order_id

