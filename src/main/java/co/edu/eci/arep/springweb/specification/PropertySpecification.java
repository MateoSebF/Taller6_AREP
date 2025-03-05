package co.edu.eci.arep.springweb.specification;

import org.springframework.data.jpa.domain.Specification;

import co.edu.eci.arep.springweb.model.Property;
import jakarta.persistence.criteria.*;

public class PropertySpecification {
    
    public static Specification<Property> withFilters(String address, Double minPrice, Double maxPrice, Integer minSize,
            Integer maxSize) {
        return (Root<Property> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction(); // Start with an empty predicate (always true)

            if (address != null && !address.isEmpty()) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("address")), "%" + address.toLowerCase() + "%"));
            }
            if (minPrice != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (minSize != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("size"), minSize));
            }
            if (maxSize != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("size"), maxSize));
            }

            return predicate;
        };
    }

}
