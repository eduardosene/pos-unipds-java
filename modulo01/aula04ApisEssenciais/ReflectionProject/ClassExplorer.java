package modulo01.aula04ApisEssenciais.ReflectionProject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassExplorer {
   public static void exploreMetadata(Object o) throws Exception{
       System.out.println("----> Extraindo os atributos");
       for(Field f: o.getClass().getDeclaredFields()){
           System.out.println(f.getName() + ":" + f.getType().getName());
       }
       System.out.println("----> Extraindo os métodos");
       for( Method m: o.getClass().getDeclaredMethods()) {
           System.out.println(m.getName()+":"+m.getReturnType().getName());
       }
       System.out.println("----> Extraindo dados do objeto");
       for( Field f: o.getClass().getDeclaredFields() ){
         if(f.isAnnotationPresent(Visible.class)){
             f.setAccessible(true);
             System.out.println("Atributo visivel: " +f.getName()+" - "+f.get(o));
             f.setAccessible(false);
         }
         else {
             System.out.println("Atributo não visivel "+ f.getName());
         }
       }
       System.out.println("----> Executando metodos");
       for(Method m: o.getClass().getDeclaredMethods()){
           if(m.getName().startsWith("get")){
               System.out.println(m.getName() + " - valor:" + m.invoke(o, null));
           }
       }
   }
}
