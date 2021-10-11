package com.springframework.sfgpetclinic.bootstrap;

import com.springframework.sfgpetclinic.model.Owner;
import com.springframework.sfgpetclinic.model.Pet;
import com.springframework.sfgpetclinic.model.PetType;
import com.springframework.sfgpetclinic.model.Vet;
import com.springframework.sfgpetclinic.services.OwnerService;
import com.springframework.sfgpetclinic.services.PetTypeService;
import com.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Max");
        owner1.setLastName("Fedich");
        owner1.setAddress("Address owner 1");
        owner1.setCity("City owner 1");
        owner1.setTelephone("1234567890");

        Pet owner1sPet = new Pet();
        owner1sPet.setName("Rick");
        owner1sPet.setPetType(savedDogPetType);
        owner1sPet.setOwner(owner1);
        owner1sPet.setBirthDate(LocalDate.parse("2014-09-12"));

        owner1.getPets().add(owner1sPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Barbara");
        owner2.setLastName("Liskov");
        owner2.setAddress("Address owner 2");
        owner2.setCity("City owner 2");
        owner2.setTelephone("0987654321");

        Pet owner2sPet = new Pet();
        owner2sPet.setName("Bob");
        owner2sPet.setOwner(owner2);
        owner2sPet.setPetType(savedCatPetType);
        owner2sPet.setBirthDate(LocalDate.parse("2019-07-23"));

        owner1.getPets().add(owner2sPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners ....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jax");
        vet1.setLastName("Pex");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mex");
        vet2.setLastName("Fex");

        vetService.save(vet2);

        System.out.println("Loaded vets ....");
    }
}
