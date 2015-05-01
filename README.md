# chat-backend
internet messaging backend with spring jms

---
First option for getting messages by ajax:

http://admin:admin@localhost:8161/api/message

destination=queue://internet-chat
oneShot=true

http-header:
selector: to='me'
