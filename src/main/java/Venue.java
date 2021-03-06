import org.sql2o.*;
import java.util.List;

public class Venue {
  private int id;
  private String venue_name;

  public Venue(String name) {
    venue_name = name;
  }

  public int getId(){
    return id;
  }

  public String getVenueName(){
    return venue_name;
  }

  @Override
  public boolean equals(Object newVenue) {
  if (newVenue instanceof Venue) {
    Venue otherVenue = (Venue) newVenue;
    return this.getVenueName().equals(otherVenue.getVenueName()) &&
      this.getId() == otherVenue.getId();
    } else {
      return false;
    }
  }

  public static List<Venue> all(){
  String sql = "SELECT * FROM venues";
  try(Connection con = DB.sql2o.open()){
    return con.createQuery(sql)
    .executeAndFetch(Venue.class);
    }
  }

  public void save(){
    String sql = "INSERT INTO venues (venue_name) VALUES (:venue_name)";
    try(Connection con = DB.sql2o.open()){
      this.id = (int) con.createQuery(sql, true)
      .addParameter("venue_name", venue_name)
      .executeUpdate()
      .getKey();
    }
  }

  public static Venue find(int id){
    String sql = "SELECT * FROM venues WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Venue.class);
    }
  }

  public void delete(){
    String sql = "DELETE FROM venues WHERE id=:id";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Band> getBands(){
  String sql = "SELECT DISTINCT ON (id) bands.* FROM venues JOIN bands_venues ON (venues.id = bands_venues.venue_id) JOIN bands ON (bands_venues.band_id = bands.id) WHERE venues.id=:id";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Band.class);
    }
  }

  public void addBand(int bandId){
    String sql = "INSERT INTO bands_venues (venue_id, band_id) VALUES (:venue_id, :band_id)";
    try(Connection con = DB.sql2o.open()){
      con.createQuery(sql)
        .addParameter("venue_id", id)
        .addParameter("band_id", bandId)
        .executeUpdate();
    }
  }
}
