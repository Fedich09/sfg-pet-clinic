package com.springframework.sfgpetclinic.services.map;

import com.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class OwnerMapServiceTest {

    private static final long ID = 1L;
    private static final String LAST_NAME = "Fedenko";
    private static final Owner OWNER = Owner.builder().id(ID).lastName(LAST_NAME).build();
    private OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(OWNER);
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(2L).build();
        Owner savedOwner = ownerMapService.save(owner);

        assertEquals(owner, savedOwner);
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ID);

        assertEquals(OWNER, owner);
    }

    @Test
    void findAll() {
        Set<Owner> all = ownerMapService.findAll();

        assertEquals(ID, all.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ID);

        assertNull(ownerMapService.findById(ID));
    }

    @Test
    void delete() {
        ownerMapService.delete(OWNER);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(LAST_NAME);

        assertNotNull(owner);
        assertEquals(OWNER, owner);
    }
}