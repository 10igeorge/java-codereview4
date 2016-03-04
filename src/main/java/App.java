import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/add-band", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newBand = request.queryParams("bandName");
      Band band = new Band(newBand);
      band.save();
      model.put("band", band);
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    post("/add-venue", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String newVenue = request.queryParams("venueName");
      Venue venue = new Venue(newVenue);
      venue.save();
      model.put("venue", venue);
      model.put("template", "templates/index.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/viewall.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("venues", Venue.all());
      model.put("template", "templates/viewall.vtl");
      return new ModelAndView (model, layout);
    }, new VelocityTemplateEngine());

    get("/bands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      model.put("venues", Venue.all());
      model.put("band", band);
      model.put("playedVenues", band.getVenues());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      String[] addedIds = request.queryParamsValues("checkVenue");
      ArrayList<Venue> addedVenues = new ArrayList<Venue>();
      if (addedIds != null) {
        for (String venueId : addedIds) {
          band.addVenue(Integer.parseInt(venueId));
          addedVenues.add(Venue.find(Integer.parseInt(venueId)));
        }
      }
      model.put("addedVenues", addedVenues);
      model.put("playedVenues", band.getVenues());
      model.put("band", band);
      model.put("venues", Venue.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/band/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Band band = Band.find(id);
      band.delete();
      model.put("band", band);
      model.put("bands", Band.all());
      model.put("template", "templates/viewall.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/venue/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(id);
      venue.delete();
      model.put("venue", venue);
      model.put("venues", Venue.all());
      model.put("template", "templates/viewall.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/venues/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(id);
      model.put("bandsHosted", venue.getBands());
      model.put("venue", venue);
      model.put("bands", Band.all());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(id);
      String[] addedIds = request.queryParamsValues("checkBand");
      ArrayList<Band> addedBands = new ArrayList<Band>();
      if (addedIds != null){
        for(String bandId : addedIds){
          venue.addBand(Integer.parseInt(bandId));
          addedBands.add(Band.find(Integer.parseInt(bandId)));
        }
      }
      model.put("addedBands", addedBands);
      model.put("bandsHosted", venue.getBands());
      model.put("venue", venue);
      model.put("bands", Band.all());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
