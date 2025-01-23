package com.valadao_davi.todolist.repositories;

import com.valadao_davi.todolist.dto.TaskDTO;
import com.valadao_davi.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query(nativeQuery = true, value = """
            UPDATE table_task
            SET
               status = :status,
               time_task = CASE
                            WHEN :time IS NOT NULL THEN :time
                            ELSE time_task
                           END
            WHERE
                id_task = :taskId;
            """)
    int updateProgress(Long taskId, String status, Double time);


}
