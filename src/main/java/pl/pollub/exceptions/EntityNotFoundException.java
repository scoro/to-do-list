package pl.pollub.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String className, int id){
        super(new StringBuilder("Entity ").append(className).append(" with id ").append(id).append(" not found").toString());
    }
}
