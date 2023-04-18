1. From the Main menu, go to `Audience`
1. Form there on the left panel select `Visit Portal` under `Developer Portal`

### Change the provider Name
1. Go back to you admin portal, into `Content` select the `Main layout`
1. Remplace the `{{ provider.name }} ` in the `navbar-brand` around line 45 by 
    ``` <img src="https://www.redhat.com/profiles/rh/themes/redhatdotcom/img/logo.png" style=“width:150px;height:30px;border:0;” alt="{{ provider.name }}"> ```
1. Click Save
1. Click Publish

### Change the Echo Name for CoffeeShop and the application plan

1. Go to `Homepage`
1. In the page user CTRL+F to search `Echo` replace by `CoffeeShop`
:exclamation: We don't need to change the url

1. Go to line 124, replace to `api plan` in the application for
    ``` provider.services.api.coffeeshop.application_plans```
1. Click Publish
