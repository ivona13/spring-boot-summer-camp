package com.ag04smarts.sha.bootstrap;

import com.ag04smarts.sha.domain.*;
import com.ag04smarts.sha.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final SymptomRepository symptomRepository;

    public DataBootstrap(PatientRepository patientRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, SymptomRepository symptomRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.symptomRepository = symptomRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Loading Bootstrap Data");
        try {
            getData();
        } catch (ParseException e) {
            System.out.println("Parse exception");
        }
    }

    public void getData()
            throws ParseException {

        Doctor drIvanRaguz = new Doctor("Ivan", "Raguz", DoctorExpertise.GENERAL_DOCTOR);
        Doctor drTanjaMaric = new Doctor("Tanja", "Maric", DoctorExpertise.PEDIATRICIAN);
        Doctor drLeaStefanek = new Doctor("Lea", "Stefanek", DoctorExpertise.SPORTS_MEDICINE_SPECIALIST);
        Doctor drMarkoCindric = new Doctor("Marko", "Cindric", DoctorExpertise.PLASTIC_SURGEON);
        Doctor drEnnaZadro = new Doctor("Enna", "Zadro", DoctorExpertise.GENERAL_DOCTOR);

        doctorRepository.save(drIvanRaguz);
        doctorRepository.save(drTanjaMaric);
        doctorRepository.save(drLeaStefanek);
        doctorRepository.save(drMarkoCindric);
        doctorRepository.save(drEnnaZadro);

        Symptom coughing = new Symptom();
        coughing.setDescription("coughing");
        Symptom fever = new Symptom();
        fever.setDescription("fever");
        Symptom kneePain = new Symptom();
        kneePain.setDescription("knee pain");
        Symptom chestPain = new Symptom();
        chestPain.setDescription("chest pain");

        symptomRepository.save(coughing);
        symptomRepository.save(fever);
        symptomRepository.save(kneePain);
        symptomRepository.save(chestPain);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.UK);

        Patient patientAnaTomic = new Patient("Ana", "Tomic", "anatomic@gmail.com", 26, "0919567888", Gender.FEMALE,
                df.parse("01-01-2018 10:00:00"), Status.ENLISTED);

        Patient patientMarkoMamic = new Patient("Marko", "Mamic", "markomamic@gmail.com", 45, "0912222444", Gender.MALE,
                df.parse("02-03-2020 12:00:00"), Status.CURED);

        Patient patientHelenaJurak = new Patient("Helena", "Jurak", "helenajurak@gmail.com", 60, "0918745695", Gender.FEMALE,
                df.parse("04-06-2020 13:00:00"), Status.DIAGNOSED);

        Patient patientTeoJukic = new Patient("Teo", "Jukić", "teojukic@gmail.com", 20, "0928887999", Gender.MALE,
                df.parse("02-03-2020 14:30:00"), Status.DIAGNOSED);

        Appointment appointment1 = new Appointment(patientMarkoMamic, drIvanRaguz, df.parse("07-07-2020 13:00:00"));

        Appointment appointment2 = new Appointment(patientTeoJukic, drLeaStefanek, df.parse("10-05-2020 12:00:00"));

        Appointment appointment3 = new Appointment(patientHelenaJurak, drEnnaZadro, df.parse("06-05-2020 14:20:00"));

        PatientMedicalRecord kneeDisease = new PatientMedicalRecord();
        kneeDisease.setDiagnosis("knee injury");
        kneeDisease.setTreatment("inaction");
        patientTeoJukic.setPatientMedicalRecord(kneeDisease);

        PatientMedicalRecord heartDisease = new PatientMedicalRecord();
        heartDisease.setDiagnosis("heart attack");
        heartDisease.setTreatment("surgery of bypass implantation");
        patientMarkoMamic.setPatientMedicalRecord(heartDisease);

        PatientMedicalRecord pneumonia = new PatientMedicalRecord();
        pneumonia.setDiagnosis("pneumonia");
        pneumonia.setTreatment("antibiotic consuming, hospitalization");
        patientAnaTomic.setPatientMedicalRecord(pneumonia);

        PatientMedicalRecord headache = new PatientMedicalRecord();
        headache.setDiagnosis("headache");
        headache.setTreatment("resting, paracetamol consuming");
        patientHelenaJurak.setPatientMedicalRecord(headache);

        PatientTreatmentHistory kneeInjuryTreatment = new PatientTreatmentHistory();
        kneeInjuryTreatment.setDoctor(drLeaStefanek);
        kneeInjuryTreatment.setTreatmentRemark("Patient should take knee rendgen in two weeks.");
        kneeInjuryTreatment.setOldStatus(Status.DIAGNOSED);
        kneeInjuryTreatment.setNewStatus(Status.UNDER_TREATMENT);
        patientTeoJukic.setPatientTreatmentHistories(new HashSet<>(Arrays.asList(kneeInjuryTreatment)));

        PatientTreatmentHistory heartAttackTreatment = new PatientTreatmentHistory();
        heartAttackTreatment.setDoctor(drIvanRaguz);
        heartAttackTreatment.setTreatmentRemark("Repeat EKG");
        heartAttackTreatment.setOldStatus(Status.UNDER_DIAGNOSIS);
        heartAttackTreatment.setNewStatus(Status.UNDER_TREATMENT);
        patientHelenaJurak.setPatientTreatmentHistories(new HashSet<>(Arrays.asList(heartAttackTreatment)));


        pneumonia.setSymptoms(Set.of(coughing, fever));
        heartDisease.setSymptoms(Set.of(chestPain));
        kneeDisease.setSymptoms(Set.of(kneePain));

        patientRepository.save(patientAnaTomic);
        patientRepository.save(patientMarkoMamic);
        patientRepository.save(patientHelenaJurak);
        patientRepository.save(patientTeoJukic);

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
    }
}
