package sample.hello.resources;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JacksonAnnotation;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONString;

@Path("/hello")
public class HelloResource {

	/**
	 * GET requests
	 */

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleGET() {
		return "No parameter";
	}

	@GET
	@Path("par")
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleGET_paramtere(@QueryParam("contact") String contact) {
		return "query parameter " + contact;
	}

	@GET
	@Path("parms")
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleGET_paramteres(@QueryParam("contact") String contact, @QueryParam("contact") String number) {
		return "Query paramters " + contact + " " + number;
	}

	@GET
	@Path("{contact}")
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleGET_pathParameter(@PathParam("contact") String contact) {
		return "Path parameter " + contact;
	}

	@GET
	@Path("{contact}/{number}")
	@Produces(MediaType.TEXT_PLAIN)
	public String simpleGET_pathParameter(@PathParam("contact") String contact, @PathParam("number") String number) {
		return "Path parameters " + contact + " " + number;
	}

	@GET
	@Path("Json")
	@Produces(MediaType.APPLICATION_JSON)
	public JsonRequest simpleGET_Johnson() {
		JsonRequest jn = new JsonRequest("adam", "luptka", 21);
		System.out.println(jn.toString());
		return jn;
	}

	@GET
	@Path("Jsons")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JsonRequest> simpleGET_Johnsons() {
		JsonRequest jn = new JsonRequest("adam", "luptka", 21);
		List<JsonRequest> arrayJson = new ArrayList<HelloResource.JsonRequest>();
		for (int i = 0; i < 10; i++) {
			jn = new JsonRequest("adam", "luptka", 21 + i);
			for (int j = 0; j < 20; j++) {
				Random randomno = new Random();
				jn.setAdres(Integer.toString(randomno.nextInt(100) + 1));
			}
			arrayJson.add(jn);
		}

		System.out.println(jn.toString());
		return arrayJson;
	}

	@GET
	@Path("/send")
	@Produces(MediaType.APPLICATION_JSON)
	public Student consumeJSONGET(@QueryParam("student") String studentString)
			throws JsonParseException, JsonMappingException, IOException {
		Logger logger = Logger.getLogger("name");
		logger.info(studentString);
		Student ob = new ObjectMapper().readValue(studentString, Student.class);
		// return Response.status(200).entity(output).build();
		return ob;
	}

	/**
	 * POST requests
	 */

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String simplePOST() {
		System.out.println("simpel post running");
		return "simple POST request";
	}

	@POST
	@Path("JsonPost")
	@Produces(MediaType.APPLICATION_JSON)
	public String simplePOST_Json() {
		System.out.println("simpel post running Json");
		return new String("sdfsdf");
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String formPost(@FormParam("id") String id, @FormParam("name") String name) {

		return "idem z formu " + id + " " + name;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, "text/json", "application/*+json" })
	public Response formPost(JsonRequest jr) {

		System.out.println(jr);
		return Response.status(200).entity(jr).build();

	}

	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Student consumeJSON(Student student) {

		String output = student.toString();

		// return Response.status(200).entity(output).build();
		return student;
	}

	@POST
	@Path("/send/jsons")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> consumeJSONS(final Student[] json) {

		for (int i = 0; i < json.length; i++) {
			System.err.println(json[i].toString());
		}

		// String output = students[0].toString();
		List<Student> students1 = new ArrayList<Student>();
		for (int i = 0; i < 10; i++) {
			students1.add(new Student("sdf", "sdfsfd", 10, 1));
		}
		// return Response.status(200).entity(output).build();

		return Arrays.asList(json);
	}

	/**
	 * PUT requests
	 */

	/**
	 * DELETE requests
	 */
	@XmlRootElement
	public class JsonRequest implements Serializable {
		private String name;
		private String surname;
		private int age;

		private List<String> adress = new ArrayList<String>();

		public JsonRequest() {

		}

		public JsonRequest(String name, String surname, int age) {

			this.name = name;
			this.surname = surname;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public void setAdres(String adress) {
			this.adress.add(adress);
		}

		public List<String> getAdress() {
			return adress;
		}

		public void setAdress(List<String> adress) {
			this.adress = adress;
		}

		@Override
		public String toString() {
			return "JsonRequest [name=" + name + ", surname=" + surname + ", age=" + age + "]";
		}

	}

}
