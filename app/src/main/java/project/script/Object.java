package project.script;

public class Object<T>{
    T object;
    Type type;
    public Object(T obj){
        object = obj;
    }
    public T cast(T obj){
        return (T)obj;
    }
}
