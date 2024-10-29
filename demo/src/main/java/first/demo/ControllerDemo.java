package first.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demo/api/")
public class ControllerDemo {

    @Autowired
    private RepositoryDemo repositoryDemo;

    // for storing the data in database
    @PostMapping("/add")
    public EntityDemo addDemo(@RequestBody EntityDemo entityDemo){
        return repositoryDemo.save(entityDemo);
    }

    // for fetching all the data from database
    @GetMapping("/get/all")
    public List<EntityDemo> getAllDemo(){
        return repositoryDemo.findAll();
    }

    // for deleting the particular file from database
    @DeleteMapping("/delete/{id}")
    public String deleteByIdDemo(@PathVariable Long id){
        repositoryDemo.deleteById(id);
        return "data deleted with id "+id;
    }

    // update the particular data
    @PutMapping("/update/{id}")
    public EntityDemo updateDemo(@PathVariable Long id, @RequestBody EntityDemo entityDemoDetails) throws GlobalExceptionHandlerDemo {
        EntityDemo entityDemo = repositoryDemo.findById(id).orElseThrow(()->new GlobalExceptionHandlerDemo(id));

        // if details present in database
        // update the details
        entityDemo.setName(entityDemoDetails.getName());
        entityDemo.setAddress(entityDemoDetails.getAddress());
        entityDemo.setCountry(entityDemoDetails.getCountry());
        entityDemo.setState(entityDemoDetails.getState());
        entityDemo.setSalary(entityDemoDetails.getSalary());
        repositoryDemo.save(entityDemo);
        return entityDemo;
    }

    // get the demo details by id
    @GetMapping("/getDemo/{id}")
    public ResponseEntity<?> getDemoDetailsById(@PathVariable Long id) throws GlobalExceptionHandlerDemo {
      Optional<EntityDemo> entityDemo = repositoryDemo.findById(id);
      if(entityDemo.isPresent()){
          return new ResponseEntity<>(entityDemo, HttpStatus.FOUND);
      }
      return new ResponseEntity<>(new GlobalExceptionHandlerDemo(id).getMessage(), HttpStatus.NOT_FOUND);
    }
}
