An example project where we have taken a Java library ([java-xirr](https://github.com/RayDeCampo/java-xirr)) and expose it as a REST service using JAX-RS.  Then the service is dockerized using the maven dockerfile plugin.

On the client side, we create a separate client using the Node.js connect server in order to illustrate various issues with CORS when utilizing REST services.

More details at http://labnotes.decampo.org/2017/08/19/jaxrs-microservices.
