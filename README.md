# Sales Order Service (Composite)

## Operations

1. Sales order customer – event subscription
	a. When a “CustomerCreated” event is published, sales order service needs to subscribe to it. Fetch the customer details(customer id, email, first name and last name) and insert it into the local customer table.

Table: Customer_SOS (cust_id, cust_first_name, cust_last_name, cust_email)

