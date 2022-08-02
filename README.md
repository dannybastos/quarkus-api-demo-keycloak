# quarkus-api-demo-keycloak

This project uses Quarkus, the Supersonic Subatomic Java Framework.

## Requirements

- docker
- docker-compose

## Data Model

```

| Account       |   | Transaction       |   | Audit             |
-----------------   ---------------------   ---------------------
| accountNumber |   | date              |   | transactionFrom   |
| balance       |   | account           |   | transactionTo     |
                    | amount            |   | dateTime          |
                    | transactionType   |

```



## Starting the application

Just run:
```
docker-compose up -d
```

Import keycloak settings run:

```
docker cp demo-realm-export.json quarkus-api-demo-keycloak-keycloak-1:/opt/keycloak-18.0.2/bin/


docker exec -it quarkus-api-demo-keycloak-keycloak-1 sh -c 'sh /opt/keycloak-18.0.2/bin/kc.sh import --file /opt/keycloak-18.0.2/bin/demo-realm-export.json'
```

Add `keycloak` to localhost in your /etc/hosts ( needs **sudo**)

``` 
echo '127.0.0.1 localhost keycloak' >> /etc/hosts
```

create 2 accounts (run 2 times)
```
curl -vkX POST 'http://localhost:9080/api/accounts' \
--header 'Content-Type: application/json' \
--data-raw '{
    "balance": 10
}'
```

list accounts
```
curl -vkX GET 'localhost:9080/api/accounts' | jq
```

perform a transfer
```
curl -vkX POST 'http://localhost:9080/api/accounts/2/credit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fromAccount": 1,
    "amount": 5
}'
```

The list transactions method is protected by keycloak, and the user needs  the `transactions` role.

To get the access token
```
export access_token=$(\
    curl -kX POST http://keycloak:8080/realms/demo/protocol/openid-connect/token \
    --user quarkus-api-demo-keycloak:AEfjcqkgqr9GBsaoWrbO4SplI8hoblkr \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=user01&password=111&grant_type=password' | jq --raw-output '.access_token' \
 )
 ```

 List transactions

 ```
curl -kX GET 'localhost:9080/api/accounts/1/transactions' --header 'Authorization: Bearer '$access_token | jq
```
