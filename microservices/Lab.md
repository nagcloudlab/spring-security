```bash
./gradlew build -x test
docker compose build
docker compose up -d
```

Acquiring access tokens


a. Acquiring access tokens using the client credentials grant flow
```bash
 curl -k https://writer:secret-writer@localhost:8443/oauth2/token -d grant_
type=client_credentials -d scope="product:read product:write" -s | jq .
```

```bash
 curl -k https://reader:secret-reader@localhost:8443/oauth2/token -d grant_
type=client_credentials -d scope="product:read" -s | jq .
```

Acquiring access tokens using the authorization code grant flow

````bash
https://localhost:8443/ oauth2/authorize?response_type=code&client_id=reader&redirect_uri=https:// my.redirect.uri&scope=product:read&state=35725.

curl -k https://reader:secret-reader@localhost:8443/oauth2/token \
 -d grant_type=authorization_code \
 -d client_id=reader \
 -d redirect_uri=https://my.redirect.uri \
 -d code=$CODE -s | jq .

 curl -k https://writer:secret-writer@localhost:8443/oauth2/token \
  -d grant_type=authorization_code \
  -d client_id=writer \
  -d redirect_uri=https://my.redirect.uri \
  -d code=$CODE -s | jq .

  ```


  Calling protected APIs using access tokens

  ````bash
  ACCESS_TOKEN=an-invalid-token
curl https://localhost:8443/product-composite/1 -k -H "Authorization:
Bearer $ACCESS_TOKEN" -i


ACCESS_TOKEN={a-reader-access-token}
curl https://localhost:8443/product-composite/1 -k -H "Authorization:
Bearer $ACCESS_TOKEN" -i

ACCESS_TOKEN={a-reader-access-token}
curl https://localhost:8443/product-composite/999 -k -H "Authorization:
Bearer $ACCESS_TOKEN" -X DELETE -i

```

