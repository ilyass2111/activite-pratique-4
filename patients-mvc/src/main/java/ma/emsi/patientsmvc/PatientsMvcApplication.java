package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
        //@Bean
        CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(
                    new Patient(null,"Hassan",new Date(),false,126));
            patientRepository.save(
                    new Patient(null,"mohammed",new Date(),true,448));
            patientRepository.save(
                    new Patient(null,"yasmine",new Date(),true,235));
            patientRepository.save(
                    new Patient(null,"Hanae",new Date(),false,196));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());

            });
        };

    }
    //@Bean
    CommandLineRunner SaveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("yassmine","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("mohamed","ADMIN");
            securityService.addRoleToUser("yasmine","USER");
            securityService.addRoleToUser("hassan","USER");

        };
    }
}

