// PatronRepository.java
package com.maidscc.library_management_system.repository.patron;

import com.maidscc.library_management_system.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {
}