package com.springframework.sfgpetclinic.services.springdatajpa;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    private static final Owner OWNER = Owner.builder().id(1L).firstName("Max").lastName("Fedenko").build();
    private static final Owner OWNER_2 = Owner.builder().id(1L).firstName("Max").lastName("Fedenko").build();

    @Mock
    private OwnerRepository ownerRepository;
    @InjectMocks
    private OwnerSDJpaService ownerSDJpaService;

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(OWNER);

        Owner actual = ownerSDJpaService.save(OWNER);

        assertNotNull(actual);
        assertEquals(OWNER, actual);
        verify(ownerRepository).save(any());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(OWNER.getId())).thenReturn(java.util.Optional.of(OWNER));
        Owner actual = ownerSDJpaService.findById(OWNER.getId());

        assertNotNull(actual);
        assertEquals(OWNER, actual);
        verify(ownerRepository).findById(any());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(OWNER.getId())).thenReturn(Optional.empty());
        Owner actual = ownerSDJpaService.findById(OWNER.getId());

        assertNull(actual);
        verify(ownerRepository).findById(any());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(OWNER);
        owners.add(OWNER_2);

        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> actual = ownerSDJpaService.findAll();

        assertNotNull(actual);
        assertEquals(owners.size(), actual.size());
        verify(ownerRepository).findAll();
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(OWNER);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(OWNER.getId());

        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName_Ok() {
        when(ownerRepository.findByLastName(any())).thenReturn(java.util.Optional.ofNullable(OWNER));

        assert OWNER != null;
        Owner byLastName = ownerSDJpaService.findByLastName(OWNER.getLastName());

        assertNotNull(byLastName);
        assertEquals(OWNER, byLastName);
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findByLastName_NotOk() {
        Owner byLastName = ownerSDJpaService.findByLastName("");

        assertNull(byLastName);
        verify(ownerRepository).findByLastName(any());
    }
}