# Dropwizard Demo

This repo serves as a very basic example of a Dropwizard application.

The API represents a very very basic Product model.

    cat | http put :8080/products/1
    {
      "name": "Product Name",
      "description": "My product description",
      "stock": 123,
      "currency_code": "GBP",
      "price": 123.45
    }
    HTTP/1.1 204 No Content
    Date: Thu, 25 Feb 2016 16:52:31 GMT
    Location: http://localhost:8080/products/1

    http get :8080/products/1
    HTTP/1.1 200 OK
    Content-Length: 165
    Content-Type: application/json
    Date: Thu, 25 Feb 2016 16:53:29 GMT

    {
        "currency_code": "GBP",
        "description": "My product description",
        "display": {
            "in_stock": true,
            "price": "£123.45"
        },
        "id": 1,
        "name": "Product Name",
        "price": 123.45,
        "stock": 123
    }

    http get :8080/products
    HTTP/1.1 200 OK
    Content-Encoding: gzip
    Content-Length: 152
    Content-Type: application/json
    Date: Thu, 25 Feb 2016 16:54:56 GMT
    Vary: Accept-Encoding

    [
        {
            "currency_code": "GBP",
            "description": "My product description",
            "display": {
                "in_stock": true,
                "price": "£123.45"
            },
            "id": 1,
            "name": "Product Name",
            "price": 123.45,
            "stock": 123
        },
        {
            "currency_code": "GBP",
            "description": "My product description",
            "display": {
                "in_stock": true,
                "price": "£123.45"
            },
            "id": 2,
            "name": "Product Name",
            "price": 123.45,
            "stock": 123
        }
    ]

    http delete :8080/products/1
    HTTP/1.1 200 OK
    Content-Length: 0
    Date: Thu, 25 Feb 2016 16:55:26 GMT

## Usage

    mvn package
    java -jar target/productdemo-1.0-SNAPSHOT.jar server config.yml

## Noteworthy Features

- Custom health checks
- Application lifecycle hooks
- Simple (de)serialization
- Code over configuration
