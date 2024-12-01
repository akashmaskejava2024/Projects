package com.nt;

public class Prod_Query {

	 static String Connection = "jdbc:mysql://localhost:3306/mydb";

	 static String add_prod = "insert into product (name,batch,price,stock,type) values(?,?,?,?,?)";

	 static String add_sales = "update product set stock = stock - 1 where name = ?";

	 static String return_prod = "upadte product set stock = stock + 1 where name = ?"; 
	 
	 
	 static String check_stock_with_lessthan_stock = "select * from product where stock < ?";
	 static String check_stock_with_lessthan_stock_count = "select count(*) as total from product where stock < ?";

	 static String check_stock_with_lessthan_price = "select id, name, batch, price, stock, type from product where price < ?";
	 static String check_stock_with_lessthan_price_count = "select count(*) as total from product where price < ?";

	 static String check_stock_with_greaterthan_price = "select id, name, batch, price, stock, type from product where price > ?";
	 static String check_stock_with_greaterthan_price_count = "select count(*) as total from product where price > ?";

	 static String check_stock_with_type = "select id, name, batch, price, stock, type from product where type = ?";
	 static String check_stock_with_type_count = "select count(*) as total from product where type = ?";

	 static String  check_prod_with_let  = "select id, name, batch, price, stock, type from product where name like ?";
	 static String check_prod_with_let_count = "select id, name, batch, price, stock, type from product where name like ?";


}