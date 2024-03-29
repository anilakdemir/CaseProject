# How to run
Inside of the ```ecommerce``` directory run the command below:
```bash
  mvn spring-boot::run
```

# Endpoints

* Campaign
  * GET /campaign
  * GET /campaign/{id}
  * POST /campaign
    ```
      Example:
        {
          title: "Campaign",
          amount: 15.0,
          minimumQuantity: 250.0,
          discountType: "AMOUNT"
          category: {
            id:1
          }
        }
    ```
* Category
  * GET /category
  * GET /category/{id}
  * POST /category
    ```
      Example:
        {
          title:"Category",
          parentCategory: {
            id:1
          }
        }
    ```
* Coupon
  * GET /coupon
  * GET /coupon/{id}
  * POST /coupon
    ```
      Example:
        {
          discountAmount: 10.0,
          minimumAmount: 200,
          discountType: "RATE"
        }
    ```
* Product
  * GET /product
  * GET /product/{id}
  * POST /product
    ```
      Example:
        {
          title: "Product",
          price: 100.50,
          category: {
            id:1
          }
        }
    ```
* ShoppingCart
  * GET /cart
  * GET /cart/{id}
  * GET /cart/{id}/orders
  * GET /cart/{cartId}/orders/{orderId}
  * POST /cart/{cartId}/applyCampaignDiscount
  ```
    Example:
    [
      {
      id:1
      },
      {
        id:2
      }
    ]
  ```
  * POST /cart/{cartId}/applyCouponDiscount
    ```
    Example:
    {
      id:1
    }
  ```
  * POST /cart
  * DELETE /cart/{id}
  * POST /cart/{id}/order
