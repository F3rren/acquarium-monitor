package com.acquarium.acquarium.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.acquarium.acquarium.models.MaintenanceTask;

public interface IMaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long> {
    
    List<MaintenanceTask> findByAquariumIdOrderByDueDateAsc(Long aquariumId);
    
    List<MaintenanceTask> findByAquariumIdAndIsCompletedOrderByDueDateAsc(Long aquariumId, Boolean isCompleted);
    
    List<MaintenanceTask> findByAquariumIdAndIsCompletedFalseOrderByDueDateAsc(Long aquariumId);
}
