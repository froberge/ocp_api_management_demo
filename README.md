# OpenShift API Management Demo



## Goal

In this Demo we will learn how to setup an API Manger and and API Gateway inside OpenShift. In order to lean the different part contain in the solutions.

3Scale consiste of 2 main component, 
  1. The API Manager
  2. The API Gateway

## Requirement.

In orther to do this demo you need an `OpenShift` with this running.

* `The product service` which reprensent your API backend for the coffee shop Product business line. The product Service was develop using Quarkus.
  * This service required a postgres database.
  * The schema creation script can be found [here](dbscripts/product-schema/createInsertProduct.sql)
  * The service uses a secret to connect to the database. Use [this template](manifest/postgresdb-secret-template.yaml)  to create your secret.
  * Requires the also the confimap found [here](manifest/productservice-cm.yaml)
  * You can generate see the OpenAPI file or use [this one](doc/open-api.json)
    * url-path/q/openapi?format=json
* The Red Hat Integration 3scale operator install.

* [OPTIONAL] Postman to test the API calls.

----

## Content

* [Demo how to get the APi Manager and Gateway running](#demo)
* [How to use the API Designer](/doc/APIDesigner.md)
* [How to use the Developer Portal](/doc/dev_portal.md)

---

## Demo

Red Hat 3scale API Management separates your APIs into two main groups:

* `Products:` Customer-facing APIs. Products facilitate the creation of strong and simplified offerings for API consumers.

* `Backends`: Internal APIs bundled in a product. Backends grant API providers the freedom to map their internal API organization structure to 3scale.

A product can contain multiple backends, and a backend can be used in multiple products. In other words, to integrate and manage your API in 3scale you need to create both:

A backend containing at least the URL of your API. The backend can optionally be configured with mapping rules, methods and metrics to facilitate reusability.
A product for which you define the application plans, and configure APIcast.

In this demo we will define the different step in setting up Red Hat OpenShift APi Management manually using these different step.

First we need to connect to the 3Scale Api admin portal
* Connect to your 3scale Admin 
    * In OpenShift in Networking select find the API Management URL. `zync-3scale-provider-*` by system-provider
    * Find the login credential under: `Wordload -> Secrets -> system-seed`


Step to create the Demo:

* [Creating an API Product](#creating-an-api-product)
* [Creating an Application Plan](#creating-an-application-plan)
* [Defining the Audience](#defining-the-audience)
* [Defining Methods and Mapping Rule](#defining-methods-and-mapping-rule)
* [Defining Policies](#defining-policies)
* [Creating a Backend](#creating-a-backend)
* [Publish the API in Staging for testing](#publish-the-api-in-staging-for-testing)
* [Inserting the Active Doc](#inserting-the-active-doc)

For added security, change the user-key authentication for OpenId Connect.
*[Secure API with RHSSO](#secure-api-with-rhsso)

---


### Creating an API Product
1. On the dashboard create a new Product Link
    ![Create Product](doc/images/create_product.png)
1. Use the following values
    * `Name:` Coffeeshop
    * `System Name:` coffeeshop
    * `Description:` The API for the coffee shop
1. Click `Create Product`
1. From the Overview Page, go to Integration->Setting to edit the API setting gateway
    ![product_setting](doc/images/product-setting.png)
1. Leave everything as default for now and click update product. This will cre

### Creating an Application Plan

Application plans establish rules like limits, pricing, and features for using your API. A developer's application accesses your API following the rules of the application plan. Configure application plans for specific target audiences with different sets of rules.

1. In `Apllication` select Application Plans.
1. `Create application Plan`
    ![application-plan](doc/images/application-plan.png)
1. Enter values:
    * `Name:` Coffee Shop Basic Plan
    * `System name:` coffeeshop_basic_plan
1. Click `Ctrea Application Plan`
1. In the list click the 3 dots, and select `Publish` for the new created Plan
    ![publish-plan](doc/images/publish-plan.png)

### Defining the Audience

1. In the Main Menu select `Audience`
    ![audience](doc/images/audience.png
1. On the default Account, select the `link under apps`
1. On that page click `Create Application`
    * `Product:` CoffeeShop
    * `Application plan:` Coffee Shop Basic Plan
    * `Name:` dev_coffeeshop_app
    * `Description:` Developer CoffeeShop Basic App

### Defining Methods and Mapping Rule

1. In the Main Menu select `Products`
1. Select `CoffeeShop`
1. Under Integration go to `Methods & Metrics`
1. Click `Add a method`
    * `Firendly name:` Get Coffees
    * `System name:` coffees_all
    * `Description:` Method to return all the coffees
1. Click Create Method
1. Go to Mapping Rules
1. Click on the edit pencil
1. Change the patern for `/product`
1. `Update Mapping Rule`

### Defining Policies

Red Hat 3scale API Management provides units of functionality that modify the behavior of the API Gateway without the need to implement code. These management components are know in 3scale as policies.

1. Under `Policies`, click `Add policy`
1. Click `CORS Request Handling` 
1. Rearrange the policies order
1. Edit the policies `CORS`
    ![edit_policie_cors](doc/images/edit_policies_cors.png)
    ![allow_methods](doc/images/allow_methods.png)
1. Update Policy Chain

### Creating a Backend

1. Click on `Backend`
1. Click `Add backend`
1. Click `Create new backend`
    * `Name:` CoffeeShop Backend
    * `System Name:` coffeeshop_backend
    * `Description:` CoffeeShop API Backend
    * `Private Base URL:` http://product-service.coffeeshop.svc:8080
    :exclamation: We are using the internal API service, as we are deploying our services inside the same OpenShift cluster.
1. Click `Create BackEnd`
1. Click `Add to Product`

### Publish the API in Staging for testing

1. Click on `Configuration` 9 Notice the warning sign beside
1. Select Promote v.X to Staging APIcast


### Inserting the Active Doc

In order for `backstage` to be able to the information it need access to the open API documentation.

1. From the `Dashboard` select the desired `Product`.
1. Once in the `Product` select `ActiveDocs`
1. Click `Create a new spec`
1. Fill in the information.  Use the json from [this file](doc/open-api.json)
1. Wait for a couple of minutes, backstage should update


### Secure API with RHSSO

1. Go to the `Dashboard Page`
1. On the `ConfeeShop API` go to `Integration`
1. Click on `Setting`
1. Change `Authentication for OpenID Connect`
    * Select Red Hat Single Sign-On
    * Enter the Connect Issuer Url
1. Select `Authorization Code Flow` & `Service Acounts Flow`
1. Select `As HTTP Headers`
1. `Update Product`
    :warning: You should se a exlamation point beside configuration appear
1. Go to Configuration and promote the `Staging APIcast`

### Create a Test App
1. Go to the Main Menu select `Audience`
1. Click on `developer`
1. Click on `Application`
1. Click on `dev_coffeeshop_app`
1. Click on `Add Random Key`
1. If the sync is desactive, Modify the user manually on Keycloak
1. test using RHSSO
----

### Using 3Scale toolbox to load and API

1. Make sure you have 3Scale toolbox install on you machine
1. Run the following [commmand](https://github.com/3scale/3scale_toolbox/blob/main/docs/openapi.md)
```
$ 3scale import openapi -d <destination> /path/to/your/spec/file.[json|yaml|yml]
```

Example:
```
3scale import openapi -d https://master_token@3[path to 3scale admin] [OpenAPI file]
```
----


## More Link

  * [RHOAM Sandbox](https://developers.redhat.com/developer-sandbox/activities/share-java--applications-openshift-api-management) let's yuo try the Red Hat OpenShift Application Manager Manage service.
  
  * [3Scale toolbox]((https://github.com/3scale/3scale_toolbox)) provide a ser of tool to manage you API using the command line[]
