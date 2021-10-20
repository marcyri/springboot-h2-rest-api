package com.moan.pet.moanHealthPrj.bootstrap;

import com.moan.pet.moanHealthPrj.domain.service.IPatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Used only to support any checking
 */
@Component
public class MoanLoader implements CommandLineRunner {
    public final IPatientService patientService;

    public MoanLoader(IPatientService patientService) {
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
