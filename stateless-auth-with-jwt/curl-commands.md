


{
"username":"user1",
"password":"password",
}

curl -X POST http://localhost:8080/authenticate \
-H "Content-Type: application/json" \
-d '{
  "username": "user1",
  "password": "password"
}'


curl -X GET http://localhost:8080/todos \
-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZW1vMiIsInN1YiI6InVzZXIxIiwiYXVkIjpbImF1ZGllbmNlLWV4YW1wbGUiXSwiaWF0IjoxNzUwNDE1OTY2LCJleHAiOjE3NTA0MTk1NjYsImp0aSI6IjgyNzgxNjk5LTBiODMtNGUxNS1hMDk1LWM2ZWI1YzBlNmMzZSJ9.MDyoXRlWU19BxUVhN_dzLvqsaVEzuNuOZtTLnKYEJSc"
