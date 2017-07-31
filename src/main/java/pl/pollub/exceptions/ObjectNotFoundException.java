package pl.pollub.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String className, int id){
        super(new StringBuilder("Entity ").append(className).append(" with id ").append(id).append(" not found").toString());
    }
}
