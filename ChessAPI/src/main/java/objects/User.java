package objects;
import DTO.DTOUser;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;

@Path("/users")
public class User {

    Gson g = new Gson();
    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("param") String name) {
        String result = name;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/s3","root","banaanmysql1234");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users where username = '" + name + "'");
            while(rs.next())
                result = rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3);
                con.close();
        }catch(Exception e){ System.out.println(e);}
        return result;
    }

    @POST
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String post(DTOUser user) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/s3","root","banaanmysql1234");
            String query = " insert into users (username, userpassword)"
                    + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, user.getName());
            preparedStmt.setString (2, user.getPassword());

            // execute the preparedstatement
            preparedStmt.execute();
            con.close();
        }catch(Exception e){ System.out.println(e);}
        return "user " + user.getName() + " has been added.";
    }
    @GET
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public String GetAccount(DTOUser u) {
        DTOUser resultU = new DTOUser("Not found.", "Not found.");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/s3","root","banaanmysql1234");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users where username = '" + u.getName() + "'and userpassword = '"+ u.getPassword() + "'");
            while(rs.next())
                resultU = new DTOUser(rs.getInt(1),rs.getString(2),rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
        return g.toJson(resultU);
    }
}