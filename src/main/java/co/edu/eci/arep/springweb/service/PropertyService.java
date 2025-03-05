package co.edu.eci.arep.springweb.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import co.edu.eci.arep.springweb.model.Property;
import co.edu.eci.arep.springweb.repository.PropertyRepository;
import co.edu.eci.arep.springweb.specification.PropertySpecification;


@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public Page<Property> searchProperties(String address, Double minPrice, Double maxPrice, Integer minSize, Integer maxSize, Pageable pageable) {
        Specification<Property> spec = PropertySpecification.withFilters(address, minPrice, maxPrice, minSize, maxSize);
        return propertyRepository.findAll(spec, pageable);
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property not found with ID: " + id));
    }

    public Property createProperty(@Valid Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, @Valid Property updatedProperty) {
        Property existingProperty = getPropertyById(id);
        existingProperty.setAddress(updatedProperty.getAddress());
        existingProperty.setPrice(updatedProperty.getPrice());
        existingProperty.setSize(updatedProperty.getSize());
        existingProperty.setDescription(updatedProperty.getDescription());
        return propertyRepository.save(existingProperty);
    }

    public void deleteProperty(Long id) {
        Property property = getPropertyById(id);
        propertyRepository.delete(property);
    }
}
