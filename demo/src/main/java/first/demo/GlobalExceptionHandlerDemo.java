package first.demo;

public class GlobalExceptionHandlerDemo extends RuntimeException {
    public GlobalExceptionHandlerDemo(){
        super("Demo details not found");
    }

    public GlobalExceptionHandlerDemo(Long id){
        super("Demo details not found with id "+ id);
    }
}
