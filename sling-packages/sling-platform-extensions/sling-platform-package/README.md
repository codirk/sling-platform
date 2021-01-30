```
        <dependency>
            <groupId>org.apache.sling</groupId>
            <artifactId>org.apache.sling.pipes</artifactId>
            <version>2.0.2</version>
        </dependency>


```
            http://localhost:8080/system/console/sc
```

def plumber = getService("org.apache.sling.pipes.Plumber");
def plumber = osgi.getService(org.apache.sling.pipes.Plumber);

plumber.newPipe(resourceResolver)
.echo("/content")
.run();




import org.apache.sling.api.resource.ResourceResolverFactory
import org.apache.sling.api.resource.ResourceResolver
import org.apache.sling.api.resource.Resource

ResourceResolverFactory rrf = osgi.getService(ResourceResolverFactory.class)
ResourceResolver resolver = rrf.getAdministrativeResourceResolver(null)

def plumber = osgi.getService(org.apache.sling.pipes.Plumber);

try{

  /*
  plumber.newPipe(resolver)
            .echo("/content")
            .run();


    final String queryString = "/jcr:root//*[jcr:contains(., '')] order by @jcr:score";

*/
      final String queryString = "select * from nt:base";



    final Iterator<Resource> i = resolver.findResources(queryString, "sql");
    i.each{
        println it
    }
}finally{
    resolver?.close()
}
       
```