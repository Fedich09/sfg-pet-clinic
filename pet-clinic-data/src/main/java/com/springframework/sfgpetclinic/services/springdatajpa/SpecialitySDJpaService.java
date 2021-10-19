package com.springframework.sfgpetclinic.services.springdatajpa;

import com.springframework.sfgpetclinic.model.Speciality;
import com.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import com.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("DB")
public class SpecialitySDJpaService implements SpecialityService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialitySDJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialtyRepository.save(speciality);
    }

    @Override
    public Speciality findById(Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialtyRepository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public void delete(Speciality speciality) {
        specialtyRepository.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
