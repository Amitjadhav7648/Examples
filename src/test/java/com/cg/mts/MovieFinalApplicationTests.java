package com.cg.mts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cg.mts.controller.LoginController;
import com.cg.mts.pojo.Booking;
import com.cg.mts.pojo.Customer;
import com.cg.mts.pojo.Movie;
import com.cg.mts.pojo.Screen;
import com.cg.mts.pojo.Seat;
import com.cg.mts.pojo.Show;
import com.cg.mts.pojo.Theatre;
import com.cg.mts.pojo.Ticket;

@SpringBootTest
class MovieFinalApplicationTests extends AbstractTest {
	@Override
	@BeforeEach
	public void setUp() {
		super.setUp();
	}

	@Autowired
	LoginController loginController;
	
	  @Test public void addCustomer() throws Exception { String uri =
	  "/customer/add"; Customer cu=new Customer("Amit", "Salgare", "7558406993",
	  "amit@gmail.com", "tom"); String inputJson = super.mapToJson(cu);
	  System.out.println("======================="+inputJson+
	  "======================"); MvcResult mvcResult = mvc.perform(
	  MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE
	  ).content(inputJson)) .andReturn(); int status =
	  mvcResult.getResponse().getStatus(); assertEquals(201, status); }
	  
	 

	@Test
	public void viewCustomerList() throws Exception {
		loginController.loginUser("amit", "cognizant@1");
		String uri = "/customer/findall";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Customer cu[] = super.mapFromJson(content, Customer[].class);
		assertEquals("vidhya",cu[cu.length-1].getCustomerName());
	}

	@Test
	public void addTheatre() throws Exception {
		loginController.loginUser("amit", "cognizant@1");
		String uri = "/theatre/insert";
		Theatre th = new Theatre("Kabuki", "Tokyo", "Vidhya", "998990181", null, null);
		String inputJson = super.mapToJson(th);
		System.out.println("=======================" + inputJson + "======================");
		MvcResult mvcResult = mvc.perform(
		MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		Theatre Th = super.mapFromJson(content, Theatre.class);
		assertEquals("Kabuki", Th.getTheatreName());
	}

	
	@Test
	public void getAlltheatres() throws Exception {
		String uri = "/theatre/all";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Theatre th[] = super.mapFromJson(content, Theatre[].class);
		assertEquals("Kabuki", th[th.length-1].getTheatreName());
	}

	
	@Test 
	  public void deleteMoviesById() throws Exception { 
		  loginController.loginUser("amit", "cognizant@1");
		  String uri="/movies/delete/34"; 
		  MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn(); 
		  int status=mvcResult.getResponse().getStatus(); 
		  assertEquals(200, status); 
		  String content = mvcResult.getResponse().getContentAsString(); 
		  Movie mv =super.mapFromJson(content, Movie.class); 
		  assertEquals("Avengers",mv.getMovieName()); 
	  }
	 
	@Test
	public void addMovie() throws Exception {
		loginController.loginUser("amit", "cognizant@1");
		String uri = "/movies/add";
		Movie mov = new Movie("Avengers", "scifi", "04", "English", "Good", uri, LocalDate.of(2007, 12, 03), null);
		String inputJson = super.mapToJson(mov);
		System.out.println("=======================" + inputJson + "======================");
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		Movie Mv = super.mapFromJson(content, Movie.class);
		assertEquals("Avengers", Mv.getMovieName());

	}

	@Test
	public void viewMovie() throws Exception {
		String uri = "/movies/viewMovie/5";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Movie mv = super.mapFromJson(content, Movie.class);
		assertEquals(5, mv.getMovieId());
	}

	@Test
	public void viewMovieList() throws Exception {
		String uri = "/movies/findall";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Movie mv[] = super.mapFromJson(content, Movie[].class);
		assertEquals("Avengers", mv[mv.length-1].getMovieName());
	}

	@Test
	public void addAScreen() throws Exception {
		loginController.loginUser("amit", "cognizant@1");
		String uri = "/screens/add";
		Theatre th = new Theatre("Kabuki", "Tokyo", "Vidhya", "998990181", null, null);
		Screen sc = new Screen(th, null, "screen1", 4, 8);
		String inputJson = super.mapToJson(sc);
		System.out.println("=======================" + inputJson + "======================");
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Screen scr = super.mapFromJson(content, Screen.class);
		assertEquals("screen1", scr.getScreenName());
	}

	@Test
	public void viewScreenList() throws Exception {
		loginController.loginUser("amit", "cognizant@1");
		String uri = "/screens/findall";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Screen sc[] = super.mapFromJson(content, Screen[].class);
		assertEquals("screen2", sc[sc.length-1].getScreenName());
	}

	@Test
	public void addShow() throws Exception {
		loginController.loginUser("amit", "cognizant@1");
		String uri = "/shows/add";
		Theatre th = new Theatre("Kabuki", "Tokyo", "Vidhya", "998990181", null, null);
		Screen sc = new Screen(th, null, "screen1", 4, 8);
		Movie mov = new Movie("Avengers", "scifi", "04", "English", "Good", uri, LocalDate.of(2007, 12, 03), null);
		Show sh = new Show(LocalDateTime.of(2020, 10, 29, 7, 4, 2, 0), LocalDateTime.of(2020, 10, 29, 9, 4, 2, 0),
				"MorningShow", null, sc, th, null, LocalDate.of(2021, 04, 28));
		String inputJson = super.mapToJson(sh);
		System.out.println("=======================" + inputJson + "======================");
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		Show sho = super.mapFromJson(content, Show.class);
		assertEquals("MorningShow", sho.getShowName());
	}

	@Test
	public void viewShowList() throws Exception {
		String uri = "/shows/findall";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Show sh[] = super.mapFromJson(content, Show[].class);
		assertEquals("MorningShow", sh[sh.length-1].getShowName());
	}

	

	@Test
	public void addASeat() throws Exception {
		loginController.loginUser("amit", "cognizant@1");
		String uri = "/seats/add";
		Seat st = new Seat("c12", "front", 25, null, null);
		String inputJson = super.mapToJson(st);
		System.out.println("=======================" + inputJson + "======================");
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		Seat seat = super.mapFromJson(content, Seat.class);
		assertEquals("c12", seat.getSeatNumber());
	}

	@Test
	public void viewSeatList() throws Exception {
		String uri = "/seats/findall";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Seat st[] = super.mapFromJson(content, Seat[].class);
		assertEquals("c12", st[st.length-1].getSeatNumber());
	}

	@Test
	public void addATicket() throws Exception {
		loginController.loginUser("amit", "cognizant@1");
		String uri = "/tickets/add";
		Customer cu = new Customer("Amit", "Salgare", "7558406993", "amit@gmail.com", "tom");
		Booking boo = new Booking(null, LocalDate.of(2007, 12, 03), "Online", "pending", 100, null, null);
		Ticket tk = new Ticket(2, true, null, null);
		String inputJson = super.mapToJson(tk);
		System.out.println("=======================" + inputJson + "======================");
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		Ticket tik = super.mapFromJson(content, Ticket.class);
		assertEquals(2, tik.getNoOfSeats());
	}

	@Test
	public void viewTicketList() throws Exception {
		String uri = "/tickets/findall";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Ticket tk[] = super.mapFromJson(content, Ticket[].class);
		assertEquals(2, tk[tk.length-1].getNoOfSeats());
	}

}
