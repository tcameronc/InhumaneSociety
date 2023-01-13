Google doc with pictures : https://docs.google.com/document/d/1p14ITdkbtSMM0hpLY5SUtwyWwYTebQLfqq6-MV0kS4w/edit

The InhumaneSociety is a java program composed in netbeans that uses a SQL database for CRUD operations. It uses mock data designed to resemble an animal shelter.

Guest login goes to the guest page where one can see, but not interact with, the database.

User Login requires a valid user from the credentials table to login. 
You can either make your own user using the sign up page below, or use:

Username: 1110 
Password: 100

Sign up with a valid User, Password, and ID. The username and ID must be numeric.

The main search page. Shows all entries in the database by default. The user can filter these results by specifying a column in the left box and a specific parameter in the right.

For example, filtering the name field with the ‘test’ parameter returns all animals who are named, “test”.

The add page. Users can add animals using the fields to the right. SQL triggers are used to keep data integrity. For example, only one character can be put into the sex column. 

The update page. Allows the user to alter values that would be altered often in a real scenario. Click the entry, then fill in the fields to the right. 

Alternatively, click the entry and click the delete button to delete it.

The user activity log. Records login events as well as all CRUD operations performed by the user sorted by the time it was performed.

The inhumanesocietydb uses several triggers to maintain the integrity of the data in the tables. 

This is one of seven triggers responsible for populating the logtable above. It activates after a user adds to the table.

This trigger checks inserted data for the correct characteristics. This will reject invalid data if the user attempts to insert it using the program.

Coming soon…

Employee table w/add/update/delete functionality.
Trigger to prevent user from *updating* to invalid data.
Allow all legal characters in user sign up
