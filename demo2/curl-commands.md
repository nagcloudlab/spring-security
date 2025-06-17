


{
"username":"user1",
"password":"password",
}

curl -X POST http://localhost:8080/authenticate \
-H "Content-Type: application/json" \
-d '{
  "username": "user2",
  "password": "password"
}'




curl -X GET http://localhost:8080/todos \
-H "Authorization: Bearer eyJhbGciOiJub25lIn0.eyJpc3MiOiJtZSIsInN1YiI6InVzZXIyIiwiYXVkIjpbInlvdSJdLCJpYXQiOjE3NTAxNjA1NjMsImp0aSI6IjdiMjdjMDg1LWE3ZDgtNDMyZS05ZjdkLTQ2MGQyY2Q0OTI3OCJ9."
