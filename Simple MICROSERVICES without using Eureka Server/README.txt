simple microservices.
first microservice is of User 
second is of contact

Resttemplate implemented in User, which will further make another API call,
and this will be made to the contact microservice.
from there, the contact details of the particular user will be appneded to the user details
while fetching a user details.

this is WITHOUT using eureka server.