package co.edu.eci.arep.springweb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.eci.arep.springweb.model.Property;
import co.edu.eci.arep.springweb.service.PropertyService;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Allow cross-origin requests
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping()
    public ResponseEntity<Page<Property>> searchProperties(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minSize,
            @RequestParam(required = false) Integer maxSize,
            Pageable pageable) {

        Page<Property> page = propertyService.searchProperties(address, minPrice, maxPrice, minSize, maxSize, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        if (property == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(property);
    }

    @PostMapping
    public ResponseEntity<Property> createProperty(@Valid @RequestBody Property property) {
        return ResponseEntity.ok(propertyService.createProperty(property));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @Valid @RequestBody Property property) {
        Property propertySaved = propertyService.getPropertyById(id);
        if (propertySaved == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(propertyService.updateProperty(id, property));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}