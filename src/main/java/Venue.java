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
}
