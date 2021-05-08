package com.tschlatd.springdatajpa;

import com.tschlatd.springdatajpa.controller.AdminController;
import com.tschlatd.springdatajpa.model.*;
import com.tschlatd.springdatajpa.repository.*;
import com.tschlatd.springdatajpa.service.AdminServiceImpl;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
// Imports related to commented test code below

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class SpringdatajpaApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(SpringdatajpaApplication.class, args);
	}

	@GetMapping("/debug")
	public String debugMessage() {
		return "Hello I am the world. Why does it not go >:(";
	}

//	Do not mess with test data declared in /test/ folder
	@GetMapping("/addData")
	public String addDebugData() {
		addDummyData();

		return "A ok!";
	}

	static private void addDummyData() {
		System.out.println("Adding dummy data");

		AdminController adminController = new AdminController();
		adminController.createAdmin(new Admin());
	}

	@Autowired
	AdminRepository adminRepo;
	@Autowired
	PupilRepository pupilRepo;
	@Autowired
	ResponsibleRepository responsibleRepo;
	@Autowired
	ParticipationRepository participationRepo;
	@Autowired
	TimeSlotRepository timeSlotRepo;
	@Autowired
	MailRepository mailRepo;
	@Autowired
	EventRepository eventRepo;
	@Autowired
	DepartmentRepository departmentRepo;
	@Autowired
	CompanyRepository companyRepo;

	@Override
	public void run(String ...args) {
		System.out.println("Command line runner. Executing!");

		try {
			Admin admin1 = Admin.builder()
					.name("Pferd").email("dog-sausage@htl.at")
					.pwdToken("GEHEIM").build();
			Admin admin2 = Admin.builder()
					.name("Hansi").email("echterEmail@htl.at")
					.pwdToken("GEHEIM").build();
			Admin admin3 = Admin.builder()
					.name("Leerer").email("leerer@htl.at")
					.pwdToken("GEHEIM").build();

			Pupil pupil1 = Pupil.builder()
					.name("Schueler1").email("schueler@htl.at")
					.pwdToken("geheim2").build();
			Pupil pupil2 = Pupil.builder()
					.name("Schueler2").email("beste@htl.at")
					.pwdToken("geheim3").build();
			Pupil pupil3 = Pupil.builder()
					.name("Schueler3").email("bestel@htl.at")
					.pwdToken("geheim4").build();

			Responsible resp1 = Responsible.builder()
					.name("Responsible1").email("resso@htl.at")
					.pwdToken("geheim4").build();
			Responsible resp2 = Responsible.builder()
					.name("Responsible2").email("gesso@htl.at")
					.pwdToken("geheim5").build();
			Responsible resp3 = Responsible.builder()
					.name("Responsible3").email("gibbet@htl.at")
					.pwdToken("geheim6").build();


			Mail mail1 = Mail.builder().subject("SUbjekt????").content("I am very content :-)")
					.at(LocalDate.of(2020, 12, 21))
					.time(LocalTime.of(23, 59))
					.receivers(Set.of(resp1))
					.sender(admin1).build();
			Mail mail2 = Mail.builder().subject("Subjekto").content("You are very contentating")
					.at(LocalDate.of(2000, 9, 11)).time(LocalTime.of(00, 59))
					.sender(admin1).receivers(Set.of(resp1)).build();

			Event event1 = Event.builder().label("Tag des offenen Fensters").defaultPrice(1000.01)
					.date(LocalDate.of(2019, 11, 11)).organiser(admin2).build();
			Event event2 = Event.builder().label("Tag der offenen Lüftung").defaultPrice(9.99)
					.date(LocalDate.of(2018, 12, 17)).organiser(admin2).build();

			Participation parti1 = Participation.builder()
					.responsible(resp1).price(365.11)
					.comments("Sers do geht nix").paidAlready(0.0).build();
			Participation parti2 = Participation.builder()
					.responsible(resp1).price(2019892.11)
					.comments("Hello do geht olles").paidAlready(0.0).build();

			Department depart1 = Department.builder().label("Informatik").build();
			Department depart2 = Department.builder().label("Bauterchnik").build();

			Company company1 = Company.builder().email("company@compa.ny").comments("Commentatitations").name("Good company")
					.phone("1234AnrufenHier").replyTo("Mister Kanister").street("Superstraße 1").zipTown("1900 Stadt")
					.build();
			Company company2 = Company.builder().email("compost@compo.st").comments("Good comment").name("Better company")
					.phone("0900 911 211").replyTo("Herr Besser").street("SupereStrasse 2").zipTown("1901 SuperStadt")
					.build();

			TimeSlot timeSlot1 = TimeSlot.builder().maxParticipants(12).ends(LocalTime.of(23, 59, 58))
					.starts(LocalTime.of(23, 59, 59)).participation(parti1).participants(Set.of(pupil1))
					.build();
			TimeSlot timeSlot2 = TimeSlot.builder().maxParticipants(12).ends(LocalTime.of(23, 59, 58))
					.starts(LocalTime.of(23, 59, 59)).participation(parti1).build();


			adminRepo.save(admin1);
			adminRepo.save(admin2);
			adminRepo.save(admin3);

			pupilRepo.save(pupil1);
			pupilRepo.save(pupil2);
			pupilRepo.save(pupil3);

			responsibleRepo.save(resp1);
			responsibleRepo.save(resp2);
			responsibleRepo.save(resp3);

//			mailRepo.save(mail1);
	//		mailRepo.save(mail2);

			participationRepo.save(parti1);
			participationRepo.save(parti2);

			eventRepo.save(event1);
			eventRepo.save(event2);

			departmentRepo.save(depart1);
			departmentRepo.save(depart2);

			companyRepo.save(company1);
			companyRepo.save(company2);

			timeSlotRepo.save(timeSlot1);
			timeSlotRepo.save(timeSlot2);

			System.out.println("Successfully inserted dummy data!");
		} catch(Exception ex) {
			System.out.println("ERROR: Could not generate dummy data: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
