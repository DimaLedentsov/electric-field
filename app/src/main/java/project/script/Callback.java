package project.script;

import java.util.Map;

public interface Callback {
    public void call(Map<String,String[]> args) throws ParseException;
}
