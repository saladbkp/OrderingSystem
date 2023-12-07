# Ordering Restaurant System
JAVA -- Assignment APU

- Role: customer, vendor, runner, admin

First update: User Interface

```
20231006
TextFunction -> readtext
Login

20231008
AdminVendorView
AdminCustomerView
CustomerTopUpView

20231009
TopUp + view customer 
runner view

20231010
add vendor 

20231012
add read modify delete function -> vendor /
set width as global par

20231015
admin done

20231118
customer bind
page navigation
customer menu + vendor food
- order not done yet
<<<<<<< HEAD

20231126
customer: 
paybutton backbutton size + function 
sum of cost 
order -> reorder
review -> all + add selection for runner ????
credit -> show balance
noti -> addlistbox 
add title
done eliminate action column 
done order input 

ask
review add selection for runner wdyt 
noti pattern

vendor:
item add
history read -> same as loading order
load customer review 

ask
noti & order status ???? wdyt 

runner:
review need to do a common txt
revenue do a common transaction txt (for all user)
history from order 
task tab let me think 2 

```

Second version update: simple function for all user

```
20231128
done review update 
done customer credit -> make it looks better

customer missing:
done menu calculate total sub total
reorder
request top up: beside transcation add listbox for pending top up
noti: display done (send and receive simultaneously not yet)

vendor missing:
done item: add remove read update 
revenue ???
status -> make a active order txt

runner missing:
task -> active order 
revenue
```

Interact with each roles

```
20231201 
customer: pay+back(back to ori?), top up, reorder 
loading spin
vendor: can accept or decline 
20231203
order withour runner done 
-> random choose runner (not yet)
done vendor can accept and decline order
can read order, but cannot update (model issue)????
20231205
ordering flow done ?
do the payment section ....
do top up section ....

to do list
done runner task clear previous task
done customer back to payment page 
done receive noti, everytime change ... txt format not same 
done register add to user.txt
done need clear pending 
done payment 
done upadate credit
done top up 
done receipt not check service type
done admin notification 
done order update runner id
done reorder (small bug: cannot make a new order)
done print receipt after order

order history + filtering features ?
```

List OOP code

```
example link for explaination: 
https://raygun.com/blog/oop-concepts-java/#:~:text=Abstraction%2C%20encapsulation%2C%20polymorphism%2C%20and,association%2C%20aggregation%2C%20and%20composition.

1. abstraction
abstract 
- CommonAttrs using abstract class for **Users,Customers,Vendors,Runners**
interface 
- IOperation for function Dao : AddCustomerDao,AddNotiDao,AddRunnerDao,AddTransactionDao,AddVendorDao
explain diff between abstract and interface 

2. encapsulation
Restricts direct access to data members (fields) of a class
semua class I used encapsulation: those got getter and setter 
like: private String name;

3. inheritance 
A class (child class) can extend another class (parent class) by inheriting its features
Users extends CommonAttrs
Customers extends CommonAttrs
Vendors extends ...
Runners extends ...
Notifications extends TargetAttrs
Transactions extends TargetAttrs
Reviews extends TargetAttrs
...

4. polymorphism
method overloading: AddItemDao
public List<Items> findDataByItem(){
	return this.itemarray;
}
public List<Items> findDataByItem(String id) {
	List<Items> findarray = this.itemarray.stream().filter(x->x.getItemId().equals(id)).toList();
	return findarray;
}
same function name, but pass diff parameter, diff function

5. Association
manual bind 2 class together
I didnt bind 2 class directly but attribute all from them
pendingOrder -> Order
pendingTask -> Task 

6. Aggregation
runner in Order?
runner without Order pun boleh run 

7. Composition
a strong “whole-part” relationship between two classes
menu class cannot run without vendor 
public Menus(Vendors vendor) {

	this.vendor = vendor;
	this.deliveryFee = 5;
}
Task cannot run without order
public Tasks(Orders order){
    this.OrderID = order.getOrderId();
    this.VendorID = order.getVendorId();
    this.RunnerID = order.getRunnerId();
    this.CustomerID = order.getCustomerId();
    this.TotalCost = 5.00;
    this.Time = order.getDatetime();
    this.Status = "Preparing Food";
}
```

Extra features 

```
com.service
pop out notification: receiveNotiService
- function: can pop out dialog for the noti service 
simultaneously communication using socket: Client,ClientHandler,Server 
- function: when up server, we can know how many client online
- function: can send and receive msg simultaneously

com.tools
png in datatable: ImageRender
- function: convert path to png
- additional: upload png by filtering format: JPEGImageFileFilter
filter table row: TableRowFilter
- function: filter revenue by monthly 
button in datatable: TableWithButtons
- function: add food in cart using button
- function: can add and remove food (show in quantity)
load txt to arraylist: TextFunction
- function: main reading txt function 
- function: public <T> ArrayList<T> readfile(Class<T> clazz) using T for all object
fixed dateformat: DateFormat formatter
- function: fixed dateformat to 05-12-2023 10:21
fixed double format: DecimalFormat decimformatter
- function: only remain 2 decimal point 
write and append file: writeFile, appendFile
- function: common writefile tools 
```

 
