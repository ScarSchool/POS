package com.scarc.springbootjparest;

import com.scarc.springbootjparest.models.*;
import com.scarc.springbootjparest.models.Event;
import com.scarc.springbootjparest.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringbootjparestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjparestApplication.class, args);
	}

	@GetMapping("/api/v1/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@Bean
	@Transactional
	//@Profile("Test")
	public CommandLineRunner mockData(AdminRepository adminRepo, PupilRepository pupilRepo, ResponsibleRepository responsibleRepo,
									  ParticipationRepository participationRepo, TimeSlotRepository timeSlotRepo, MailRepository mailRepo,
									  EventRepository eventRepo, DepartmentRepository departmentRepo, CompanyRepository companyRepo) {
		System.out.println("Command line runner?");
		User u = User.builder().name("hugo").build();

		Admin admin1 = Admin.builder()
				.name("Guy").eMail("guy@admin.at")
				.pwdToken("GEHEIM").build();
		Admin admin2 = Admin.builder()
				.name("guy2").eMail("electric@boogaloo.at")
				.pwdToken("GEHEIM").build();
		Admin admin3 = Admin.builder()
				.name("deleterAdmin").eMail("electric@boogaloo.at")
				.pwdToken("GEHEIM").build();

		Pupil pupil1 = Pupil.builder()
				.name("p1").eMail("schueler@htl.at")
				.pwdToken("geheim2").build();
		Pupil pupil2 = Pupil.builder()
				.name("p2").eMail("beste@htl.at")
				.pwdToken("geheim3").build();
		Pupil pupil3 = Pupil.builder()
				.name("deleterPupil").eMail("beste@htl.at")
				.pwdToken("geheim3").build();

		Responsible resp1 = Responsible.builder()
				.name("res1").eMail("resso@htl.at")
				.pwdToken("geheim4").build();
		Responsible resp2 = Responsible.builder()
				.name("res2").eMail("gesso@htl.at")
				.pwdToken("geheim5").build();


		Mail mail1 = Mail.builder().subject("Yessing").content("very fun")
				.date(LocalDate.of(2020, 12, 21)).time(LocalTime.of(23, 59))
				.sender(admin1).build();
		Mail mail2 = Mail.builder().subject("haha").content("I laugh becuase of funny :-)")
				.date(LocalDate.of(2000, 9, 11)).time(LocalTime.of(00, 59))
				.sender(admin1).recipients(java.util.List.of(resp1)).build();

		Event event1 = Event.builder().label("Schultag").defaultPrice(1000.01)
				.date(LocalDate.of(2019, 11, 11)).organizer(admin2).build();
		Event event2 = Event.builder().label("Anfoch bestes fach!#1").defaultPrice(9.99)
				.date(LocalDate.of(2018, 12, 17)).organizer(admin2).build();

		Participation parti1 = Participation.builder().price(365.11)
				.comments("parti1").paidAlready(0.0).build();
		Participation parti2 = Participation.builder().price(2019892.11)
				.comments("parti2").paidAlready(0.0).build();
		Participation parti3 = Participation.builder().price(2019892.11)
				.comments("deleterParti").paidAlready(0.0).build();

		Department depart1 = Department.builder().label("Informatik").build();
		Department depart2 = Department.builder().label("Bautechnik").build();

		Company company1 = Company.builder().eMail("company@mail.at").comments("Supa guat").name("Ka geld")
				.phone("1234AnrufenHier").replyTo("Mister Kanister").street("Superstraße 1").zipTown("1900 Stadt")
				.build();
		Company company2 = Company.builder().eMail("company2@mail.at").comments("Einfach spitze").name("Immer mehr")
				.phone("0900 911 211").replyTo("Herr Besser").street("SupereStrasse 2").zipTown("1901 SuperStadt")
				.build();

		TimeSlot timeSlot1 = TimeSlot.builder().maxParticipants(12).ends(LocalTime.of(23, 59, 58))
				.starts(LocalTime.of(23, 59, 59)).participation(parti1).pupils(List.of(pupil1, pupil2))
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

		mailRepo.save(mail1);
		mailRepo.save(mail2);

		participationRepo.save(parti1);
		participationRepo.save(parti2);
		participationRepo.save(parti3);

		eventRepo.save(event1);
		eventRepo.save(event2);

		departmentRepo.save(depart1);
		departmentRepo.save(depart2);

		companyRepo.save(company1);
		companyRepo.save(company2);

		timeSlotRepo.save(timeSlot1);
		timeSlotRepo.save(timeSlot2);

		return null;
	}
}