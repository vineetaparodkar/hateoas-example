# HATEOAS Notes

## Notes

- When fetching data from a table you probably implement your response object as a List<Object>. This works perfectly fine if it is a small data set.
When you have a data set that is 'large', you do not want to send the data to the client all at once. Imagine you have 100k rows in your table and each row has 10 columns. If the user is only vieweing maybe 50 rows in the UI(our client) at a specific time, why send 100k rows?
Unless it a specific export functionality, you shouldn't send back all the data to the user. Instead you should send a subset of the data only.
At the same time, we do not want to restrict the user from seeing all the data. If the user wants to add a filter or sort some data out of these 100k rows, he should be able to do it.
From a front end perspective, if the user clicks on the next page, they will do another call to the backend to get the next set of data.
This is where Pagination + Filtering + Sorting becomes very very helpful.
Hateoas also provides links to other pages which is extremely helpful as we will see.


- we need to use PagingAndSortingRepository repository which provides methods to retrieve entities using the pagination and sorting abstraction. It is important because we do not want to rewrite the JPA queries to read data in paging fashion, as it is available just by implementing this simple interface.

 ```   import org.springframework.data.repository.PagingAndSortingRepository;
    import com.howtodoinjava.rest.entity.AlbumEntity;

    public interface AlbumRepository 
    extends PagingAndSortingRepository<AlbumEntity, Long>{
    
    }
```
- To enable automatic pagination links, we must use PagedModel provided by spring hateoas module which helps in creating representations of pageable collections.
PagedResourcesAssembler accepts the JPA entities list, and convert it to PagedModel.
Additionally, we can use RepresentationModelAssembler to convert JPA entities into CollectionModel having custom resource representation.



## References

- https://howtodoinjava.com/spring5/hateoas/pagination-links/

- https://dev.to/markbdsouza/paginationwith-hateoas-filtering-sorting-with-spring-boot-and-jpa-1mpp
