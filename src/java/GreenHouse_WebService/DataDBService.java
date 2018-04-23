package GreenHouse_WebService;

import java.util.List; 
import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType; 


@Path("/DataDBService")

public class DataDBService {
    
     private static DataDaoDB dataDao = new DataDaoDB();
    
   @GET 
   @Path("/data") 
   @Produces(MediaType.APPLICATION_XML) 
   public List<Sensordata> getData(){ 
      return dataDao.getAllData();
   }  
   
   @GET 
   @Path("/dataJSON") 
   @Produces(MediaType.APPLICATION_JSON) 
   public List<Sensordata> getDataJSON(){ 
      return dataDao.getAllData();
   }  
   
   @GET 
   @Path("/dataHTML") 
   @Produces(MediaType.TEXT_HTML) 
   public String getDataHTML(){ 
      String res = "<HTML><HEAD><TITLE>data</TITLE></HEAD><BODY><TABLE>";
      for (Sensordata b : dataDao.getAllData()){
          res += "<TR><TD>"+b.getId()+"</TD><TD>"
                  +b.getTemperatur() +"</TD><TD>" 
                  +b.getBelysning()+"</TD><TD>"
                  +b.getEl() +"</TD><TD>"
                  +b.getLuftfuktighet()+"</TD><TD>"
                  +b.getTid()+"</TD><TD>"
                  +b.getSektorId()+"</TD></TR>";
      }
      res += "</TABLE></HTML>";
      return res;
   } 
   
   
   @GET 
   @Path("/data/{id}") 
   @Produces(MediaType.APPLICATION_XML) 
   public Sensordata getDataById(@PathParam("id") int id){ 
       Sensordata res = new Sensordata();
       for (Sensordata b : dataDao.getAllData()){
           if (b.getId() == id){
               res = b;
           }
       }
      return res;
   } 
   
   @GET 
   @Path("/dataJSON/{id}") 
   @Produces(MediaType.APPLICATION_JSON) 
   public Sensordata getDataByIdJson(@PathParam("id") int id){ 
       Sensordata res = new Sensordata();
       for (Sensordata b : dataDao.getAllData()){
           if (b.getId() == id){
               res = b;
           }
       }
      return res;
   } 
   
   @GET 
   @Path("/greenhouse_webservice.sensordata/{id}/delete") 
   @Produces(MediaType.APPLICATION_XML) 
   public Response deleteDataById(@PathParam("id") int id){ 
      Response res = new Response("Sensordata deleted", dataDao.deleteData(id));
      return res;
   } 
   
   @POST
   @Path("greenhouse_webservice.sensordata/add") 
   @Produces(MediaType.APPLICATION_XML) 
   public Response addData(Sensordata b){ 
       System.out.println("posting sensordata");
       Response res = new Response("Sensordata added", dataDao.addData(b));
       return res;
   } 
   
   @POST
   @Path("greenhouse_webservice.sensordata/add") 
   @Produces(MediaType.APPLICATION_XML) 
   public Response addData2(int i){ 
       System.out.println("posting sensordata");
      // Response res = new Response("Sensordata added", dataDao.addData(b));
       return new Response("Sensordata added", true);
   } 
   
   @POST
   @Path("greenhouse_webservice.sensordata/update") 
   public Response upsertData(Sensordata b){ 
       Response res = new Response("Sensordata updated", dataDao.updateData(b));
       return res;
   } 
}
