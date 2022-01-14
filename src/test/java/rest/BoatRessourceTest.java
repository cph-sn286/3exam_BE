package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AuctionDTO;
import dtos.BoatDTO;
import entities.Auction;
import entities.Boat;
import entities.Role;
import entities.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

import io.restassured.response.Response;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;


class BoatRessourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Boat b1, b2;
    private static Auction a1, a2;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    private static String securityToken;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);

    }

    @BeforeAll
    public static void setUpClass() {
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();

        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;

        setupUsers();


    }

    @AfterAll
    public static void closeTestServer() {

        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();

    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        b1 = new Boat("bådbrand", "noget", "sejl", 1999);
        b2 = new Boat("bådgatti", "andet", "torsk", 1990);
        a1 = new Auction("a1", "dqweq", "as", "nu");
        a2 = new Auction("fix", "fix", "det", "nu");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Boat.deleteAllRows").executeUpdate();
            em.createNamedQuery("Auction.deleteAllRows").executeUpdate();
            em.persist(a1);
            em.persist(a2);
            em.persist(b1);
            em.persist(b2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }


    }

    @Test
    public void testServerIsUp() {
        given().when().get("/boat").then().statusCode(200);
    }


    @Test
    void getAllAuctions() {
        Response response = RestAssured.get("http://localhost:8080/SP1_war_exploded/api/auction/all");

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }

    @Test
    void GetBoatByOwner() {
        login("user", "test");
        given()
                .contentType(ContentType.JSON).header("x-access-token", securityToken)
                .get("http://localhost:8080/SP1_war_exploded/api/boat/getboatbyowner/kurt")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());

    }


    @Test
    void deleteAuctions () {

        login("admin", "test");
        given()
                .contentType(ContentType.JSON).header("x-access-token", securityToken)
                .delete("/auction/deleteauction/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }

    @Test
    void createAuction() {
        AuctionDTO auctionDTO = new AuctionDTO("auctionName", "auctionDate", "auctionTime", "auctionLocation");

        login("admin", "test");
        given()
                .contentType(ContentType.JSON).header("x-access-token", securityToken)
                .body(GSON.toJson(auctionDTO))
                .post("/auction/createauction")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());


    }
    @Test
    void createBoat() {
        BoatDTO boatDTO = new BoatDTO("boatbrand", "supermakebåd", "minbåd", 23);

        login("admin", "test");
        given()
                .contentType(ContentType.JSON).header("x-access-token", securityToken)
                .body(GSON.toJson(boatDTO))
                .post("/boat/createboat")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());


    }


    private static void setupUsers() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //Delete existing users and roles to get a "fresh" database
            em.createQuery("delete from User").executeUpdate();
            em.createQuery("delete from Role").executeUpdate();

            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            User user = new User("user", "test");
            user.addRole(userRole);
            User admin = new User("admin", "test");
            admin.addRole(adminRole);
            User both = new User("user_admin", "test");
            both.addRole(userRole);
            both.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.persist(both);
            //System.out.println("Saved test data to database");
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        securityToken = given()
                .contentType("application/json")
                .body(json)
                //.when().post("/api/login")
                .when().post("/login")
                .then()
                .extract().path("token");
        //System.out.println("TOKEN ---> " + securityToken);
    }

}