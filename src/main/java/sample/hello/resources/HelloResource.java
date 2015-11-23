package sample.hello.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {
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
			for (int j = 0; j < 100; j++) {
				jn.setAdres(Integer.toString(i));
			}
			arrayJson.add(jn);
		}

		System.out.println(jn.toString());
		return arrayJson;
	}

	public class JsonRequest {
		private String name;
		private String surname;
		private int age;

		private List<String> adress = new ArrayList<String>();

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
