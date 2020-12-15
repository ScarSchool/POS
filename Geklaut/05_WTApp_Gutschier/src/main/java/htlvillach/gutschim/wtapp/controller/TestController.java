package htlvillach.gutschim.wtapp.controller;

import htlvillach.gutschim.wtapp.models.*;
import htlvillach.gutschim.wtapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {

    private AdminRepository adminRepository;
    private CompanyRepository companyRepository;
    private DepartmentRepository departmentRepository;
    private EventRepository eventRepository;
    private MailRepository mailRepository;
    private ParticipationRepository participationRepository;
    private PupilRepository pupilRepository;
    private ResponsibleRepository responsibleRepository;
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    public TestController(AdminRepository adminRepository, CompanyRepository companyRepository,
                          DepartmentRepository departmentRepository, EventRepository eventRepository,
                          MailRepository mailRepository, ParticipationRepository participationRepository,
                          PupilRepository pupilRepository, ResponsibleRepository responsibleRepository,
                          TimeSlotRepository timeSlotRepository) {
        this.adminRepository = adminRepository;
        this.companyRepository = companyRepository;
        this.departmentRepository = departmentRepository;
        this.eventRepository = eventRepository;
        this.mailRepository = mailRepository;
        this.participationRepository = participationRepository;
        this.pupilRepository = pupilRepository;
        this.responsibleRepository = responsibleRepository;
        this.timeSlotRepository = timeSlotRepository;
    }

    private Admin admin;
    private Company company;
    private Department department;
    private Event event;
    private Mail mail;
    private Participation participation;
    private Pupil pupil;
    private Responsible responsible;
    private TimeSlot timeslot;

    @GetMapping
    private List<Object> getTest() {
        initObjects();
        //return Arrays.asList(new String[]{"hallo", "servus"});

        return Arrays.asList(new Object[]{admin, company, department, event, mail, participation, pupil, responsible, timeslot});
    }

    private void initObjects() {
        admin = new Admin();
        company = new Company();
        department = new Department();
        event = new Event();
        mail = new Mail();
        participation = new Participation();
        pupil = new Pupil();
        responsible = new Responsible();
        timeslot = new TimeSlot();

        admin.setName("Admin");
        admin.setPwdToken("pwdtoken");
        admin.setEmail("email");
        admin = adminRepository.save(admin);

        company.setName("Company");
        company = companyRepository.save(company);

        department.setLabel("Department");
        department = departmentRepository.save(department);

        event.setLabel("Event");
        event.setOrganiser(admin);
        event = eventRepository.save(event);

        mail.setNr(1);
        mail.setSender(admin);
        mail = mailRepository.save(mail);

        participation.setPrice(800);
        participation.setAt(event);
        participation.setInterestedIn(department);
        participation.setCompany(company);
        participation = participationRepository.save(participation);

        pupil.setName("Pupil");
        pupil = pupilRepository.save(pupil);

        responsible.setName("responsible");
        responsible.setMail(mail);
        responsible = responsibleRepository.save(responsible);

        timeslot.setMaxParticipants(33);
        timeslot.setParticipation(participation);
        timeslot = timeSlotRepository.save(timeslot);

        participation.setResponsible(responsible);
        timeslot.setPupils(Arrays.asList(new Pupil[]{pupil}));
        participation.setResponsible(responsible);
    }
}
