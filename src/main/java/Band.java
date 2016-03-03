import org.sql2o.*;
import java.util.List;

public class Band {
  private int id;
  private String band_name;

  public Band(String name){
    band_name = name;
  }

  public int getId(){
    return id;
  }

  public String getBandName(){
    return band_name;
  }

  @Override
  public boolean equals(Object newBand){
    if (newBand instanceof Band){
      Band otherBand = (Band) newBand;
      return this.getBandName().equals(otherBand.getBandName()) &&
      this.getId() == otherBand.getId();
    } else {
      return false;
    }
  }

  public static List<Band> all(){
    String sql = "SELECT * FROM bands";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .executeAndFetch(Band.class);
    }
  }

  public void save(){
    String sql = "INSERT INTO bands (band_name) VALUES (:band_name)";
    try(Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
        .addParameter("band_name", band_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Band find(int id){
    String sql = "SELECT * FROM bands WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Band.class);
    }
  }

  public void delete(){
    String sql = "DELETE FROM bands WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void update(String newBandName){
    band_name = newBandName;
    String sql = "UPDATE bands SET band_name=:band_name WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("band_name", band_name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  
}
