package project.script;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Parser {


    private Map<String, Callback> types;
    public Parser(Map<String, Callback> t){
        types = t;
    }
    public void execute(String code) throws ParseException{
        //List<Object<?>> objects = new LinkedList<>();
        if(code==null || code.trim().equals("")|| code.trim().split("\n").length==0) throw new ParseException("пустой скрипт");
        for(String line: code.split("\n")){
            if(line.trim().equals("")) throw new ParseException("обнаружена пустая строка");
            if(line.trim().startsWith("#")) continue;
            String pair[] = line.trim().split(" ",2);
            if (pair.length!=2) throw new ParseException("неправильно задан объект, формат тип пробел данные(или !)");
            String typeKey = pair[0].trim();
            String data = pair[1].trim();
            
            if(!types.containsKey(typeKey)) throw new ParseException("нет такого типа "+ typeKey);
            
            String args[] = data.split(",");
        
            
            Map<String,String[]> argMap = new HashMap<>();
            if(!data.trim().equals("!")){
                for(String arg: args){
                    String argPair[] = arg.trim().split(" ",2);
                    
                    if(argPair.length==1) throw new ParseException("неправильно задан параметр " + argPair[0]);
                    if (argPair.length!=2) throw new ParseException("неправильно задан параметр");
                
                    String argKey = argPair[0].trim();
                    String argData = argPair[1].trim();
                    String argDataArray[] = argData.split(" ");
                    argMap.put(argKey, argDataArray);
                }
            }
           try{
               types.get(typeKey).call(argMap);
           } catch(NumberFormatException e){
               throw new ParseException("неправильный формат числа");
           }
                    
        }

    }

}
