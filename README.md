## What is Validated monad?

The `Validated` monad is a monad that can be used to accumulate errors in a computation. It is
similar to the `Either` monad,
but it can accumulate multiple errors instead of short-circuiting on the first error.

## Why use Validated monad?

The `Validated` monad is useful when you want to accumulate errors in a computation. For example,
if you are validating a form and you want to show all the errors to the user at once, you can use
the `Validated` monad to accumulate all the errors and then display them to the user.

## Example of using the Validated monad

This repo contains a sample Spring Boot application that demonstrates how to use the `Validated`
monad to validate a form containing some person specific information and accumulate errors.

It consists of a REST endpoint that accepts a JSON payload containing some person identification
information and validates it using the `Validated` monad. If there are any errors, it returns a
`400` response with a JSON payload containing the errors. If the validation is successful, it
returns a `204` response.

## How it differs from a traditional imperative style of using jakarta.validation annotations ?

The traditional way of validating a form in a Spring Boot application is to use the
`jakarta.validation` annotations. While this approach is simple and easy to use, it has some
limitations. For example, it does not allow you to accumulate errors in a computation. If there are
multiple errors in the form, it will short-circuit on the first error and return a `400` response
with only that error.

Additionally, the `jakarta.validation` annotations are not composable. If you have multiple
validation rules that depend on each other, it can be difficult to express them using the
`jakarta.validation` annotations.

Moreover, the `jakarta.validation` annotations are not type-safe. If you make a mistake in the
validation logic, you will only find out at runtime when the validation fails. Also,
`jakarta.validation` annotations are not easily testable. You have to run the entire Spring Boot
application to test the validation logic whereas with the `Validated` monad, you can test the
validation logic in isolation.

Also the `jakarta.validation` annotations are using runtime reflection to validate the form
which can be slow and error-prone. The `Validated` monad, on the other hand, is a compile-time
construct that is type-safe and efficient.

Finally, it can be used with any framework, not just Spring.

## There is one subtle way with Spring to accumulate errors in a computation. Why not use that?

Spring provides a way to accumulate errors in a computation using the `BindingResult` class. You can
use the `BindingResult` class to accumulate errors in a form validation. However, the
`BindingResult`
class is not composable. If you have multiple validation rules that depend on each other, it can be
difficult to express them using the `BindingResult` class.

Additionally, using the `BindingResult` implies that you have correctly configured the
`@Valid` annotation on the controller method parameter. If you forget to add the `@Valid`
annotation, the validation will not be performed and you will not get any errors.

## Should we always use Validated monad for validation?

The `Validated` monad is a powerful tool for accumulating errors in a computation. However, it is
not
always necessary to use the `Validated` monad for validation. If you have a simple form with only a
few validation rules that do not depend on each other, you can use the `jakarta.validation`
annotations or the `BindingResult` class to validate the form.

## How to run the sample application?

To run the sample application, you need to have Java 21 and Maven installed on your machine. You can
run the application by executing the following command:

```shell
mvn spring-boot:run
```

This will start the Spring Boot application on port `9999`. You can then send a `POST` request to
`http://localhost:9999/api/person` with a JSON payload containing some person identification.