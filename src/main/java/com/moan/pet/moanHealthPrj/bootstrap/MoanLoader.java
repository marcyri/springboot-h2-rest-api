package com.moan.pet.moanHealthPrj.bootstrap;

import com.moan.pet.moanHealthPrj.services.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MoanLoader implements CommandLineRunner {
    public final PatientService patientService;

    public MoanLoader(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("start loader run");
//
//        patientService.getPatientById(99L);
//
////        patientService.getPatients();
//        patientService.getPatientsWithAttendances();
//        System.out.println("end loader run");
    }
}
