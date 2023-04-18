# Demo for API Designer

### From new

1. Select NEW API( OpenAPI 3)
    * `Name:` Coffeeshop
    * `Description:` CoffeeShop API

1. Create a Data Type
    * `Name:` product
    * `Json Example:`
        ```
        {
            "id": 1,
            "name": "Cortado",
            "description": "Description Cortado",
            "size": "small",
            "price": "3.50"
        }
        ```
1. Add a path `product`
    * Add reponse
    * Add media type and Aray + product
    * Add an example
    * Add a description. `Returns an array of location records`

1. Add another path `/product/{id}