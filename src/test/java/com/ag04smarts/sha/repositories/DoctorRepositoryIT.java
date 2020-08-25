package com.ag04smarts.sha.repositories;

import com.ag04smarts.sha.domain.Doctor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DoctorRepositoryIT {

    @Autowired
    private DoctorRepository doctorRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByFirstNameAndLastName() {
        Optional<Doctor> drIvanRaguz = doctorRepository.findByFirstNameAndLastName("Ivan", "Raguz");

        assertEquals("Ivan", drIvanRaguz.get().getFirstName());
        assertEquals("Raguz", drIvanRaguz.get().getLastName());
    }
}